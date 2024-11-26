package br.com.buscacnpj.adpters
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.data.model.Partner

class PartnerAdapter(private val partners: List<Partner>) :
    RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_partner, parent, false)
        return PartnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        Log.d("PartnerAdapter", "onBindViewHolder: $partners[position]")
        holder.bind(partners[position])
    }

    override fun getItemCount(): Int {
        Log.d("PartnerAdapter", "Número de parceiros: ${partners.size}")
        return partners.size
    }

    class PartnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.partnerNameTextView)
        private val roleTextView: TextView = itemView.findViewById(R.id.partnerRoleTextView)
        private val ageRangeTextView: TextView = itemView.findViewById(R.id.partnerAgeRangeTextView)

        fun bind(partner: Partner) {
            Log.d("PartnerAdapter", "bind: $partner")
            nameTextView.text = partner.nome_socio
            roleTextView.text = partner.qualificacao_socio
            ageRangeTextView.text = partner.faixa_etaria
        }
    }
}
