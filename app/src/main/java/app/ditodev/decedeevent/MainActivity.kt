package app.ditodev.decedeevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.ditodev.decedeevent.databinding.ActivityMainBinding
import app.ditodev.decedeevent.ui.settings.SettingsDataStore
import app.ditodev.decedeevent.ui.settings.SettingsFactory
import app.ditodev.decedeevent.ui.settings.SettingsViewModel
import app.ditodev.decedeevent.ui.settings.dataStore
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm : SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_finished, R.id.navigation_upcoming,R.id.navigation_favorite,R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val pref = SettingsDataStore.getInstance(this.dataStore)
        val factory = SettingsFactory(pref)
        vm = ViewModelProvider(this, factory)[SettingsViewModel::class.java]
        vm.getThemeSettings().observe(this){
            if(it){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}