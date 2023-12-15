package com.example.audience

data class Persons(
    val name : String,
    val surname: String,
    val middlename: String,
    val personId: Int
){
    fun doesMatchSearchQueryPersons(query: String): Boolean {
        val matchingCombinations = listOf(
            "${name} ${surname} ${middlename}",
            "${surname} ${name} ${middlename}",
            "${surname.first()}",
            "${name.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
