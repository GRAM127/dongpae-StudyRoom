package kr.dongpae.studyroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kr.dongpae.data.repository.FirebaseDataRepositoryImpl
import kr.dongpae.domain.model.DeskData
import kr.dongpae.domain.usecase.GetStudyRoomDataUseCaseImpl

class StudyRoomViewModel(application: Application): AndroidViewModel(application) {

    private val repository = FirebaseDataRepositoryImpl.get()

    private val getStudyRoomDataUseCase = GetStudyRoomDataUseCaseImpl(repository)


    @ExperimentalCoroutinesApi
    val deskData: LiveData<DeskData> = getStudyRoomDataUseCase().asLiveData()

}