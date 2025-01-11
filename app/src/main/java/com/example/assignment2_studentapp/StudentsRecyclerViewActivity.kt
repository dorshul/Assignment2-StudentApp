package com.example.assignment2_studentapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_studentapp.adapter.StudentsRecyclerAdapter
import com.example.assignment2_studentapp.model.Model
import com.example.assignment2_studentapp.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}
class StudentsRecyclerViewActivity : AppCompatActivity() {
    var students: MutableList<Student>? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val addStudentButton: Button = findViewById(R.id.student_details_add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.students_recycler_view)
        recyclerView?.setHasFixedSize(true)



        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(Model.shared.students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
                val intent = Intent(this@StudentsRecyclerViewActivity, StudentDetailsActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")
            }
        }
        recyclerView?.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        recyclerView?.adapter?.notifyDataSetChanged()
    }
}