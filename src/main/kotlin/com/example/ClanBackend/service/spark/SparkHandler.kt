package com.example.ClanBackend.service.spark

import com.example.ClanBackend.model.currentSession.ChatEntry
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import java.lang.Exception

object SparkHandler {
    private val conf: SparkConf = SparkConf().setAppName("Spark App").setMaster("local[*]")
    private val sparkContext: SparkContext = SparkContext(conf)

    fun parseLog(filePath: String): List<ChatEntry> {
        try {
            val lines: RDD<String> = sparkContext.textFile(filePath, 1)

            val list = lines.toJavaRDD().filter { ".*\\[.*\\].*".toRegex().containsMatchIn(it) }

            return list.map {
                val time = it.slice(1..8)
                val name = "\\[.*?\\]".toRegex().find(it)?.value
                val text = it.split(".*?\\][ :]".toRegex()).last()

                Triple(time, name ?: "", text)
            }.collect().map { (a, b, c) -> ChatEntry(a, b, c) }
        } catch (e: Exception) {
            throw e
        }
    }
}