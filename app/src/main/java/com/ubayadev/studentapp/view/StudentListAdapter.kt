package com.ubayadev.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubayadev.studentapp.R
import com.ubayadev.studentapp.databinding.StudentListItemBinding
import com.ubayadev.studentapp.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
    , ButtonDetailClickListener
{
    class StudentViewHolder(var view: StudentListItemBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,
            R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this

    //        holder.view.txtID.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imgStudent, object:
//            Callback {
//            override fun onSuccess() {
//                holder.view.progressImage.visibility = View.INVISIBLE
//                holder.view.imgStudent.visibility = View.VISIBLE
//            }
//            override fun onError(e: Exception?) {
//                Log.e("picasso_error", e.toString())
//            }
//        })
//
//        holder.view.btnDetail.setOnClickListener {
//            val studentId = studentList[position].id!!.toString()!!
//            val action = StudentListFragmentDirections.actionStudentDetail(studentId)
//            Navigation.findNavController(it).navigate(action)
//        }

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}
