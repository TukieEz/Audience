package com.example.audience

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SearchCardAudiences(
    val audienceId: Int,
    val audienceTitle: String,
){

    fun doesMatchSearchQueryAud(query: String): Boolean {
        val matchingCombinations = listOf(
            "$audienceTitle",
            "${audienceTitle.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
public val Cards = listOf(
    SearchCardAudiences(R.drawable._31,"1-431"),
    SearchCardAudiences(R.drawable._32,"1-432"),
    SearchCardAudiences(R.drawable._33,"1-433"),
    SearchCardAudiences(R.drawable._34,"1-434"),
    SearchCardAudiences(R.drawable._35,"1-435"),
    SearchCardAudiences(R.drawable._36,"1-436"),
    SearchCardAudiences(R.drawable._40,"1-440"),
    SearchCardAudiences(R.drawable._41,"1-441"),
    SearchCardAudiences(R.drawable._42,"1-442"),
    SearchCardAudiences(R.drawable._43,"1-443"),
    SearchCardAudiences(R.drawable._44,"1-444"),
    SearchCardAudiences(R.drawable._45,"1-445"),
    SearchCardAudiences(R.drawable._46,"1-446"),
    Persons("Светлана","Зайдуллина", "Галлимуловна", R.drawable.frame_42),
    Persons("Зарипов","Дамир", "Мунзирович", R.drawable.frame_41),
    Persons("Дружинская","Елена", "Владимировна", R.drawable.frame_43)
)
