package com.example.RegisterPracticing.cafeteria.repository

import com.example.RegisterPracticing.cafeteria.entity.Cafeteria
import org.springframework.data.jpa.repository.JpaRepository

interface CafeteriaRepository : JpaRepository<Cafeteria, Long> {

    fun findByName(name : String) : Cafeteria?

}