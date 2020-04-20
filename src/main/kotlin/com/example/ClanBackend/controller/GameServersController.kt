package com.example.clanBackend.controller

import com.example.ClanBackend.Util.FileSystem.FileSystemUtil
import com.example.ClanBackend.model.currentSession.JoinEntry
import com.example.ClanBackend.service.mongoDb.MongoHandler
import com.example.ClanBackend.service.spark.SparkHandler
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

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
        val log = SparkHandler.parseLog("src/main/resources/server_log_04_04_20.txt")
        MongoHandler.insertInto<JoinEntry>("testCollections", log as List<JoinEntry>)
        return log
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/servers/gameServers/mb-warband/mapFiles", produces = ["application/json"])
    fun getMbWarbandMapFiles(): Any {
        val pathLinux = "/mnt/e/_Projects/_Clan-Projects/Mount\\&Blade\\ Warband\\ Dedicated/Modules/Native/SceneObj/"
        val test = "/mnt/e/"
        val pathWin = "E:\\_Projects\\_Clan-Projects\\Mount&Blade Warband Napoleonic Wars Dedicated\\Modules\\Napoleonic Wars\\SceneObj"

        return FileSystemUtil.getFilesForFolder(pathWin)
    }

    @CrossOrigin
    @PostMapping("/servers/gameServers/mb-warband/mapFiles", produces = ["application/json"])
    fun funSaveWarbandMapFiles(@RequestParam("mapFiles") mapFiles: List<MultipartFile>): String {

        mapFiles.forEach { writeFile(it) }

        return ""
    }

    fun writeFile(file: MultipartFile): String {
        if (file.isEmpty) return "File is Empty"

        var stream: BufferedOutputStream? = null
        return try {
            val bytes: ByteArray = file.bytes
            val file: File = File("E:\\_Projects\\_Clan-Projects\\Mount&Blade Warband Napoleonic Wars Dedicated\\Modules\\Napoleonic Wars\\SceneObj\\${file.originalFilename}")
            stream = BufferedOutputStream(FileOutputStream(file))
            stream.write(bytes)
            "You successfully uploaded!"
        } catch (e: Exception) {
            e.printStackTrace()
            "Failed to upload"
        } finally {
            stream?.close()
        }

    }

    @GetMapping("/servers/gameServers/mb-bannerlord", produces = ["application/json"])
    fun getMbBannerlord(): String {
        return ""
    }
}