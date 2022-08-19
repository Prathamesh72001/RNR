package com.example.rnr.view.adapters

import android.animation.LayoutTransition
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.rnr.R
import com.example.rnr.model.dataclasses.employeesurveylist.EmployeeSurvey
import com.example.rnr.model.dataclasses.surveylist.orgsurveylist.Employee


class EmployeeSurveyAdapter() :
    RecyclerView.Adapter<EmployeeSurveyAdapter.customViewHolder>() {

    lateinit var context: Context
    lateinit var project: String
    lateinit var employee_list: ArrayList<Employee>
    constructor(context: Context,project:String,employee_list:ArrayList<Employee>):this(){
        this.context=context
        this.project=project
        this.employee_list=employee_list
    }

    inner class customViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_employee_name:TextView=itemView.findViewById(R.id.tv_employee_name)
        val tv_project_name:TextView=itemView.findViewById(R.id.tv_project_name)
        val iv_dropdown_card:ImageView=itemView.findViewById(R.id.iv_dropdown_card)
        val iv_dropup_card:ImageView=itemView.findViewById(R.id.iv_dropup_card)
        val cv_employee_ratings:CardView=itemView.findViewById(R.id.cv_employee_ratings)
        val cv_employee_details:CardView=itemView.findViewById(R.id.cv_employee_details)
        val ll_emplyee_ratings:LinearLayout=itemView.findViewById(R.id.ll_emplyee_ratings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_employee_survey_item, parent, false)

        return customViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: customViewHolder, position: Int) {

        //hooks
        val obj=employee_list[position]

        //setters
        holder.tv_employee_name.text=obj.fullName
        holder.tv_project_name.text="Project: "+project

        holder.cv_employee_ratings.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        //holder.tv_provide_more_feedback_title.paintFlags=holder.tv_provide_more_feedback_title.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        //kpis

        for(i in 0 until obj.kpi.size){
            val tv_rating=TextView(context)
            tv_rating.text=obj.kpi[i].category
            tv_rating.id=i
            tv_rating.textSize=20F
            tv_rating.setTextColor(Color.BLACK)
            tv_rating.typeface=Typeface.createFromAsset(context.assets,"res/font/hind_bold.ttf")
            tv_rating.setPadding(0,50,0,0)

            holder.ll_emplyee_ratings.addView(tv_rating)

            val rb_rating=RatingBar(context)
            rb_rating.numStars=5
            rb_rating.stepSize=3F

            holder.ll_emplyee_ratings.addView(rb_rating)
        }

        val tv_provide_more_feedback_title=TextView(context)
        tv_provide_more_feedback_title.text=context.getString(R.string.string_provide_more_feedback_title)
        tv_provide_more_feedback_title.textSize=20F
        tv_provide_more_feedback_title.setTextColor(Color.BLACK)
        tv_provide_more_feedback_title.typeface=Typeface.createFromAsset(context.assets,"res/font/hind_bold.ttf")
        tv_provide_more_feedback_title.setPadding(0,50,0,50)
        tv_provide_more_feedback_title.paintFlags=Paint.UNDERLINE_TEXT_FLAG

        holder.ll_emplyee_ratings.addView(tv_provide_more_feedback_title)

        //listeners
        tv_provide_more_feedback_title.setOnClickListener{
            showFeedBackDialogue(obj.fullName)
        }

        holder.cv_employee_details.setOnClickListener {
            if(holder.iv_dropdown_card.visibility==View.VISIBLE)
            {
                holder.iv_dropdown_card.visibility=View.GONE
                holder.iv_dropup_card.visibility=View.VISIBLE
                holder.ll_emplyee_ratings.visibility=View.VISIBLE
            }
            else if(holder.iv_dropup_card.visibility==View.VISIBLE)
            {
                holder.iv_dropup_card.visibility=View.GONE
                holder.iv_dropdown_card.visibility=View.VISIBLE
                holder.ll_emplyee_ratings.visibility=View.GONE
            }
        }

        val v = if (holder.ll_emplyee_ratings.visibility == View.GONE) View.GONE else View.VISIBLE

        TransitionManager.beginDelayedTransition(holder.cv_employee_ratings, AutoTransition())
        holder.ll_emplyee_ratings.visibility = v
    }

    private fun showFeedBackDialogue(fullName: String) {
        val dialog= Dialog(context)
        dialog.setContentView(R.layout.dialogue_box_provide_feedback)
        dialog.window!!.setBackgroundDrawable(context.getDrawable(R.drawable.dialogue_bg))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.show()
        val tv_provide_additional_feedback_for_employee=dialog.findViewById<TextView>(R.id.tv_provide_additional_feedback_for_employee)
        tv_provide_additional_feedback_for_employee.text=
            "Provide additional feedback for $fullName"

        val btn_submit=dialog.findViewById<RelativeLayout>(R.id.btn_submit)
        btn_submit.setOnClickListener{
            dialog.dismiss()
            showFeedBackSubmittedDialogue()
        }

        val tv_cancel=dialog.findViewById<TextView>(R.id.tv_cancel)
        tv_cancel.setOnClickListener{
            dialog.dismiss()
        }
    }

    private fun showFeedBackSubmittedDialogue() {
        val dialog= Dialog(context)
        dialog.setContentView(R.layout.dialogue_box_feedback_submitted)
        dialog.window!!.setBackgroundDrawable(context.getDrawable(R.drawable.dialogue_bg))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.show()
        val btn_done=dialog.findViewById<RelativeLayout>(R.id.btn_done)
        btn_done.setOnClickListener{
            dialog.dismiss()
        }
    }

    override fun getItemCount(): Int {
        return employee_list.size
    }
}