package com.loki.okoaloan.data

import com.loki.okoaloan.data.data_source.UserDao
import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository {

    override suspend fun insertUser(user: User) {
       return dao.insertUser(user)
    }

    override suspend fun getUser(phoneNumber: String): User {
        return dao.getUser(phoneNumber)
    }
}