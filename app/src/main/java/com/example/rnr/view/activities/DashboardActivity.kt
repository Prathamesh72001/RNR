package com.example.rnr.view.activities

import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rnr.R
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.view.adapters.SurveyListingAdapter
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory

class DashboardActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sessionManager1:SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }

        //session
        sessionManager1 = SessionManager(this, SessionManager.SESSION_USER)

        val tokenDetails: HashMap<String, String?>? = sessionManager1.getTokenFromSession()
        val token =tokenDetails?.get(SessionManager.KEY_AUTHTOKEN)

        //hooks
        viewModel = ViewModelProvider(
            this,
            ViewFactory()
        ).get(MyViewModel::class.java)
        viewModel.authme(this@DashboardActivity,token!!)

        tv_username=findViewById(R.id.tv_username)
        tv_signout=findViewById(R.id.tv_signout)
        tv_available_survey_count=findViewById(R.id.tv_available_survey_count)
        btn_go_to_survey=findViewById(R.id.btn_goto_survey)
        btn_share_feedback=findViewById(R.id.btn_share_feedback)

        //setters
        val userDetails: HashMap<String, String?>? = sessionManager1.getUserDetailFromSession()
        val fullname =userDetails?.get(SessionManager.KEY_USERNAME)
        val tenantId =userDetails?.get(SessionManager.KEY_USERTENANTID)

        viewModel.surveylist(token,tenantId!!)

        tv_username!!.text=fullname

        //listeners
        viewModel.getSurveyListNumberResult().observe(this
        ) {
            if(it!=0){
                tv_available_survey_count!!.text=it.toString()
            }
            else{
                tv_available_survey_count!!.text="0"
            }
        }

        btn_go_to_survey!!.setOnClickListener{
            startActivity(Intent(this@DashboardActivity,SurveyListingActivity::class.java))
        }

        btn_share_feedback!!.setOnClickListener{
            startActivity(Intent(this@DashboardActivity,FeedbackActivity::class.java))
        }

        tv_signout!!.setOnClickListener{
            val sessionManager = SessionManager(this@DashboardActivity, SessionManager.SESSION_USER)
            sessionManager.logout()

            startActivity(Intent(this@DashboardActivity,LoginActivity::class.java))
            finishAffinity()
        }


    }


    private fun isConnectedToInternet(login: DashboardActivity): Boolean {
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

    companion object{
        var tv_username:TextView?=null
        var tv_signout:TextView?=null
        var tv_available_survey_count:TextView?=null
        var btn_go_to_survey:RelativeLayout?=null
        var btn_share_feedback:RelativeLayout?=null
    }
}