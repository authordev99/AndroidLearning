package com.teddybrothers.androidlearning.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Genre  : Serializable{
    @SerializedName("id")
    val id : Int = 0
    @SerializedName("name")
    val name : String = ""

}