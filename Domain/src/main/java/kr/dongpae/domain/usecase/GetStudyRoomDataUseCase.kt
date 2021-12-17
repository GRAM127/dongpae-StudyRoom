package kr.dongpae.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kr.dongpae.domain.model.DeskData

interface GetStudyRoomDataUseCase {

    @ExperimentalCoroutinesApi
    operator fun invoke(): Flow<DeskData>
}