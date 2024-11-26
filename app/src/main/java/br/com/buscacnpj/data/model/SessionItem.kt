package br.com.buscacnpj.data.model

data class SessionItem(
    val title: String,
    val data: List<Any>,
    var isExpanded: Boolean = false
)
data class Partner(
    val nome_socio: String,
    val qualificacao_socio: String,
    val faixa_etaria: String
)

data class Cnae(
    val codigo: Int,
    val descricao: String
)

data class DefaultData(
    val key: String,
    val value: String
)
