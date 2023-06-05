package com.batista.json.Views

import com.batista.json.Observer.JsonObserver
import com.batista.json.models.JsonNumber
import com.batista.json.models.JsonObject
import com.batista.json.models.JsonString
import com.batista.json.models.JsonValue
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

import com.batista.json.models.*



class JsonEditor : JsonObserver, ActionListener {
    private val model: JsonObject
    private val previewTextArea: JTextArea
    private val addButton: JButton
    private val removeButton: JButton

    init {
        // Inicializa o modelo e outros componentes do editor
        model = JsonObject(mutableMapOf())
        previewTextArea = JTextArea()
        addButton = JButton("Adicionar Propriedade")
        removeButton = JButton("Remover Propriedade")

        // Adiciona o editor como observador do modelo
        model.addObserver(this)

        // Configura os componentes do editor
        val panel = JPanel()
        panel.add(addButton)
        panel.add(removeButton)
        panel.setSize(1024, 768)

        val frame = JFrame("JSON Editor")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = BorderLayout()
        frame.add(previewTextArea, BorderLayout.CENTER)
        frame.add(panel, BorderLayout.SOUTH)
        frame.pack()
        frame.isVisible = true

        // Registra os botões para eventos de clique
        addButton.addActionListener(this)
        removeButton.addActionListener(this)

        // Atualiza o preview inicial
        updatePreview()
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === addButton) {
            val propertyName = JOptionPane.showInputDialog("Digite o nome da propriedade:")
            val propertyType = JOptionPane.showInputDialog("Digite o tipo da propriedade (Object, Array, String, Boolean):")

            when (propertyType.toLowerCase()) {
                "object" -> {
                    val jsonObject = JsonObject(mutableMapOf())
                    model.addProperty(propertyName, jsonObject)
                }
                "array" -> {
                    val jsonArray = JsonArray(mutableListOf())
                    model.addProperty(propertyName, jsonArray)
                }
                "string" -> {
                    val propertyValue = JOptionPane.showInputDialog("Digite o valor da propriedade:")
                    val jsonString = JsonString(propertyValue)
                    model.addProperty(propertyName, jsonString)
                }
                "boolean" -> {
                    val propertyValue = JOptionPane.showInputDialog("Digite o valor da propriedade (true/false):")
                    val jsonBoolean = JsonBoolean(propertyValue.toBoolean())
                    model.addProperty(propertyName, jsonBoolean)
                }
                else -> {
                    JOptionPane.showMessageDialog(null, "Tipo de propriedade inválido.")
                }
            }

            updatePreview()
        } else if (e.source === removeButton) {
            val propertyName = JOptionPane.showInputDialog("Digite o nome da propriedade a ser removida:")

            updatePreview()
        }
    }

    override fun onJsonChanged() {
        // Atualiza o preview quando ocorrerem alterações no modelo
        updatePreview()
    }

    private fun updatePreview() {
        val jsonString = model.toJsonString()
        previewTextArea.text = jsonString
    }
}

fun main() {
    JsonEditor()
}
