package com.lab02.lab04

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val Context.dataStore by preferencesDataStore(NotePrefs.PREFS_NAME)
    lateinit var notePrefs: NotePrefs
    lateinit var btnBlue:Button
    lateinit var btnRed:Button

    lateinit var btnSerif:Button
    lateinit var btnMonospace:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBlue = findViewById(R.id.blue)
        btnRed = findViewById(R.id.red)

        btnSerif=findViewById(R.id.serif)
        btnMonospace=findViewById(R.id.monospace)

        lateinit var layoutConst : LinearLayout

        lateinit var txtPeriodo : TextView
        lateinit var edtPeriodo : EditText

        notePrefs=NotePrefs(dataStore)

        layoutConst = findViewById(R.id.LayoutConst)

        txtPeriodo=findViewById(R.id.periodo)
        edtPeriodo=findViewById(R.id.periodoEdit)

        //Recogiendo el color
        lifecycleScope.launch {
            notePrefs.backgroundColor.collect { mycolor ->
                layoutConst.setBackgroundColor(Integer.parseInt(mycolor))

            }
        }

        //Guardando el color por el respectivo boton
        btnBlue.setOnClickListener{
            lifecycleScope.launch{
                notePrefs.saveNoteBackgroundColor(Color.BLUE.toString())
            }
        }

        btnRed.setOnClickListener{
            lifecycleScope.launch{
                notePrefs.saveNoteBackgroundColor(Color.RED.toString())
            }
        }

        btnSerif.setOnClickListener{
            lifecycleScope.launch{
                notePrefs.saveNoteTypeface(Typeface.SERIF.toString())
            }
        }
        btnMonospace.setOnClickListener{
            lifecycleScope.launch{
                notePrefs.saveNoteTypeface(Typeface.MONOSPACE.toString())
            }
        }

    }

}