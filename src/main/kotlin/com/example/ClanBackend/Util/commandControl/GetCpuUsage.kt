package com.example.ClanBackend.Util.commandControl

import com.example.ClanBackend.interfaces.ICommand
import java.io.BufferedReader
import java.io.InputStreamReader


object GetCpuUsage : ICommand<String> {
    override fun execute(): String{
        var result: String = ""
        var reader: BufferedReader? = null

        try {
            val command = String.format("/home/kilian/IdeaProjects/Clan-Backend/src/main/resources/Scripts/getCPU.sh")
            val process = Runtime.getRuntime().exec(command)

            reader = BufferedReader(InputStreamReader(
                    process.inputStream))
            result = reader.readLines().last()
        } catch (e: Exception) {
            e.stackTrace
        }finally {
            reader?.close()
        }
        return result
    }
}