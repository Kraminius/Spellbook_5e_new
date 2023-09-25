package com.dtu.uemad.birthdaycardtest.Model

import com.google.gson.Gson

class JSON_to_Spell {
    fun convertFromJSON(json : String) : Spell_Info.DnD5eSpell? {
        val gson = Gson()
        val spell = gson.fromJson(json, Spell_Info.DnD5eSpell::class.java)
        if(spell.desc.isEmpty()) return null
        return spell
    }
}