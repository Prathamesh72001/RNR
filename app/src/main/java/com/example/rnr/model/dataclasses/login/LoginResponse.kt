package com.example.rnr.model.dataclasses.login

import com.google.gson.annotations.SerializedName

class LoginResponse() {

    @SerializedName("token")
    lateinit var token:String

    constructor(token:String):this(){
        this.token=token
    }
}