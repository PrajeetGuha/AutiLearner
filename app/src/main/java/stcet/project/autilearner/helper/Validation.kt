package stcet.project.autilearner.helper

import android.util.Patterns
import java.util.regex.Pattern

class Validation {

    fun validateEmail(email : String) : Int{
        return if (email.isEmpty())
            1
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            2
        else
            3
    }

    fun validatePassword(password : String) : Int{
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        return if (password.isEmpty())
            1
        else if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches())
            2
        else
            3
    }

    fun checkPasswordMatches(password: String, reenteredPassword: String) : Boolean{
        return password.compareTo(reenteredPassword) == 0
    }
}