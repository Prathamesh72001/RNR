package com.example.rnr.model.dataclasses

import com.google.gson.annotations.SerializedName

class Project(){
    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("name")
    lateinit var name:String

    @SerializedName("id")
    lateinit var id:String

    @SerializedName("label")
    lateinit var label:String

    constructor(id:String,label:String, name:String):this(){
        this.id=id
        this.label=label
        this.name=name
    }

    constructor(_id:String,name:String):this(){
        this.id=id
        this.name=name
    }
}