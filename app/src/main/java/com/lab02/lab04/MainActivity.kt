package com.lab02.lab04

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val Context.dataStore by preferencesDataStore(NotePrefs.PREFS_NAME)
    lateinit var notePrefs: NotePrefs
    lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var layoutConst: LinearLayout
        notePrefs = NotePrefs(dataStore)

        layoutConst = findViewById(R.id.LayoutConst)
        layoutConst.setBackgroundColor(Color.RED)

        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            lifecycleScope.launch {
                notePrefs.backgroundColor.collect { mycolor ->
                    layoutConst.setBackgroundColor(Integer.parseInt(mycolor.toString()))
                }
            }
        }


    }

}