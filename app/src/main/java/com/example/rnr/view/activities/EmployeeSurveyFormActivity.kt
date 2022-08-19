package com.example.rnr.view.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rnr.R
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurvey
import com.example.rnr.model.dataclasses.surveylist.Survey
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.Employee
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.OrgSurveyResponse
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.view.adapters.EmployeeSurveyAdapter
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory
import kotlin.math.cos
import kotlin.math.sin

class EmployeeSurveyFormActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sessionManager1: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_survey_form)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //intent
        survey_id= intent.getStringExtra("survey_id")

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }

        //session
        sessionManager1 = SessionManager(this, SessionManager.SESSION_USER)

        val tokenDetails: HashMap<String, String?>? = sessionManager1.getTokenFromSession()
        val token =tokenDetails?.get(SessionManager.KEY_AUTHTOKEN)

        val userDetails: HashMap<String, String?>? = sessionManager1.getUserDetailFromSession()
        val fullname =userDetails?.get(SessionManager.KEY_USERNAME)
        val tenantId =userDetails?.get(SessionManager.KEY_USERTENANTID)

        //hooks
        viewModel = ViewModelProvider(
            this,
            ViewFactory()
        ).get(MyViewModel::class.java)
        viewModel.get_org_survey(this@EmployeeSurveyFormActivity,token!!,tenantId!!, survey_id!!)

        tv_employee_survey_title=findViewById(R.id.tv_emplyee_survey_title)
        tv_username =findViewById(R.id.tv_username)
        iv_back =findViewById(R.id.iv_back)
        rcv_employee_surveys=findViewById(R.id.rcv_employee_surveys)
        progressBar=findViewById(R.id.progressBar)

        //setters
        tv_username!!.text=fullname

        //listeners
        iv_back!!.setOnClickListener{
            startActivity(Intent(this@EmployeeSurveyFormActivity,SurveyListingActivity::class.java))
            finishAffinity()
        }

        //textview_color
        tv_employee_survey_title!!.post {
            val length: Int = tv_employee_survey_title!!.measuredWidth
            val endX = sin(45.0) * length
            val endY = cos(45.0) * length
            val textShader: Shader = LinearGradient(
                0f,
                0f,
                endX.toFloat(),
                endY.toFloat(),
                intArrayOf(
                    applicationContext.resources.getColor(R.color.sky_blue_card_color), applicationContext.resources.getColor(R.color.light_green)
                ),
                null,
                Shader.TileMode.CLAMP
            )
            tv_employee_survey_title!!.paint.shader = textShader
            tv_employee_survey_title!!.invalidate()
        }

    }

    private fun isConnectedToInternet(login: EmployeeSurveyFormActivity): Boolean {
        val connectivityManager =
            login.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilecon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wificon != null && wificon.isConnected || mobilecon != null && mobilecon.isConnected
    }

    fun showDialog(){
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialogue_box_no_internet)
        dialog.window!!.setBackgroundDrawable(getDrawable(R.drawable.dialogue_bg))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.show()
        val btn_ok=dialog.findViewById<RelativeLayout>(R.id.btn_ok)
        btn_ok.setOnClickListener{
            dialog.dismiss()
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@EmployeeSurveyFormActivity,SurveyListingActivity::class.java))
        finishAffinity()
    }

    companion object{
        var tv_username: TextView?=null
        var iv_back: ImageView?=null
        var tv_employee_survey_title: TextView?=null
        var rcv_employee_surveys: RecyclerView?=null
        var progressBar: RelativeLayout?=null
        var survey_id:String?=null
    }
}