package br.com.buscacnpj.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ApiResponse(
    val uf: String?,
    val cep: String?,
    val qsa: List<Socio>?,
    val cnpj: String?,
    val pais: String?,
    val email: String?,
    val porte: String?,
    val bairro: String?,
    val numero: String?,
    val ddd_fax: String?,
    val municipio: String?,
    val logradouro: String?,
    val cnae_fiscal: Int?,
    val codigo_pais: String?,
    val complemento: String?,
    val codigo_porte: Int?,
    val razao_social: String?,
    val nome_fantasia: String?,
    val capital_social: Long?,
    val ddd_telefone_1: String?,
    val ddd_telefone_2: String?,
    val opcao_pelo_mei: Boolean?,
    val descricao_porte: String?,
    val codigo_municipio: Int?,
    val cnaes_secundarios: List<CnaeSecundario>?,
    val natureza_juridica: String?,
    val situacao_especial: String?,
    val opcao_pelo_simples: Boolean?,
    val situacao_cadastral: Int?,
    val data_opcao_pelo_mei: String?,
    val data_exclusao_do_mei: String?,
    val cnae_fiscal_descricao: String?,
    val codigo_municipio_ibge: Int?,
    val data_inicio_atividade: String?,
    val data_situacao_especial: String?,
    val data_opcao_pelo_simples: String?,
    val data_situacao_cadastral: String?,
    val nome_cidade_no_exterior: String?,
    val codigo_natureza_juridica: Int?,
    val data_exclusao_do_simples: String?,
    val motivo_situacao_cadastral: Int?,
    val ente_federativo_responsavel: String?,
    val identificador_matriz_filial: Int?,
    val qualificacao_do_responsavel: Int?,
    val descricao_situacao_cadastral: String?,
    val descricao_tipo_de_logradouro: String?,
    val descricao_motivo_situacao_cadastral: String?,
    val descricao_identificador_matriz_filial: String?
): Parcelable

@Parcelize
data class Socio(
    val pais: String?,
    val nome_socio: String?,
    val codigo_pais: String?,
    val faixa_etaria: String?,
    val cnpj_cpf_do_socio: String?,
    val qualificacao_socio: String?,
    val codigo_faixa_etaria: Int?,
    val data_entrada_sociedade: String?,
    val identificador_de_socio: Int?,
    val cpf_representante_legal: String?,
    val nome_representante_legal: String?,
    val codigo_qualificacao_socio: Int?,
    val qualificacao_representante_legal: String?,
    val codigo_qualificacao_representante_legal: Int?
): Parcelable

@Parcelize
data class CnaeSecundario(
    val codigo: Int,
    val descricao: String
): Parcelable
/*
*  Pair("Nome", data.nome_fantasia),
                        Pair("CNPJ", data.cnpj),
                        Pair("Data de Abertura", data.data_inicio_atividade),
                        Pair("Telefone", data.ddd_telefone_2),
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
                        Pair("Atividade Principal", data.cnae_fiscal),
                        Pair("Atividades Secundárias", data.cnaes_secundarios),
                        Pair("Capital Social", data.capital_social),
                        Pair("QSA", data.qsa),*/


data class Information (
    val cnpj: String,
    val data_inicio_atividade: String?,
    val ddd_telefone_2: String?,
    val email: String?,
    val situacao_especial: String?,
    val cep: String?,
    val uf: String?,
    val municipio: String?,
    val bairro: String?,
    val logradouro: String?,
    val numero: String?,
    val complemento: String?,
    val natureza_juridica: String?,
    val cnae_fiscal: Int?,
    val capital_social: Long?,
    val porte: String?,

)