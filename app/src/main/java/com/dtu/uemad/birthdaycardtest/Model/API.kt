package com.dtu.uemad.birthdaycardtest.Model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.UnknownHostException

class API {
    var errors = 0
    var success = 0
    val client = OkHttpClient()

    suspend fun getSpellFromApi(spellName: String): String? {
        return withContext(Dispatchers.IO) {
            val spellInfo = getDnD5eSpell(spellName)
            spellInfo
        }
    }
    suspend fun getSpellFromApiWithRetry(spellName: String, maxRetries: Int): String? {
        var retryCount = 0
        var result: String? = null

        while (retryCount < maxRetries) {
            try {
                result = getSpellFromApi(spellName)
                if (result != null) {
                    break
                }
            } catch (e: UnknownHostException) {
                // Handle the exception or log it
            }

            // Wait for a short duration before retrying
            delay(1000) // 1000 = 1 second delay

            retryCount++
        }

        return result
    }
    suspend fun getListOfSpells() : String?{
        return withContext(Dispatchers.IO) {
            val command = "https://www.dnd5eapi.co/api/spells"
            getJsonStringFromCommand(command)
        }

    }
    private fun getDnD5eSpell(spellName: String): String? {
        val command = "https://www.dnd5eapi.co/api/spells/$spellName"
        return getJsonStringFromCommand(command)
    }
    suspend fun getSpellsFromApi(spellNames: List<String>): List<String?> {
        return coroutineScope {
            spellNames.map { spellName ->
                async {
                    getSpellFromApiWithRetry(spellName, 100)
                }
            }.awaitAll()
        }
    }
    suspend fun getSpellsFromApiInCollections(spellNames: List<String>): List<String?> {
        val spellInfoList = mutableSetOf<String?>()

        val (part1, part2) = spellNames.partition { spellNames.indexOf(it) < (spellNames.size/2) }

        val list = coroutineScope {
            part1.map { spellName ->
                async {
                    getSpellFromApiWithRetry(spellName, 100)
                }
            }.awaitAll()
        }
        for(json in list){
            spellInfoList.add(json)
        }
        val list2 = coroutineScope {
            part2   .map { spellName ->
                async {
                    getSpellFromApiWithRetry(spellName, 100)
                }
            }.awaitAll()
        }
        for(json in list2){
            spellInfoList.add(json)
        }
        return spellInfoList.toList()
    }

    private fun getJsonStringFromCommand(command : String) : String? {
        val request = Request.Builder()
            .url(command)
            .header("Accept", "application/json")
            .build()

        try {
            // Execute the request and get the response
            val response: Response = client.newCall(request).execute()
            // Check if the request was successful (HTTP status code 200)
            if (response.isSuccessful) {
                return response.body?.string()
            } else {
                println("Error: ${response.code} - ${response.message}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}