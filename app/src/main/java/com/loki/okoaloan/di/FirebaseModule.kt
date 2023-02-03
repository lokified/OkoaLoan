package com.loki.okoaloan.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.loki.okoaloan.data.repository.FirebaseAccountRepositoryImpl
import com.loki.okoaloan.domain.repository.FirebaseAccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuthentication(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAccountRepository(auth: FirebaseAuth): FirebaseAccountRepository {
        return FirebaseAccountRepositoryImpl(auth)
    }
}