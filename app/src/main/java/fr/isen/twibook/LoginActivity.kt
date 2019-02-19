package fr.isen.twibook

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_activity_connectbutton.setOnClickListener { login(login_activity_email_edittxt.text.toString(), login_activity_password_edittxt.text.toString()) }

        login_activity_inscrirebutton.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    fun validate(): Boolean {
        var valid = true

        val email = login_activity_email_edittxt.text.toString()
        val password = login_activity_password_edittxt.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_activity_email_edittxt.error = "entrez une addresse mail valide"
            valid = false
        } else {
            login_activity_email_edittxt.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 12) {
            login_activity_password_edittxt.error = "entre 4 et 12 caracteres alphanumeriques"
            valid = false
        } else {
            login_activity_password_edittxt.error = null
        }

        return valid
    }

    private fun login(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validate()) {
            return
        }

        showProgressDialog()

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI()
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI()
                }
                
                hideProgressDialog()

            }
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
        private val TAG = "LoginActivity"
    }

}




