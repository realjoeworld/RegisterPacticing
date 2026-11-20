package com.example.RegisterPracticing.cafeteria.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cafeterias")
class Cafeteria(

    @Id
    @GeneratedValue(GenerationType.IDENTITY)
    val id : Long? = null,

    @Column(name = "name", nullable = false)
    var name : String,

    @Column(name = "phone", nullable = false)
    var phone : String

)