package br.com.buscacnpj.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.data.model.Partner

class PartnersAdapter(private val partners: List<Partner>):  RecyclerView.Adapter<PartnersAdapter.PartnersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.informations_item, parent, false)
        return PartnersViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartnersViewHolder, position: Int) {
        val (key, value) = partners[position]
        holder.tvKey.text = key
        holder.tvValue.text = value
    }

    override fun getItemCount(): Int = partners.size

    class PartnersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKey: TextView = view.findViewById(R.id.tvKey)
        val tvValue: TextView = view.findViewById(R.id.tvValue)
    }

}