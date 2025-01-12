package com.col.studentapp.model

data class Student(
    var id: Int,
    var name: String,
    var phone: String,
    var address: String,
    val avatarUrl: String,
    var isChecked: Boolean
)
