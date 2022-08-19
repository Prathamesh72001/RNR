package com.example.rnr.model.dataclasses.authme

import com.google.gson.annotations.SerializedName

class AuthTenant() {

    @SerializedName("_id")
    lateinit var _id: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("createdBy")
    lateinit var createdBy: String

    @SerializedName("updatedBy")
    lateinit var updatedBy: String

    @SerializedName("createdAt")
    lateinit var createdAt: String

    @SerializedName("updatedAt")
    lateinit var updatedAt: String

    @SerializedName("__v")
    var __v: Int = 0

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("settings")
    lateinit var settings: AuthSettings

    constructor(
        _id: String, name: String, url: String, createdBy: String, updatedBy: String,
        createdAt: String, updatedAt: String, __v: Int, id: String, settings: AuthSettings
    ) : this() {
        this._id=_id
        this.name=name
        this.url=url
        this.createdBy=createdBy
        this.updatedBy=updatedBy
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.id=id
        this.settings=settings
    }

    /*"_id": "62e8be6835015b4448306fcb",
    "name": "default",
    "url": "default",
    "createdBy": "62e8be6835015b4448306fca",
    "updatedBy": "62e8be6835015b4448306fca",
    "createdAt": "2022-08-02T06:04:24.623Z",
    "updatedAt": "2022-08-02T06:04:24.623Z",
    "__v": 0,
    "id": "62e8be6835015b4448306fcb",
    "settings": {
        "_id": "62e8be6835015b4448306fcc",
        "theme": "default",
        "tenant": "62e8be6835015b4448306fcb",
        "createdBy": "62e8be6835015b4448306fca",
        "backgroundImages": [],
        "logos": [],
        "createdAt": "2022-08-02T06:04:24.637Z",
        "updatedAt": "2022-08-02T06:04:24.637Z",
        "__v": 0,
        "id": "62e8be6835015b4448306fcc"
    }*/
}