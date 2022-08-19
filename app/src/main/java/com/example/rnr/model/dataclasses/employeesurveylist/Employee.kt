package com.example.rnr.model.dataclasses.employeesurveylist

import com.google.gson.annotations.SerializedName

class Employee() {

    @SerializedName("id")
    lateinit var id:String

    @SerializedName("label")
    lateinit var label:String

    @SerializedName("email")
    lateinit var email:String

    @SerializedName("fullName")
    lateinit var fullName:String

    constructor(id:String,label:String,email:String,fullName:String):this(){
        this.id=id
        this.label=label
        this.email=email
        this.fullName=fullName
    }

    /*"employees": {
        "id": "62fc870af91dc416268d8c9f",
        "label": "Test user",
        "email": "user@inspeero.com",
        "fullName": "Test user"
    }*/
}