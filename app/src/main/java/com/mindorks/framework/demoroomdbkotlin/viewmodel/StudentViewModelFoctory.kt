package com.mindorks.framework.demoroomdbkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.framework.demoroomdbkotlin.repository.StudentRepository
import java.lang.IllegalArgumentException

class StudentViewModelFoctory(var repository: StudentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}