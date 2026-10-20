
package com.redwhite.football2027.model

data class Team(val id: String, val nameAr: String, val nameEn: String, val primaryHex: String, val secondaryHex: String)

object TeamsRepo {
    val teams = listOf(
        Team("CA","النادي الإفريقي","Club Africain","#D10A1F","#FFFFFF"),
        Team("EST","الترجي الرياضي","Esperance","#E5C100","#9C0000"),
        Team("ESS","النجم الساحلي","Etoile Sahel","#CC0000","#FFFFFF"),
        Team("CSS","النادي الصفاقسي","CS Sfaxien","#000000","#FFFFFF"),
        Team("ACA","نادي العاصمة","Capital FC","#D10A1F","#FFFFFF"),
        Team("WHI","النسور البيضاء","White Eagles","#FFFFFF","#D10A1F"),
        Team("RED","الحمر","Reds United","#B00000","#FFFFFF"),
        Team("BLU","الزرق","Blues City","#0A2A9A","#FFFFFF"),
    )
    fun getById(id: String) = teams.find { it.id==id } ?: teams[0]
}
