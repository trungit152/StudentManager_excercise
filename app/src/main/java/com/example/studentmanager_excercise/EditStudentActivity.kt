package com.example.studentmanager_excercise

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        nameEditText = findViewById(R.id.nameEditText)
        studentIdEditText = findViewById(R.id.studentIdEditText)

        // Nhận đối tượng student từ Intent
        student = intent.getParcelableExtra("student")!!

        // Điền dữ liệu vào các trường edittext
        nameEditText.setText(student.name)
        studentIdEditText.setText(student.studentId)
    }

    fun onSaveClick(view: View) {
        val newName = nameEditText.text.toString()
        val newStudentId = studentIdEditText.text.toString()

        if (newName.isNotEmpty() && newStudentId.isNotEmpty()) {
            // Tạo đối tượng Student mới với các giá trị đã chỉnh sửa
            val updatedStudent = student.copy(name = newName, studentId = newStudentId)

            // Trả lại kết quả cho MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("editedStudent", updatedStudent)
            setResult(RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
