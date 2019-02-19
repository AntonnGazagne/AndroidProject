package fr.isen.twibook

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        signup_activity_register_btn.setOnClickListener { register() }

    }

    fun register() {
        if (validateForm()) {
            createAccount(signup_activity_email_edittxt.text.toString(), signup_activity_validmdp_edittxt.text.toString())
        }
    }

    fun validateForm(): Boolean {
        var valid = true

        if (signup_activity_nom_edittxt.text.toString().isEmpty()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un nom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (signup_activity_prenom_edittxt.text.toString().isEmpty()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un prenom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (signup_activity_email_edittxt.text.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(signup_activity_email_edittxt.text.toString()).matches()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez une addresse mail")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (signup_activity_mdp_edittxt.text.toString().isEmpty() || signup_activity_mdp_edittxt.text.toString().length < 4 || signup_activity_mdp_edittxt.text.toString().length > 12) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un mot de passe")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (!(signup_activity_validmdp_edittxt.text.toString().equals(signup_activity_mdp_edittxt.text.toString(),true))) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Mots de passe differents")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()
        }
        return valid
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")

        showProgressDialog()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI()
                    startActivity(Intent(this@SignupActivity, HomeActivity::class.java ))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI()
                }

                // [START_EXCLUDE]
                hideProgressDialog()
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }

    private fun updateUI() {
        hideProgressDialog()
    }

    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(this)
    }

    fun showProgressDialog() {
        progressDialog.setMessage("Loading...")
        progressDialog.isIndeterminate = true
        progressDialog.show()
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    companion object {
        private const val TAG = "SignupActivity"
    }

}
