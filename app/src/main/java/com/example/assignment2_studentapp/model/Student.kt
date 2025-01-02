package com.example.assignment2_studentapp.model

import android.os.Parcelable
import java.io.Serializable

data class Student(
    val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean
)