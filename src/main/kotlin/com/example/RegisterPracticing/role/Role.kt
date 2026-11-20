package com.example.RegisterPracticing.role.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role(

    @Id
    @GeneratedValue(GenerationType.IDENTITY)
    val id : Long? = null,

    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    val roleName : String


)