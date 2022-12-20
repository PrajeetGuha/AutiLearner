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

class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {
    private lateinit var fView : View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fView = view

        view.findViewById<Button>(R.id.loginButton).setOnClickListener(this)
        view.findViewById<TextView>(R.id.redirectToRegisterText).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.loginButton -> {
                val emailView = fView.findViewById<TextInputEditText>(R.id.emailField)
                val passwordView = fView.findViewById<TextInputEditText>(R.id.passwordField)
                val auth = AuthO()
                auth.loginUser(fView.context,emailView.text.toString(),passwordView.text.toString())
            }
            R.id.redirectToRegisterText -> {
                (activity as AuthActivity).useRegisterFragment()
            }
        }
    }
}