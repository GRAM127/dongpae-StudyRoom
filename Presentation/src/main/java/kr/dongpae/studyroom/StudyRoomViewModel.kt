package kr.dongpae.studyroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kr.dongpae.data.repository.FirebaseAuthRepositoryImpl
import kr.dongpae.data.repository.FirebaseDatabaseRepositoryImpl
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.usecase.GetStudyRoomDataUseCaseImpl

class StudyRoomViewModel(application: Application): AndroidViewModel(application) {

    private val authRepository = FirebaseAuthRepositoryImpl.get()
    private val databaseRepository = FirebaseDatabaseRepositoryImpl.get()

    private val getStudyRoomDataUseCase = GetStudyRoomDataUseCaseImpl(authRepository, databaseRepository)


    @ExperimentalCoroutinesApi
    val deskData: LiveData<DeskData> = getStudyRoomDataUseCase().asLiveData()

}