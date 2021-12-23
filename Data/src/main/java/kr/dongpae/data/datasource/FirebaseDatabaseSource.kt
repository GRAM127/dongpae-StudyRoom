package kr.dongpae.data.datasource

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseSource {

    @ExperimentalCoroutinesApi
    fun loadFirebaseData(reference: DatabaseReference): Flow<Any?>
}