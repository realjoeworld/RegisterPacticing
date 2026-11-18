package com.example.RegisterPracticing.person.entity

import com.example.RegisterPracticing.cafeteria.entity.Cafeteria
import com.example.RegisterPracticing.role.entity.Role
import jakarta.persistence.*


@Entity
@Table(name = "people")
class Person(

    @Id
    @GeneratedValue(GenerationType.IDENTITY)
    val id : Long? = null,

    @Column(name = "fullName",
        nullable = false)
    val fullName : String,

    @Column(name = "email",
        nullable = false)
    val email : String,

    @Column(name = "phone",
        nullable = false)
    val phone : String,

    @Column(name = "passwordHashed",
        nullable = false)
    var passwordHashed : String,


    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private val role : Role,

    @ManyToOne()
    @JoinColumn(name = "CAFETERIA_ID", nullable = true)
    val cafeteria: Cafeteria? = null


)