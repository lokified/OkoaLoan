package com.loki.okoaloan.domain.use_cases

import com.loki.okoaloan.domain.model.UserException
import com.loki.okoaloan.domain.repository.UserRepository

class LoginUser(
    private val repository: UserRepository
) {

    @Throws(UserException::class)
    suspend operator fun invoke(phoneNumber: String, password: String) {

        val user = repository.getUser(phoneNumber)

        if (user.phoneNumber != phoneNumber){
            throw UserException("Wrong phone number")
        }
        else if(user.password != password) {
            throw UserException("Wrong password")
        }
        else {
            throw UserException("login success")
        }
    }
}