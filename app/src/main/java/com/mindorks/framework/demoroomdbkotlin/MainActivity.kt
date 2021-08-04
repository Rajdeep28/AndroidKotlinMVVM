package com.mindorks.framework.demoroomdbkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.demoroomdbkotlin.adapter.StudentRecyclerViewAdapter
import com.mindorks.framework.demoroomdbkotlin.databinding.ActivityMainBinding
import com.mindorks.framework.demoroomdbkotlin.db.Student
import com.mindorks.framework.demoroomdbkotlin.db.StudentDao
import com.mindorks.framework.demoroomdbkotlin.repository.StudentRepository
import com.mindorks.framework.demoroomdbkotlin.viewmodel.StudentViewModel
import com.mindorks.framework.demoroomdbkotlin.viewmodel.StudentViewModelFoctory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var studenViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        var dao: StudentDao = StudentDatabase.getInstance(application).studentDao
        var repository = StudentRepository(dao)
        var factory = StudentViewModelFoctory(repository)
        studenViewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)
        binding.myViewModel = studenViewModel
        binding.lifecycleOwner = this

        displayStudentList()

        showDetailsRecyclerView()

    }

    private fun showDetailsRecyclerView() {
        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        displayStudentList()
    }

    private fun displayStudentList(){
        studenViewModel.students.observe(this, Observer {
            Log.i("MyTAG", it.toString())
            binding.studentRecyclerView.adapter = StudentRecyclerViewAdapter(it,{selectedItem: Student->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(student: Student){
        Toast.makeText(this,"Selected name is:${student.name}",Toast.LENGTH_SHORT).show()
        studenViewModel.initUpdateAndDelete(student)
    }
}