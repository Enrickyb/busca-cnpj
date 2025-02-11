package br.com.buscacnpj.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import br.com.buscacnpj.R

import br.com.buscacnpj.adapters.ViewPagerAdapter
import br.com.buscacnpj.data.model.Cnae
import br.com.buscacnpj.data.model.Partner
import br.com.buscacnpj.ui.fragments.BasicInfoFragment
import br.com.buscacnpj.ui.fragments.CnaesFragment
import br.com.buscacnpj.ui.fragments.PartnersFragment
import br.com.buscacnpj.utils.addCnpjMask
import br.com.buscacnpj.viewmodel.ApiViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val apiViewModel: ApiViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etBusinessCNPJ = findViewById<EditText>(R.id.etBusinessCNPJ)
        val btnSearch = findViewById<TextView>(R.id.btnSearch)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        etBusinessCNPJ.addCnpjMask()


        fun isValidCNPJ(cnpj: String): Boolean {
            return cnpj.matches(Regex("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"))
        }


        btnSearch.setOnClickListener(){

            val cnpj = etBusinessCNPJ.text.toString()
            if (isValidCNPJ(cnpj)) {
                progressBar.visibility = View.VISIBLE
                apiViewModel.fetchData(cnpj)
            } else {
                Toast.makeText(this, "CNPJ inválido!", Toast.LENGTH_SHORT).show()
            }

        }



        // Observar a resposta da API e atualizar a interface
        apiViewModel.apiData.observe(this) { data ->
            progressBar.visibility = View.GONE
            data?.let {
                //mudar para a view DetailsActivity e enviar data como parâmetro
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("data", it) // Passando o modelo Parcelable
                startActivity(intent)



            }?: run {
                Toast.makeText(this, "Erro ao buscar dados. Tente novamente.", Toast.LENGTH_LONG).show()
            }
        }

//33.683.111/0002-80


    }


}