package com.example.ageinminutescalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    private  var tvSelectedDate : TextView? = null
    private var tvSelectedDateInMinutes : TextView? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

        tvSelectedDate?.setOnClickListener {
            clickDatePicker()
        }
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val tvSelectedDate : TextView = findViewById(R.id.tvSelectedDate)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{

                _, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this, "Year was ${selectedYear}, Month was ${selectedMonth+1}, day of month ${selectedDayOfMonth}", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            tvSelectedDateInMinutes?.text = differenceInMinutes.toString()

        }, year, month, day)
         //   .show()
        dpd.datePicker.maxDate = System.currentTimeMillis() - 864000000
        dpd.show()


    }

}