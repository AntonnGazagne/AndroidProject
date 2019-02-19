package fr.isen.twibook

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.database.DataSnapshot






class HomeActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    private lateinit var articles : ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        CreateArticle.setOnClickListener{
            goCreateArticle()
        }

        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance().reference


        Wall.setOnClickListener{
            goPost()
        }
        database.child("Articles").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                articles = ArrayList<Article>()
                for (messageSnapshot in dataSnapshot.children) {
                    val auteur = messageSnapshot.child("auteur").value as String?
                    val photo = messageSnapshot.child("photo").value as String?
                    val description = messageSnapshot.child("description").value as String?
                    val date = messageSnapshot.child("date").value as String?
                    val titre = messageSnapshot.child("titre").value as String?
                    val id = messageSnapshot.child("id").value as String?
                    val article = Article(date,auteur,photo,titre,description,null,null,id)
                    articles.add(article)
                }
                val viewManager = LinearLayoutManager(this@HomeActivity)
                val viewAdapter = MyAdapterWall(articles)

                Wall.apply {
                    layoutManager = viewManager
                    adapter = viewAdapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeActivity, "Un des champs n'est pas rempli", Toast.LENGTH_LONG).show()
            }
        })

    }
    private fun goPost() {
            val intent = Intent(this@HomeActivity, PostActivity::class.java)
            val b = Bundle()
            b.putString("extraArticleKey", "-LZ4HSrGfp8xuh43PhOC") //Your id
            intent.putExtras(b) //Put your id to your next Intent
            startActivity(intent)
    }

    private fun goCreateArticle(){
        val intent = Intent(this@HomeActivity, CreateArticleActivity::class.java )
        startActivity(intent)
    }

}
