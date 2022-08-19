package com.example.rnr.model.dataclasses.surveylist

import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurvey
import com.google.gson.annotations.SerializedName

class SurveyListResponse() {
    @SerializedName("rows")
    lateinit var rows:Array<Survey>

    constructor(rows:Array<Survey>):this(){
        this.rows=rows
    }

}