package com.example.rnr.model.dataclasses.surveylist.orgsurveylist

import com.google.gson.annotations.SerializedName

class KPI() {
    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("name")
    lateinit var name:String

    @SerializedName("category")
    lateinit var category:String

    @SerializedName("weightage")
    lateinit var weightage:String

    @SerializedName("role")
    lateinit var role:String

    @SerializedName("department")
    lateinit var department:String

    @SerializedName("tenant")
    lateinit var tenant:String

    @SerializedName("createdBy")
    lateinit var createdBy:String

    @SerializedName("updatedBy")
    lateinit var updatedBy:String

    @SerializedName("createdAt")
    lateinit var createdAt:String

    @SerializedName("updatedAt")
    lateinit var updatedAt:String

    @SerializedName("__v")
    var __v:Int=0

    @SerializedName("id")
    lateinit var id:String

    constructor(_id:String,name:String,category:String,weightage:String,role:String,
                department:String,tenant:String,createdBy:String,updatedBy:String,
                createdAt:String,updatedAt:String,__v:Int,id:String):this()
    {
        this._id=_id
        this.name=name
        this.category=category
        this.weightage=weightage
        this.role=role
        this.department=department
        this.tenant=tenant
        this.createdBy=createdBy
        this.updatedBy=updatedBy
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id
                }

    /*{
        "_id": "62ea5c0a2b61ee4581601612",
        "name": "Collaboration",
        "category": "Collaboration",
        "weightage": "8",
        "role": "softwareEng2",
        "department": "web",
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": "62e8be6835015b4448306fca",
        "updatedBy": "62e8be6835015b4448306fca",
        "createdAt": "2022-08-03T11:29:14.306Z",
        "updatedAt": "2022-08-03T11:29:14.306Z",
        "__v": 0,
        "id": "62ea5c0a2b61ee4581601612"
    },*/
}