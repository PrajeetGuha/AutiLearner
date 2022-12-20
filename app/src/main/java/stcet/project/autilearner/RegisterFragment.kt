package stcet.project.autilearner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.helper.Validation

class RegisterFragment : Fragment(R.layout.fragment_register), View.OnClickListener {

    private lateinit var fView : View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fView = view

        view.findViewById<Button>(R.id.registerbutton).setOnClickListener(this)
        view.findViewById<TextView>(R.id.redirectTologinText).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.registerbutton -> {
                checkAllFields()
            }
            R.id.redirectTologinText -> {
                (activity as AuthActivity).useLoginFragment()
            }
        }
    }

    private fun checkAllFields(){
        val validate = Validation()
        val emailLabel = fView.findViewById<TextInputLayout>(R.id.emailLabel)
        val emailView = fView.findViewById<TextInputEditText>(R.id.emailField)
        val passwordLabel = fView.findViewById<TextInputLayout>(R.id.passwordLabel)
        val passwordView = fView.findViewById<TextInputEditText>(R.id.passwordField)
        val reenteredPasswordLabel = fView.findViewById<TextInputLayout>(R.id.confirmPasswordLabel)
        val reenteredPasswordView = fView.findViewById<TextInputEditText>(R.id.confirmPasswordField)
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

        passwordView.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordLabel.error = null
            }
        })

        reenteredPasswordView.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                reenteredPasswordLabel.error = null
            }
        })

        if ( checked == 3 ){
            Log.d("VALIDATION","All data is validated")
            val register = AuthO()
            register.registerUser(fView.context,emailView.text.toString(), passwordView.text.toString())
        }
    }
}