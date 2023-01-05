package com.loki.okoaloan.data.repository

import com.loki.okoaloan.data.data_source.UserDao
import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository {

    override suspend fun insertUser(user: User) : String {
        dao.insertUser(user)
        return "Registration successfully"
    }

    override suspend fun getUser(phoneNumber: String): User {
        return dao.getUser(phoneNumber)
    }
}