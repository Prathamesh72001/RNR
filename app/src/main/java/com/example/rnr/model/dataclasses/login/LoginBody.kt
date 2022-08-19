package com.example.rnr.model.dataclasses.login

import com.google.gson.annotations.SerializedName

class LoginBody() {

    @SerializedName("email")
    lateinit var email:String

    @SerializedName("password")
    lateinit var password: String

    constructor(email:String,password:String):this(){
        this.email=email
        this.password=password
    }

}