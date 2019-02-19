package fr.isen.twibook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {

    var _nameText: EditText? = null
    var _firstnameText: EditText? = null
    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _validpasswordText: EditText? = null
    var _registerButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_activity_register_btn!!.setOnClickListener { register() }

    }

    fun register() {

        _nameText = findViewById(R.id.signup_activity_nom_edittxt)
        _firstnameText = findViewById(R.id.signup_activity_prenom_edittxt)
        _emailText = findViewById(R.id.signup_activity_email_edittxt)
        _passwordText = findViewById(R.id.signup_activity_mdp_edittxt)
        _validpasswordText = findViewById(R.id.signup_activity_validmdp_edittxt)

        if (signup_activity_nom_edittxt.text.toString().isEmpty()) {
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un nom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (signup_activity_prenom_edittxt.text.toString().isEmpty()) {
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un prenom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (signup_activity_email_edittxt.text.toString().isEmpty()) {
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez une addresse mail")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (_passwordText!!.text.toString().isEmpty()) {

            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un mot de passe")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (!(_validpasswordText!!.text.toString().equals(_passwordText!!.text.toString(),true))) {
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Mots de passe differents")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        }
    }
}
