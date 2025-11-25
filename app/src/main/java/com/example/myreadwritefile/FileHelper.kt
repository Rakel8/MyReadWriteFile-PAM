package com.example.myreadwritefile

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

internal object FileHelper {

    fun writeToFile(filename: String, data: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readFromFile(context: Context, filename: String): String {
        val fileModel = StringBuilder()
        try {
            val inputStream = context.openFileInput(filename)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String?

                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    fileModel.append(receiveString)
                }
                inputStream.close()
            }
        } catch (e: Exception) {
            return "File tidak ditemukan."
        }
        return fileModel.toString()
    }
}