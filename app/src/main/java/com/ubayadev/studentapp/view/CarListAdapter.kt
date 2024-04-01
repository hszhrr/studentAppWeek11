package com.ubayadev.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.studentapp.databinding.CarListItemBinding
import com.ubayadev.studentapp.model.Car
import com.ubayadev.studentapp.model.Student

class CarListAdapter (val carList:ArrayList<Car>)
    :RecyclerView.Adapter<CarListAdapter.CarViewHolder>()
{
    class CarViewHolder(var binding: CarListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListAdapter.CarViewHolder {
        val binding = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CarListAdapter.CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarListAdapter.CarViewHolder, position: Int) {
        holder.binding.txtBrand.text = carList[position].brand
        holder.binding.txtModel.text = carList[position].model
        holder.binding.txtYear.text = carList[position].year
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun updateCarList(newCarList: ArrayList<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }
}