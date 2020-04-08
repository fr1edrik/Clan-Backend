package com.example.ClanBackend.model.currentSession

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.codehaus.jackson.annotate.JsonValue
import org.codehaus.jackson.map.annotate.JsonSerialize

data class ChatEntry(val timeStamp: String, val userName: String, val text: String)