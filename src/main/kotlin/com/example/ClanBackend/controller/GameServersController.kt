package com.example.clanBackend.controller

import com.example.ClanBackend.service.spark.SparkHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameServersController {
    @GetMapping("/servers/gameServers", produces = ["application/json"])
    fun getGameServers(): String {
        return ""
    }

    @GetMapping("/servers/gameServers/mb-warband", produces = ["application/json"])
    fun getMbWarband(): Any {
        return SparkHandler.readLog()
    }

    @GetMapping("/servers/gameServers/mb-bannerlord", produces = ["application/json"])
    fun getMbBannerlord(): String {
        return ""
    }
}