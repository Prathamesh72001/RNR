package com.example.rnr.view.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.rnr.R
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var tl_email: TextInputLayout
    lateinit var tl_confirmemail: TextInputLayout
    lateinit var tv_cancel: TextView
    lateinit var iv_back: ImageView
    lateinit var viewModel: MyViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

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
        tl_confirmemail = findViewById(R.id.tl_confirm_email)
        btn_reset_password = findViewById(R.id.btn_reset_password)
        tv_cancel = findViewById(R.id.tv_cancel)
        iv_back = findViewById(R.id.iv_back)
        progress=findViewById(R.id.progressBar)

        //listeners
        viewModel.getResetPasswordEmailResult().observe(this
        ) {
            if(it.equals("Sent")){
                showMailSentDialog()
            }
            else if(it.equals("Not Sent")){

            }
            else{
                showError(it)
            }
        }

        btn_reset_password!!.setOnClickListener {
            CheckBeforeResetPassword()
        }

        tv_cancel.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        iv_back.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun CheckBeforeResetPassword() {

        //CheckInternetConnection
        if (!isConnectedToInternet(this)) {
            showDialog()
        }

        //Validation
        if (!validateEmail() or !validateConfirmEmail()) {
            return
        }

        progress!!.visibility= View.VISIBLE

        viewModel.resetpassword(tl_email.editText!!.text.toString())

    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun showError(error:String){
        progress!!.visibility= View.GONE
        Log.d("tag",error)
        val snack = Snackbar.make(btn_reset_password!!,error, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(Color.RED)
        snack.show()
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

    private fun validateConfirmEmail(): Boolean{
        val value = Objects.requireNonNull<EditText>(tl_confirmemail.editText).text.toString()
            .trim { it <= ' ' }
        return if (value.isEmpty()) {
            tl_confirmemail.error = "Empty"
            false
        } else {
            tl_confirmemail.error = null
            tl_confirmemail.requestFocus()
            true
        }
    }

    private fun isConnectedToInternet(login: ForgotPasswordActivity): Boolean {
        val connectivityManager =
            login.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilecon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wificon != null && wificon.isConnected || mobilecon != null && mobilecon.isConnected
    }

    private fun showMailSentDialog() {
        progress!!.visibility= View.GONE
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialogue_box_feedback_submitted)
        dialog.window!!.setBackgroundDrawable(getDrawable(R.drawable.dialogue_bg))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.show()

        //hooks
        val btn_done=dialog.findViewById<RelativeLayout>(R.id.btn_done)
        val tv_btn_name=dialog.findViewById<TextView>(R.id.tv_btn_name)
        val tv_description=dialog.findViewById<TextView>(R.id.tv_description)
        val tv_title=dialog.findViewById<TextView>(R.id.tv_title)

        //settexts
        tv_btn_name.text=resources.getString(R.string.string_ok)
        tv_description.text=resources.getString(R.string.string_email_sent_successfully)
        tv_title.text=resources.getString(R.string.string_check_mail)

        //listeners
        btn_done.setOnClickListener{
            dialog.dismiss()
            startActivity(Intent(this@ForgotPasswordActivity,LoginActivity::class.java))
            finishAffinity()
        }
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
        startActivity(Intent(this@ForgotPasswordActivity,LoginActivity::class.java))
        finishAffinity()
    }

    companion object {
        var progress: RelativeLayout?=null
        var btn_reset_password: RelativeLayout?=null

    }
}