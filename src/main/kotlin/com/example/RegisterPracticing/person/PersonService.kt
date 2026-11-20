package com.example.RegisterPracticing.person

import com.example.RegisterPracticing.cafeteria.entity.Cafeteria
import com.example.RegisterPracticing.cafeteria.repository.CafeteriaRepository
import com.example.RegisterPracticing.exceptions.ResourceNotFoundException
import com.example.RegisterPracticing.person.repository.PersonRepository
import com.example.RegisterPracticing.role.repository.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.collections.map

@Service
class PersonService(
   private val personRepository: PersonRepository,
   private val roleRepository: RoleRepository,
   private val cafeteriaRepository: CafeteriaRepository

) {

    fun getAllPeople(): List<PersonResponse> {

        return personRepository.findAll().map {

            PersonResponse(
                id = it.id!!,
                fullName = it.fullName,
                email = it.email,
                phone = it.phone,
                role = it.role,
                cafeteria = it.cafeteria

            );

        }


    }


    fun getPersonById(id: Long): PersonResponse {

        val getPerson = personRepository.findById(id).orElseThrow( {ResourceNotFoundException("Person With Id: $id not found")})

           return PersonResponse(
                id = getPerson.id!!,
                fullName = getPerson.fullName,
                email = getPerson.email,
                phone = getPerson.phone,
                role = getPerson.role,
                cafeteria = getPerson.cafeteria

            );
    }

    fun deletePerson(id: Long) : Unit {

        val person = personRepository.findById(id).orElseThrow( {ResourceNotFoundException("Person With Id: $id not found")})
         personRepository.delete(person)

    }

    @Transactional
    fun updatePerson(id : Long, updatedPerson: PersonRequest) : PersonResponse {

        val personMustBeUpdated = personRepository.findByIdOrNull(id) ?:
            throw ResourceNotFoundException("Person With Id: ${id} not found")

        val role = roleRepository.findByIdOrNull(updatedPerson.roleId) ?:
        throw ResourceNotFoundException("Role With Id: ${updatedPerson.roleId} not found")

        var cafeteria : Cafeteria? = null

        if(role.roleName != "CUSTOMER") {
            cafeteria  = cafeteriaRepository.findByIdOrNull(updatedPerson.cafeteriaId) ?:
                    throw ResourceNotFoundException("Cafeteria With Id: ${updatedPerson.cafeteriaId} not found")
        }



            personMustBeUpdated.fullName = updatedPerson.fullName
            personMustBeUpdated.email = updatedPerson.email
            personMustBeUpdated.phone = updatedPerson.phone

            personMustBeUpdated.role = role
            personMustBeUpdated.cafeteria = cafeteria

            val savedPerson = personRepository.save(personMustBeUpdated)

            return PersonResponse(
                id = savedPerson.id!!,
                fullName = savedPerson.fullName,
                email = savedPerson.email,
                phone = savedPerson.phone,
                cafeteria = savedPerson.cafeteria,
                role = savedPerson.role
            )


    }



}