package com.lab02.lab04

import android.graphics.Color
import android.graphics.Typeface
import android.webkit.WebSettings
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotePrefs(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveNoteBackgroundColor(color : String) {
        dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR] = color
        }
    }
    suspend fun saveNoteTypeface(type:String){
        dataStore.edit { preferences ->
            preferences[TYPE_FACE]=type
        }
    }
    suspend fun saveNoteTextSize(size:String){
        dataStore.edit { preferences ->
            preferences[TEXT_SIZE]=size
        }
    }

    val backgroundColor: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[BACKGROUND_COLOR] ?: Color.CYAN.toString()
        }

    val typeface: Flow<String>
        get()=dataStore.data.map { preferences ->
            preferences[TYPE_FACE] ?: Typeface.DEFAULT.toString()
        }

    val textSize: Flow<String>
        get()=dataStore.data.map { preferences ->
            preferences[TEXT_SIZE] ?: WebSettings.TextSize.NORMAL.toString()
        }


    companion object {
        val PREFS_NAME = "PREFS_NAME"
        private val BACKGROUND_COLOR = stringPreferencesKey("key_app_background_color")
        private val TYPE_FACE = stringPreferencesKey("key_app_type_face")
        private val TEXT_SIZE = stringPreferencesKey("key_app_text_size")

    }
}