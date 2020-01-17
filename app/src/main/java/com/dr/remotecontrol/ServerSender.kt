package com.dr.remotecontrol

import android.util.Log
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class ServerSender(val ip: String) {

    companion object {
        private val TAG = ServerSender::class.java.name
    }

    fun sendMessage(message: String) {
        try {
            val socket = Socket(ip, 8080)
            while (socket.isConnected && !socket.isClosed) {
                val out = PrintWriter(
                    BufferedWriter(
                        OutputStreamWriter(socket.getOutputStream())
                    ), true
                )
                out.println(message)

                out.close()
                socket.close()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message)
        }
    }
}
