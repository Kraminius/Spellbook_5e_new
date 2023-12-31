package com.dtu.uemad.birthdaycardtest.Model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JSON_to_Spell {
    fun jsonToSpell(json : String) : Spell_Info.SpellInfo? {
        val gson = Gson()
        val spell = gson.fromJson(json, Spell_Info.SpellInfo::class.java)
        if(spell.description?.isEmpty() != false) return null
        return spell
    }
    fun jsonToSpellList(json : String) : SpellList?{
        val spells = SpellList()
        spells.setSpellNamesList(extractIndexesFromJson(json))
        return spells
    }

    private fun extractIndexesFromJson(jsonString: String): List<String> {
        val jsonParser = JsonParser()
        val jsonElement = jsonParser.parse(jsonString)
        if (jsonElement is JsonObject) {
            val resultsArray = jsonElement.getAsJsonArray("results")
            if (resultsArray != null) {
                val indexes = mutableListOf<String>()
                for (element in resultsArray) {
                    if (element is JsonObject) {
                        // Check if the JSON object has an "index" key
                        val index = element.get("index")
                        if (index != null && index.isJsonPrimitive) {
                            indexes.add(index.asString)
                        }
                    }
                }
                return indexes
            }
        }
        return emptyList()
    }

}