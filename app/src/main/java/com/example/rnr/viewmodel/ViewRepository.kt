package com.example.rnr.viewmodel

import android.util.Log
import com.example.rnr.model.dataclasses.authme.AuthMeResponse
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurveyListResponse
import com.example.rnr.model.dataclasses.login.LoginBody
import com.example.rnr.model.dataclasses.login.LoginResponse
import com.example.rnr.model.dataclasses.resetpasswordemail.ResetPasswordEmailBody
import com.example.rnr.model.dataclasses.surveylist.SurveyListResponse
import com.example.rnr.model.dataclasses.surveylist.casualsurveylist.CasualSurveyResponse
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.OrgSurveyResponse
import com.example.rnr.model.retrofit.AuthRetrofitInterface
import com.example.rnr.model.retrofit.RetrofitClient
import com.example.rnr.model.retrofit.RetrofitClient_for_AuthMe
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewRepository {
    fun loginRemote(body: LoginBody, loginResponse: IloginResponse){
        val service= RetrofitClient.getRetrofit().create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<LoginResponse> = service.signin(body)

        initiateLogin.enqueue(object:Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    loginResponse.onResponse(response.body()!!)
                }
                else{
                    loginResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResponse.onFailure(t)
            }
        })
    }

    fun authmeRemote(token: String, authmeResponse: Iauth_meResponse){
        val service=RetrofitClient_for_AuthMe.getRetrofit(token).create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<AuthMeResponse> = service.authme()

        initiateLogin.enqueue(object:Callback<AuthMeResponse>{
            override fun onResponse(
                call: Call<AuthMeResponse>,
                response: Response<AuthMeResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("tag", response.body()!!.firstName)
                    authmeResponse.onResponse(response.body()!!)
                }
                else{
                    authmeResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<AuthMeResponse>, t: Throwable) {
                authmeResponse.onFailure(t)
            }

        })
    }

    fun send_reset_password_emailRemote(body: ResetPasswordEmailBody, resetpasswordemailResponse: Isend_reset_password_emailResponse){
        val service= RetrofitClient.getRetrofit().create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<Boolean> = service.send_password_reset_email(body)

        initiateLogin.enqueue(object:Callback<Boolean>{
            override fun onResponse(
                call: Call<Boolean>,
                response: Response<Boolean>
            ) {
                if(response.isSuccessful){
                    Log.d("tag",response.body().toString())
                    resetpasswordemailResponse.onResponse(response.body()!!)
                }
                else{
                    resetpasswordemailResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("tag",t.message.toString())
                resetpasswordemailResponse.onFailure(t)
            }

        })
    }

    fun get_org_survey(token:String,tenantId: String, surveyId:String ,orgsurveyResponse: IorgsurveyResponse){
        val service=RetrofitClient_for_AuthMe.getRetrofit(token).create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<OrgSurveyResponse> = service.get_org_survey(tenantId,surveyId)

        initiateLogin.enqueue(object:Callback<OrgSurveyResponse> {
            override fun onResponse(
                call: Call<OrgSurveyResponse>,
                response: Response<OrgSurveyResponse>
            ) {
                if(response.isSuccessful){
                    orgsurveyResponse.onResponse(response.body()!!)
                }
                else{
                    orgsurveyResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<OrgSurveyResponse>, t: Throwable) {
                orgsurveyResponse.onFailure(t)
            }
        })
    }

    fun get_casual_survey(token:String,tenantId: String, surveyId:String ,casualsurveyResponse:IcasualsurveyResponse){
        val service=RetrofitClient_for_AuthMe.getRetrofit(token).create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<CasualSurveyResponse> = service.get_casual_survey(tenantId,surveyId)

        initiateLogin.enqueue(object:Callback<CasualSurveyResponse> {
            override fun onResponse(
                call: Call<CasualSurveyResponse>,
                response: Response<CasualSurveyResponse>
            ) {
                if(response.isSuccessful){
                    casualsurveyResponse.onResponse(response.body()!!)
                }
                else{
                    casualsurveyResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<CasualSurveyResponse>, t: Throwable) {
                casualsurveyResponse.onFailure(t)
            }
        })
    }

    fun surveylist(token:String,tenantId: String, surveylistResponse: IsurveylistResponse){
        val service=RetrofitClient_for_AuthMe.getRetrofit(token).create(AuthRetrofitInterface::class.java)
        val initiateLogin: Call<SurveyListResponse> = service.survey_list(tenantId)

        initiateLogin.enqueue(object:Callback<SurveyListResponse> {
            override fun onResponse(
                call: Call<SurveyListResponse>,
                response: Response<SurveyListResponse>
            ) {
                if(response.isSuccessful){
                    surveylistResponse.onResponse(response.body()!!)
                }
                else{
                    surveylistResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<SurveyListResponse>, t: Throwable) {
                surveylistResponse.onFailure(t)
            }
        })
    }

    interface IloginResponse{
        fun onResponse(response: LoginResponse)
        fun onFailure(t:Throwable)
    }

    interface Isend_reset_password_emailResponse{
        fun onResponse(response: Boolean)
        fun onFailure(t:Throwable)
    }

    interface Iauth_meResponse{
        fun onResponse(response: AuthMeResponse)
        fun onFailure(t:Throwable)
    }

    interface Iemployee_listResponse{
        fun onResponse(response: JSONObject)
        fun onFailure(t:Throwable)
    }

    interface Iemployee_surveylistResponse{
        fun onResponse(response: EmployeeSurveyListResponse)
        fun onFailure(t:Throwable)
    }

    interface IorgsurveyResponse{
        fun onResponse(response: OrgSurveyResponse)
        fun onFailure(t:Throwable)
    }

    interface IcasualsurveyResponse{
        fun onResponse(response: CasualSurveyResponse)
        fun onFailure(t:Throwable)
    }

    interface IsurveylistResponse{
        fun onResponse(response:SurveyListResponse)
        fun onFailure(t:Throwable)
    }

}
