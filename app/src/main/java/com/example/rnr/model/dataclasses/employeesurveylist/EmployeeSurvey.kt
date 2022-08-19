package com.example.rnr.model.dataclasses.employeesurveylist

import com.example.rnr.model.dataclasses.Project
import com.google.gson.annotations.SerializedName

class EmployeeSurvey() {

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("feedback")
    lateinit var feedback:String

    @SerializedName("employees")
    lateinit var employees:Employee

    @SerializedName("project")
    lateinit var project:Project

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

    @SerializedName("status")
    lateinit var status:String

    constructor(_id:String, feedback:String, employees:Employee, project: Project,
                tenant:String, createdBy:String, updatedBy:String, createdAt:String,
                updatedAt:String, __v:Int, id:String, status:String):this(){
        this._id=_id
        this.feedback=feedback
        this.employees=employees
        this.project=project
        this.tenant=tenant
        this.createdBy=createdBy
        this.updatedBy=updatedBy
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id
        this.status=status
    }

    /*{
        "_id": "62fcd3608c53e25fb157dbe4",
        "feedback": "FeedbackFeedback",
        "employees": {
        "id": "62fc870af91dc416268d8c9f",
        "label": "Test user",
        "email": "user@inspeero.com",
        "fullName": "Test user"
    },
        "project": {
        "id": "62f2311dab6bbc0a6e036965",
        "label": "RNR",
        "name": "RNR"
    },
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": "preeti",
        "updatedBy": "62e8e9281e50044773e17885",
        "createdAt": "2022-08-17T11:39:12.982Z",
        "updatedAt": "2022-08-17T11:39:12.982Z",
        "__v": 0,
        "id": "62fcd3608c53e25fb157dbe4",
        "status": "active"
    }*/
}