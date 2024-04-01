package com.ubayadev.studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String

    /*var id:String?,
    var name:String?,
    var dob:String?,
    var phone:String?,
    var photoUrl:String? */
)

data class Car(
    @SerializedName("brand")
    val brand:String?,
    @SerializedName("model")
    val model:String?,
    @SerializedName("year")
    val year:String?,
    @SerializedName("images")
    val image:String?,
    val features:CarFeatures?
)

data class CarFeatures(
    @SerializedName("engine") val engine:String?,
    @SerializedName("transmission") val transmission:String?,
    @SerializedName("fuelType") val fuelType:String?
)