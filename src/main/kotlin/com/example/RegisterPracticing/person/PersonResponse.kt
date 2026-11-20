package com.example.RegisterPracticing.person

import com.example.RegisterPracticing.cafeteria.entity.Cafeteria
import com.example.RegisterPracticing.role.entity.Role

class PersonResponse(
    val id: Long,
    val fullName: String,
    val email: String,
    val phone: String,
    val role : Role,
    val cafeteria: Cafeteria?
) {
}