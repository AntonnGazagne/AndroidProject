package fr.isen.twibook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.R.attr.key
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val database = FirebaseDatabase.getInstance()

        val articleKey = intent.extras!!.getString("extraArticleKey")
        val article = database.getReference("Articles").child(articleKey)

        AuthorNameView.text = article.child("auteur").toString()
        DescriptionView.setText(article.child("description").toString())
        val image = article.child("Photo").toString()
        val res = "R.drawable" + image
        val id = resources.getIdentifier(res, "drawable", packageName)
        PhotoView.setImageResource(id)

        article.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })


    }



}
