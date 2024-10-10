package com.project.retrofit.response

@Serializable
class AdressResponse (

    private val logradouro : String,
    private val bairro : String,
    private val cidade : String,
    private val estado : String
)
{
    fun toAdressFormUiState() = Adress
}