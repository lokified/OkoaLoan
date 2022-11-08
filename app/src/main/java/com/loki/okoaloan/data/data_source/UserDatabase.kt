package com.loki.okoaloan.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loki.okoaloan.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}