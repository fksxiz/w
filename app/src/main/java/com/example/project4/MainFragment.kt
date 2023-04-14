package com.example.project4

import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import java.util.*


class MainFragment : Fragment() {

    private lateinit var sendButton: MaterialButton
    private lateinit var authButton: MaterialButton
    private lateinit var createButton: MaterialButton
    private lateinit var dataButton: MaterialButton
    private lateinit var catalogButton: MaterialButton
    private lateinit var codeEditText: EditText
    private lateinit var emailEditText: EditText
    private val apiViewModel: ApiViewModel by viewModels()
    private val timer: Timer=Timer();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            sendButton = findViewById(R.id.SendButton)
            authButton = findViewById(R.id.AuthButton)
            createButton = findViewById(R.id.CreateProfileButton)
            dataButton = findViewById(R.id.dataButton)
            catalogButton = findViewById(R.id.catalogButton)
            codeEditText = findViewById(R.id.EmailCodeEditText)
            emailEditText = findViewById(R.id.EmailEditText)
        }
        sendButton.setOnClickListener(OnSendButtonClickListener)
        authButton.setOnClickListener(OnAuthButtonClickListener)
        createButton.setOnClickListener(OnCreateButtonClickListener)
        dataButton.setOnClickListener(onDataButtonClickListener)
        catalogButton.setOnClickListener(OnCatalogClickListener)
    }

    private val OnCatalogClickListener = OnClickListener {
        (activity as MainActivity).showFragment(CatalogFragment.newInstance())
    }

    private val OnSendButtonClickListener = OnClickListener {
        apiViewModel.SendCode(emailEditText.text.toString())
        sendButton.isEnabled=false
        val timer = object: CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                sendButton.text = ("remaining: "+millisUntilFinished/1000)
            }

            override fun onFinish() {
                sendButton.isEnabled=true
                sendButton.text = "Отправить повторно"
            }
        }.start()
    }

    private val OnAuthButtonClickListener = OnClickListener {
        if(emailEditText.text!=null && codeEditText.text!=null){
            val data = EmailData(emailEditText.text.toString(),
                codeEditText.text.toString())
            apiViewModel.AuthProfile(data)
        }
    }

    private val onDataButtonClickListener = OnClickListener {
        (activity as MainActivity).showFragment(DataFragment.newInstance())
    }

    private fun LoadPreferences(){
        val myPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    private val OnCreateButtonClickListener = OnClickListener {
        (activity as MainActivity).showFragment(CreateFragment.newInstance())
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}