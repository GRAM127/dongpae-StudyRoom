package kr.dongpae.data.repository

import android.app.Application
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformLatest
import kr.dongpae.data.datasource.FirebaseDataSourceImpl
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.repository.FirebaseDataRepository
import org.json.JSONObject

class FirebaseDataRepositoryImpl private constructor(application: Application): FirebaseDataRepository {

    private val firebaseDataSources =  FirebaseDataSourceImpl()

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
        private var instance: FirebaseDataRepositoryImpl? = null

        fun initialize(application: Application) {
            if (instance == null) instance = FirebaseDataRepositoryImpl(application)
        }
        fun get() = instance ?: throw IllegalStateException("FirebaseDataRepository must be initialized")

        private const val DEV = "dev"
    }

}