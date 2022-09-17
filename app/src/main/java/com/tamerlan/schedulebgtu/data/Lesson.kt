package com.tamerlan.schedulebgtu.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lesson_table")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var time:String,
    var name:String,
    var position: String,
    var sequence:Sequence,
    var teacher: String,
    var dayOfWeek: String
)

enum class Sequence{
    even, //четный
    odd,
    all
}