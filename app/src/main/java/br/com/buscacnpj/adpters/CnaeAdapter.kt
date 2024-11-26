package br.com.buscacnpj.adpters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.data.model.Cnae

class CnaeAdapter(private val cnaes: List<Cnae>) :
    RecyclerView.Adapter<CnaeAdapter.CnaeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CnaeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cnae, parent, false)
        return CnaeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CnaeViewHolder, position: Int) {
        holder.bind(cnaes[position])
    }

    override fun getItemCount(): Int = cnaes.size

    class CnaeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val codeTextView: TextView = itemView.findViewById(R.id.cnaeCodeTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.cnaeDescriptionTextView)



        fun bind(cnae: Cnae) {

            codeTextView.text = cnae.codigo.toString()
            descriptionTextView.text = cnae.descricao
        }
    }
}
