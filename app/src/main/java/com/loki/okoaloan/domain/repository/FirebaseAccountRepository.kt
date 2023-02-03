package com.loki.okoaloan.domain.repository

import com.loki.okoaloan.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseAccountRepository {

    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun createAccount(email: String, password: String)
    suspend fun createAnonymousAccount()
    suspend fun sendRecoveryEmail(email: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}