package br.com.buscacnpj.adapters

import InformationsAdpter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.adpters.CnaeAdapter
import br.com.buscacnpj.adpters.PartnerAdapter
import br.com.buscacnpj.data.model.Cnae
import br.com.buscacnpj.data.model.Information
import br.com.buscacnpj.data.model.Partner
import br.com.buscacnpj.data.model.SessionItem

class SessionAdapter(private val sessions: List<SessionItem>) : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    inner class SessionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.sessionTitle)
        private val recyclerView: RecyclerView = view.findViewById(R.id.innerRecyclerView)
        private val arrowIcon: ImageView = view.findViewById(R.id.arrowIcon)

        fun bind(session: SessionItem) {
            title.text = session.title

            // Configure a visibilidade do RecyclerView aninhado e o ícone de seta
            recyclerView.visibility = if (session.isExpanded) View.VISIBLE else View.GONE
            arrowIcon.rotation = if (session.isExpanded) 180f else 0f

            // Configure o LayoutManager do RecyclerView aninhado
            recyclerView.layoutManager = LinearLayoutManager(itemView.context)

            // Alterna a expansão do item ao clicar no título
            title.setOnClickListener {
                session.isExpanded = !session.isExpanded
                notifyItemChanged(adapterPosition)
            }
            Log.d("SessionAdapter", "bind: ${session.data}")
            // Configure o adaptador para o RecyclerView aninhado
            val innerAdapter = when (session.title) {
                "Informações Básicas" -> {
                    Log.d("SessionAdapter", "Inicializando InformationsAdpter informações.")
                    InformationsAdpter(session.data as List<Pair<String, String>>)
                }

                "Sócios" -> {
                    Log.d("SessionAdapter", "Inicializando PartnerAdapter com ${session.data.size} sócios.")
                    PartnerAdapter(session.data as List<Partner>)
                }
                "CNAEs Secundários" -> CnaeAdapter(session.data as List<Cnae>)
                else -> null
            }
            recyclerView.adapter = innerAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return SessionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(sessions[position])
    }

    class BasicInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)

        fun bind(data: List<Pair<String, String>>) {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            recyclerView.adapter = InformationsAdpter(data)
        }
    }



    override fun getItemCount() = sessions.size
}
