package com.col.studentapp.model


class Model private constructor() {

    private val students = mutableListOf<Student>()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(): List<Student> {
        return students
    }


    fun add(student: Student) {
        students.add(student)
    }

    fun update(student: Student): Boolean {
        val index = students.indexOfFirst { it.id == student.id }
        return if (index != -1) {
            students[index] = student
            true
        } else {
            false
        }
    }

    fun getByIndex(index: Int): Student {
        return students[index]
    }

    fun delete(student: Student) {
        students.removeIf { it.id == student.id }
    }
}


