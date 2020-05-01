package com.example.ClanBackend.Util.commandControl

import com.example.ClanBackend.interfaces.ICommand
import java.io.BufferedReader
import java.io.InputStreamReader


object GetCpuUsage : ICommand<String> {
    override fun execute(args:List<String>): String{
        var result: String = ""
        var reader: BufferedReader? = null

        try {
            val command = String.format("src/main/resources/Scripts/getCPU.sh")
            val process = Runtime.getRuntime().exec(command)

            reader = BufferedReader(InputStreamReader(
                    process.inputStream))
            result = reader.readLines().last()
        } catch (e: Exception) {
            throw e
        }finally {
            reader?.close()
        }
        return result
    }
}