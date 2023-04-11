package com.example.legosearchapp.model

data class Set(
    val setId: String,
    val name: String,
    val year: Int,
    val themeId: Int,
    val numParts: Int
) : HasStringProperties{
    override val properties: List<String>
        get() = listOf(
            setId,
            name,
            year.toString(),
            themeId.toString(),
            numParts.toString()
        )
}

interface HasStringProperties {
    val properties: List<String>
}