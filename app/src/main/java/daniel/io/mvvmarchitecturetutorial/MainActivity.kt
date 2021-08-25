package daniel.io.mvvmarchitecturetutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import daniel.io.mvvmarchitecturetutorial.data.UserPreferences
import daniel.io.mvvmarchitecturetutorial.ui.auth.AuthActivity
import daniel.io.mvvmarchitecturetutorial.ui.home.HomeActivity
import daniel.io.mvvmarchitecturetutorial.ui.startNewActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}