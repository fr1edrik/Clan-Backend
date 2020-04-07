package com.example.ClanBackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServersController {
    @GetMapping("/servers", produces = ["application/json"])
    fun getServers(): String {
        return ""
    }

    @GetMapping("/servers/ts3", produces = ["application/json"])
    fun getTs(): String {
        return "Ts3"
    }

}