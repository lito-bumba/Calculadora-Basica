package com.project.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var resultado: TextView
    private lateinit var expressao: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val btn_0 = findViewById<TextView>(R.id.btn_0)
        val btn_1 = findViewById<TextView>(R.id.btn_1)
        val btn_2 = findViewById<TextView>(R.id.btn_2)
        val btn_3 = findViewById<TextView>(R.id.btn_3)
        val btn_4 = findViewById<TextView>(R.id.btn_4)
        val btn_5 = findViewById<TextView>(R.id.btn_5)
        val btn_6 = findViewById<TextView>(R.id.btn_6)
        val btn_7 = findViewById<TextView>(R.id.btn_7)
        val btn_8 = findViewById<TextView>(R.id.btn_8)
        val btn_9 = findViewById<TextView>(R.id.btn_9)
        val btn_ponto = findViewById<TextView>(R.id.btn_ponto)

        val btn_somar = findViewById<TextView>(R.id.btn_somar)
        val btn_subtrair = findViewById<TextView>(R.id.btn_subtrair)
        val btn_multiplicar = findViewById<TextView>(R.id.btn_multiplicar)
        val btn_dividir = findViewById<TextView>(R.id.btn_dividir)

        val btn_limpar = findViewById<TextView>(R.id.btn_limpar)
        val btn_backspace = findViewById<ImageView>(R.id.btn_backspace)
        val btn_igual = findViewById<TextView>(R.id.btn_igual)

        btn_0.setOnClickListener { AcrescentarUmaExpressao("0", true) }
        btn_1.setOnClickListener { AcrescentarUmaExpressao("1", true) }
        btn_2.setOnClickListener { AcrescentarUmaExpressao("2", true) }
        btn_3.setOnClickListener { AcrescentarUmaExpressao("3", true) }
        btn_4.setOnClickListener { AcrescentarUmaExpressao("4", true) }
        btn_5.setOnClickListener { AcrescentarUmaExpressao("5", true) }
        btn_6.setOnClickListener { AcrescentarUmaExpressao("6", true) }
        btn_7.setOnClickListener { AcrescentarUmaExpressao("7", true) }
        btn_8.setOnClickListener { AcrescentarUmaExpressao("8", true) }
        btn_9.setOnClickListener { AcrescentarUmaExpressao("9", true) }
        btn_ponto.setOnClickListener { AcrescentarUmaExpressao(".", true) }

        btn_somar.setOnClickListener { AcrescentarUmaExpressao("+", false) }
        btn_subtrair.setOnClickListener { AcrescentarUmaExpressao("-", false) }
        btn_multiplicar.setOnClickListener { AcrescentarUmaExpressao("*", false) }
        btn_dividir.setOnClickListener { AcrescentarUmaExpressao("/", false) }

        btn_limpar.setOnClickListener {
            expressao.text = ""
            resultado.text = ""
        }

        btn_backspace.setOnClickListener{
            val string = expressao.text.toString()

            if (string.isNotBlank()){
                expressao.text = string.substring(0, string.length - 1)
            }
            resultado.text = ""
        }

        btn_igual.setOnClickListener {
            try {
                val express = ExpressionBuilder(expressao.text.toString()).build()
                val resul = express.evaluate()
                val long_result = resul.toLong()

                if (resul == long_result.toDouble())
                    resultado.text = long_result.toString()
                else
                    resultado.text = resul.toString()
            }
            catch (e: Exception){

            }
        }

    }

    fun AcrescentarUmaExpressao(string: String, limpar_dados: Boolean){

        resultado = findViewById(R.id.resultado)
        expressao = findViewById(R.id.expressao)

        if (resultado.text.isNotEmpty()){
            expressao.text = ""
        }

        if (limpar_dados){
            resultado.text = ""
            expressao.append(string)
        }
        else{
            expressao.append(resultado.text)
            expressao.append(string)
            resultado.text = ""
        }
    }
}