package com.example.ClanBackend.service.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SparkHandler {
    private val conf: SparkConf = SparkConf().setAppName("Spark App").setMaster("local[4]")
    private val sparkContext: SparkContext = SparkContext(conf)

    fun readLog(): Any {
        val filePath: String = "src/main/resources/log.txt"
        val lines: RDD<String> = sparkContext.textFile(filePath,1).cache()


        return lines.count()
    }
}