package com.wesleyerick.gitcoffe.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("SimpleDateFormat")
fun String.toDateFormatter(): String {
    // Check if the device is running API 24 or above
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Use the new API to parse and format the date
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formattedDate = LocalDateTime.parse(this, dateFormatter)
        return DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mma").format(formattedDate)
    } else {
        // Use the SimpleDateFormat class to parse and format the date
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(this)
        val formattedDate = SimpleDateFormat("MMMM dd, yyyy | hh:mma").format(date)
        return formattedDate
    }
}