package br.com.buscacnpj.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.adapters.CnaesAdapter
import br.com.buscacnpj.adapters.PartnersAdapter
import br.com.buscacnpj.data.model.Cnae


class CnaesFragment(private val cnaes: List<Cnae>): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cnaes, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCnaes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =CnaesAdapter(cnaes)

        return view
    }


}