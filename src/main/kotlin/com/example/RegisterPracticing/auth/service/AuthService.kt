package com.example.RegisterPracticing.auth.service

import com.example.RegisterPracticing.auth.dto.RegisterRequest
import com.example.RegisterPracticing.auth.dto.RegisterResponse
import com.example.RegisterPracticing.person.entity.Person
import com.example.RegisterPracticing.person.repository.PersonRepository
import com.example.RegisterPracticing.role.repository.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
open class AuthService(

    private val passwordEncoder : PasswordEncoder,

    private val personRepository: PersonRepository,

    private val roleRepository: RoleRepository

    )
    {

    @Transactional
    open fun register(registerRequest: RegisterRequest) : RegisterResponse {

        if(personRepository.existsByEmail(registerRequest.email))
        {
         throw IllegalArgumentException("Email already exists")
        }

        val role = roleRepository.findByRoleName("CUSTOMER") ?:
        throw IllegalStateException("Role 'CUSTOMER' does not exist")

        val hashedPassword = passwordEncoder.encode(registerRequest.password)!!

        val person = Person(
            fullName = registerRequest.fullName,
            email = registerRequest.email,
            phone = registerRequest.phone,
            passwordHashed = hashedPassword,
            role = role
        )

        val personSaved = personRepository.save(person)

        return RegisterResponse(
            id = personSaved.id!!,
            fullName = personSaved.fullName,
            email = person.email,
            phone = person.phone,
            roleId = role.id!!
        )

    }

}