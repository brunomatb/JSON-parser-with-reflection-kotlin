package com.batista.json.Command

interface JsonCommand {
    fun execute()
    fun undo()
    fun redo()
}