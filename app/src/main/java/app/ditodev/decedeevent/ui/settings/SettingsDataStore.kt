package app.ditodev.decedeevent.ui.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

private val THEME_KEY = booleanPreferencesKey("theme_setting")

class SettingsDataStore private constructor(private val dataStore: DataStore<Preferences>) {
    fun getThemeSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSettings(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingsDataStore? = null
        fun getInstance(dataStore: DataStore<Preferences>): SettingsDataStore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingsDataStore(dataStore).also {
                    INSTANCE = it
                }
            }
        }
    }
}
