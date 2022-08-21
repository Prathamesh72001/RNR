package com.example.rnr.model.dataclasses.surveylist.casualsurveylist

import com.google.gson.annotations.SerializedName

class Question() {

    @SerializedName("options")
    lateinit var options:Array<String>

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("title")
    lateinit var title:String

    @SerializedName("type")
    lateinit var type:String

    constructor(options:Array<String>,_id:String,title:String,type:String):this(){
        this.options=options
        this._id=_id
        this.title=title
        this.type=type
    }

    /*"options": [
    "cards",
    "carrom",
    "cricket",
    "find a missing"
    ],
    "_id": "62fc88e1f196eb5349697273",
    "title": "What will be the best game ?",
    "type": "Checkbox"*/
}