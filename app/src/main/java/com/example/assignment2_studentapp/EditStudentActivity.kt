package com.example.assignment2_studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentapp.model.Model
import com.example.assignment2_studentapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val nameInput: EditText = findViewById(R.id.activity_edit_student_name_edit_text)
        val idInput: EditText = findViewById(R.id.activity_edit_student_id_edit_text)
        val saveButton: Button = findViewById(R.id.activity_edit_student_save_button)
        val cancelButton: Button = findViewById(R.id.activity_edit_student_cancel_button)
        val deleteButton: Button = findViewById(R.id.activity_edit_student_delete_button)

        position = intent.getIntExtra("position", -1)
        if (position >= 0) {
            val student = Model.shared.students[position]
            nameInput.setText(student.name)
            idInput.setText(student.id)
        } else {
            Toast.makeText(this, "Invalid student position", Toast.LENGTH_SHORT).show()
            finish()
        }

        saveButton.setOnClickListener {
            val updatedName = nameInput.text.toString()
            val updatedId = idInput.text.toString()

            if (updatedName.isBlank() || updatedId.isBlank()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val previousStudent = Model.shared.students[position]
            val updatedStudent = Student(updatedName, updatedId, previousStudent.avatarUrl, previousStudent.isChecked)
            Model.shared.students[position] = updatedStudent

            Toast.makeText(this, "Student updated successfully!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)

        }

        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            Model.shared.students.removeAt(position)
            Toast.makeText(this, "Student deleted successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}