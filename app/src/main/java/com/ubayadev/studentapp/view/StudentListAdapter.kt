package com.ubayadev.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubayadev.studentapp.databinding.StudentListItemBinding
import com.ubayadev.studentapp.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imgStudent, object:
            Callback {
            override fun onSuccess() {
                holder.binding.progressImage.visibility = View.INVISIBLE
                holder.binding.imgStudent.visibility = View.VISIBLE
            }
            override fun onError(e: Exception?) {
                Log.e("picasso_error", e.toString())
            }
        })

        holder.binding.btnDetail.setOnClickListener {
            val studentId = studentList[position].id!!.toString()!!
            val action = StudentListFragmentDirections.actionStudentDetail(studentId)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
