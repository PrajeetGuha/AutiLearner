package stcet.project.autilearner.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import stcet.project.autilearner.R
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.helper.Validation
import stcet.project.autilearner.home.MainActivity

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
//        val sharedPreferences = context?.getSharedPreferences("my_data", Context.MODE_PRIVATE)

        registerButton.setOnClickListener{
            if (checkAllFields(emailView,emailLabel,passwordView,passwordLabel,reenteredPasswordView,reenteredPasswordLabel,validate)){
                GlobalScope.launch{
//                    if (sharedPreferences != null) {
                        auth.registerUser(emailView.text.toString(),passwordView.text.toString(),view)
//                    }
                }

//                if (registered){
//                    Log.d("REGISTER","User successfully registered")
//                    val main = Intent(view.context, MainActivity::class.java)
//                    view.context.startActivity(main)
//                }
//                else{
//                    Log.d("REGISTER","User registration failed")
//                    Toast.makeText(view.context,getString(R.string.register_issue),Toast.LENGTH_SHORT).show()
//                }
            }
        }
        redirectText.setOnClickListener{
            view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        addTextListener(passwordView,passwordLabel)
        addTextListener(reenteredPasswordView,reenteredPasswordLabel)
        addTextListener(emailView,emailLabel)
    }

    private fun checkAllFields(
        emailView : TextInputEditText,
        emailLabel : TextInputLayout,
        passwordView : TextInputEditText,
        passwordLabel : TextInputLayout,
        reenteredPasswordView : TextInputEditText,
        reenteredPasswordLabel : TextInputLayout,
        validate : Validation): Boolean {

        var checked = 0

        when(validate.validateEmail(emailView.text.toString())){

            1 -> emailLabel.error = getString(R.string.email_empty)
            2 -> emailLabel.error = getString(R.string.email_invalid)
            3 -> {
                emailLabel.error = null
                checked += 1
            }
        }

        when(validate.validatePassword(passwordView.text.toString())){

            1 -> passwordLabel.error = getString(R.string.password_empty)
            2 -> passwordLabel.error = getString(R.string.password_invalid)
            3 -> {
                passwordLabel.error = null
                checked += 1
            }
        }

        if(!validate.checkPasswordMatches(passwordView.text.toString(), reenteredPasswordView.text.toString()))
            reenteredPasswordLabel.error = getString(R.string.confirm_password_not_matching)

        else{
            reenteredPasswordLabel.error = null
            checked += 1
        }

        return checked == 3
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