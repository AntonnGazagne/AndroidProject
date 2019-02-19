package fr.isen.twibook

import android.os.AsyncTask
import fr.isen.roue.androidtoolbox.CommentInterface

import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class CommentTask(val arg: CommentInterface) : AsyncTask<Unit, Unit, String>() {

    override fun onPostExecute(result: String) {
        if(result.isNullOrEmpty()){
            arg.onFailure()
        }else{
            arg.onSucces(result)
        }

    }

    override fun doInBackground(vararg params: Unit?): String? {
        val url = URL("https://randomuser.me/api/?results=50&nat=fr")
        val httpClient = url.openConnection() as HttpURLConnection
        if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
            try {
                val stream = BufferedInputStream(httpClient.inputStream)
                val data: String = readStream(inputStream = stream)
                return data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                httpClient.disconnect()
            }
        } else {
            println("ERROR ${httpClient.responseCode}")
        }
        return null
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

}