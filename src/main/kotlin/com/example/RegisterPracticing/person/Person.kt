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
    var fullName : String,

    @Column(name = "email",
        nullable = false)
    var email : String,

    @Column(name = "phone",
        nullable = false)
    var phone : String,

    @Column(name = "passwordHashed",
        nullable = false)
    var passwordHashed : String,


    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    var role : Role,

    @ManyToOne()
    @JoinColumn(name = "CAFETERIA_ID", nullable = true)
    var cafeteria: Cafeteria? = null


)