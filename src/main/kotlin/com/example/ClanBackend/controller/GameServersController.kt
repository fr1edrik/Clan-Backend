package com.example.clanBackend.controller

import com.example.ClanBackend.Util.FileSystem.FileSystemUtil
import com.example.ClanBackend.service.mongoDb.MongoHandler
import com.example.ClanBackend.service.spark.SparkHandler
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class GameServersController {
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/servers/gameServers", produces = ["application/json"])
    fun getGameList(): List<Map<String, String>> {
        val mockedServerList = listOf(
                mapOf("serverName" to "Mount and Blade", "game" to "mountAndBlade"),
                mapOf("serverName" to "Counter", "game" to "countersrike"),
                mapOf("serverName" to "MineCr", "game" to "minecraft")
        )
        return mockedServerList
    }

    @GetMapping("/servers/gameServers/mb-warband", produces = ["application/json"])
    fun getMbWarband(): Any {
        val log = SparkHandler.parseLog("src/main/resources/log.txt")
        MongoHandler.insertInto("myCollections", log)
        return "OK"
    }

    @GetMapping("/servers/gameServers/mb-warband/fileManager", produces = ["application/json"])
    fun getMbWarbandMapFiles(): Any {
        val pathLinux = "/mnt/e/_Projects/_Clan-Projects/Mount\\&Blade\\ Warband\\ Dedicated/Modules/Native/SceneObj/"
        val test = "/mnt/e/"
        val pathWin= "E:\\_Projects\\_Clan-Projects\\Mount&Blade Warband Dedicated\\Modules\\Native\\SceneObj"

        return FileSystemUtil.getFilesForFolder(pathWin)
    }

    @GetMapping("/servers/gameServers/mb-bannerlord", produces = ["application/json"])
    fun getMbBannerlord(): String {
        return ""
    }
}