package com.example.simuladorcredito

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var textName: EditText
    private lateinit var textApellido: EditText
    private lateinit var textPhone: EditText
    private lateinit var textMonto: EditText
    private lateinit var checkBoxMensual: CheckBox
    private lateinit var checkBoxQuincenal: CheckBox
    private lateinit var sendButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textName = findViewById(R.id.textName)
        textApellido = findViewById(R.id.textApellido)
        textPhone = findViewById(R.id.textPhone)
        textMonto = findViewById(R.id.textMonto)
        checkBoxMensual = findViewById(R.id.checkBoxMensual)
        checkBoxQuincenal = findViewById(R.id.checkBoxQuincenal)
        sendButton = findViewById(R.id.sendButton)
        resultTextView = findViewById(R.id.resultTextView)

        sendButton.setOnClickListener {
            enviarFormulario()
        }
    }

    private fun enviarFormulario() {

        val nombre = textName.text.toString()
        val apellido = textApellido.text.toString()
        val telefono = textPhone.text.toString()
        val monto = textMonto.text.toString().toDoubleOrNull() ?: 0.0 // Convertir a Double

        val cuotas = if (checkBoxMensual.isChecked) 3 else if (checkBoxQuincenal.isChecked) 6 else 0

        val interes = 0.10 * monto
        val total = monto + interes
        // Calcular las cuotas
        val cuotaMensual = (total / cuotas)

        val decimalFormat = DecimalFormat("#,###.##")
        val montoFormateado = decimalFormat.format(monto)
        val interesFormateado = decimalFormat.format(interes)
        val cuotaMensualFormateada = decimalFormat.format(cuotaMensual)
        val totalFormateada = decimalFormat.format(total)

        Log.d("MainActivity", "Cuota Mensual: $cuotaMensual")
        resultTextView.text = """
            | Nombre: $nombre
            | Apellido: $apellido
            | Teléfono: $telefono
            | Monto: $montoFormateado
            | Interés (10%): $interesFormateado
            | Cuotas: $cuotas de $cuotaMensualFormateada cada una
            | Total: $totalFormateada
        """.trimMargin()
    }
}
