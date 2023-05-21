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
import stcet.project.autilearner.R
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.home.MainActivity

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
//        val sharedPreferences = context?.getSharedPreferences("my_data", Context.MODE_PRIVATE)

        loginButton.setOnClickListener{
            if (checkAllFields(view,emailView,emailLabel,passwordView,passwordLabel)){
//                if (sharedPreferences != null) {
                    auth.loginUser(emailView.text.toString(),passwordView.text.toString(),view)
//                }
//                if (auth.loginUser(emailView.text.toString(),passwordView.text.toString())) {
//                    Log.d("LOGIN", "User successfully logged in")
//                    val main = Intent(view.context, MainActivity::class.java)
//                    view.context.startActivity(main)
//                }
//                else{
//                    Log.d("LOGIN","User credential is wrong or not registered")
//                    Toast.makeText(view.context,getString(R.string.login_issues),Toast.LENGTH_SHORT).show()
//                }
            }
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
        passwordLabel: TextInputLayout) : Boolean {

        var checked = 0

        if (emailView.text?.isEmpty() == true)
            emailLabel.error = getString(R.string.email_empty)
        else{
            emailLabel.error = null
            checked += 1
        }

        if (passwordView.text?.isEmpty() == true)
            passwordLabel.error = getString(R.string.password_empty)
        else{
            passwordLabel.error = null
            checked += 1
        }

        return checked == 2
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