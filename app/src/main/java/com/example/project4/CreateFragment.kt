package com.example.project4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton


class CreateFragment : Fragment() {

    private val apiViewModel: ApiViewModel by viewModels()

    private lateinit var okButton: MaterialButton
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

        view.apply {
            okButton=findViewById(R.id.okButton)
            nameEditText=findViewById(R.id.nameEditText)
            middleNameEditText=findViewById(R.id.middleNameEditText)
            lastNameEditText=findViewById(R.id.lastNameEditText)
            bithEditText=findViewById(R.id.birthDateEditText)
            genderEditText=findViewById(R.id.genderEditText)
        }
        okButton.setOnClickListener(OnButtonClickListener)
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
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}