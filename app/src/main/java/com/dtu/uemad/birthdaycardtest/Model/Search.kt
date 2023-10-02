package com.dtu.uemad.birthdaycardtest.Model

class Search {

    private var fullSpellList = SpellList()
    private val api = API()
    private val json = API()


    fun searchSpellList(spellList : SpellList, searchString : String) : SpellList {
        val namesList = spellList.getSpellNamesList()
        val keywords = searchString.split(" ")
        val toReturn = SpellList()
        val matches = mutableListOf<String>()
        for(name in namesList){
            for(keyword in keywords){
                if(keyword in name){
                    matches.add(name)
                    break
                }
            }
        }
        toReturn.setSpellNamesList(matches.toList())
        return toReturn
    }





}