package kr.dongpae.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.repository.FirebaseAuthRepository
import kr.dongpae.domain.repository.FirebaseDatabaseRepository

class GetStudyRoomDataUseCaseImpl(private val authRepository: FirebaseAuthRepository, private val databaseRepository: FirebaseDatabaseRepository): GetStudyRoomDataUseCase {

    @ExperimentalCoroutinesApi
    override fun invoke(): Flow<DeskData> = authRepository.signInAnonymously().flatMapLatest {
        if (it) databaseRepository.loadDeskData()
        else throw Exception("LOGIN ERROR")
    }
}