package com.loki.okoaloan.domain.use_cases

import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.UserRepository
import com.loki.okoaloan.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RegisterUser(
    private val repository: UserRepository
) {

     operator fun invoke(user: User) = flow<Resource<String>> {

         try {
             emit(Resource.Loading<String>())

             delay(3000L)
             emit(Resource.Success<String>(data = repository.insertUser(user), message = null))
         }
         catch (e: HttpException) {
             emit(Resource.Error<String>(e.localizedMessage ?: "An unexpected error occurred", data = null))
         }
         catch (e: IOException) {
             emit(Resource.Error<String>("check your internet connection", data = null))
         }
    }
}