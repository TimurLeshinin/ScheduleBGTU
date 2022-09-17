package com.tamerlan.schedulebgtu.data.room.dao

import androidx.room.Entity

@Entity(tableName = "user_table")
data class User (
    var groupe:String
)