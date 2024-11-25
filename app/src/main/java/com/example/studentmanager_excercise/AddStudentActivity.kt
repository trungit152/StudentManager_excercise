package com.example.studentmanager_excercise

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        nameEditText = findViewById(R.id.nameEditText)
        studentIdEditText = findViewById(R.id.studentIdEditText)
    }

    fun onAddStudentClick(view: View) {
        val name = nameEditText.text.toString()
        val studentId = studentIdEditText.text.toString()

        if (name.isNotEmpty() && studentId.isNotEmpty()) {
            val student = Student(name, studentId)
            val resultIntent = Intent()
            resultIntent.putExtra("newStudent", student)
            setResult(RESULT_OK, resultIntent)
            finish()  // Quay lại MainActivity với kết quả
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
