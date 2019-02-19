package fr.isen.twibook

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {

    var _nameText: EditText? = null
    var _firstnameText: EditText? = null
    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _validpasswordText: EditText? = null
    var _registerButton: Button? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        _nameText = findViewById(R.id.signup_activity_nom_edittxt)
        _firstnameText = findViewById(R.id.signup_activity_prenom_edittxt)
        _emailText = findViewById(R.id.signup_activity_email_edittxt)
        _passwordText = findViewById(R.id.signup_activity_mdp_edittxt)
        _validpasswordText = findViewById(R.id.signup_activity_validmdp_edittxt)
        _registerButton = findViewById(R.id.signup_activity_register_btn)

        signup_activity_register_btn.setOnClickListener { register() }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI()
    }

    fun register() {

        if (validateForm()) {
            createAccount(_emailText!!.text.toString(), _validpasswordText!!.text.toString())
        }



    }

    fun validateForm(): Boolean {
        val name = _nameText!!.text.toString()
        val firstname = _firstnameText!!.text.toString()
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
        val validpassword = _validpasswordText!!.text.toString()

        var valid = true

        if (name.isEmpty()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un nom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (firstname.isEmpty()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un prenom")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez une addresse mail")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (password.isEmpty() || password.length < 4 || password.length > 12) {
            valid = false
            val alertDialog = AlertDialog.Builder(this@SignupActivity)
            alertDialog.setTitle("oops!")
            alertDialog.setMessage("Entrez un mot de passe")
            alertDialog.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@SignupActivity,"Fermeture du dialog",Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = alertDialog.create()
            dialog.show()

        } else if (!(validpassword.equals(_passwordText!!.text.toString(),true))) {
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
