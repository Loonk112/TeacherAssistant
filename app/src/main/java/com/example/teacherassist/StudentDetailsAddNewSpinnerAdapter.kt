package com.example.teacherassist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentDetailsAddNewSpinnerAdapter(ctx: Context, courseList : ArrayList<CoursesData>) : ArrayAdapter<CoursesData>(ctx, 0, courseList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.student_details_spinner_item_template, parent, false)

        list?.let {
            val idTV = view.findViewById<TextView>(R.id.studentDetailsSpinnerTemplateId)
            val yearTV = view.findViewById<TextView>(R.id.studentDetailsSpinnerTemplateYear)
            val nameTV = view.findViewById<TextView>(R.id.studentDetailsSpinnerTemplateName)

            idTV.text = list.id.toString()
            yearTV.text = list.year.toString()
            nameTV.text = list.name
        }

        return view
    }
}