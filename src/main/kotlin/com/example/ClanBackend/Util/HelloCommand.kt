package com.example.ClanBackend.Util

import com.example.ClanBackend.interfaces.ICommand
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder


object HelloCommand : ICommand {
    override fun execute(): List<String> {
        val homeDirectory = System.getProperty("user.home")
        var s: MutableList<String> = mutableListOf()
        try {
            val command = String.format("cmd.exe /c dir %s", homeDirectory)
            val process = Runtime.getRuntime().exec(command)
            val reader = BufferedReader(InputStreamReader(
                    process.inputStream))
            while (reader.readLine().also { s.add(it) } != null) {
                println("Script output: $s")
            }

        }catch (e:Exception){
            e.stackTrace
        }
        return s.toList()
    }
}