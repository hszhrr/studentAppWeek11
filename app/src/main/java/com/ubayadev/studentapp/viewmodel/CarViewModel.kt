package com.ubayadev.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.studentapp.model.Car

class CarViewModel(application: Application): AndroidViewModel(application) {
    val carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        carLoadErrorLD.value = false
        loadingLD.value = false

        Log.d("CEKMASUK", "masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/cars/cars.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Car>>() { }.type
                val result = Gson().fromJson<List<Car>>(it, sType)
                carsLD.value = result as ArrayList<Car>?
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showvolley", it.toString())
                carLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}