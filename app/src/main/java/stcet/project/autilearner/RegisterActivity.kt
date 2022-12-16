package stcet.project.autilearner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
        val emailLabel = findViewById<TextInputLayout>(R.id.emailLabel)
        val emailView = findViewById<TextInputEditText>(R.id.emailField)
        val passwordLabel = findViewById<TextInputLayout>(R.id.passwordLabel)
        val passwordView = findViewById<TextInputEditText>(R.id.passwordField)
        val reenteredPasswordLabel = findViewById<TextInputLayout>(R.id.confirmPasswordLabel)
        val reenteredPasswordView = findViewById<TextInputEditText>(R.id.confirmPasswordField)
        var checked = 0

        when(validate.validateEmail(emailView.text.toString())){

            1 -> emailLabel.error = "Expected an email address"
            2 -> emailLabel.error = "Expected a valid email address"
            3 -> {
                emailLabel.error = null
                checked += 1
            }
        }

        when(validate.validatePassword(passwordView.text.toString())){

            1 -> passwordLabel.error = "Expected a password"
            2 -> passwordLabel.error = "8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special character atleast"
            3 -> {
                passwordLabel.error = null
                checked += 1
            }
        }

        if(!validate.checkPasswordMatches(passwordView.text.toString(), reenteredPasswordView.text.toString()))
            reenteredPasswordLabel.error = "Re-entered password don't match"

        else{
            reenteredPasswordLabel.error = null
            checked += 1
        }

        passwordView.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordLabel.error = null
            }
        })

        reenteredPasswordView.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                reenteredPasswordLabel.error = null
            }
        })

        if ( checked == 3 ){
            Log.d("VALIDATION","All data is validated")
            val register = Identity()
            register.registerUser(this,emailView.text.toString(), passwordView.text.toString())
        }
    }
}