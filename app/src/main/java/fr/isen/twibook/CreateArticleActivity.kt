package fr.isen.twibook

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_create_article.*
import java.lang.Exception


class CreateArticleActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_article)

        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance().reference

        sharedPreferences = getSharedPreferences("Twibook", Context.MODE_PRIVATE)
        val pseudo = sharedPreferences.getString("pseudo","TestPseudo") ?: ""

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

            val article = Article(date,pseudo, titre,description)
            val child = database.child("Articles").child(database.push().key.toString())
            child.setValue(article)
        } else {
            Toast.makeText(this, "Un des champs n'est pas rempli", Toast.LENGTH_LONG).show()
        }
    }
}
