package com.example.rnr.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rnr.R
import com.example.rnr.model.dataclasses.surveylist.Survey
import com.example.rnr.view.activities.CasualSurveyActivity
import com.example.rnr.view.activities.EmployeeSurveyFormActivity
import java.text.SimpleDateFormat

class SurveyListingAdapter() :
    RecyclerView.Adapter<SurveyListingAdapter.customViewHolder>() {

    lateinit var context: Context
    lateinit var survey_list: ArrayList<Survey>

    constructor(survey_list:ArrayList<Survey>,context: Context):this(){
        this.context=context
        this.survey_list=survey_list
    }

    inner class customViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_survey_name: TextView =itemView.findViewById(R.id.tv_survey_name)
        val tv_survey_date: TextView =itemView.findViewById(R.id.tv_survey_date)
        val btn_view_summary: RelativeLayout =itemView.findViewById(R.id.btn_view_summary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_survey_listing_item, parent, false)

        return customViewHolder(view)
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        //hooks
        val obj=survey_list[position]

        //setters
        val day=obj.startTime.substring(8,10)
        val month=obj.startTime.substring(5,7)
        val year=obj.startTime.substring(0,4)

        holder.tv_survey_date.text= day+"/"+month+"/"+year
        holder.tv_survey_name.text="Survey on "+obj.name

        //listeners
        holder.btn_view_summary.setOnClickListener {
            if(obj.type=="Casual") {
                val intent = Intent(context, CasualSurveyActivity::class.java)
                intent.putExtra("survey_id", obj._id)
                context.startActivity(intent)
            }
            else if(obj.type=="Organization Feedback"){
                val intent = Intent(context, EmployeeSurveyFormActivity::class.java)
                intent.putExtra("survey_id", obj._id)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return survey_list.size
    }
}