package com.example.moviecatalogappp.ui.profile

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
import com.example.moviecatalogappp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class RegisterFragment():Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth : FirebaseAuth
    private var email = ""
    private var password = ""
    val appContext = requireContext().applicationContext
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
     /*   progressDialog = ProgressDialog(appContext)
        progressDialog.setTitle("Подождите")
        progressDialog.setMessage("Регистрация")
        progressDialog.setCanceledOnTouchOutside(false) */

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        binding.logText.setOnClickListener {
            regText.findNavController()
                .navigate(R.id.action_navigation_register_to_navigation_login)
        }
        binding.regButton.setOnClickListener {
            validateData()
        }

    }
    private fun validateData(){
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error = "Некорректный формат"
        }
        else if ( TextUtils.isEmpty(password)){
            binding.password.error = "Введите пароль"
        } else if(password.length<6){
            binding.password.error = "Слишком короткий пароль"
        }
        else {
            firebaseSignUp()
        }

    }

    private fun firebaseSignUp(){
       // progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
            //    progressDialog.dismiss()
                binding.regButton.findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
            }
            .addOnFailureListener { e->
               // progressDialog.dismiss()
                Toast.makeText(appContext,"Ошибка из-за ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser!=null){
            startActivity(Intent(appContext, LoggedActivity::class.java))
        }
    }
}
