package com.dtu.uemad.birthdaycardtest.Model

class SpellList {
    private var namesList: List<String> = emptyList()
    private var spellInfoList: List<Spell_Info.SpellInfo> = emptyList()

    fun setSpellNamesList(names: List<String>) {
        namesList = names
    }
    fun getSpellNamesList(): List<String> {
        return namesList
    }
    fun setSpellInfoList(spellInfo: List<Spell_Info.SpellInfo>) {
        spellInfoList = spellInfo
    }
    fun getSpellInfoList(): List<Spell_Info.SpellInfo> {
        return spellInfoList
    }
    fun printNamesToConsole(){
        println("Printing spells from list:")
        for(name in namesList){
            println("- $name")
        }
    }
}