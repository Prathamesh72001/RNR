package com.example.rnr.model.dataclasses.surveylist.orgsurveylist


import com.example.rnr.model.dataclasses.Project
import com.example.rnr.model.dataclasses.surveylist.SurveyListEmployee
import com.google.gson.annotations.SerializedName

class OrgSurveyResponse() {
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
    lateinit var employees:Array<Employee>

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
                startTime:String, endTime:String, status:String, employees:Array<Employee>,
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
            "id": "62eba1fdfca59149364d33a4",
            "email": "vmalel@inspeero.com",
            "fullName": "venkatesh malel",
            "kpi": [
            {
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
            },
            {
                "_id": "62ea5f762b61ee458160161b",
                "name": "Team Management",
                "category": "Team Management",
                "weightage": "10",
                "role": "softwareEng2",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:43:50.872Z",
                "updatedAt": "2022-08-03T11:43:50.872Z",
                "__v": 0,
                "id": "62ea5f762b61ee458160161b"
            },
            {
                "_id": "62ea5bd72b61ee458160160e",
                "name": "Overall Development",
                "category": "Overall Development",
                "weightage": "8",
                "role": "softwareEng2",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:28:23.285Z",
                "updatedAt": "2022-08-03T11:28:23.285Z",
                "__v": 0,
                "id": "62ea5bd72b61ee458160160e"
            }
            ]
        },
        {
            "id": "62f0b8cbfca59149364d33aa",
            "email": "mshah@inspeero.com",
            "fullName": "M shah",
            "kpi": [
            {
                "_id": "62ea60ac2b61ee4581601627",
                "name": "Adherence to Policy",
                "category": "Adherence to Policy",
                "weightage": "6",
                "role": "softwareEng1",
                "department": "tester",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:49:00.182Z",
                "updatedAt": "2022-08-03T11:49:00.182Z",
                "__v": 0,
                "id": "62ea60ac2b61ee4581601627"
            },
            {
                "_id": "62ea5baf2b61ee458160160b",
                "name": "Overall Development",
                "category": "Overall Development",
                "weightage": "7",
                "role": "softwareEng1",
                "department": "tester",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:27:43.546Z",
                "updatedAt": "2022-08-03T11:27:43.546Z",
                "__v": 0,
                "id": "62ea5baf2b61ee458160160b"
            },
            {
                "_id": "62ea5b772b61ee4581601607",
                "name": "Collaboration",
                "category": "Collaboration",
                "weightage": "5",
                "role": "softwareEng1",
                "department": "tester",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:26:47.676Z",
                "updatedAt": "2022-08-03T11:26:47.676Z",
                "__v": 0,
                "id": "62ea5b772b61ee4581601607"
            }
            ]
        },
        {
            "id": "62e8e9911e50044773e17887",
            "email": "durgesh@inspeero.com",
            "fullName": "durgesh",
            "kpi": [
            {
                "_id": "62ea5a94de1c357e06b57e15",
                "name": "Collaboration",
                "category": "Collaboration",
                "weightage": "5",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:23:00.744Z",
                "updatedAt": "2022-08-03T11:23:00.744Z",
                "__v": 0,
                "id": "62ea5a94de1c357e06b57e15"
            },
            {
                "_id": "62ea5ba62b61ee458160160a",
                "name": "Overall Development",
                "category": "Overall Development",
                "weightage": "7",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:27:34.193Z",
                "updatedAt": "2022-08-03T11:27:34.193Z",
                "__v": 0,
                "id": "62ea5ba62b61ee458160160a"
            },
            {
                "_id": "62ea5ffa2b61ee4581601622",
                "name": "Creativity and innovation",
                "category": "Creativity and innovation",
                "weightage": "6",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:46:02.598Z",
                "updatedAt": "2022-08-03T11:46:02.598Z",
                "__v": 0,
                "id": "62ea5ffa2b61ee4581601622"
            }
            ]
        },
        {
            "id": "62e8e9281e50044773e17885",
            "email": "pbhondave@inspeero.com",
            "fullName": "preeti",
            "kpi": []
        },
        {
            "id": "62fc8e65f91dc416268d8ca1",
            "email": "dpatil@inspeero.com",
            "fullName": "Darshan Patil",
            "kpi": [
            {
                "_id": "62ea5c3b2b61ee4581601616",
                "name": "Team Management",
                "category": "Team Management",
                "weightage": "9",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:30:03.434Z",
                "updatedAt": "2022-08-03T11:30:03.434Z",
                "__v": 0,
                "id": "62ea5c3b2b61ee4581601616"
            },
            {
                "_id": "62ea5ba62b61ee458160160a",
                "name": "Overall Development",
                "category": "Overall Development",
                "weightage": "7",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:27:34.193Z",
                "updatedAt": "2022-08-03T11:27:34.193Z",
                "__v": 0,
                "id": "62ea5ba62b61ee458160160a"
            },
            {
                "_id": "62ea5a94de1c357e06b57e15",
                "name": "Collaboration",
                "category": "Collaboration",
                "weightage": "5",
                "role": "softwareEng1",
                "department": "web",
                "tenant": "62e8be6835015b4448306fcb",
                "createdBy": "62e8be6835015b4448306fca",
                "updatedBy": "62e8be6835015b4448306fca",
                "createdAt": "2022-08-03T11:23:00.744Z",
                "updatedAt": "2022-08-03T11:23:00.744Z",
                "__v": 0,
                "id": "62ea5a94de1c357e06b57e15"
            }
            ]
        }
        ],
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": "admin",
        "updatedBy": "62fc8e65f91dc416268d8ca1",
        "questions": [],
        "createdAt": "2022-08-18T08:12:28.995Z",
        "updatedAt": "2022-08-18T11:52:17.728Z",
        "__v": 0,
        "id": "62fdf46c76cc813bef0e9b40"
    }*/
}