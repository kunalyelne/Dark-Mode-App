package com.kyodude.darkmodeapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.kyodude.darkmodeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private val NIGHTMODE: String = "NightMode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appSettingsPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs",0)
        val appSettingsPrefsEditor: SharedPreferences.Editor = appSettingsPrefs.edit()
        val isNightModeOn: Boolean = appSettingsPrefs.getBoolean(NIGHTMODE, false)

        if(isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.switchBtn.setText("Disable Dark Mode")
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.switchBtn.setText("Enable Dark Mode")
        }

        binding.switchBtn.setOnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                appSettingsPrefsEditor.putBoolean(NIGHTMODE, false)
                appSettingsPrefsEditor.apply()
                binding.switchBtn.setText("Enable Dark Mode")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                appSettingsPrefsEditor.putBoolean(NIGHTMODE, true)
                appSettingsPrefsEditor.apply()
                binding.switchBtn.setText("Disable Dark Mode")
            }
        }
    }
}