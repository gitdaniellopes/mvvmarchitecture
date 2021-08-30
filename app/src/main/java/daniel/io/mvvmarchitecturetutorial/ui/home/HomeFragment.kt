package daniel.io.mvvmarchitecturetutorial.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import daniel.io.mvvmarchitecturetutorial.data.modelresponse.User
import daniel.io.mvvmarchitecturetutorial.data.remote.Resource
import daniel.io.mvvmarchitecturetutorial.data.remote.UserApi
import daniel.io.mvvmarchitecturetutorial.databinding.FragmentHomeBinding
import daniel.io.mvvmarchitecturetutorial.repository.UserRepository
import daniel.io.mvvmarchitecturetutorial.ui.handleApiError
import daniel.io.mvvmarchitecturetutorial.ui.logout
import daniel.io.mvvmarchitecturetutorial.ui.visible
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }
}