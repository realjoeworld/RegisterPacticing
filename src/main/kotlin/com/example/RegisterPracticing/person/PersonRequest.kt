package com.example.RegisterPracticing.person

import com.example.RegisterPracticing.role.entity.Role

class PersonRequest(

    var fullName : String,

    var email : String,

    var phone : String,

    var roleId : Long,

    var cafeteriaId : Long,

    ) {
}