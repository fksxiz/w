package com.example.project4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton


class DataFragment : Fragment() {

    private lateinit var myPreferences:SharedPreferences

    private lateinit var backButton:MaterialButton
    private lateinit var nameTextView: TextView
    private lateinit var middlenameTextView: TextView
    private lateinit var lastnameTextView: TextView
    private lateinit var birthTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            myPreferences = PreferenceManager.getDefaultSharedPreferences((activity as MainActivity))
            backButton = findViewById(R.id.backButton)
            nameTextView = findViewById(R.id.dataNameTextView)
            middlenameTextView = findViewById(R.id.dataMiddleNameTextView)
            lastnameTextView = findViewById(R.id.dataLastNameTextView)
            birthTextView = findViewById(R.id.dataBirthTextView)
            genderTextView = findViewById(R.id.dataGenderTextView)

            backButton.setOnClickListener(OnBackButtonClickListener)
        }
        LoadPreferences()
    }

    private val OnBackButtonClickListener= View.OnClickListener {
        (activity as MainActivity).showFragment(MainFragment.newInstance())
    }

    private fun LoadPreferences(){
         nameTextView.text = myPreferences.getString(NAME_KEY,"not")
         lastnameTextView.text = myPreferences.getString(LASTNAME_KEY,"not")
         middlenameTextView.text = myPreferences.getString(MIDDLENAME_KEY,"not")
         birthTextView.text = myPreferences.getString(BIRTH_KEY,"not")
         genderTextView.text = myPreferences.getString(GENDER_KEY,"not")

    }

    companion object {

        private val NAME_KEY="NAME_KEY"
        private val MIDDLENAME_KEY="MIDDLENAME_KEY"
        private val LASTNAME_KEY="LASTNAME_KEY"
        private val BIRTH_KEY="BIRTH_KEY"
        private val GENDER_KEY="GENDER_KEY"

        @JvmStatic
        fun newInstance() =
            DataFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}