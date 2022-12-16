package stcet.project.autilearner.helper

import android.util.Patterns
import java.util.regex.Pattern

class Validation {

    fun validateEmail(email : String) : Boolean{
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password : String) : Boolean{
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()
    }

    fun checkPasswordMatches(password: String, reenteredPassword: String) : Boolean{
        return password.compareTo(reenteredPassword) == 0
    }
}