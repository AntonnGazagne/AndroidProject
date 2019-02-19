package fr.isen.twibook

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = Intent(this@HomeActivity, LoginActivity::class.java )
        startActivity(intent)

        CreateArticle.setOnClickListener{
            goCreateArticle()
        }

    }

    private fun goCreateArticle(){
        val intent = Intent(this@HomeActivity, CreateArticleActivity::class.java )
        startActivity(intent)
    }

}
