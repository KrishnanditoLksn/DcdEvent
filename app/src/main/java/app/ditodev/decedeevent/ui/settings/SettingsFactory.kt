package app.ditodev.decedeevent.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SettingsFactory(private val ref: SettingsDataStore) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(ref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }
}