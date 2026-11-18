package com.example.RegisterPracticing.role.repository

import com.example.RegisterPracticing.role.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByRoleName(roleName : String) : Role?

}