package com.example.rnr.model.dataclasses.authme

import com.google.gson.annotations.SerializedName

class AuthSettings() {

    @SerializedName("_id")
    lateinit var _id: String

    @SerializedName("theme")
    lateinit var theme: String

    @SerializedName("tenant")
    lateinit var tenant: String

    @SerializedName("createdBy")
    lateinit var createdBy: String

    @SerializedName("backgroundImages")
    lateinit var backgroundImages: Array<Any>

    @SerializedName("logos")
    lateinit var logos: Array<Any>

    @SerializedName("createdAt")
    lateinit var createdAt: String

    @SerializedName("updatedAt")
    lateinit var updatedAt: String

    @SerializedName("__v")
    var __v: Int = 0

    @SerializedName("id")
    lateinit var id: String

    constructor(
        _id: String, theme: String,tenant:String, createdBy: String, backgroundImages: Array<Any>,
        logos: Array<Any>, createdAt: String, updatedAt: String, __v: Int,id:String
    ) : this() {
        this._id=_id
        this.theme=theme
        this.tenant=tenant
        this.createdBy=createdBy
        this.backgroundImages=backgroundImages
        this.logos=logos
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id

    }

    /*"_id": "62e8be6835015b4448306fcc",
    "theme": "default",
    "tenant": "62e8be6835015b4448306fcb",
    "createdBy": "62e8be6835015b4448306fca",
    "backgroundImages": [],
    "logos": [],
    "createdAt": "2022-08-02T06:04:24.637Z",
    "updatedAt": "2022-08-02T06:04:24.637Z",
    "__v": 0,
    "id": "62e8be6835015b4448306fcc"*/
}