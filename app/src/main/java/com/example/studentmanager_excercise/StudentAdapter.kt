package com.example.studentmanager_excercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(context: Context, private val resource: Int, private val students: List<Student>) : ArrayAdapter<Student>(context, resource, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val student = getItem(position)
        val studentNameTextView: TextView = view.findViewById(R.id.studentNameTextView)

        student?.let {
            studentNameTextView.text = "${it.name} - ${it.studentId}"
        }

        return view
    }
}
