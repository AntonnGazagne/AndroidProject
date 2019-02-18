package fr.isen.twibook

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_article.*


class CreateArticleActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_article)

        sharedPreferences = getSharedPreferences("Twibook", Context.MODE_PRIVATE)
        val pseudo = sharedPreferences.getString("pseudo","") ?: ""

        Post.setOnClickListener{
            onPost(pseudo)
        }
    }

    private fun onPost(pseudo : String) {
        if (descriptionArticle.text.isNotEmpty() && titreArticle.text.isNotEmpty()) {
            val titre = titreArticle.text.toString()
            val description = descriptionArticle.text.toString()
            val cal = Calendar.getInstance()
            val formater = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
            val date = formater.format(cal.time)

            val json = getJsonData(titre, pseudo, description, date)

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Articles")
            //myRef.setValue(json)
        } else {
            Toast.makeText(this, "Un des champs n'est pas rempli", Toast.LENGTH_LONG).show()
        }
    }

    private fun getJsonData(titre: String, auteur: String, description : String, date: String) : String{
        val jsonObject = JSONObject()
        jsonObject.put("Titre", titre)
        jsonObject.put("Auteur", auteur)
        jsonObject.put("Description", description)
        jsonObject.put("date", date)

        return jsonObject.toString()
    }
}
