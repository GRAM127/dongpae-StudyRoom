package kr.dongpae.domain.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kr.dongpae.domain.model.DeskData

interface FirebaseDatabaseRepository {

    @ExperimentalCoroutinesApi
    fun loadDeskData(): Flow<DeskData>
}