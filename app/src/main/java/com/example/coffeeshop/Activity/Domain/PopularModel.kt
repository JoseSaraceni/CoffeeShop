package com.example.coffeeshop.Activity.Domain

import android.accessibilityservice.GestureDescription
import java.io.Serializable

data class PopularModel (
    var title : String="",
    var description : String="",
    var picUrl : ArrayList<String> = ArrayList(),
    var price: Double=0.0,
    var rating: Double=0.0,
    var numberInCart: Int=0,
    var extra: String=""
): Serializable
