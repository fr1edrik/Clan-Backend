package com.example.ClanBackend.model.authentication

import java.io.Serializable

class JwtResponse: Serializable {
    var jwttoken: String?= null;

    constructor(jwttoken: String){
        this.jwttoken = jwttoken
    }
    companion object {
        private const val serialVersionUID = -8091879091924046844L;
    }
}