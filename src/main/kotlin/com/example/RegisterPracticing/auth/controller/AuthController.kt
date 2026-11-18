package com.example.RegisterPracticing.auth

import com.example.RegisterPracticing.auth.dto.RegisterRequest
import com.example.RegisterPracticing.auth.dto.RegisterResponse
import com.example.RegisterPracticing.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/register")
class AuthController(

    private val authService: AuthService

) {

    @PostMapping()
    fun register(@Valid @RequestBody registerRequest: RegisterRequest): ResponseEntity<RegisterResponse> {
        val response =  authService.register(registerRequest)

        return ResponseEntity(response, HttpStatus.CREATED)
    }

}