package com.example.ClanBackend.service.mongoDb

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.bson.Document

object MongoHandler {
    private val mongoClient = MongoClient(MongoClientURI("mongodb://localhost:27017"))

    fun getDataBase(){
        val database = mongoClient.getDatabase("example")
        val collection = database.getCollection("myCollections")
        collection.insertOne(Document("schlüssel", 12345))
    }
}