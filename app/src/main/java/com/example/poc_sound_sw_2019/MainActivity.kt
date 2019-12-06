package com.example.poc_sound_sw_2019

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val music = resources.getStringArray(R.array.musicArray)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, music)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {}
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

            mp = MediaPlayer.create(this, R.raw.hollow)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        var vol = progress / 100f
                        mp.setVolume(vol, vol)
                    }
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )
    }

    fun toast(view : View) {
        val duration = Toast.LENGTH_SHORT

        if (mp.isPlaying) {
            button.setBackgroundResource(R.drawable.play)
            mp.pause()
        } else {
            button.setBackgroundResource(R.drawable.stop)
            mp.start()
        }
    }
}

