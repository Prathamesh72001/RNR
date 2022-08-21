package com.example.rnr.view.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rnr.R
import com.example.rnr.model.dataclasses.surveylist.casualsurveylist.CasualSurveyResponse
import com.example.rnr.model.sharedprefs.SessionManager
import com.example.rnr.view.adapters.SurveyListingAdapter
import com.example.rnr.viewmodel.MyViewModel
import com.example.rnr.viewmodel.ViewFactory

class CasualSurveyActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sessionManager1: SessionManager
    lateinit var casualSurveyResponse: CasualSurveyResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casual_survey)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //intent
        survey_id = intent.getStringExtra("survey_id")

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
        viewModel.get_casual_survey(token!!,tenantId!!, survey_id!!)

        tv_casual_survey_title =findViewById(R.id.tv_casual_survey_title)
        tv_username =findViewById(R.id.tv_username)
        iv_back =findViewById(R.id.iv_back)
        progressBar =findViewById(R.id.progressBar)
        ll_container=findViewById(R.id.ll_container)
        tv_cancel =findViewById(R.id.tv_cancel)
        btn_submit =findViewById(R.id.btn_submit)

        //setters
        tv_username!!.text=fullname

        //listeners
        viewModel.getCasualSurveyTitleResult().observe(this
        ) {
            if(it!="") {
                tv_casual_survey_title!!.text = it
            }
        }
        
        viewModel.getCasualSurveyQuestionsResult().observe(this){
            if(it.isNotEmpty()){
                progressBar!!.visibility= View.GONE
                ll_container!!.visibility= View.VISIBLE
                //setdata
                for(i in it.indices){
                    val tv_question=TextView(this)
                    tv_question.text=it[i].title
                    tv_question.textSize=24F
                    tv_question.setTextColor(Color.BLACK)
                    tv_question.typeface= Typeface.createFromAsset(assets,"res/font/hind_regular.ttf")
                    tv_question.setPadding(0,40,0,0)

                    ll_container!!.addView(tv_question)


                    if(it[i].type=="Checkbox"){
                        for(j in 0 until it[i].options.size){
                            val cb_option=CheckBox(this)
                            cb_option.text=it[i].options[j]
                            cb_option.textSize=20F
                            cb_option.setTextColor(Color.BLACK)
                            cb_option.typeface=Typeface.createFromAsset(assets,"res/font/hind_regular.ttf")
                            cb_option.setPadding(0,5,0,0)

                            ll_container!!.addView(cb_option)
                        }
                    }
                    else if(it[i].type=="Radio"){
                        val rb_option= arrayOfNulls<RadioButton>(it[i].options.size)
                        val rg_option = RadioGroup(this)
                        rg_option.orientation = RadioGroup.VERTICAL
                        for(j in 0 until it[i].options.size){
                            rb_option[j] = RadioButton(this)
                            rb_option[j]!!.text = it[i].options[j]
                            rb_option[j]!!.textSize = 20F
                            rb_option[j]!!.id = j
                            rg_option.addView(rb_option[j])
                        }
                        ll_container!!.addView(rg_option)
                    }
                    else if(it[i].type=="Dropdown"){
                        val rl_ans=RelativeLayout(this)
                        val lp=RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,50)
                        rl_ans.background=getDrawable(R.drawable.dropdown_bg)

                        ll_container!!.addView(rl_ans,lp)


                        val iv_dropdown=ImageView(this)
                        val lp_1=RelativeLayout.LayoutParams(16,8)
                        lp_1.addRule(RelativeLayout.CENTER_VERTICAL)
                        lp_1.addRule(RelativeLayout.ALIGN_PARENT_END)

                        iv_dropdown.id=i
                        iv_dropdown.layoutParams=lp_1
                        iv_dropdown.setImageDrawable(getDrawable(R.drawable.iv_dropdown))
                        iv_dropdown.setPadding(0,0,15,0)

                        rl_ans.addView(iv_dropdown)


                        val tv_ans=TextView(this)
                        val lp_2=RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.FILL_PARENT)
                        lp_2.addRule(RelativeLayout.CENTER_VERTICAL)
                        lp_2.addRule(RelativeLayout.LEFT_OF,i)

                        tv_ans.layoutParams=lp_2
                        tv_ans.setPadding(15,0,5,0)
                        tv_ans.textSize=15F
                        tv_ans.setTextColor(Color.BLACK)
                        tv_ans.typeface=Typeface.createFromAsset(assets,"res/font/hind_regular.ttf")

                        rl_ans.addView(tv_ans)
                    }

                }
            }
            else{
                ll_container!!.visibility= View.GONE
                progressBar!!.visibility= View.VISIBLE
            }
        }

        btn_submit!!.setOnClickListener{
        }

        tv_cancel!!.setOnClickListener{
            startActivity(Intent(this@CasualSurveyActivity,DashboardActivity::class.java))
        }

        iv_back!!.setOnClickListener{
            startActivity(Intent(this@CasualSurveyActivity,SurveyListingActivity::class.java))
            finishAffinity()
        }
        
    }

    private fun isConnectedToInternet(login: CasualSurveyActivity): Boolean {
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
        startActivity(Intent(this@CasualSurveyActivity,SurveyListingActivity::class.java))
        finishAffinity()
    }

    companion object{
        var tv_username: TextView?=null
        var iv_back: ImageView?=null
        var tv_casual_survey_title: TextView?=null
        var progressBar: RelativeLayout?=null
        var survey_id:String?=null
        var ll_container:LinearLayout?=null
        var tv_cancel: TextView?=null
        var btn_submit: RelativeLayout?=null
    }
}