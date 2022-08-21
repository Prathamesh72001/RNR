package com.example.rnr.model.dataclasses.surveylist.casualsurveylist

import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.Employee
import com.google.gson.annotations.SerializedName

class CasualSurveyResponse() {

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("name")
    lateinit var name:String

    @SerializedName("type")
    lateinit var type:String

    @SerializedName("status")
    lateinit var status:String

    @SerializedName("questions")
    lateinit var questions:Array<Question>

    @SerializedName("startTime")
    lateinit var startTime:String

    @SerializedName("endTime")
    lateinit var endTime:String

    @SerializedName("tenant")
    lateinit var tenant:String

    @SerializedName("createdBy")
    lateinit var createdBy:String

    @SerializedName("updatedBy")
    lateinit var updatedBy:String

    @SerializedName("employees")
    lateinit var employees:Array<Any>

    @SerializedName("createdAt")
    lateinit var createdAt:String

    @SerializedName("updatedAt")
    lateinit var updatedAt:String

    @SerializedName("__v")
    var __v:Int=0

    @SerializedName("id")
    lateinit var id:String

    constructor(_id:String,name:String,type:String,status:String,questions:Array<Question>,
                startTime:String,endTime:String,tenant:String,createdBy:String, updatedBy:String,
                employees:Array<Any>,createdAt:String,updatedAt:String, __v:Int,id:String):this(){
        this._id=_id
        this.name=name
        this.type=type
        this.status=status
        this.questions=questions
        this.startTime=startTime
        this.endTime=endTime
        this.tenant=tenant
        this.createdBy=createdBy
        this.updatedBy=updatedBy
        this.employees=employees
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id
    }

    /*{
        "_id": "62fc88e1f196eb5349697272",
        "name": "Fun friday",
        "type": "Casual",
        "status": "active",
        "questions": [
        {
            "options": [
            "cards",
            "carrom",
            "cricket",
            "find a missing"
            ],
            "_id": "62fc88e1f196eb5349697273",
            "title": "What will be the best game ?",
            "type": "Checkbox"
        },
        {
            "options": [
            "Morning",
            "Evening",
            "Late night"
            ],
            "_id": "62fc88e1f196eb5349697274",
            "title": "What will be best time ?",
            "type": "Radio"
        },
        {
            "options": [
            "Chinease",
            "japanease",
            "Veg",
            "Pure Veg"
            ],
            "_id": "62fc88e1f196eb5349697275",
            "title": "food to eat",
            "type": "Dropdown"
        }
        ],
        "startTime": "2022-08-16T18:30:00.000Z",
        "endTime": "2022-08-30T18:30:00.000Z",
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": null,
        "updatedBy": "62eba1fdfca59149364d33a4",
        "employees": [],
        "createdAt": "2022-08-17T06:21:21.791Z",
        "updatedAt": "2022-08-21T11:04:07.769Z",
        "__v": 0,
        "id": "62fc88e1f196eb5349697272"
    }*/
}