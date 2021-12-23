package kr.dongpae.domain.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {

    @ExperimentalCoroutinesApi
    val auth: Flow<FirebaseAuth>
    @ExperimentalCoroutinesApi
    fun signInAnonymously(): Flow<Boolean>
    fun signOut()
}