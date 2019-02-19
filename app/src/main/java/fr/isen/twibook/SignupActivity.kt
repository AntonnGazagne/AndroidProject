package fr.isen.twibook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_activity_register_btn.setOnClickListener { register() }

    }

    fun register() {

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

        } else if (signup_activity_mdp_edittxt.text.toString().isEmpty()) {

            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un mot de passe")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (!(signup_activity_validmdp_edittxt.text.toString().equals(signup_activity_mdp_edittxt.text.toString(),true))) {
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
