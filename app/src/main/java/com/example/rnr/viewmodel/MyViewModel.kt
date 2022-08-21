package com.example.rnr.viewmodel

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rnr.model.dataclasses.authme.AuthMeResponse
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurvey
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurveyListResponse
import com.example.rnr.model.dataclasses.login.LoginBody
import com.example.rnr.model.dataclasses.login.LoginResponse
import com.example.rnr.model.dataclasses.resetpasswordemail.ResetPasswordEmailBody
import com.example.rnr.model.dataclasses.surveylist.Survey
import com.example.rnr.model.dataclasses.surveylist.SurveyListResponse
import com.example.rnr.model.dataclasses.surveylist.casualsurveylist.CasualSurveyResponse
import com.example.rnr.model.dataclasses.surveylist.casualsurveylist.Question
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.Employee
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.OrgSurveyResponse
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.view.activities.EmployeeSurveyFormActivity
import com.example.rnr.view.activities.LoginActivity
import com.example.rnr.view.adapters.EmployeeSurveyAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONObject

class MyViewModel : ViewModel() {
    var LoginResultMutableData:MutableLiveData<String> = MutableLiveData()
    var AuthTokenMutableData:MutableLiveData<String> = MutableLiveData()
    var ResetPasswordEmailResultMutableData:MutableLiveData<String> = MutableLiveData()
    var SurveyListMutableData:MutableLiveData<ArrayList<Survey>> = MutableLiveData()
    var SurveyListNumberMutableData:MutableLiveData<Int> = MutableLiveData()
    var CasualSurveyTitleMutableData:MutableLiveData<String> = MutableLiveData()
    var CasualSurveyQuestionsMutableData:MutableLiveData<Array<Question>> = MutableLiveData()

    init {
        LoginResultMutableData.postValue("Not Logged In")
        ResetPasswordEmailResultMutableData.postValue("Not Sent")
        AuthTokenMutableData.postValue("")
        SurveyListMutableData.postValue(arrayListOf())
        SurveyListNumberMutableData.postValue(0)
        CasualSurveyTitleMutableData.postValue("")
        CasualSurveyQuestionsMutableData.postValue(arrayOf())
    }

    fun login(email: String, password: String) {
        ViewRepository().loginRemote(
            LoginBody(email, password),
            object : ViewRepository.IloginResponse {
                override fun onResponse(response: LoginResponse) {
                    AuthTokenMutableData.postValue(response.token)
                    LoginResultMutableData.postValue("Login Success")
                }

                @RequiresApi(Build.VERSION_CODES.P)
                override fun onFailure(t: Throwable) {
                    LoginResultMutableData.postValue("Login Failed : "+ t.message.toString())
                }

            })
    }

    fun authme(context:Context,token: String){
        ViewRepository().authmeRemote(token,object :ViewRepository.Iauth_meResponse{
            override fun onResponse(response: AuthMeResponse) {
                //Create Session
                val sessionManager1 = SessionManager(context, SessionManager.SESSION_USER)
                sessionManager1.creatingUserSession(response.fullName,response.tenants[0].tenant._id)

            }

            override fun onFailure(t: Throwable) {
                val snack = Snackbar.make(LoginActivity.btn_login!!,t.message.toString(),Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.RED)
                snack.show()
            }

        })
    }

    fun get_org_survey(context: Context,token:String,tenantId: String, surveyId:String){
        ViewRepository().get_org_survey(token,tenantId,surveyId,object :ViewRepository.IorgsurveyResponse{
            override fun onResponse(response: OrgSurveyResponse) {
                EmployeeSurveyFormActivity.progressBar!!.visibility= View.GONE
                EmployeeSurveyFormActivity.rcv_employee_surveys!!.visibility= View.VISIBLE
                val list=ArrayList<Employee>()
                for(i in 0 until response.employees.size){
                    list.add(response.employees[i])
                }
                EmployeeSurveyFormActivity.rcv_employee_surveys!!.adapter=EmployeeSurveyAdapter(context,response.project.name,list)
            }

            override fun onFailure(t: Throwable) {
                val snack = Snackbar.make(LoginActivity.btn_login!!,t.message.toString(),Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.RED)
                snack.show()
            }

        })
    }

    fun get_casual_survey(token:String,tenantId: String, surveyId:String){
        ViewRepository().get_casual_survey(token,tenantId,surveyId,object :ViewRepository.IcasualsurveyResponse{
            override fun onResponse(response: CasualSurveyResponse) {
                CasualSurveyTitleMutableData.postValue(response.name)
                CasualSurveyQuestionsMutableData.postValue(response.questions)
            }

            override fun onFailure(t: Throwable) {
                val snack = Snackbar.make(LoginActivity.btn_login!!,t.message.toString(),Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.RED)
                snack.show()
            }

        })
    }

    fun surveylist(token:String,tenantId:String){
        ViewRepository().surveylist(token,tenantId,object :ViewRepository.IsurveylistResponse{
            override fun onResponse(response: SurveyListResponse) {
                SurveyListNumberMutableData.postValue(response.count)

                val list=ArrayList<Survey>()
                for(i in 0 until response.rows.size){
                    //Log.d("tag", response.rows[i].id)
                    list.add(response.rows[i])
                }
                SurveyListMutableData.postValue(list)
            }

            override fun onFailure(t: Throwable) {
                val snack = Snackbar.make(LoginActivity.btn_login!!,t.message.toString(), Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.RED)
                snack.show()            }
        })
    }

    fun resetpassword(email:String) {
        ViewRepository().send_reset_password_emailRemote(
            ResetPasswordEmailBody(email),
            object : ViewRepository.Isend_reset_password_emailResponse {
                override fun onResponse(response: Boolean) {
                    ResetPasswordEmailResultMutableData.postValue("Sent")
                }

                override fun onFailure(t: Throwable) {
                    ResetPasswordEmailResultMutableData.postValue("Reset Password Failed : " + t.message.toString())
                }
            })
    }


    fun getLoginResult():LiveData<String> {
        return LoginResultMutableData
    }

    fun getAuthTokenResult():LiveData<String> {
        return AuthTokenMutableData
    }

    fun getSurveyListResult():LiveData<ArrayList<Survey>> {
        return SurveyListMutableData
    }

    fun getSurveyListNumberResult():LiveData<Int> {
        return SurveyListNumberMutableData
    }

    fun getCasualSurveyQuestionsResult():LiveData<Array<Question>> {
        return CasualSurveyQuestionsMutableData
    }

    fun getCasualSurveyTitleResult():LiveData<String> {
        return CasualSurveyTitleMutableData
    }

    fun getResetPasswordEmailResult():LiveData<String> {
        return ResetPasswordEmailResultMutableData
    }
}