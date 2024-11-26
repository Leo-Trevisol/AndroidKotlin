package com.project.custoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.custoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonCalculate){
                calculate()
        }
    }

    /**
     * Função responsável por realizar o cálculo dos gastos com a viagem
     * Baseado na distância percorrida * preço médio do combustível / pela autonomia do veículo
     */
    private fun calculate() {
        if (isValidationOk()) {

            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            // Realiza o cálculo ((distancia * preço) / autonomia)
            val total = (distance * price) / autonomy

            // Seta o valor calculado na tela - Formatado com duas casas
            binding.textResult.text = "R$ ${"%.2f".format(total)}"
        } else {
            // Caso não tenha preenchido todos os campos, solicita o preenchimento
            Toast.makeText(this, getString(R.string.validation_fill_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Verifica se todos os campos foram preenchidos
     */
    private fun isValidationOk(): Boolean = (
            binding.editDistance.text.toString() != "" &&
                    binding.editPrice.text.toString() != "" &&
                    binding.editAutonomy.text.toString() != "" &&
                    binding.editAutonomy.text.toString() != "0"
            )
}