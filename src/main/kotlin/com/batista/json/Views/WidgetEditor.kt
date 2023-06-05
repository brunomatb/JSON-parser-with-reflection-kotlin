import com.batista.json.models.*
import com.batista.json.Observer.JsonObserver
import com.batista.json.visitor.JsonVisiter
import com.batista.json.jsonReflextion.DataClassConverter
import javax.swing.*
import java.awt.*
import java.awt.event.*

fun main() {
    Editor().open()
}

class WidgetEditor {
    private val frame = JFrame("Josue - JSON Object Editor").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = GridLayout(0, 2)
        size = Dimension(600, 600)

        val left = JPanel()
        left.layout = GridLayout()
        val scrollPane = JScrollPane(testPanel()).apply {
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        }
        left.add(scrollPane)
        add(left)

        val right = JPanel()
        right.layout = GridLayout()
        val srcArea = JTextArea()
        srcArea.tabSize = 2
        srcArea.text = "TODO"
        right.add(srcArea)
        add(right)
    }

    fun open() {
        frame.isVisible = true
    }

    fun testPanel(): JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            val jsonString = JsonString("um")
            jsonString.addObserver(object : JsonObserver {
                override fun onJsonChanged() {
                    updateJsonStringInTextArea(jsonString, srcArea)
                }
            })
            add(testWidget("A", jsonString))

            val jsonBoolean = JsonBoolean(true)
            jsonBoolean.addObserver(object : JsonObserver {
                override fun onJsonChanged() {
                    updateJsonBooleanInTextArea(jsonBoolean, srcArea)
                }
            })
            add(testWidget("B", jsonBoolean))

            val jsonArray = JsonArray(mutableListOf(JsonString("um"), JsonString("dois"), JsonString("três")))
            jsonArray.addObserver(object : JsonObserver {
                override fun onJsonChanged() {
                    updateJsonArrayInTextArea(jsonArray, srcArea)
                }
            })
            add(testWidget("C", jsonArray))

            // menu
            addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        val menu = JPopupMenu("Message")
                        val add = JButton("add")
                        add.addActionListener {
                            val text = JOptionPane.showInputDialog("text")
                            jsonArray.addElement(JsonString(text))
                            menu.isVisible = false
                        }
                        val del = JButton("delete all")
                        del.addActionListener {
                            jsonArray.values.clear()
                            menu.isVisible = false
                        }
                        menu.add(add);
                        menu.add(del)
                        menu.show(this@apply, 100, 100);
                    }
                }
            })
        }

    fun testWidget(key: String, value: JsonValue): JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val text = JTextField(value.toJsonString())
            text.addFocusListener(object : FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    when (value) {
                        is JsonString -> value.updateValue(text.text)
                        is JsonBoolean -> value.updateValue(text.text.toBoolean())
                    }
                }
            })
            add(text)
        }

    fun updateJsonStringInTextArea(jsonString: JsonString, textArea: JTextArea) {
        val jsonStringObject = JsonObject(mutableMapOf("value" to jsonString))
        val jsonVisitor = JsonStringVisitor()
        jsonStringObject.accept(jsonVisitor)
        textArea.text = jsonVisitor.jsonString
    }

    fun updateJsonBooleanInTextArea(jsonBoolean: JsonBoolean, textArea: JTextArea) {
        val jsonStringObject = JsonObject(mutableMapOf("value" to jsonBoolean))
        val jsonVisitor = JsonStringVisitor()
        jsonStringObject.accept(jsonVisitor)
        textArea.text = jsonVisitor.jsonString
    }

    fun updateJsonArrayInTextArea(jsonArray: JsonArray, textArea: JTextArea) {
        val jsonStringObject = JsonObject(mutableMapOf("values" to jsonArray))
        val jsonVisitor = JsonStringVisitor()
        jsonStringObject.accept(jsonVisitor)
        textArea.text = jsonVisitor.jsonString
    }
}

class JsonStringVisitor : JsonVisiter {
    var jsonString: String = ""

    override fun visit(obj: JsonObject) {
        jsonString = obj.toJsonString()
    }

    override fun visit(arrayValues: JsonArray) {
        jsonString = arrayValues.toJsonString()
    }

    override fun visit(stringValue: JsonString) {
        jsonString = stringValue.toJsonString()
    }

    override fun visit(numberValue: JsonNumber) {
        jsonString = numberValue.toJsonString()
    }

    override fun visit(bolValue: JsonBoolean) {
        jsonString = bolValue.toJsonString()
    }

    override fun visit(nullValue: JsonNull) {
        jsonString = nullValue.toJsonString()
    }
}
