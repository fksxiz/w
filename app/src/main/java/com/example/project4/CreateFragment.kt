package com.example.project4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.google.android.material.button.MaterialButton


class CreateFragment : Fragment() {

    private val apiViewModel: ApiViewModel by viewModels()
    private lateinit var myPreferences: SharedPreferences


    private lateinit var okButton: MaterialButton
    private lateinit var updateButton: MaterialButton
    private lateinit var nameEditText: EditText
    private lateinit var middleNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var bithEditText: EditText
    private lateinit var genderEditText: EditText
    private var id:Long=99999

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreferences = PreferenceManager.getDefaultSharedPreferences((activity as MainActivity))
        view.apply {
            okButton=findViewById(R.id.okButton)
            updateButton=findViewById(R.id.updateButton)
            nameEditText=findViewById(R.id.nameEditText)
            middleNameEditText=findViewById(R.id.middleNameEditText)
            lastNameEditText=findViewById(R.id.lastNameEditText)
            bithEditText=findViewById(R.id.birthDateEditText)
            genderEditText=findViewById(R.id.genderEditText)
        }
        okButton.setOnClickListener(OnButtonClickListener)
        updateButton.setOnClickListener(OnUpdateClick)
    }

    private val OnButtonClickListener = View.OnClickListener {
        id++
        apiViewModel.CreateProfile(1,
            nameEditText.text.toString(),
            middleNameEditText.text.toString(),
            lastNameEditText.text.toString(),
            bithEditText.text.toString(),
            genderEditText.text.toString(),
            "qweqwe"
        )
        SavePreferences()
    }

    private val OnUpdateClick= View.OnClickListener {
        apiViewModel.UpdateProfile(
            nameEditText.text.toString(),
            middleNameEditText.text.toString(),
            lastNameEditText.text.toString(),
            bithEditText.text.toString(),
            genderEditText.text.toString()
        )
        SavePreferences()
    }

    private fun SavePreferences(){
        val myEditor: SharedPreferences.Editor = myPreferences.edit()
        myEditor.putString(NAME_KEY,nameEditText.text.toString())
        myEditor.putString(MIDDLENAME_KEY,middleNameEditText.text.toString())
        myEditor.putString(LASTNAME_KEY,lastNameEditText.text.toString())
        myEditor.putString(BIRTH_KEY,bithEditText.text.toString())
        myEditor.putString(GENDER_KEY,genderEditText.text.toString())

        myEditor.commit()
    }

    companion object {

        private val NAME_KEY="NAME_KEY"
        private val MIDDLENAME_KEY="MIDDLENAME_KEY"
        private val LASTNAME_KEY="LASTNAME_KEY"
        private val BIRTH_KEY="BIRTH_KEY"
        private val GENDER_KEY="GENDER_KEY"

        @JvmStatic
        fun newInstance() =
            CreateFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}