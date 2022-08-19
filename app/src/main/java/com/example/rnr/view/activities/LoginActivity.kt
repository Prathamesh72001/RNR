package com.example.rnr.view.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rnr.R
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var tl_email: TextInputLayout
    lateinit var tl_password: TextInputLayout
    lateinit var tv_forgot_password: TextView
    lateinit var dialog: Dialog
    lateinit var viewModel: MyViewModel


    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sessionManager1 = SessionManager(this@LoginActivity, SessionManager.SESSION_USER)
        val isLoggedIn=sessionManager1.checkLogin()

        if(isLoggedIn){
            startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
            finishAffinity()
        }

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }


        //hooks
        viewModel = ViewModelProvider(
            this,
            ViewFactory()
        ).get(MyViewModel::class.java)
        tl_email = findViewById(R.id.tl_email)
        tl_password = findViewById(R.id.tl_password)
        btn_login = findViewById(R.id.btn_login)
        tv_forgot_password = findViewById(R.id.tv_forgot_password)
        progress=findViewById(R.id.progressBar)

        //listeners
        viewModel.getLoginResult().observe(this
        ) {
            if(it.equals("Login Success")){
                val intent=Intent(this,DashboardActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            else if(it.equals("Not Logged In")){

            }
            else{
                showError(it)
            }
        }

        viewModel.getAuthTokenResult().observe(this){
            if(it.equals("")){

            }
            else{
                //Create Session
                val sessionManager1 = SessionManager(this@LoginActivity, SessionManager.SESSION_USER)
                sessionManager1.creatingTokenSession(it)


            }
        }

        btn_login!!.setOnClickListener {
            CheckBeforeLoginUser()
        }

        tv_forgot_password.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finishAffinity()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun CheckBeforeLoginUser() {

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
           showDialog()
        }

        //Validation
        if (!validateEmail() or !validatePassword()) {
            return
        }

        progress!!.visibility=View.VISIBLE

        viewModel.login(tl_email.editText!!.text.toString(),tl_password.editText!!.text.toString())

    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun showError(error:String){
        progress!!.visibility=View.GONE
        Log.d("tag",error)
        val snack = Snackbar.make(btn_login!!,error,Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(Color.RED)
        snack.show()
    }

    private fun validatePassword(): Boolean{
        val value = Objects.requireNonNull<EditText>(tl_password.editText).text.toString()
            .trim { it <= ' ' }
        return if (value.isEmpty()) {
            tl_password.error = "Empty"
            false
        } else {
            tl_password.error = null
            tl_password.requestFocus()
            true
        }
    }

    private fun validateEmail(): Boolean{
        val value = Objects.requireNonNull<EditText>(tl_email.editText).text.toString()
            .trim { it <= ' ' }
        return if (value.isEmpty()) {
            tl_email.error = "Empty"
            false
        } else {
            tl_email.error = null
            tl_email.requestFocus()
            true
        }
    }

    private fun isConnectedToInternet(login: LoginActivity): Boolean {
        val connectivityManager =
            login.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilecon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wificon != null && wificon.isConnected || mobilecon != null && mobilecon.isConnected
    }

    fun showDialog(){
        dialog= Dialog(this)
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


    companion object {
        var progress:RelativeLayout?=null
        var btn_login: RelativeLayout?=null

    }
}