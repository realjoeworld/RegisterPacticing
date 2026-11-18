package com.example.RegisterPracticing.person.repository

import com.example.RegisterPracticing.person.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {

    fun existsByEmail(email : String) : Boolean

}