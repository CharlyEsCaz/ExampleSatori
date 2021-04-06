package mx.com.satoritech.example.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.satoritech.example.R
import mx.com.satoritech.example.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var vBind: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }
}