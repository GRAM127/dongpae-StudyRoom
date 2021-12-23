package kr.dongpae.studyroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.dongpae.data.repository.FirebaseAuthRepositoryImpl
import kr.dongpae.data.repository.FirebaseDatabaseRepositoryImpl
import kr.dongpae.studyroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StudyRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseAuthRepositoryImpl.initialize(application)
        FirebaseDatabaseRepositoryImpl.initialize(application)

        viewModel = ViewModelProvider(this)[StudyRoomViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)
    }
}