package kr.dongpae.data.repository

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformLatest
import kr.dongpae.data.datasource.FirebaseDatabaseSourceImpl
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.repository.FirebaseDatabaseRepository
import org.json.JSONObject

class FirebaseDatabaseRepositoryImpl private constructor(application: Application): FirebaseDatabaseRepository {

    private val firebaseDataSources =  FirebaseDatabaseSourceImpl()

    private val database = FirebaseDatabase.getInstance()

    @ExperimentalCoroutinesApi
    override fun loadDeskData(): Flow<DeskData> = firebaseDataSources.loadFirebaseData(database.reference.child(DEV)).transformLatest { data ->
        val list = MutableList(data.size) { false }
        data.forEach { (key, value) ->
            key?.toIntOrNull()?.let {
                list[it] = JSONObject(value.toString()).optBoolean("value", false)
            }
        }
        emit(list)
    }


    companion object {
        private var instance: FirebaseDatabaseRepositoryImpl? = null

        fun initialize(application: Application) {
            if (instance == null) instance = FirebaseDatabaseRepositoryImpl(application)
        }
        fun get() = instance ?: throw IllegalStateException("FirebaseDataRepository must be initialized")

        private const val DEV = "dev"
    }

}