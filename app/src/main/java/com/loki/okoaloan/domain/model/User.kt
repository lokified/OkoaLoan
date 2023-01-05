package com.loki.okoaloan.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int? = null,
    val phoneNumber: String,
    val password: String
)