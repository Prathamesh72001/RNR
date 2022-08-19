package com.example.rnr.model.retrofit

import com.example.rnr.model.dataclasses.authme.AuthMeResponse
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurveyListResponse
import com.example.rnr.model.dataclasses.login.LoginBody
import com.example.rnr.model.dataclasses.login.LoginResponse
import com.example.rnr.model.dataclasses.resetpasswordemail.ResetPasswordEmailBody
import com.example.rnr.model.dataclasses.surveylist.SurveyListResponse
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.OrgSurveyResponse
import com.example.rnr.model.sharedprefs.SessionManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthRetrofitInterface {
    @POST("/api/auth/sign-in")
    fun signin(@Body body: LoginBody): Call<LoginResponse>

    @GET("/api/auth/me")
    fun authme(): Call<AuthMeResponse>

    @GET("/api/app/{tenantId}/{surveyId}")
    fun get_org_survey(@Path("tenantId") tenantId: String,@Path("surveyId") surveyId:String): Call<OrgSurveyResponse>

    @GET("/api/app/{tenantId}/list")
    fun survey_list(@Path("tenantId") tenantId: String): Call<SurveyListResponse>

    @POST("/api/auth/send-password-reset-email")
    fun send_password_reset_email(@Body body: ResetPasswordEmailBody) : Call<Boolean>



}