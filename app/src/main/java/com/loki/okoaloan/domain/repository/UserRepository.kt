package com.loki.okoaloan.domain.repository

import com.loki.okoaloan.domain.model.User

interface UserRepository {

    suspend fun insertUser(user: User)
    suspend fun getUser(phoneNumber: String): User
}