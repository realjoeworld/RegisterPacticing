package com.example.RegisterPracticing.auth.service

import com.example.RegisterPracticing.auth.dto.RegisterRequest
import com.example.RegisterPracticing.auth.dto.RegisterResponse
import com.example.RegisterPracticing.cafeteria.entity.Cafeteria
import com.example.RegisterPracticing.cafeteria.repository.CafeteriaRepository
import com.example.RegisterPracticing.person.entity.Person
import com.example.RegisterPracticing.person.repository.PersonRepository
import com.example.RegisterPracticing.role.repository.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
open class AuthService(

    private val passwordEncoder : PasswordEncoder,

    private val personRepository: PersonRepository,

    private val roleRepository: RoleRepository,

    private val cafeteriaRepository: CafeteriaRepository,


    )
    {

    @Transactional
    open fun register(registerRequest: RegisterRequest) : RegisterResponse {

        if (personRepository.existsByEmail(registerRequest.email)) {
            throw IllegalArgumentException("Email already exists")
        }

        val role =
            roleRepository.findByRoleName(registerRequest.roleName) ?: throw IllegalStateException("Role '${registerRequest.roleName}' does not exist")


        var Cafteria: Cafeteria? = null



        if (role.roleName != "CUSTOMER") {

            if(registerRequest.CafteriaId == null)
                throw IllegalArgumentException("Cafeteria ID is required for role ${role.roleName}")

            Cafteria = cafeteriaRepository.findByIdOrNull(registerRequest.CafteriaId)

            if(Cafteria == null)
                throw IllegalArgumentException("There Is No Cafteria With That Id: ${registerRequest.CafteriaId}")
        }
        else {
            if(registerRequest.CafteriaId != null)
                throw IllegalArgumentException("Customers Should Not Send An Cafeteria Id")
        }

        val hashedPassword = passwordEncoder.encode(registerRequest.password)!!

        val person = Person(
            fullName = registerRequest.fullName,
            email = registerRequest.email,
            phone = registerRequest.phone,
            passwordHashed = hashedPassword,
            role = role ?: throw IllegalStateException("Role 'CUSTOMER' does not exist"),
            cafeteria = Cafteria
        )

        val personSaved = personRepository.save(person)

        return RegisterResponse(
            id = personSaved.id!!,
            fullName = personSaved.fullName,
            email = person.email,
            phone = person.phone,
            roleId = role.id!!,
            CafteriaId = personSaved.cafeteria?.id
        )

    }

}