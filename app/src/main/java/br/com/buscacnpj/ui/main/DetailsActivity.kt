package br.com.buscacnpj.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import br.com.buscacnpj.R
import br.com.buscacnpj.adapters.ViewPagerAdapter
import br.com.buscacnpj.data.model.ApiResponse
import br.com.buscacnpj.data.model.Cnae
import br.com.buscacnpj.data.model.Partner
import br.com.buscacnpj.ui.fragments.BasicInfoFragment
import br.com.buscacnpj.ui.fragments.CnaesFragment
import br.com.buscacnpj.ui.fragments.PartnersFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.details)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)



        val data = intent.getParcelableExtra<ApiResponse>("data") ?: return


        Log.d("DetailsActivity", data.toString())


        fun convertDateToBrazilianDate(data: String): String {
            val year = data.substring(0, 4)
            val month = data.substring(5, 7)
            val day = data.substring(8, 10)
            return "$day/$month/$year"
        }


        val basicInfo = listOf(
            Pair("Nome Fantasia", data.nome_fantasia.toString()),
            Pair("CNPJ", data.cnpj.toString()),
            Pair("Razão Social", data.razao_social.toString()),
            Pair("Porte", data.descricao_porte.toString()),
            Pair("Data de Abertura", convertDateToBrazilianDate(data.data_inicio_atividade.toString())),
            Pair("Telefone", data.ddd_telefone_1.toString()),
            Pair("Email", data.email.toString()),
            Pair("Situação", data.situacao_especial.toString()),
            Pair("CEP", data.cep.toString()),
            Pair("UF", data.uf.toString()),
            Pair("Município", data.municipio.toString()),
            Pair("Bairro", data.bairro.toString()),
            Pair("Logradouro", data.logradouro.toString()),
            Pair("Número", data.numero.toString()),
            Pair("Complemento", data.complemento.toString()),
            Pair("Natureza Jurídica", data.natureza_juridica.toString()),
            Pair("Atividade Principal", data.cnae_fiscal.toString()),
            Pair("Capital Social", data.capital_social.toString()),
        )

        val sociosList = data.qsa?.map { partner ->
            Partner(partner.nome_socio.toString(), partner.qualificacao_socio.toString(), partner.faixa_etaria.toString())
        } ?: emptyList()

        val cnaesList = data.cnaes_secundarios?.map { cnae ->
            Cnae(cnae.codigo, cnae.descricao.toString())
        } ?: emptyList()


        val fragments = listOf(
            BasicInfoFragment(basicInfo),
            PartnersFragment(sociosList),
            CnaesFragment(cnaesList)
        )



        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Informações Básicas"
                1 -> "Sócios"
                2 -> "CNAEs Secundários"
                else -> "Aba ${position + 1}"
            }
        }.attach()

    }


}