package com.example.studentmanager_excercise

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentListView: ListView
    private var studentList: MutableList<Student> = mutableListOf()

    companion object {
        private const val ADD_STUDENT_REQUEST = 1
        private const val EDIT_STUDENT_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentListView = findViewById(R.id.studentListView)

        // Sử dụng adapter tùy chỉnh
        val adapter = StudentAdapter(this, R.layout.student_item, studentList)
        studentListView.adapter = adapter

        // Đăng ký context menu cho ListView
        registerForContextMenu(studentListView)

        // Kiểm tra và hiển thị danh sách sinh viên
        loadStudentData(adapter)
    }

    private fun loadStudentData(adapter: StudentAdapter) {
        // Thêm dữ liệu mẫu vào danh sách
        studentList.addAll(getStudentData())
        adapter.notifyDataSetChanged()
    }

    private fun getStudentData(): List<Student> {
        return listOf(
            Student("Nguyen Hoang Duc Trung", "20215491"),
            Student("Tran Van Nam", "20210504"),
            Student("Le Van Dau Tu", "20215425"),
            Student("Pham Minh Tu", "20210457"),
            Student("Nguyen Thi Mai", "20212561"),
            Student("Tran Quoc Khanh", "20210243"),
            Student("Le Thi Hong", "20216325"),
            Student("Vu Minh Tu", "20211234"),
            Student("Nguyen Thi Lan", "20212150"),
            Student("Pham Hong Son", "20211839"),
            Student("Tran Thi Lan", "20212614"),
            Student("Nguyen Thi Lan", "20211231"),
            Student("Le Minh Hoang", "20210976"),
            Student("Nguyen Hoang Minh", "20210892"),
            Student("Tran Thi Thu", "20215482"),
            Student("Le Thi Mai", "20211120"),
            Student("Nguyen Minh Tu", "20210236"),
            Student("Tran Minh Duc", "20211623"),
            Student("Le Thi Thuong", "20210395"),
            Student("Nguyen Thi Bich", "20211784"),
            Student("Pham Minh Thanh", "20210918"),
            Student("Tran Thi Hoa", "20210743"),
            Student("Le Minh Anh", "20211579"),
            Student("Nguyen Hoang Bao", "20211064"),
            Student("Pham Thi Linh", "20211345")
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, ADD_STUDENT_REQUEST)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_STUDENT_REQUEST) {
                val newStudent = data?.getParcelableExtra<Student>("newStudent")
                newStudent?.let {
                    studentList.add(it)
                    val adapter = studentListView.adapter as StudentAdapter
                    adapter.notifyDataSetChanged()
                }
            }
            if (requestCode == EDIT_STUDENT_REQUEST) {
                val editedStudent = data?.getParcelableExtra<Student>("editedStudent")
                editedStudent?.let {
                    val index = studentList.indexOfFirst { it.studentId == editedStudent.studentId }
                    if (index != -1) {
                        studentList[index] = it
                        val adapter = studentListView.adapter as StudentAdapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    // Context menu - Hiển thị khi nhấn lâu vào một sinh viên
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // Xử lý khi người dùng chọn một mục trong context menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedStudent = studentList[info.position]

        return when (item.itemId) {
            R.id.edit -> {
                // Mở Activity để sửa thông tin sinh viên
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("student", selectedStudent)
                startActivityForResult(intent, EDIT_STUDENT_REQUEST)
                true
            }
            R.id.remove -> {
                // Xóa sinh viên khỏi danh sách
                studentList.removeAt(info.position)
                val adapter = studentListView.adapter as StudentAdapter
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
