package com.example.rnr.model.dataclasses.surveylist

import android.app.backup.FullBackupDataOutput
import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

class SurveyListEmployee() {

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("id")
    lateinit var id:String

    @SerializedName("email")
    lateinit var email:String

    @SerializedName("fullName")
    lateinit var fullName:String

    constructor(_id:String,id:String,email:String,fullName:String):this(){
        this._id=_id
        this.id=id
        this.email=email
        this.fullName=fullName
    }

    /*{
        "_id": "62fdf46c76cc813bef0e9b41",
        "id": "62eba1fdfca59149364d33a4",
        "email": "vmalel@inspeero.com",
        "fullName": "venkatesh malel"
    },*/
}