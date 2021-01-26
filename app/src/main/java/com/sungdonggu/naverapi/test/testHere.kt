package com.sungdonggu.naverapi.test

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    val dateList = ArrayList<String>()
    var onlyDate: LocalDate = LocalDate.now()
    var realDate = onlyDate.toString().replace("-", "")

    var fakeDate = LocalDate.of(2021, 1, 22)
    var fromDate = fakeDate.toString().replace("-", "")

    while (fakeDate != onlyDate) {
        var realDate = fakeDate.toString().replace("-", "")
        dateList.add(realDate)
        fakeDate = fakeDate.plusDays(1)
    }
    dateList.add(realDate)
    println(dateList)
}