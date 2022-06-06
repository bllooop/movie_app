package com.example.moviecatalogappp.ui.profile.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moviecatalogappp.LoggedActivity
import com.example.moviecatalogappp.R
import com.example.moviecatalogappp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment() : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth : FirebaseAuth
    private var email = ""
    private var password = ""
    val appContext = context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
       /* progressDialog = ProgressDialog(appContext)
        progressDialog.setTitle("Подождите")
        progressDialog.setMessage("Авторизовывается")
        progressDialog.setCanceledOnTouchOutside(false) */

        firebaseAuth = FirebaseAuth.getInstance()
            checkUser()
        binding.regText.setOnClickListener {
            regText.findNavController()
                .navigate(R.id.action_navigation_login_to_navigation_register)
        }
        binding.logButton.setOnClickListener {
            validateData()
        }

    }
    private fun validateData(){
        email = binding.emails.text.toString()
        password = binding.passwords.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emails.error = "Некорректный формат"
        }
        else if ( TextUtils.isEmpty(password)){
            binding.passwords.error = "Введите пароль"
        } else {
            firebaseLogin()
        }

    }

    private fun firebaseLogin(){
      //  progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
             //  progressDialog.dismiss()
                binding.logButton.findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
            }
            .addOnFailureListener { e->
                //progressDialog.dismiss()
                Toast.makeText(appContext,"Ошибка из-за ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        activity?.let {
            if (firebaseUser != null) {
                startActivity(Intent(it, LoggedActivity::class.java))
            }
        }
    }
}