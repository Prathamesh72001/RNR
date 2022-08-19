package com.example.rnr.model.dataclasses.authme

import com.google.gson.annotations.SerializedName

class AuthTenants() {

    @SerializedName("roles")
    lateinit var roles: Array<String>

    @SerializedName("department")
    lateinit var department: Array<String>

    @SerializedName("_id")
    lateinit var _id: String

    @SerializedName("tenant")
    lateinit var tenant: AuthTenant

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("updatedAt")
    lateinit var updatedAt: String

    @SerializedName("createdAt")
    lateinit var createdAt: String

    @SerializedName("id")
    lateinit var id: String

    constructor(
        roles: Array<String>, department: Array<String>, _id: String, tenant: AuthTenant,
        status: String, updatedAt: String, createdAt: String, id: String
    ) : this() {
        this.roles=roles
        this.department=department
        this._id=_id
        this.tenant=tenant
        this.status=status
        this.updatedAt=updatedAt
        this.createdAt=createdAt
        this.id=id
    }

    /*"roles": [
    "admin"
    ],
    "department": [
    "admin"
    ],
    "_id": "62f37f8e098ab0420aa68531",
    "tenant": {
        "_id": "62e8be6835015b4448306fcb",
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
        }
    },
    "status": "active",
    "updatedAt": "2022-08-10T09:51:10.198Z",
    "createdAt": "2022-08-10T09:51:10.198Z",
    "id": "62f37f8e098ab0420aa68531"*/

}