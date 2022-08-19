package com.example.rnr.model.dataclasses.authme

import com.google.gson.annotations.SerializedName

class AuthMeResponse() {

    @SerializedName("emailVerified")
    var emailVerified:Boolean=false

    @SerializedName("_id")
    lateinit var _id:String

    @SerializedName("email")
    lateinit var email:String

    @SerializedName("firstName")
    lateinit var firstName:String

    @SerializedName("fullName")
    lateinit var fullName:String

    @SerializedName("avatars")
    lateinit var avatars:Array<Any>

    @SerializedName("tenants")
    lateinit var tenants:Array<AuthTenants>

    @SerializedName("createdAt")
    lateinit var createdAt:String

    @SerializedName("updatedAt")
    lateinit var updatedAt:String

    @SerializedName("__v")
    var __v:Int=0

    @SerializedName("passwordResetTokenExpiresAt")
    lateinit var passwordResetTokenExpiresAt:String

    @SerializedName("updatedBy")
    lateinit var updatedBy:String

    @SerializedName("jwtTokenInvalidBefore")
    lateinit var jwtTokenInvalidBefore:String

    @SerializedName("lastName")
    lateinit var lastName:String

    @SerializedName("phoneNumber")
    lateinit var phoneNumber:String

    @SerializedName("id")
    lateinit var id:String

    constructor(emailVerified:Boolean,_id:String,email:String,firstName:String,
                fullName:String?,avatars:Array<Any>,tenants:AuthTenants,createdAt:String,
                updatedAt:String,__v:Int,passwordResetTokenExpiresAt:String,updatedBy:String,
                jwtTokenInvalidBefore:String,lastName:String?,phoneNumber:String?,id:String
    ):this(){
        this.emailVerified=emailVerified
        this._id=_id
        this.email=email
        this.firstName=firstName
        if (fullName != null) {
            this.fullName=fullName
        }
        this.avatars=avatars
        this.tenants= arrayOf(tenants)
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.__v=__v
        this.passwordResetTokenExpiresAt=passwordResetTokenExpiresAt
        this.updatedBy=updatedBy
        this.jwtTokenInvalidBefore=jwtTokenInvalidBefore
        if (lastName != null) {
            this.lastName=lastName
        }
        if (phoneNumber != null) {
            this.phoneNumber=phoneNumber
        }
        this.id=id
    }

    /*"emailVerified": true,
    "_id": "62f37f8e098ab0420aa68530",
    "email": "admindev@inspeero.com",
    "firstName": "admindev",
    "fullName": null,
    "avatars": [],
    "tenants": [
    {
        "roles": [
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
        "id": "62f37f8e098ab0420aa68531"
    }
    ],
    "createdAt": "2022-08-10T09:51:10.171Z",
    "updatedAt": "2022-08-11T10:54:52.272Z",
    "__v": 0,
    "passwordResetTokenExpiresAt": "2022-08-11T09:51:10.225Z",
    "updatedBy": "62f37f8e098ab0420aa68530",
    "jwtTokenInvalidBefore": "2022-08-10T09:52:13.420Z",
    "lastName": null,
    "phoneNumber": null,
    "id": "62f37f8e098ab0420aa68530"*/
}