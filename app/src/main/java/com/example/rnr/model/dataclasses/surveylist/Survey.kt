package com.example.rnr.model.dataclasses.surveylist

import com.example.rnr.model.dataclasses.Project
import com.google.gson.annotations.SerializedName

class Survey() {

    @SerializedName("project")
    lateinit var project: Project

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("name")
    lateinit var name:String

    @SerializedName("type")
    lateinit var type:String

    @SerializedName("startTime")
    lateinit var startTime:String

    @SerializedName("endTime")
    lateinit var endTime:String

    @SerializedName("status")
    lateinit var status:String

    @SerializedName("employees")
    lateinit var employees:Array<SurveyListEmployee>

    @SerializedName("tenant")
    lateinit var tenant:String

    @SerializedName("createdBy")
    lateinit var createdBy:String

    @SerializedName("updatedBy")
    lateinit var updatedBy:String

    @SerializedName("questions")
    lateinit var questions:Array<Any>

    @SerializedName("createdAt")
    lateinit var createdAt:String

    @SerializedName("updatedAt")
    lateinit var updatedAt:String

    @SerializedName("__v")
    var __v:Int=0

    @SerializedName("id")
    lateinit var id:String

    constructor(project:Project, _id:String, name:String, type:String,
                startTime:String, endTime:String, status:String, employees:Array<SurveyListEmployee>,
                tenant:String, createdBy:String, updatedBy:String, questions:Array<Any>,
                createdAt:String, updatedAt:String, __v:Int, id:String
    ):this(){
        this.project=project
        this._id=_id
        this.name=name
        this.type=type
        this.startTime=startTime
        this.endTime=endTime
        this.status=status
        this.employees=employees
        this.tenant=tenant
        this.createdBy=createdBy
        this.updatedBy=updatedBy
        this.questions=questions
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id
    }

    /*{
        "project": {
        "id": "62f11414cb761d496a76916a",
        "name": "Inspeero2"
    },
        "_id": "62fdf46c76cc813bef0e9b40",
        "name": "teste",
        "type": "Organization Feedback",
        "startTime": "2022-08-15T11:52:34.432Z",
        "endTime": "2022-08-17T11:52:34.432Z",
        "status": "inactive",
        "employees": [
        {
            "_id": "62fdf46c76cc813bef0e9b41",
            "id": "62eba1fdfca59149364d33a4",
            "email": "vmalel@inspeero.com",
            "fullName": "venkatesh malel"
        },
        {
            "_id": "62fdf46c76cc813bef0e9b42",
            "id": "62f0b8cbfca59149364d33aa",
            "email": "mshah@inspeero.com",
            "fullName": "M shah"
        },
        {
            "_id": "62fdf46c76cc813bef0e9b43",
            "id": "62e8e9911e50044773e17887",
            "email": "durgesh@inspeero.com",
            "fullName": "durgesh"
        },
        {
            "_id": "62fdf46c76cc813bef0e9b44",
            "id": "62e8e9281e50044773e17885",
            "email": "pbhondave@inspeero.com",
            "fullName": "preeti"
        },
        {
            "_id": "62fdf46c76cc813bef0e9b45",
            "id": "62fc8e65f91dc416268d8ca1",
            "email": "dpatil@inspeero.com",
            "fullName": "Darshan Patil"
        }
        ],
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": "admin",
        "updatedBy": "62eba1fdfca59149364d33a4",
        "questions": [],
        "createdAt": "2022-08-18T08:12:28.995Z",
        "updatedAt": "2022-08-18T09:14:18.190Z",
        "__v": 0,
        "id": "62fdf46c76cc813bef0e9b40"
    },*/
}