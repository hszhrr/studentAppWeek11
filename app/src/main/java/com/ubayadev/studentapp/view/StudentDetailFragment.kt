package com.ubayadev.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubayadev.studentapp.R
import com.ubayadev.studentapp.databinding.FragmentStudentDetailBinding
import com.ubayadev.studentapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(studentId)

        observeViewModel()
    }

    fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtStudentid.setText(it.id)
            binding.txtStudentName.setText(it.name)
            binding.txtDob.setText(it.bod)
            binding.txtPhone.setText(it.phone)
            Picasso.get().load(it.photoUrl).into(binding.imgStudent)
            var student = it

            binding.btnUpdate?.setOnClickListener {Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "Five seconds")
                    MainActivity.showNotif(student.name.toString(), "A new notification created", R.drawable.baseline_person_24)
                }
            }
        })
    }

}