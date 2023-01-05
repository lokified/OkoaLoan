package com.loki.okoaloan.domain.use_cases

import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.UserRepository
import com.loki.okoaloan.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginUser(
    private val repository: UserRepository
) {

    operator fun invoke(phoneNumber: String, password: String) = flow<Resource<User>> {

        try {
            emit(Resource.Loading<User>())

            val user = repository.getUser(phoneNumber)
            if (user != null) {
                if (user.phoneNumber != phoneNumber){
                    emit(Resource.Error<User>("Wrong phone number"))
                }
                else if(user.password != password) {
                    emit(Resource.Error<User>("Wrong password"))
                }

                else {
                    delay(3000L)
                    emit(Resource.Success<User>(data = user, message = "Login Success"))
                }
            }
            else {
                emit(Resource.Error<User>("User does not exist"))
            }
        }
        catch (e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occurred", data = null))
        }
        catch (e: IOException) {
            emit(Resource.Error<User>("check your internet connection", data = null))
        }
    }
}