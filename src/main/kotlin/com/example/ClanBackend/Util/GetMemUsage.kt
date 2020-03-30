package com.example.ClanBackend.Util

import com.example.ClanBackend.interfaces.ICommand
import com.example.ClanBackend.model.metrics.Memory
import java.io.BufferedReader
import java.io.InputStreamReader

object GetMemUsage : ICommand {
    override fun execute(): Memory {
        val homeDirectory = System.getProperty("user.home")
        var result: String = ""

        var reader: BufferedReader? = null

        var memList: List<Map<String, String>>? = null

        try {

            val command = String.format("/home/kilian/IdeaProjects/Clan-Backend/src/main/resources/Scripts/getMem.sh")
            val process = Runtime.getRuntime().exec(command)

            reader = BufferedReader(InputStreamReader(
                    process.inputStream))

            memList = reader.readLines().map { value ->

                val splited = value.split(' ')


                    mapOf("total" to splited[0],"used" to splited[1])
            }


        } catch (e: Exception) {
            e.stackTrace
        } finally {
            reader?.close()
        }
        return Memory(memList?.get(0)?: emptyMap(), memList?.get(1)?: emptyMap())
    }
}