package daniel.io.mvvmarchitecturetutorial.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import daniel.io.mvvmarchitecturetutorial.data.UserPreferences
import daniel.io.mvvmarchitecturetutorial.databinding.ActivityHomeBinding
import daniel.io.mvvmarchitecturetutorial.ui.auth.AuthActivity
import daniel.io.mvvmarchitecturetutorial.ui.startNewActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences
    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        userPreferences.clear()
        startNewActivity(AuthActivity::class.java)
    }
}