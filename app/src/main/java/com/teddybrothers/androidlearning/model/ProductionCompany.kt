package com.teddybrothers.androidlearning.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ProductionCompany {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("logo_path")
    var logoPath: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("origin_country")
    var originCountry: String = ""
}