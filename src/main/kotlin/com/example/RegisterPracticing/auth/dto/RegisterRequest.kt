package com.example.RegisterPracticing.auth.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class RegisterRequest(
    @field:NotBlank
    val fullName: String,

    @field:NotBlank
    @field:Pattern(
        regexp = "^07[0-9]{8}$",
        message = "Phone number must contain exactly 10 digits and start with 07"
    )

    val phone : String,

    @field:Email
    @field:NotBlank
    val email : String,

    @field:NotBlank
    val password: String

)