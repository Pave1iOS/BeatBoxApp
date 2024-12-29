package com.example.beatboxapp

private const val TAG = "Smile"

class Smile(beatBox: BeatBox) {

    private val soundsCount = beatBox.sounds.size

    private val smiles = listOf(
        "😈", "🤨", "😎", "😌",
        "😊", "😂", "😍", "🤔",
        "😢", "😜", "😇", "🤐",
        "😪", "😲", "🙃", "🤯",
        "🤧", "🤠", "🤖", "👻",
        "👾", "🐯", "😇", "🥹"
    )

    private var rowIndex = 0

    fun changeDefaultTitleToSmile(name: String): String {

        if (rowIndex > soundsCount - 1) {
            rowIndex = 0
        }

        val newName = smiles[rowIndex]

        rowIndex++

        return name.replace(name, newName)
    }
}