package stcet.project.autilearner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import stcet.project.autilearner.helper.Identity
import stcet.project.autilearner.helper.Validation

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.registerbutton).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.registerbutton -> {
                checkAllFields()
            }
        }
    }

    private fun checkAllFields(){
        val validate = Validation()
        val emaillabel = findViewById<TextInputLayout>(R.id.emailLabel)
        val emailview = findViewById<TextInputEditText>(R.id.emailField)
        val passwordlabel = findViewById<TextInputLayout>(R.id.passwordLabel)
        val passwordview = findViewById<TextInputEditText>(R.id.passwordField)
        val reenteredpasswordlabel = findViewById<TextInputLayout>(R.id.confirmPasswordLabel)
        val reenteredpasswordview = findViewById<TextInputEditText>(R.id.confirmPasswordField)
        var checked = 0

        when(validate.validateEmail(emailview.text.toString())){

            1 -> emaillabel.setError("Expected an email address")
            2 -> emaillabel.setError("Expected a valid email address")
            3 -> {
                emaillabel.setError(null)
                checked += 1
            }
        }

        when(validate.validatePassword(passwordview.text.toString())){

            1 -> passwordlabel.setError("Expected a password")
            2 -> passwordlabel.setError("8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special character atleast")
            3 -> {
                passwordlabel.setError(null)
                checked += 1
            }
        }

        if(!validate.checkPasswordMatches(passwordview.text.toString(), reenteredpasswordview.text.toString()))
            reenteredpasswordlabel.setError("Re-entered password don't match")
        else{
            reenteredpasswordlabel.setError(null)
            checked += 1
        }

        if ( checked == 3 ){
            Log.d("VALIDATION","All data is validated")
            val register = Identity()
            register.registerUser(this,emailview.text.toString(), passwordview.text.toString())
        }
    }
}