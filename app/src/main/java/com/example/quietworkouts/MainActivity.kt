package com.example.quietworkouts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.DateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var nextText: TextView
    private lateinit var toggleButton: Button
    private lateinit var previewButton: Button

    private val requestNotifPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                WorkoutScheduler.scheduleNext(this)
            }
            refreshUi()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        nextText = findViewById(R.id.nextText)
        toggleButton = findViewById(R.id.toggleButton)
        previewButton = findViewById(R.id.previewButton)

        toggleButton.setOnClickListener {
            if (WorkoutScheduler.isEnabled(this)) {
                WorkoutScheduler.cancel(this)
                refreshUi()
            } else {
                startWithPermissionCheck()
            }
        }

        previewButton.setOnClickListener {
            val w = Workouts.random()
            statusText.text = "Sample: ${w.title}\n${w.description}"
        }

        refreshUi()
    }

    override fun onResume() {
        super.onResume()
        refreshUi()
    }

    private fun startWithPermissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                requestNotifPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                return
            }
        }
        WorkoutScheduler.scheduleNext(this)
        refreshUi()
    }

    private fun refreshUi() {
        val enabled = WorkoutScheduler.isEnabled(this)
        toggleButton.text = if (enabled) "Stop" else "Start"
        statusText.text = if (enabled) {
            "Running. You'll get a workout every 1-3 hours when on WiFi and unplugged."
        } else {
            "Stopped. Tap Start to begin."
        }

        val next = WorkoutScheduler.nextTriggerAt(this)
        nextText.text = if (enabled && next > 0) {
            "Next scheduled: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(next))
        } else {
            ""
        }
    }
}
