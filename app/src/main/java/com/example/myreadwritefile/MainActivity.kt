package com.example.myreadwritefile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTitle = findViewById<EditText>(R.id.edit_title)
        val editInput = findViewById<EditText>(R.id.edit_input)
        val btnSave = findViewById<Button>(R.id.button_save)
        val btnRead = findViewById<Button>(R.id.button_read)

        btnSave.setOnClickListener {
            val title = editTitle.text.toString()
            val text = editInput.text.toString()

            if (title.isNotEmpty() && text.isNotEmpty()) {
                FileHelper.writeToFile(title, text, this)

                Toast.makeText(this, "File $title berhasil disimpan", Toast.LENGTH_SHORT).show()

                editInput.text.clear()
            } else {
                Toast.makeText(this, "Isi judul dan catatan dulu!", Toast.LENGTH_SHORT).show()
            }
        }

        btnRead.setOnClickListener {
            val title = editTitle.text.toString()
            if (title.isNotEmpty()) {
                val hasilBaca = FileHelper.readFromFile(this, title)
                editInput.setText(hasilBaca)
                Toast.makeText(this, "Data dimuat dari Internal Storage", Toast.LENGTH_SHORT).show()
            } else {
                val namaFileDefault = "catatan.txt" // Contoh default
                val hasilBaca = FileHelper.readFromFile(this, namaFileDefault)
                editInput.setText(hasilBaca)
                editTitle.setText(namaFileDefault)
                Toast.makeText(this, "Mencoba buka file default...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}