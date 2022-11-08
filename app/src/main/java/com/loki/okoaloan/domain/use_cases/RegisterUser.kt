package com.loki.okoaloan.domain.use_cases

import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.UserRepository

class RegisterUser(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.insertUser(user)
    }
}