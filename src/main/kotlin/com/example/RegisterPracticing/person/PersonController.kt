package com.example.RegisterPracticing.person

import com.example.RegisterPracticing.person.entity.Person
import com.example.RegisterPracticing.person.repository.PersonRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/people")
class PersonController(
    private val personService : PersonService
) {


    @GetMapping
    fun findAll() : List<PersonResponse> {
        return personService.getAllPeople()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long) : PersonResponse {
        return personService.getPersonById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id : Long) {
        return personService.deletePerson(id)
    }

    @PutMapping("/{id}")
    fun UpdatePerson(@PathVariable id : Long, @RequestBody person: PersonRequest) : PersonResponse {
        return personService.updatePerson(id,person)
    }



}