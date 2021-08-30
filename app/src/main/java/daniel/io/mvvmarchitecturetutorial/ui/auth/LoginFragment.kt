package daniel.io.mvvmarchitecturetutorial.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import daniel.io.mvvmarchitecturetutorial.MainActivity
import daniel.io.mvvmarchitecturetutorial.data.remote.AuthApi
import daniel.io.mvvmarchitecturetutorial.data.remote.Resource
import daniel.io.mvvmarchitecturetutorial.databinding.FragmentLoginBinding
import daniel.io.mvvmarchitecturetutorial.repository.AuthRepository
import daniel.io.mvvmarchitecturetutorial.ui.base.BaseFragment
import daniel.io.mvvmarchitecturetutorial.ui.enable
import daniel.io.mvvmarchitecturetutorial.ui.startNewActivity
import daniel.io.mvvmarchitecturetutorial.ui.visible

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.user.access_token!!)
                    requireActivity().startNewActivity(MainActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Falha", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)
            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}