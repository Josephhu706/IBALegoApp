package com.example.legosearchapp.fake.fake
import com.example.legosearchapp.model.Theme
import com.example.legosearchapp.model.Set

val expectedSets = listOf<Set>(
    Set("00-1",
        "Weetabix Castle",
        1970,
        414,
        471,
    ),
)

val expectedThemes = listOf<Theme>(
    Theme(2,
        "Arctic Technic",
        1,
    ),
)

val expectedSearchResults = listOf<Set>(
    Set(setId="10194-1", name="Emerald Night", year=2009, themeId=240, numParts=1089),
    Set(setId="31015-1", name="Emerald Express", year=2014, themeId=22, numParts=56),
    Set(setId="K10194-1", name="Emerald Night Collection", year=2009, themeId=240, numParts=8)
)