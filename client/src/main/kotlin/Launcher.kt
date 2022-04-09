import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme
import java.applet.Applet
import java.applet.AppletContext
import java.applet.AppletStub
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JDialog
import javax.swing.JFrame
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.isAccessible

object Launcher {

    /**
     * Client Constants
     */
    val CODEBASE = "127.0.0.1"

    // Jagex Original Modulus
    //val MODULUS = "a297f6692a7a1d8b2786f93cf85ef1d85f2a702a6f04b4503c079d0c3970d7a7bda84292dd1c8249b1cd8d0eb0fe10e16ad2d42a7fbbb321f4f5603afec3f13a03d534b8e2233ba479c346208306d2d816ef9f8f1ee69896d2cd1f3dfcb7d8e5987ed6e9412f962811bfbfb59b689ce506438d4f3c8dfb5c95c1670ad4d2e767"

    val MODULUS = Launcher::class.java.getResource("/modulus.txt")!!.readText()

    val DEFAULT_WORLD = 1
    val TITLE = "Paradigm"
    val JAGEX_OLDSCHOOL_URL = "http://oldschool1.runescape.com/"

    private val params = mutableMapOf<String, String>()

    @JvmStatic
    fun main(args: Array<String>) {
        JFrame.setDefaultLookAndFeelDecorated(true)
        JDialog.setDefaultLookAndFeelDecorated(true)
        FlatAtomOneDarkContrastIJTheme.setup()

        initParams()
        start()
    }

    private fun initParams() {
        URL(JAGEX_OLDSCHOOL_URL + "jav_config.ws").readText(Charsets.UTF_8).split("\n").forEach {
            var line = it
            if(line.startsWith("param=")) {
                line = line.substring(6)
            }
            val idx = line.indexOf('=')
            if(idx >= 0) {
                params[line.substring(0, idx)] = line.substring(idx + 1)
            }
        }

        overrideParams()
    }

    private fun overrideParams() {
        params["codebase"] = "http://$CODEBASE/"
        params["12"] = DEFAULT_WORLD.toString()
        params["17"] = "http://$CODEBASE/"
    }

    private fun start() {
        val applet = Client()
        applet.background = Color.BLACK
        applet.preferredSize = Dimension(params["applet_minwidth"]!!.toInt(), params["applet_minheight"]!!.toInt())
        applet.size = applet.preferredSize
        Dimension(params["applet_minwidth"]!!.toInt(), params["applet_minheight"]!!.toInt())
        applet.setStub(applet.createStub())
        applet.isVisible = true
        applet.init()

        val frame = JFrame(TITLE)
        frame.iconImages = listOf(ImageIcon(Launcher::class.java.getResource("/icon.png")).image)
        frame.layout = GridLayout(1, 0)
        frame.add(applet)
        frame.pack()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        frame.minimumSize = frame.size
        frame.maximumSize = Dimension(params["applet_maxwidth"]!!.toInt(), params["applet_maxheight"]!!.toInt())
    }

    private fun Applet.createStub(): AppletStub = object : AppletStub {
        override fun getCodeBase(): URL = URL(params["codebase"])
        override fun getDocumentBase(): URL = URL(params["codebase"])
        override fun getParameter(name: String): String? = params[name]
        override fun getAppletContext(): AppletContext? = null
        override fun isActive(): Boolean = true
        override fun appletResize(width: Int, height: Int) { this@createStub.setSize(width, height) }
    }
}