package kr.dongpae.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.repository.FirebaseDataRepository

class GetStudyRoomDataUseCaseImpl(private val firebaseDataRepository: FirebaseDataRepository): GetStudyRoomDataUseCase {

    @ExperimentalCoroutinesApi
    override fun invoke(): Flow<DeskData> = firebaseDataRepository.loadDeskData()
}