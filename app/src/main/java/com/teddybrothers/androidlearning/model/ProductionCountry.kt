package com.teddybrothers.androidlearning.model

import com.google.gson.annotations.SerializedName


class ProductionCountry {
    @SerializedName("iso_3166_1")
    var iso31661: String = ""

    @SerializedName("name")
    var name: String = ""
}