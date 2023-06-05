package com.batista.json.Command

import com.batista.json.models.JsonString

class UpdateJsonString(private val jsonString: JsonString, private val newValue: String) : JsonCommand{
    private var previousValue: String = ""

    override fun execute() {
        previousValue = jsonString.value
        jsonString.updateValue(newValue)
    }

    override fun undo() {
        jsonString.updateValue(previousValue)
    }

    override fun redo() {
        TODO("Not yet implemented")
    }
}