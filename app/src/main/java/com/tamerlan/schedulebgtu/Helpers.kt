package com.tamerlan.schedulebgtu

import java.util.*


class Helpers {

    fun getCurrentStudyPeriod(): String {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        return if (Calendar.getInstance().get(Calendar.MONTH) < 8)
            "${currentYear - 1}-${currentYear}_2_1"
        else
            "${currentYear}-${currentYear + 1}_1_1"
    }

    fun getGroupNumberOfCourse(course:Int): String {
        var currentYear = Calendar.getInstance().get(Calendar.YEAR)

        currentYear =
            if (Calendar.getInstance().get(Calendar.MONTH) < 8)
                currentYear
            else
                ++currentYear

        println(currentYear)

        return (currentYear- course).toString().substring(2)
    }
}