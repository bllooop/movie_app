package com.example.moviecatalogappp.ui.profile

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moviecatalogappp.LoggedActivity
import com.example.moviecatalogappp.MainActivity
import com.example.moviecatalogappp.R
import com.example.moviecatalogappp.databinding.FragmentProfileBinding
import com.example.moviecatalogappp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

    }
    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser!=null){
            val email = firebaseUser.email
            binding.emailname.text = email
        }
        if(firebaseUser==null){
            startActivity(Intent(appContext, MainActivity::class.java))
        }
    }
}