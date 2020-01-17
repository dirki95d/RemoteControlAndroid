package com.dr.remotecontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupControls()
    }

    private fun setupControls() {
        rewind.setOnClickListener { view ->
            launchSuspendSendMessage("rewind")
        }

        playPause.setOnClickListener { view ->
            launchSuspendSendMessage("play")
        }

        forward.setOnClickListener { view ->
            launchSuspendSendMessage("forward")
        }
    }

    private fun launchSuspendSendMessage(message: String) {
        lifecycleScope.launch {
            sendMessage(message)
        }
    }

    private suspend fun sendMessage(message: String) {
        withContext(Dispatchers.IO) {
            val ipAdress = ip_address.text.toString()
            if (ipAdress.isNotBlank()) {
                val serverSender = ServerSender(ip_address.text.toString())
                serverSender.sendMessage(message)
            }
        }
    }
}
