package com.example.stopwatchpractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lapCount = 1
    private var recordList = mutableListOf<LapTimeList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(recordList)
        labTimeRecyclerView.adapter = adapter


        playStopFAB.setOnClickListener {
            isRunning = !isRunning //this because the FAB has a two different function (start and pause)

            if (isRunning) {
                timerStart()
            } else {
                timerPause()
            }
        }

        lapTimeBtn.setOnClickListener{
            if (!isRunning) {
                Toast.makeText(this, getString(R.string.no_lapTime), Toast.LENGTH_SHORT).show()
            } else {
                val currentLapRecord = LapTimeList(lapCount, getLapTime())
                recordList.add(currentLapRecord)
                lapCount++
                adapter.notifyDataSetChanged()
            }
        }

        refreshFAB.setOnClickListener {
            resetTimer()
            adapter.notifyDataSetChanged()
        }


    }

    private fun timerStart() {
        playStopFAB.setImageResource(R.drawable.ic_baseline_pause_24) // Change the playstop FAB's image resource as soon as user touches start btn.

        timerTask = timer(period = 10) {
            //timer(period(period = 10): Doing Certain task every 10 ms
            time++
            val sec = time / 100
            val millySec = time % 100

            runOnUiThread{
                //Since the timer function is a worker thread, which cannot handle a UI, using runOnUiTread to manage the UI-Level task.
                timeDisplay1.text = "$sec"
                millySecDisplay.text = "$millySec"
            }
        }

    }

    private fun timerPause() {
        playStopFAB.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel() //End timer.
    }

    private fun getLapTime() : String {
        val lapTimeSec = timeDisplay1.text.toString()
        val lapTimeMillySec = millySecDisplay.text.toString()
        return "lap $lapCount : $lapTimeSec : $lapTimeMillySec"
    }

    private fun resetTimer() {
        timerTask?.cancel()
        time = 0
        isRunning = false
        lapCount = 1
        playStopFAB.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timeDisplay1.text = "0"
        millySecDisplay.text = "00"
        recordList.clear()
    }
}