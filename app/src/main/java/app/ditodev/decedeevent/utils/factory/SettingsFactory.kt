package app.ditodev.decedeevent.utils.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.ditodev.decedeevent.ui.settings.SettingsDataStore
import app.ditodev.decedeevent.ui.settings.SettingsViewModel

class SettingsFactory(private val ref: SettingsDataStore) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(ref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }
}