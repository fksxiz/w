package com.example.project4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.viewModels


class CatalogFragment : Fragment() {

    private val apiViewModel: ApiViewModel by viewModels()

    private lateinit var catalogListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListAdapter(requireContext())
        view.apply {
            catalogListView=findViewById(R.id.CatalogListView)
        }
        catalogListView.adapter = adapter
        adapter.notifyDataSetChanged()
        apiViewModel.GetCatalog{
            adapter.catalog = it
        }

        apiViewModel.catalog.observe(viewLifecycleOwner){
            adapter.catalog = it
            adapter.notifyDataSetChanged()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CatalogFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}