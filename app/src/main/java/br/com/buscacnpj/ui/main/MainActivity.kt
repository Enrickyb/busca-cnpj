package br.com.buscacnpj.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.buscacnpj.R
import br.com.buscacnpj.adapters.SessionAdapter
import br.com.buscacnpj.data.model.Cnae
import br.com.buscacnpj.data.model.Partner
import br.com.buscacnpj.data.model.SessionItem
import br.com.buscacnpj.viewmodel.ApiViewModel

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
        val etBusinessCNPJ = findViewById<TextView>(R.id.etBusinessCNPJ)
        val btnSearch = findViewById<TextView>(R.id.btnSearch)
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)





        btnSearch.setOnClickListener(){
            val cnpj = etBusinessCNPJ.text.toString()
            if (cnpj.isNotEmpty()) {
                // Mostrar o ProgressBar e esconder a RecyclerView
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE

                // Iniciar chamada para buscar dados da API
                apiViewModel.fetchData(cnpj)
            }
        }



        // Observar a resposta da API e atualizar a interface
        apiViewModel.apiData.observe(this) { data ->
            progressBar.visibility = View.GONE
            data?.let {
                Log.d("MainActivity", "Dados recebidos: ${it.nome_fantasia}, ${it.cnpj}") // Verifique os dados recebidos
                recyclerView.visibility = View.VISIBLE
                val sociosList = it.qsa?.map { partner ->
                    Partner(partner.nome_socio.toString(), partner.qualificacao_socio.toString(), partner.faixa_etaria.toString())
                }

                Log.d("MainActivity", "Sócios: $sociosList.")
                Log.d("MainActivity", "Quantidade de sócios: ${sociosList?.size}.")

                val cnaesList = it.cnaes_secundarios?.map { cnae ->
                    Cnae(cnae.codigo, cnae.descricao.toString())
                }

                fun convertDateToBrazilianDate(data: String): String {
                    val year = data.substring(0, 4)
                    val month = data.substring(5, 7)
                    val day = data.substring(8, 10)
                    return "$day/$month/$year"
                }

                val sessions = listOf(
                    SessionItem("Informações Básicas", listOf(
                        Pair("Nome Fantasia", data.nome_fantasia),
                        Pair("CNPJ", data.cnpj),
                        Pair("Razão Social", data.razao_social),
                        Pair("Porte", data.descricao_porte),
                        Pair("Data de Abertura", convertDateToBrazilianDate(data.data_inicio_atividade.toString())),
                        Pair("Telefone", data.ddd_telefone_1),
                        Pair("Email", data.email),
                        Pair("Situação", data.situacao_especial),
                        Pair("CEP", data.cep),
                        Pair("UF", data.uf),
                        Pair("Município", data.municipio),
                        Pair("Bairro", data.bairro),
                        Pair("Logradouro", data.logradouro),
                        Pair("Número", data.numero),
                        Pair("Complemento", data.complemento),
                        Pair("Natureza Jurídica", data.natureza_juridica),
                        Pair("Atividade Principal", data.cnae_fiscal.toString()),
                        Pair("Capital Social", data.capital_social.toString()),
                    ),
                        isExpanded = true),
                    SessionItem("Sócios", sociosList as List<Partner>),
                    SessionItem("CNAEs Secundários", cnaesList as List<Cnae>)

                )


                val adapter = SessionAdapter(sessions as List<SessionItem>)
                recyclerView.layoutManager = LinearLayoutManager(this) // Ou outro tipo de LayoutManager, como GridLayoutManager
                recyclerView.isNestedScrollingEnabled = false
                recyclerView.adapter = adapter



            }?: run {
                Log.e("MainActivity", "Nenhum dado recebido ou erro na API.")
            }
        }

//33.683.111/0002-80


    }
}