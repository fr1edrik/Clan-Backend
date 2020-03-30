package com.example.ClanBackend.Util

import com.example.ClanBackend.interfaces.ICommand
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder


object GetCpuUsage : ICommand {
    override fun execute(): String{
        val homeDirectory = System.getProperty("user.home")
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