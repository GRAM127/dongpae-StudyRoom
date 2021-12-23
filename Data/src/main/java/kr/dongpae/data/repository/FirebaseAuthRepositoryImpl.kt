package kr.dongpae.data.repository

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kr.dongpae.domain.repository.FirebaseAuthRepository

class FirebaseAuthRepositoryImpl private constructor(application: Application): FirebaseAuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private var authStateCallback: FirebaseAuth.AuthStateListener? = null

    private var signInCompleteCallback: OnCompleteListener<AuthResult>? = null
    private var signInFailureCallback: OnFailureListener? = null

    @ExperimentalCoroutinesApi
    override val auth = callbackFlow {
        authStateCallback = FirebaseAuth.AuthStateListener {
            trySend(it)
        }
        awaitClose {
            authStateCallback = null
        }
    }

    @ExperimentalCoroutinesApi
    override fun signInAnonymously() = callbackFlow {
        signInCompleteCallback = OnCompleteListener<AuthResult> {
            trySend(true)
        }
        signInFailureCallback = OnFailureListener {
            trySend(false)
        }
        firebaseAuth.signInAnonymously().addOnCompleteListener(signInCompleteCallback!!).addOnFailureListener(signInFailureCallback!!)
        awaitClose {
            signInCompleteCallback = null
            signInFailureCallback = null
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }


    companion object {
        private var instance: FirebaseAuthRepositoryImpl? = null

        fun initialize(application: Application) {
            if (instance == null) instance = FirebaseAuthRepositoryImpl(application)
        }
        fun get() = instance ?: throw IllegalStateException("FirebaseAuthRepository must be initialized")

        private const val DEV = "dev"
    }

}