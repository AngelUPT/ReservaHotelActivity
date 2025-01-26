package com.angelcorrea.reservahotelactivity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private var startDate: String? = null
    private var endDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtNumberOfPeople = findViewById<EditText>(R.id.editTextNumber)
        val btnReserve = findViewById<Button>(R.id.button3)
        val btnSelectStartDate = findViewById<Button>(R.id.button)
        val btnSelectEndDate = findViewById<Button>(R.id.button2)

        btnSelectStartDate.setOnClickListener {
            showDatePickerDialog(true)
        }

        btnSelectEndDate.setOnClickListener {
            showDatePickerDialog(false)
        }

        btnReserve.setOnClickListener {
            val numberOfPeople = edtNumberOfPeople.text.toString()
            if (startDate != null && endDate != null && numberOfPeople.isNotEmpty()) {
                val message = "Número de Personas: $numberOfPeople\n" +
                        "Fecha de Entrada: $startDate\n" +
                        "Fecha de Salida: $endDate"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "complete todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showDatePickerDialog(isStartDate: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            if (isStartDate) {
                startDate = selectedDate
            } else {
                endDate = selectedDate
            }
        }, year, month, day)

        datePickerDialog.show()
    }
}