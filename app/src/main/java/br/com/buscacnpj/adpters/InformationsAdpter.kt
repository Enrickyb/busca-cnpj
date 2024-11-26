import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R

class InformationsAdpter(private val infoList: List<Pair<String, String>>) :
    RecyclerView.Adapter<InformationsAdpter.InfoViewHolder>() {

    class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val keyTextView: TextView = itemView.findViewById(R.id.keyTextView)
        private val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)

        fun bind(pair: Pair<String, String>) {

                keyTextView.text = pair.first

                if (pair.second.isNullOrBlank()) {
                    valueTextView.text = "Não informado"
                } else {
                    valueTextView.text = pair.second
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.informations_item, parent, false)
        return InfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int = infoList.size
}
