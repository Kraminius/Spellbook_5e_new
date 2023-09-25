package com.dtu.uemad.birthdaycardtest.Model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
class API {


    suspend fun getSpellFromApi(spellName: String): String? {
        return withContext(Dispatchers.IO) {
            val spellInfo = getDnD5eSpell(spellName)
            spellInfo // This will be the return value
        }
    }

    private fun getDnD5eSpell(spellName: String): String? {
        val client = OkHttpClient()

        // Define the API URL for the spell using the provided spellName
        val url = "https://www.dnd5eapi.co/api/spells/$spellName"

        // Create a GET request
        val request = Request.Builder()
            .url(url)
            .header("Accept", "application/json")
            .build()

        try {
            // Execute the request and get the response
            val response: Response = client.newCall(request).execute()

            // Check if the request was successful (HTTP status code 200)
            if (response.isSuccessful) {
                // Parse and return the response body as a string
                return response.body?.string()
            } else {
                // Handle the error case, e.g., by logging the error message
                println("Error: ${response.code} - ${response.message}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}