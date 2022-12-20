package stcet.project.autilearner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.helper.Validation

class RegisterFragment : Fragment(R.layout.fragment_register){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailLabel = view.findViewById<TextInputLayout>(R.id.emailLabel)
        val emailView = view.findViewById<TextInputEditText>(R.id.emailField)
        val passwordLabel = view.findViewById<TextInputLayout>(R.id.passwordLabel)
        val passwordView = view.findViewById<TextInputEditText>(R.id.passwordField)
        val reenteredPasswordLabel = view.findViewById<TextInputLayout>(R.id.confirmPasswordLabel)
        val reenteredPasswordView = view.findViewById<TextInputEditText>(R.id.confirmPasswordField)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val redirectText = view.findViewById<TextView>(R.id.redirectTologinText)
        val validate = Validation()
        val auth = AuthO()

        registerButton.setOnClickListener{
            checkAllFields(view, emailView,emailLabel,passwordView,passwordLabel,reenteredPasswordView,reenteredPasswordLabel,validate,auth)
        }
        redirectText.setOnClickListener{
            (activity as AuthActivity).useLoginFragment()
        }
        addTextListener(passwordView,passwordLabel)
        addTextListener(reenteredPasswordView,reenteredPasswordLabel)
        addTextListener(emailView,emailLabel)
    }

    private fun checkAllFields(
        view: View,
        emailView : TextInputEditText,
        emailLabel : TextInputLayout,
        passwordView : TextInputEditText,
        passwordLabel : TextInputLayout,
        reenteredPasswordView : TextInputEditText,
        reenteredPasswordLabel : TextInputLayout,
        validate : Validation,
        register : AuthO){

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

        if ( checked == 3 ){
            Log.d("VALIDATION","All data is validated")
            register.registerUser(view.context,emailView.text.toString(), passwordView.text.toString())
        }
    }

    private fun addTextListener(textView: TextInputEditText, textLabel : TextInputLayout){
        textView.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textLabel.error = null
            }
        })
    }
}