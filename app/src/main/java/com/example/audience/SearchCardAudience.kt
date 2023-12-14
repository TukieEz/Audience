package com.example.audience

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SearchCardAudiences(
    val audienceId: Int,
    val audienceTitle: String,
    val teacherName: String,
    val teacherSurname: String,
    val teacherMiddlename: String
){
    fun doesMathSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            "$audienceTitle$teacherName$teacherSurname$teacherMiddlename",
            "$audienceTitle $teacherName $teacherSurname $teacherMiddlename",
            "${audienceTitle.first()} ${teacherName.first()} ${teacherSurname.first()} ${teacherMiddlename.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
public val Cards = listOf(
    SearchCardAudiences(R.drawable.section_1__4_,"1-435","Зайдуллина", "Светлана","Галлимуловна"),
    SearchCardAudiences(0,"1-432","Зайдуллина", "Светлана","Галлимуловна"),
    SearchCardAudiences(0,"1-442","Зайдуллина", "Светлана","Галлимуловна"),
    SearchCardAudiences(0,"1-441","Зайдуллина", "Светлана","Галлимуловна"),
    SearchCardAudiences(0,"1-440","Зайдуллина", "Светлана","Галлимуловна")
)
