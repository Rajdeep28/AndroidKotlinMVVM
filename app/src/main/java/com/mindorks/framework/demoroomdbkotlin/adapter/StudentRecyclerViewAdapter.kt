package com.mindorks.framework.demoroomdbkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.demoroomdbkotlin.R
import com.mindorks.framework.demoroomdbkotlin.databinding.StudentDetailsListItemsBinding
import com.mindorks.framework.demoroomdbkotlin.db.Student

class StudentRecyclerViewAdapter(private var studentList: List<Student>,
                                 private var clickListener:(Student)->Unit):
        RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var layoutInflatter = LayoutInflater.from(parent.context)
        var binding: StudentDetailsListItemsBinding =
                DataBindingUtil.inflate(layoutInflatter, R.layout.student_details_list_items,parent,false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

}

class StudentViewHolder(var binding: StudentDetailsListItemsBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(student: Student, clickListener:(Student)->Unit){
        binding.nameTextList.text = student.name
        binding.emailTextList.text = student.email
        binding.listItemLayout.setOnClickListener {
            clickListener(student)
        }
    }
}