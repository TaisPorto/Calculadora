package com.calculadora.calculadora


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calculadora.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Oculta a barra de ferramentas
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fun AcresentarUmaExpressao(string: String, limpar_dados: Boolean) {
            if (binding.result1.text.isNotEmpty()) {
                binding.express.text = ""
            }
            if (limpar_dados) {
                binding.result1.text = ""
                binding.express.append(string)
            } else {
                binding.express.append(binding.result1.text)
                binding.express.append(string)
                binding.result1.text = ""
            }
        }

        // Criando eventos de click

        binding.numero0.setOnClickListener { AcresentarUmaExpressao("0", true) }
        binding.numero1.setOnClickListener { AcresentarUmaExpressao("1", true) }
        binding.numero2.setOnClickListener { AcresentarUmaExpressao("2", true) }
        binding.numero3.setOnClickListener { AcresentarUmaExpressao("3", true) }
        binding.numero4.setOnClickListener { AcresentarUmaExpressao("4", true) }
        binding.numero5.setOnClickListener { AcresentarUmaExpressao("5", true) }
        binding.numero6.setOnClickListener { AcresentarUmaExpressao("6", true) }
        binding.numero7.setOnClickListener { AcresentarUmaExpressao("7", true) }
        binding.numero8.setOnClickListener { AcresentarUmaExpressao("8", true) }
        binding.numero9.setOnClickListener { AcresentarUmaExpressao("9", true) }
        binding.numeroPonto.setOnClickListener { AcresentarUmaExpressao(".", true) }

        //Operadores
        binding.numeroAdicao.setOnClickListener {
            AcresentarUmaExpressao("+", false)
        }
        binding.numeroSubtracao.setOnClickListener {
            AcresentarUmaExpressao("-", false)
        }
        binding.numeroMultiplicacao.setOnClickListener {
            AcresentarUmaExpressao("*", false)
        }
        binding.numeroDivisao.setOnClickListener {
            AcresentarUmaExpressao("/", false)
        }

        // Ativando a opção limpar "C"!
        binding.numeroLimpar.setOnClickListener {
            binding.express.text = ""
            binding.result1.text = ""
        }

        // Ativando a opção backspace

        binding.numeroApagar.setOnClickListener {
            val string = binding.express.text.toString()

            // Toda vez que clicar ele remove um caracter
            if (string.isNotBlank()) {
                binding.express.text = string.substring(0, string.length - 1)
            }
            binding.result1.text = ""
        }
        // Ativando a opçãoo de Resultado " ="
        binding.numeroResultado.setOnClickListener {
            try {

                val expressao = ExpressionBuilder(binding.express.text.toString()).build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if (resultado == longResult.toDouble()) {
                    binding.result1.text = longResult.toString()
                } else {
                    binding.result1.text = resultado.toString()
                }

            } catch (e: Exception) {

            }

        }
    }
}

