package com.example.ClanBackend.service.mongoDb

import com.example.ClanBackend.model.currentSession.ChatEntry
import com.mongodb.*
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider

object MongoHandler {
    private val pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private val mongoClient =MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    val database = mongoClient.getDatabase("example")

    fun getDataBase() {
        val collection = database.getCollection("myCollections")
        collection.insertOne(Document("schlüssel", 12345))
    }

    inline fun <reified T> insertInto(collectionName:String, values: List<T>){
        try {
//            val collection = database.getCollection(collectionName, ChatEntry::class.java)
            val collection = database.getCollection(collectionName, T::class.java)

            collection.insertMany(values)
        }catch (e:Exception){
            throw e
        }
    }
}