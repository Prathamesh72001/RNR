package com.example.rnr.view.activities

import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rnr.R
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.view.adapters.EmployeeSurveyAdapter
import com.example.rnr.view.adapters.SurveyListingAdapter
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory

class SurveyListingActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sessionManager1: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_listing)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }

        //sessions
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
        viewModel.surveylist(token!!,tenantId!!)

        tv_username =findViewById(R.id.tv_username)
        iv_back =findViewById(R.id.iv_back)
        rcv_surveys =findViewById(R.id.rcv_surveys)
        progressBar =findViewById(R.id.progressBar)

        //setters
        tv_username!!.text=fullname

        //listeners
        viewModel.getSurveyListResult().observe(this
        ) {
            if(it.isNotEmpty()){
                progressBar!!.visibility= View.GONE
                rcv_surveys!!.visibility= View.VISIBLE
                rcv_surveys!!.adapter=
                    SurveyListingAdapter(it,this@SurveyListingActivity)
            }
            else{
                rcv_surveys!!.visibility= View.GONE
                progressBar!!.visibility= View.VISIBLE
            }
        }

        iv_back!!.setOnClickListener{
            startActivity(Intent(this@SurveyListingActivity,DashboardActivity::class.java))
            finishAffinity()
        }
    }

    private fun isConnectedToInternet(login: SurveyListingActivity): Boolean {
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
        startActivity(Intent(this@SurveyListingActivity,DashboardActivity::class.java))
        finishAffinity()
    }

    companion object{
        var tv_username: TextView?=null
        var iv_back: ImageView?=null
        var rcv_surveys: RecyclerView?=null
        var progressBar: RelativeLayout?=null
    }
}