package br.com.buscacnpj.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.data.model.Cnae

class CnaesAdapter(private val cnaes: List<Cnae>):  RecyclerView.Adapter<CnaesAdapter.CnaesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CnaesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.informations_item, parent, false)
        return CnaesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CnaesViewHolder, position: Int) {
        val (key, value) = cnaes[position]
        holder.tvKey.text = key.toString()
        holder.tvValue.text = value
    }

    override fun getItemCount(): Int = cnaes.size

    class CnaesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKey: TextView = view.findViewById(R.id.tvKey)
        val tvValue: TextView = view.findViewById(R.id.tvValue)
    }

}