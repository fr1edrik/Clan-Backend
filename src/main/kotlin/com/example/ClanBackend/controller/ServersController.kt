package com.example.ClanBackend.controller

import com.example.ClanBackend.Util.commandControl.CommandControl
import com.example.ClanBackend.interfaces.StartType
import com.example.ClanBackend.service.mongoDb.MongoHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ServersController {
    @GetMapping("/servers", produces = ["application/json"])
    fun getServers(): String {
        MongoHandler.getDataBase()
        return "hello"
    }

    @GetMapping("/servers/ts3", produces = ["application/json"])
    fun getTs(): String {
        return "Ts3"
    }

    @PostMapping("/servers/sinusbot", produces = ["application/json"])
    fun startHandleSinusbot(@RequestParam("startHandle") startHandle: String){
        var parsedHandle: String?=null
        when(startHandle){
            StartType.START.value -> parsedHandle=StartType.START.value
            StartType.STOP.value -> parsedHandle=StartType.STOP.value
            StartType.RESTART.value -> parsedHandle=StartType.RESTART.value
        }
        if (parsedHandle != null) {
            CommandControl.StartHandleSinusbot.execute(listOf(parsedHandle))
        }
   }

}