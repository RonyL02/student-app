package com.col.studentapp.model

import android.provider.ContactsContract.CommonDataKinds.Phone

data class Student(
    var id: String,
    var name: String,
    var phone: String,
    var address: String,
    val avatarUrl: String,
    var isChecked: Boolean,

)
