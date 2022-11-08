package com.loki.okoaloan.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loki.okoaloan.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE phoneNumber = :phoneNumber")
    suspend fun getUser(phoneNumber: String): User
}