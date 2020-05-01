package com.example.ClanBackend.Util.commandControl

import com.example.ClanBackend.interfaces.ICommand
import java.lang.Exception

object StartHandleSinusbot : ICommand<Unit> {
    override fun execute(argument: List<String>): Unit {
        try {
            val command = String.format("src/main/resources/Scripts/handleSinusbot.sh %s", argument.first())
            val process = Runtime.getRuntime().exec(command)
        } catch (e: Exception) {
            throw e
        }
    }
}