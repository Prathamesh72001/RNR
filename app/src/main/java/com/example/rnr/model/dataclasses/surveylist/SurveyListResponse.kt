package com.example.rnr.model.dataclasses.surveylist

import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurvey
import com.google.gson.annotations.SerializedName

class SurveyListResponse() {
    @SerializedName("rows")
    lateinit var rows:Array<Survey>

    @SerializedName("count")
    var count:Int=0

    constructor(rows:Array<Survey>,count:Int):this(){
        this.rows=rows
        this.count=count
    }

}