package com.example.ClanBackend.controller

import com.example.ClanBackend.service.mongoDb.MongoHandler
import org.springframework.web.bind.annotation.GetMapping
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

}