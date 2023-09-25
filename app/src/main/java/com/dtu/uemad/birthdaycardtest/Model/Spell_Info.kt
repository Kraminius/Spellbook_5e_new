package com.dtu.uemad.birthdaycardtest.Model

class Spell_Info {


    data class DnD5eSpell(
        val index: String,
        val name: String,
        val desc: List<String>,
        val higher_level: List<String>,
        val range: String,
        val components: List<String>,
        val material: String,
        val ritual: Boolean,
        val duration: String,
        val concentration: Boolean,
        val casting_time: String,
        val level: Int,
        val damage: DamageInfo,
        val dc: DcInfo,
        val area_of_effect: AreaOfEffectInfo,
        val school: SchoolInfo,
        val classes: List<ClassInfo>,
        val subclasses: List<SubclassInfo>,
        val url: String
    )

    data class DamageInfo(
        val damage_type: DamageType,
        val damage_at_slot_level: Map<String, String>
    )

    data class DamageType(
        val index: String,
        val name: String,
        val url: String
    )

    data class DcInfo(
        val dc_type: DcType,
        val dc_success: String
    )

    data class DcType(
        val index: String,
        val name: String,
        val url: String
    )

    data class AreaOfEffectInfo(
        val type: String,
        val size: Int
    )

    data class SchoolInfo(
        val index: String,
        val name: String,
        val url: String
    )

    data class ClassInfo(
        val index: String,
        val name: String,
        val url: String
    )

    data class SubclassInfo(
        val index: String,
        val name: String,
        val url: String
    )


}