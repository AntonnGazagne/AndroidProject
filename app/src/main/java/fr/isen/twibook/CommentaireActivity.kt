package fr.isen.twibook

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_commentaire.*
import org.json.JSONObject
import java.io.FileOutputStream
import java.io.IOException

class CommentaireActivity : AppCompatActivity() {

    private val JSON_FILE = "json_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commentaire)

        valideCommentaire.setOnClickListener{

            publication(commentaireText.text.toString())
            testPublication()
            //val intent = Intent( this, Activity::class.java)
            //startActivity(intent)
        }
    }

    fun publication(monCommentaire: String){

        if(monCommentaire.isNotEmpty()) {

            val jsonString = "{ 'commentaire' : '$monCommentaire'}"
            val fOut : FileOutputStream
            try {
                fOut = openFileOutput(JSON_FILE, Context.MODE_PRIVATE)
                fOut.write(jsonString.toByteArray())
                fOut.close()
            }catch (e: IOException) {
                Toast.makeText(this, "Impossible de sauvegarder le commentaire", Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText( this, "Vous devez rentrer un commentaire", Toast.LENGTH_LONG).show()
        }

    }

    private fun testPublication() {
        val fis = openFileInput(JSON_FILE)
        val result = fis.bufferedReader().use { it.readText()}
        fis.close()
        //Toast.makeText(this, result, Toast.LENGTH_LONG).show()

        val jsonData = JSONObject(result)
        val comment = jsonData.optString("commentaire")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("commentaire enregistré")
        builder.setMessage("VOTRE COMMENTAIRE : $comment")
        builder.create().show()
    }
}
