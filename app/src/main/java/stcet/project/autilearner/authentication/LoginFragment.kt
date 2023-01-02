package stcet.project.autilearner.authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import stcet.project.autilearner.R
import stcet.project.autilearner.helper.AuthO

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailLabel = view.findViewById<TextInputLayout>(R.id.emailLabel)
        val emailView = view.findViewById<TextInputEditText>(R.id.emailField)
        val passwordLabel = view.findViewById<TextInputLayout>(R.id.passwordLabel)
        val passwordView = view.findViewById<TextInputEditText>(R.id.passwordField)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val redirectText = view.findViewById<TextView>(R.id.redirectToRegisterText)
        val auth = AuthO()

        loginButton.setOnClickListener{
            checkAllFields(view,emailView,emailLabel,passwordView,passwordLabel,auth)
        }
        redirectText.setOnClickListener{
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        addTextListener(passwordView,passwordLabel)
        addTextListener(emailView,emailLabel)
    }

    private fun checkAllFields(
        view: View,
        emailView: TextInputEditText,
        emailLabel: TextInputLayout,
        passwordView: TextInputEditText,
        passwordLabel: TextInputLayout,
        login: AuthO) {

        var checked = 0

        if (emailView.text?.isEmpty() == true)
            emailLabel.error = "Email required"
        else{
            emailLabel.error = null
            checked += 1
        }

        if (passwordView.text?.isEmpty() == true)
            passwordLabel.error = "Password required"
        else{
            passwordLabel.error = null
            checked += 1
        }

        if (checked == 2)
            login.loginUser(view.context,emailView.text.toString(),passwordView.text.toString())
    }

    private fun addTextListener(textView: TextInputEditText, textLabel : TextInputLayout) {
        textView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textLabel.error = null
            }
        })
    }
}