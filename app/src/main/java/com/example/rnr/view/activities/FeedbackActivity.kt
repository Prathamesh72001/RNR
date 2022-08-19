package com.example.rnr.view.activities

import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.rnr.R
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory

class FeedbackActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }

        //session
        val sessionManager1 = SessionManager(this, SessionManager.SESSION_USER)

        val tokenDetails: HashMap<String, String?>? = sessionManager1.getTokenFromSession()
        val token =tokenDetails?.get(SessionManager.KEY_AUTHTOKEN)

        val userDetails: HashMap<String, String?>? = sessionManager1.getUserDetailFromSession()
        val fullname =userDetails?.get(SessionManager.KEY_USERNAME)
        val tenantid=userDetails?.get(SessionManager.KEY_USERTENANTID)


        //hooks
        viewModel = ViewModelProvider(
            this,
            ViewFactory()
        ).get(MyViewModel::class.java)

        tv_username=findViewById(R.id.tv_username)
        tv_cancel=findViewById(R.id.tv_cancel)
        btn_submit=findViewById(R.id.btn_submit)
        iv_back=findViewById(R.id.iv_back)


        //setters
        tv_username!!.text=fullname

        //listeners
        btn_submit!!.setOnClickListener{
        }

        tv_cancel!!.setOnClickListener{
            startActivity(Intent(this@FeedbackActivity,DashboardActivity::class.java))
        }

        iv_back!!.setOnClickListener{
            startActivity(Intent(this@FeedbackActivity,DashboardActivity::class.java))
        }


    }


    private fun isConnectedToInternet(login: FeedbackActivity): Boolean {
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
        var tv_username: TextView?=null
        var tv_cancel: TextView?=null
        var btn_submit: RelativeLayout?=null
        var iv_back: ImageView?=null
    }
}