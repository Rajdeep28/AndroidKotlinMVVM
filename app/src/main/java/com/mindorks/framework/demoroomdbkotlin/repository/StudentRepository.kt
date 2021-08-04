package com.mindorks.framework.demoroomdbkotlin.repository

import com.mindorks.framework.demoroomdbkotlin.db.Student
import com.mindorks.framework.demoroomdbkotlin.db.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    val students = studentDao.getAllStudents()

    suspend fun insert(student: Student){
        studentDao.insertStudentDetails(student)
    }
    suspend fun update(student: Student) = studentDao.updateStudentDetails(student)

    suspend fun delete(student: Student) = studentDao.deleteStudentDetails(student)

    suspend fun deleteAll() = studentDao.deleteAll()

}