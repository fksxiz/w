package com.example.project4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(private val context:Context):BaseAdapter() {
    var catalog: List<Catalog> = emptyList()

    override fun getCount(): Int {
        return catalog.count()
    }

    override fun getItem(position: Int): Any {
        return catalog[position]
    }

    override fun getItemId(position: Int): Long {
        return catalog[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val catalogItem = getItem(position) as Catalog
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.catalog_item, parent, false)

        view.apply {
            findViewById<TextView>(R.id.titleTextView).text = catalogItem.name
            findViewById<TextView>(R.id.categoryTextView).text = catalogItem.category
            findViewById<TextView>(R.id.descriptionTextView).text = catalogItem.description
            findViewById<TextView>(R.id.priceTextView).text = catalogItem.price
        }
        return view
    }
}