package br.com.buscacnpj.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.buscacnpj.R
import br.com.buscacnpj.adapters.BasicInfoAdapter

class BasicInfoFragment(private val basicInfo: List<Pair<String, String>>) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basic_info, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvBasicInfo)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BasicInfoAdapter(basicInfo)

        return view
    }
}
