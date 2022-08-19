package com.example.rnr.model.dataclasses.surveylist.orgsurveylist

import com.google.gson.annotations.SerializedName

class Employee() {

    @SerializedName("id")
    lateinit var id:String

    @SerializedName("email")
    lateinit var email:String

    @SerializedName("fullName")
    lateinit var fullName:String

    @SerializedName("kpi")
    lateinit var kpi:Array<KPI>

    constructor(id:String,email:String,fullName:String,kpi:Array<KPI>):this(){
        this.id=id
        this.email=email
        this.fullName=fullName
        this.kpi=kpi
    }

    /*{
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
    },*/
}