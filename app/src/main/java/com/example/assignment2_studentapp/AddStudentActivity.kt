package com.example.assignment2_studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.activity_add_student_save_button)
        val cancelButton: Button = findViewById(R.id.activity_add_student_cancel_button)

        val nameEditText: EditText = findViewById(R.id.activity_add_student_name_edit_text)
        val idEditText: EditText = findViewById(R.id.activity_add_student_id_edit_text)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            println("${nameEditText.text} Hello ${idEditText.text}")
        }

    }
}