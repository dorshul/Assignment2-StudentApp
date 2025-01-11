package com.example.assignment2_studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentapp.model.Model
import com.example.assignment2_studentapp.model.Student

class AddStudentActivity : AppCompatActivity() {

    private var studentsRecyclerViewActivity = StudentsRecyclerViewActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val nameInput: EditText = findViewById(R.id.activity_add_student_name_edit_text)
        val idInput: EditText = findViewById(R.id.activity_add_student_id_edit_text)
        val saveButton: Button = findViewById(R.id.activity_add_student_save_button)
        val cancelButton: Button = findViewById(R.id.activity_add_student_cancel_button)


        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val id = idInput.text.toString()
            val avatarUrl = "default"

            if (name.isBlank() || id.isBlank()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newStudent = Student(name, id, avatarUrl, isChecked = false)

            if (studentsRecyclerViewActivity.students == null) {
               studentsRecyclerViewActivity.students = mutableListOf()
            }

            Model.shared.students.add(newStudent)


            // Finish the activity and go back
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}