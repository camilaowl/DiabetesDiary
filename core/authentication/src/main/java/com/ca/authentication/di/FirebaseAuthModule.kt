package com.ca.authentication.di

import com.ca.authentication.FirebaseAuthProvider
import com.ca.authentication.token.JWTService
import com.ca.authentication.token.JWTServiceImpl
import com.ca.datastore.UserDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal class FirebaseAuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthProvider(firebaseAuth: FirebaseAuth): FirebaseAuthProvider {
        return FirebaseAuthProvider(firebaseAuth)
    }

    @Provides
    fun provideJWTService(userDataStore: UserDataStore): JWTService = JWTServiceImpl(userDataStore)
}