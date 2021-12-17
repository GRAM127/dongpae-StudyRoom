package kr.dongpae.data.datasource

import com.google.firebase.database.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseDataSourceImpl: FirebaseDataSource {

    private var callback: ChildEventListener? = null

    @ExperimentalCoroutinesApi
    override fun loadFirebaseData(reference: DatabaseReference): Flow<Map<String?, Any?>> = callbackFlow {
        val map = mutableMapOf<String?, Any?>()
        callback = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                map[snapshot.key] = snapshot.value
                trySend(map)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                map[snapshot.key] = snapshot.value
                trySend(map)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                map.remove(snapshot.key)
                trySend(map)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                map[snapshot.key] = snapshot.value
                trySend(map)
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException()
            }

        }
        reference.addChildEventListener(callback!!)
        awaitClose {
            callback = null
        }
    }
}