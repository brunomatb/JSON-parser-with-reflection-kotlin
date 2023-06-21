package com.batista.json.Views

import javax.swing.*
import java.awt.*
import java.awt.event.*
import com.batista.json.jsonValues.*
import com.batista.json.jsonReflextion.Reflection

fun main() {
    ViewEditor().open()
}

class ViewEditor {
    private val frame = JFrame("Josue - JSON Object Editor").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = GridLayout(0, 2)
        size = Dimension(600, 600)

        val left = JPanel()
        left.layout = BoxLayout(left, BoxLayout.Y_AXIS)
        val scrollPane = JScrollPane(left).apply {
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        }
        add(scrollPane)

        val right = JPanel()
        right.layout = GridLayout()
        val srcArea = JTextArea()
        srcArea.tabSize = 1
        right.add(srcArea)
        add(right)

        val firstObject = mutableMapOf<String, Any>()

        fun displayJsonObject(obj: Any?): String {
            val reflection = Reflection()
            val jsonValue: JsonValue = reflection.toJsonValue(obj)
            val jsonString: String = jsonValue.toJsonString()
            return jsonString
        }

        fun updatePreview() {
            val preview = displayJsonObject(firstObject)
            srcArea.text = preview
        }

        fun removeLastWidget() {
            if (firstObject.isNotEmpty()) {
                val lastKey = firstObject.keys.last()
                firstObject.remove(lastKey)
                left.remove(left.components.last())
                left.revalidate()
                left.repaint()
                updatePreview()
            }
        }

        fun createWidgetArray(key: String, values: MutableList<String>): JPanel {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
            panel.alignmentX = Component.LEFT_ALIGNMENT
            panel.alignmentY = Component.TOP_ALIGNMENT

            panel.add(JLabel(key))

            val jArray = JsonArray(values.map { JsonString(it) }.toMutableList())

            fun updateArrayValues() {
                jArray.values.clear()
                for (textField in panel.components.filterIsInstance<JTextField>()) {
                    jArray.values.add(JsonString(textField.text))
                }
                if (firstObject.containsKey(key)) {
                    firstObject[key] = jArray.toJsonString()
                    updatePreview()
                }
            }

            for (value in values) {
                val text = JTextField(value)
                text.addKeyListener(object : KeyAdapter() {
                    override fun keyReleased(e: KeyEvent) {
                        updateArrayValues()
                    }
                })

                val popupMenu = JPopupMenu()
                val removeItem = JMenuItem("Remover")
                removeItem.addActionListener {
                    panel.remove(text)
                    updateArrayValues()
                    panel.revalidate()
                    panel.repaint()
                }
                val addItem = JMenuItem("Adicionar")
                addItem.addActionListener {
                    val textField = JTextField()
                    panel.add(textField, panel.componentCount - 1)
                    textField.addKeyListener(object : KeyAdapter() {
                        override fun keyReleased(e: KeyEvent) {
                            updateArrayValues()
                        }
                    })
                    val removeItem = JMenuItem("Remover")
                    removeItem.addActionListener {
                        panel.remove(textField)
                        updateArrayValues()
                        panel.revalidate()
                        panel.repaint()
                    }

                    textField.componentPopupMenu = JPopupMenu().apply {
                        add(removeItem)
                        add(addItem)
                    }
                    updateArrayValues()
                    panel.revalidate()
                    panel.repaint()
                    textField.requestFocus()
                }

                popupMenu.add(removeItem)
                popupMenu.add(addItem)

                text.componentPopupMenu = popupMenu
                updateArrayValues()
                panel.add(text)
            }

            val removeButton = JButton("Remover")
            removeButton.addActionListener {
                removeLastWidget()
            }
            panel.add(removeButton)

            return panel
        }
        fun createWidgetObject(key: String, values: MutableMap<String, String>): JPanel {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
            panel.alignmentX = Component.LEFT_ALIGNMENT
            panel.alignmentY = Component.TOP_ALIGNMENT

            panel.add(JLabel(key))

            val jObject = JsonObject(mutableMapOf())
            values.map { (k,v) ->
                jObject.objMap[v] = JsonString(v)
            }
            fun updateJsonValues() {
                jObject.objMap.clear()
                for (textField in panel.components.filterIsInstance<JTextField>()) {
                    val valueKey = textField.getClientProperty("valueKey") as? String
                    if (valueKey != null) {
                        jObject.objMap[valueKey] =  JsonString(textField.text)
                    }
                }
                if (firstObject.containsKey(key)) {
                    firstObject[key] = jObject.toJsonString()
                    updatePreview()
                }
            }

            for ((valueKey, value) in values) {
                val text = JTextField(value)
                text.putClientProperty("valueKey", valueKey)
                text.addKeyListener(object : KeyAdapter() {
                    override fun keyReleased(e: KeyEvent) {
                        updateJsonValues()
                    }
                })

                val popupMenu = JPopupMenu()
                val removeItem = JMenuItem("Remover")
                removeItem.addActionListener {
                    panel.remove(text)
                    updateJsonValues()
                    panel.revalidate()
                    panel.repaint()
                }
                val addItem = JMenuItem("Adicionar")
                addItem.addActionListener {

                    val key = JOptionPane.showInputDialog(this, "Enter element key")
                    val value = JOptionPane.showInputDialog(this, "Enter value to: ${key}")
                    val textField = JTextField(value)
                    panel.add(textField, panel.componentCount - 1)
                    textField.putClientProperty("valueKey", key)

                    textField.addKeyListener(object : KeyAdapter() {
                        override fun keyReleased(e: KeyEvent) {
                            updateJsonValues()
                        }
                    })
                    val removeItem = JMenuItem("Remover")
                    removeItem.addActionListener {
                        panel.remove(textField)
                        updateJsonValues()
                        panel.revalidate()
                        panel.repaint()
                    }

                    textField.componentPopupMenu = JPopupMenu().apply {
                        add(removeItem)
                        add(addItem)
                    }
                    updateJsonValues()
                    panel.revalidate()
                    panel.repaint()
                    textField.requestFocus()
                }

                popupMenu.add(removeItem)
                popupMenu.add(addItem)

                text.componentPopupMenu = popupMenu

                panel.add(text)
            }

            val removeButton = JButton("Remover")
            removeButton.addActionListener {
                removeLastWidget()
            }
            panel.add(removeButton)
            updateJsonValues()
            return panel
        }






        fun createWidgetPanel(key: String, value: String): JPanel {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.X_AXIS)
            panel.alignmentX = Component.LEFT_ALIGNMENT
            panel.alignmentY = Component.TOP_ALIGNMENT

            panel.add(JLabel(key))
            val text = JTextField(value)
            text.addKeyListener(object : KeyAdapter() {
                override fun keyReleased(e: KeyEvent)  {
                    val newValue = text.text
                    if (firstObject.containsKey(key)) {
                        firstObject[key] = newValue
                        updatePreview()
                    }
                }
            })
            panel.add(text)
            val removeButton = JButton("Remove")
            removeButton.addActionListener {
                removeLastWidget()
            }
            panel.add(removeButton)
            return panel
        }
        fun createWidgetNull(key: String, value: String): JPanel {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.X_AXIS)
            panel.alignmentX = Component.LEFT_ALIGNMENT
            panel.alignmentY = Component.TOP_ALIGNMENT

            panel.add(JLabel(key))
            val text = JTextField(value)
            text.addKeyListener(object : KeyAdapter() {
                override fun keyReleased(e: KeyEvent)  {
                    val newValue = text.text
                    if (firstObject.containsKey(key)) {
                        firstObject[key] = newValue
                        updatePreview()
                    }
                }
            })
            panel.add(text)
            val removeButton = JButton("Remove")
            removeButton.addActionListener {
                removeLastWidget()
            }
            panel.add(removeButton)
            return panel
        }
        fun createWidgetBoolean(key: String, value: String): JPanel {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.X_AXIS)
            panel.alignmentX = Component.LEFT_ALIGNMENT
            panel.alignmentY = Component.TOP_ALIGNMENT

            panel.add(JLabel(key))
            val radioButton = JRadioButton(value)

            radioButton.addItemListener { e ->
                if (e.stateChange == ItemEvent.SELECTED) {
                    radioButton.text = "True"
                    firstObject[key] = JsonBoolean(true).toJsonString()
                    updatePreview()
                } else if (e.stateChange == ItemEvent.DESELECTED) {
                    radioButton.text = "False"
                    firstObject[key] = JsonBoolean(false).toJsonString()
                    updatePreview()
                }
            }


            panel.add(radioButton)
            val removeButton = JButton("Remove")
            removeButton.addActionListener {
                removeLastWidget()
            }
            panel.add(removeButton)
            return panel
        }

        fun addWidget(key: String, value: String? = null, values: MutableList<String>? = null, objValues: MutableMap<String, String>? = null) {
            if (firstObject.containsKey(key)) {
                JOptionPane.showMessageDialog(this, "A key with the name '$key' already exists.", "Key Already Exists", JOptionPane.WARNING_MESSAGE)
                return
            }

            firstObject[key] = value!!
            var panel: JPanel? = null
            if(value=="true" || value=="false"){
                panel = createWidgetBoolean(key, value)
            }
            else if(values!=null){
                panel = createWidgetArray(key, values)
            }
            else if(objValues!=null){
                panel = createWidgetObject(key, objValues)
            }
            else if(value!=null){
                panel = createWidgetPanel(key, value!!)
            }


            left.add(panel)
            left.revalidate()
            left.repaint()
            updatePreview()
        }

        // Menu de contexto
        val menu = JPopupMenu()

        val addJsonObjectItem = JMenuItem("Add JsonObject")
        addJsonObjectItem.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter Object name:") as? String
            if (key != null) {
                val values = mutableMapOf<String, String>()
                while (true) {
                    val keyObj = JOptionPane.showInputDialog(this, "Enter element key")
                    val value = JOptionPane.showInputDialog(this, "Enter value to: ${keyObj}")
                    if (keyObj != null && value != null) {
                        values[keyObj] = value
                    } else {
                        break
                    }
                }
                if (values != null) {
                    addWidget(key, "",null, values)

                }
            }
        }
        menu.add(addJsonObjectItem)

        val addJsonArrayItem = JMenuItem("Add JsonArray")
        addJsonArrayItem.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter element key:") as? String
            if (key != null) {
                val values = mutableListOf<String>()
                while (true) {
                    val value = JOptionPane.showInputDialog(this, "Enter value (or click Cancel to exit):")
                    if (value != null) {
                        values.add(value)
                    } else {

                        break
                    }
                }
                if (values != null) {
                    addWidget(key, "",values)

                }
            }
        }

        menu.add(addJsonArrayItem)
        val addJsonNumber = JMenuItem("Add JsonNumber")
        addJsonNumber.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter element key:") as? String
            val value = JOptionPane.showInputDialog(this, "Enter Number:")
            if (key != null) {
                addWidget(key, value)
                updatePreview()
            }
        }
        menu.add(addJsonNumber)

        val addJsonBoolean = JMenuItem("Add JsonBoolean")
        addJsonBoolean.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter element key:") as? String
            if (key != null) {
                val trueRadioButton = JRadioButton("true")
                val falseRadioButton = JRadioButton("false")

                val buttonGroup = ButtonGroup()
                buttonGroup.add(trueRadioButton)
                buttonGroup.add(falseRadioButton)

                val radioButtonPanel = JPanel()
                radioButtonPanel.layout = BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS)
                radioButtonPanel.add(trueRadioButton)
                radioButtonPanel.add(falseRadioButton)

                val result = JOptionPane.showConfirmDialog(
                    this,
                    radioButtonPanel,
                    "Select value:",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
                )

                val value = if (result == JOptionPane.OK_OPTION) {
                    if (trueRadioButton.isSelected) "true" else "false"
                } else {
                    null
                }

                if (value != null) {
                    addWidget(key, value)
                }
            }


        }
        menu.add(addJsonBoolean)

        val addJsonStringItem = JMenuItem("Add JsonString")
        addJsonStringItem.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter element key:") as? String
            val value = JOptionPane.showInputDialog(this, "Enter value:")
            if (key != null) {
                addWidget(key, value)
            }
        }
        menu.add(addJsonStringItem)

        val addJsonNullItem = JMenuItem("Add JsonNull")
        addJsonNullItem.addActionListener {
            val key = JOptionPane.showInputDialog(this, "Enter element key:") as? String
            if (key != null) {
                addWidget(key, JsonNull().toJsonString())
            }
        }
        menu.add(addJsonNullItem)

        left.addMouseListener(object : MouseAdapter() {
            override fun mouseReleased(e: MouseEvent) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    menu.show(e.component, e.x, e.y)
                }
            }
        })
    }

    fun open() {
        frame.isVisible = true
    }
}
