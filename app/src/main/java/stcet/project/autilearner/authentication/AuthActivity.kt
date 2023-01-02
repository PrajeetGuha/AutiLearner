package stcet.project.autilearner.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import stcet.project.autilearner.R

class AuthActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val register = intent.getStringExtra("Register?")
        val navFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment) as NavHostFragment
        navController = navFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.auth_nav)

        val start = if (register == "1") R.id.registerFragment else R.id.loginFragment
        navGraph.setStartDestination(start)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onBackPressed() {
        if (doubleBackPressed)
            return super.onBackPressed()
        else{
            doubleBackPressed = true
            Toast.makeText(this,"Press Back Again to Exit App", Toast.LENGTH_SHORT).show()
        }
    }
}