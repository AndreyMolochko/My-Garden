package com.ostrovec.mygarden.utils

class CalendarWorker {
    companion object {
        fun convertDaysToMilliseconds(days: Int): Long {
            return days * 1000L * 60 * 60 * 24
        }

        fun convertMillisecondsInDays(milliseconds: Long): Int {
            return (milliseconds / 1000 / 60 / 60 / 24).toInt()
        }
    }
}