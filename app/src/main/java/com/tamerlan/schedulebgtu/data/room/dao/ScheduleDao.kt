package com.tamerlan.schedulebgtu.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tamerlan.schedulebgtu.data.Lesson

interface ScheduleDao {
    @Insert
    fun insertAll(vararg lesson: Lesson)

    @Delete
    fun delete(lesson: Lesson)


    @Query("SELECT * FROM lesson_table")
    fun getAllLessons(): List<Lesson?>?

    @Query("SELECT * FROM lesson_table WHERE dayOfWeek LIKE :dayOfWeek")
    fun getAllLessonWithDayOfWeek(dayOfWeek: String?): List<Lesson>?
}