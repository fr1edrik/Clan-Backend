package com.example.ClanBackend.Util.FileSystem

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.stream.Collectors

object FileSystemUtil {

    fun getFilesForFolder(path: String?): List<Map<String, String>> {
        var result: List<Map<String, String>>? = null

        val dateformat = SimpleDateFormat("dd/MM/yyy HH:mm:ss")
        try {
            Files.walk(Paths.get(path)).use { walk ->
                result = walk
                        .filter { path: Path? -> Files.isRegularFile(path) }
                        .map { x: Path ->
                            mapOf("name" to x.fileName.toString(),
                                    "lastModified" to dateformat.format(x.toFile().lastModified()),
                                    "size" to convertToKB(x.toFile().length())
                            )
                        }.collect(Collectors.toList())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result ?: emptyList<Map<String, String>>()
    }
    private val pathWin = "E:\\_Projects\\_Clan-Projects\\Mount&Blade Warband Napoleonic Wars Dedicated\\Modules\\Napoleonic Wars\\SceneObj"

    fun saveFile(){
        var fileWriter: FileWriter? = null
        try {
            fileWriter = FileWriter(File("$pathWin/test.txt"))
            fileWriter.write("Hallo welt");
        }catch (e:IOException){
            e.printStackTrace();
        }finally {
            fileWriter?.close()
        }
    }

    fun convertToKB(bytes: Long): String {
        return String.format("%d KB", bytes/1024)
    }

}