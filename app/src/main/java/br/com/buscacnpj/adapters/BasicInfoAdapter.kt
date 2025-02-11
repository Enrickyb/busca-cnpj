package br.com.buscacnpj.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R

class BasicInfoAdapter(private val basicInfo: List<Pair<String, String>>) :
    RecyclerView.Adapter<BasicInfoAdapter.BasicInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.informations_item, parent, false)
        return BasicInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasicInfoViewHolder, position: Int) {
        val (key, value) = basicInfo[position]
        holder.tvKey.text = key
        holder.tvValue.text = value
    }

    override fun getItemCount(): Int = basicInfo.size

    class BasicInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKey: TextView = view.findViewById(R.id.tvKey)
        val tvValue: TextView = view.findViewById(R.id.tvValue)
    }
}