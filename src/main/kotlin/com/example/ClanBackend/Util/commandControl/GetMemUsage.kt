package com.example.ClanBackend.Util.commandControl

import com.example.ClanBackend.interfaces.ICommand
import com.example.ClanBackend.model.metrics.Memory
import java.io.BufferedReader
import java.io.InputStreamReader

object GetMemUsage : ICommand<Memory> {
    override fun execute(args: List<String>): Memory {
        var result: String = ""

        var reader: BufferedReader? = null

        var memList: List<Map<String, String>>? = null

        try {

            val command = String.format("src/main/resources/Scripts/getMem.sh")
            val process = Runtime.getRuntime().exec(command)

            reader = BufferedReader(InputStreamReader(
                    process.inputStream))

            memList = reader.readLines().map { value: String ->
                val splited = value.split(' ')
                    mapOf("total" to splited[0],"used" to splited[1])
            }
        } catch (e: Exception) {
            throw e
        } finally {
            reader?.close()
        }
        return Memory(memList?.get(0)?: emptyMap(), memList?.get(1)?: emptyMap())
    }
}