package com.example.rnr.model.dataclasses.employeesurveylist

import com.google.gson.annotations.SerializedName

class EmployeeSurveyListResponse() {

    @SerializedName("rows")
    lateinit var rows:Array<EmployeeSurvey>

    constructor(rows:Array<EmployeeSurvey>):this(){
        this.rows=rows
    }

    /*{
        "rows": [
        {
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
        },
    }*/

}