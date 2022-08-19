package com.example.rnr.model.dataclasses.resetpasswordemail

import com.google.gson.annotations.SerializedName

class ResetPasswordEmailBody() {

    @SerializedName("email")
    lateinit var email:String

    constructor(email:String):this(){
        this.email=email
    }
}