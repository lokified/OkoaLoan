package com.loki.okoaloan.di

import android.app.Application
import androidx.room.Room
import com.loki.okoaloan.data.UserRepositoryImpl
import com.loki.okoaloan.data.data_source.UserDatabase
import com.loki.okoaloan.domain.repository.UserRepository
import com.loki.okoaloan.domain.use_cases.LoginUser
import com.loki.okoaloan.domain.use_cases.RegisterUser
import com.loki.okoaloan.domain.use_cases.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: UserDatabase): UserRepository {
        return UserRepositoryImpl(database.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCase {
        return UserUseCase(
            loginUser = LoginUser(repository),
            registerUser = RegisterUser(repository)
        )
    }
}