package com.example.assignment2_studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_studentapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val position = intent.getIntExtra("position", 0)
        val student = Model.shared.students[position]

        val editButton: Button = findViewById(R.id.activity_student_details_edit_button)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }


        val nameView: TextView = findViewById(R.id.activity_students_details_name_value)
        nameView.text = student.name

        val idView: TextView = findViewById(R.id.activity_students_details_id_value)
        idView.text = student.id

        val checkedView: CheckBox = findViewById(R.id.activity_students_details_checked)
        checkedView.isChecked = student.isChecked
    }
}

