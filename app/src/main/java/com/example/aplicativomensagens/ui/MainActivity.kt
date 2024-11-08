package com.example.aplicativomensagens.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativomensagens.R
import com.example.aplicativomensagens.data.DadosFrases
import com.example.aplicativomensagens.databinding.ActivityMainBinding
import com.example.aplicativomensagens.infra.Constants
import com.example.aplicativomensagens.infra.Security

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var category = Constants.FILTER.SOL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName()
        selectClick(R.id.clickSol)

        binding.botaoMsg.setOnClickListener(this)
        binding.clickSol.setOnClickListener(this)
        binding.clickNuvem.setOnClickListener(this)
        binding.clickSolNuvem.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.botaoMsg) {
            selectFrase()
            //Toast.makeText(this, "Clique no botão para aparecer essa mensagem!", Toast.LENGTH_SHORT).show()
        } else if (view.id in listOf(R.id.clickSol, R.id.clickNuvem, R.id.clickSolNuvem)) {
            selectClick(view.id)
        }
    }

    private fun selectFrase() {
        binding.msgPrincipal.text = DadosFrases().getFrase(category)
    }

    @SuppressLint("SetTextI18n")
    private fun userName(){
        val nome = Security(this).getString(Constants.KEY.USER_NAME)
        binding.nome.text = "Olá, $nome"
    }

    private fun selectClick(id: Int){
        binding.clickSol.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.clickNuvem.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.clickSolNuvem.setColorFilter(ContextCompat.getColor(this, R.color.black))

        when (id) {
            R.id.clickSol -> {
                binding.clickSol.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = Constants.FILTER.SOL
                selectFrase()
            }
            R.id.clickNuvem -> {
                binding.clickNuvem.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = Constants.FILTER.NUVEM
                selectFrase()
            }
            R.id.clickSolNuvem -> {
                binding.clickSolNuvem.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = Constants.FILTER.SOLNUVEM
                selectFrase()
            }
        }

    }
}