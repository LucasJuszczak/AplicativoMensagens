package com.example.aplicativomensagens.data

import com.example.aplicativomensagens.infra.Constants
import kotlin.random.Random

data class Frases(val desc: String, val category: Int)

class DadosFrases {
    private val sol = Constants.FILTER.SOL
    private val nuvem = Constants.FILTER.NUVEM
    private val solNuvem = Constants.FILTER.SOLNUVEM

    val listaDeFrases = listOf<Frases>(
        Frases(
            "Se você estiver comigo não me importo se estivermos na chuva, no sol muito quente, granizo ou neve.", sol),
        Frases("Você diz que ama a chuva, mas você abre seu guarda-chuva quando chove.", nuvem),
        Frases("Um dia de chuva é tão belo como um dia de sol. Ambos existem; cada um como é.", solNuvem)
        )

    fun getFrase(value: Int): String{
        val filtro = listaDeFrases.filter { it.category == value }
        return filtro[Random.nextInt(filtro.size)].desc
    }
}