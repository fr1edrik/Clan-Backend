package com.example.ClanBackend.service.spark

import com.example.ClanBackend.model.currentSession.ChatEntry
import com.example.ClanBackend.model.currentSession.JoinEntry
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.rdd.RDD
import scala.Tuple4
import java.lang.Exception

object SparkHandler {
    private val conf: SparkConf = SparkConf().setAppName("Spark App").setMaster("local[*]")
    private val sparkContext: SparkContext = SparkContext(conf)

    private val findTimeStamp: Regex = "([012][0-9]:[0-5][0-9]:[0-5][0-9])".toRegex()
    private val findJoinedPlayer: Regex = "([012][0-9]:[0-5][0-9]:[0-5][0-9]) - .+ has joined|left the game with ID: [0-9]+".toRegex()
    private val findChat: Regex = ".*\\[.*\\].*".toRegex()
    private val findID: Regex = "(?<=ID: )[0-9]+".toRegex()
    private val findEntrance: Regex = "[(joined)(left)]".toRegex()
    private val findPlayerName: Regex = "(?<=- )(.*?)(?= )".toRegex()

    fun parseLog(filePath: String): List<Any> {
        try {
            val list: JavaRDD<String> = sparkContext.textFile(filePath, 1).toJavaRDD()

            return getChats(list)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getChats(rdd: JavaRDD<String>): List<ChatEntry> {
        return rdd.filter { findChat.containsMatchIn(it) }.map {
            val time = it.slice(1..8)
            val name = findChat.find(it)?.value
            val text = it.split(".*?\\][ :]".toRegex()).last()

            Triple(time, name ?: "", text)
        }.collect().map { (a, b, c) -> ChatEntry(a, b, c) }
    }

    // Player joins or leaves the server
    fun getServerEntrance(rdd: JavaRDD<String>): List<JoinEntry> {
        return rdd.filter { findJoinedPlayer.containsMatchIn(it) }.map {
             mapOf<String, String>("timetamp" to findTimeStamp.find(it)?.value!!, "playerName" to findPlayerName.find(it)?.value!!, "playerID" to findID.find(it)?.value!!,"entranceStatus" to findEntrance.find(it)?.value!!)
        }.collect().map {
            JoinEntry(it["timetamp"]?:"", it["playerID"]?:"", it["playerName"]?:"", it["entranceStatus"]?:"")
        }
    }
}