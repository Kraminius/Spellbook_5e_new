package com.dtu.uemad.birthdaycardtest

import com.dtu.uemad.birthdaycardtest.Model.API
import com.dtu.uemad.birthdaycardtest.Model.JSON_to_Spell
import com.dtu.uemad.birthdaycardtest.Model.Spell_Info
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class SpellController {

    private val api = API()
    private val JSON_to_Spell = JSON_to_Spell()

    fun getSpellFromName(spellName : String) : Spell_Info.DnD5eSpell? {
        var spell: Spell_Info.DnD5eSpell? = null
        runBlocking {
            try {
                val deferredSpellInfo: Deferred<String?> = async(Dispatchers.IO) {
                    api.getSpellFromApi(spellName)
                }
                val spellInfo: String? = deferredSpellInfo.await()
                if (spellInfo != null) {
                    spell = JSON_to_Spell.convertFromJSON(spellInfo)
                } else {
                    println("Failed to fetch spell information.")
                }
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }
        return spell
    }


}