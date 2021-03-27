package mx.com.satoritech.example.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.satoritech.example.R
import mx.com.satoritech.example.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var vBind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

}