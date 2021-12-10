@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package util


import me.grison.aoc.*
import org.apache.commons.io.IOUtils
import org.monte.media.Format
import org.monte.media.FormatKeys.*
import org.monte.media.Registry
import org.monte.media.VideoFormatKeys.CompressorNameKey
import org.monte.media.VideoFormatKeys.DepthKey
import org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE
import org.monte.media.VideoFormatKeys.QualityKey
import org.monte.media.math.Rational
import org.monte.screenrecorder.ScreenRecorder
import org.openqa.selenium.Cookie
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.chrome.ChromeDriver
import org.reflections.Reflections
import java.awt.*
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_DATE
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue


@ExperimentalTime
object Runner {
    private val defaultYear = 2021
    private var reflections = Reflections("me.grison.aoc")
    private var allYears = false

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty() && "input-" in args[0]) {
            val arg = args[0].replace("input-", "")
            val (year, day) = when ("/" in arg) {
                true -> Pair(arg.before("/").toInt(), arg.after("/"))
                false -> Pair(defaultYear, arg)
            }
            val session = File(javaClass.classLoader.getResource("cookie.txt").toURI()).readText()
            val output = File(
                javaClass.classLoader.getResource("$year").toURI().path.replace(
                    "build/resources/main",
                    "src/main/resources"
                ) + "/$day.txt"
            )
            var created = false
            if (!output.exists()) {
                output.createNewFile()
                created = true
            }
            val url =
                URL("https://adventofcode.com/$year/day/${if (day.startsWith("0")) day.substring(1) else day}/input")
            with(url.openConnection() as HttpURLConnection) {
                setRequestProperty("Cookie", "session=$session")
                inputStream.bufferedReader().use {
                    if (!created) { // reset
                        output.writeText("")
                    }
                    output.writeText(IOUtils.toString(it).butLast())
                    println("Saved $year/$day to ${output.path}.")
                }
            }
            System.exit(0)
        }


        if (args.isNotEmpty() && "screenshotold" in args[0]) {
            System.setProperty("webdriver.chrome.driver", "C:/dev/tools/chromedriver96.exe")
            val driver = ChromeDriver()
            driver.get("https://adventofcode.com/2017")
            driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS)
            val session = File(javaClass.classLoader.getResource("cookieold.txt").toURI()).readText()
            driver.manage().addCookie(Cookie("session", session))
            driver.navigate().refresh()

            val js = driver as JavascriptExecutor
            js.executeScript("document.querySelector('.user').innerHTML = document.querySelector('.user').innerHTML.replace('Flaie', 'Alexandre Grison <a href=\"/2021/support\" class=\"supporter-badge\">(AoC++)</a>');")
            js.executeScript("document.querySelector('#sidebar').remove();")
            js.executeScript("document.body.style.zoom = '1.5';")

            Thread.sleep(40000)
            val screenshot = driver as TakesScreenshot
//            for (i in 1..100) {
            val sc = screenshot.getScreenshotAs(OutputType.FILE)
            Files.move(sc.toPath(), Paths.get("screenshots/2017/25.png"), REPLACE_EXISTING)
//                Files.move(sc.toPath(), Paths.get("screenshots/2017/25-${i}.png"), REPLACE_EXISTING)
            Thread.sleep(10)
//            }

            // reinit zoom
            js.executeScript("document.body.style.zoom = '1';")

            driver.quit()
            System.exit(0)
        } else if (args.isNotEmpty() && "screenshot" in args[0]) {
            System.setProperty("webdriver.chrome.driver", "C:/dev/tools/chromedriver96.exe")
            val driver = ChromeDriver()
            driver.get("https://adventofcode.com/")
            driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS)
            val session = File(javaClass.classLoader.getResource("cookie.txt").toURI()).readText()
            driver.manage().addCookie(Cookie("session", session))
            driver.navigate().refresh()

            val js = driver as JavascriptExecutor
            js.executeScript("document.querySelector('.user').innerHTML = document.querySelector('.user').innerHTML.replace('Flaie', 'Alexandre Grison <a href=\"/2021/support\" class=\"supporter-badge\">(AoC++)</a>');")
            js.executeScript("document.querySelector('#sidebar').remove();")
            js.executeScript("document.body.style.zoom = '1.5';")

            val screenshot = driver as TakesScreenshot
            val sc = screenshot.getScreenshotAs(OutputType.FILE)
            Files.move(
                sc.toPath(),
                Paths.get("screenshots/2021/${LocalDate.now().format(ISO_DATE)}.png"),
                REPLACE_EXISTING
            )

            // reinit zoom
            js.executeScript("document.body.style.zoom = '1';")

            driver.quit()
            System.exit(0)
        } else if (args.isNotEmpty() && "video" in args[0]) {
            val file = Paths.get("screenshots/2015/")

            val screenSize: Dimension = Toolkit.getDefaultToolkit().getScreenSize()
            val width: Int = screenSize.width
            val height: Int = screenSize.height

            val captureSize = Rectangle(0, 0, width, height)

            val gc = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration

            val screenRecorder = SpecializedScreenRecorder(
                gc,
                captureSize,
                Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                Format(
                    MediaTypeKey,
                    MediaType.VIDEO,
                    EncodingKey,
                    ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey,
                    ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey,
                    24,
                    FrameRateKey,
                    Rational.valueOf(60.0),
                    QualityKey,
                    1.0f,
                    KeyFrameIntervalKey,
                    60 * 60
                ),
                Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(60.0)),
                null,
                file.toFile(),
                "MyVideo"
            )
            screenRecorder.start()

            System.setProperty("webdriver.chrome.driver", "C:/dev/tools/chromedriver96.exe")
            val driver = ChromeDriver()
            driver.get("https://adventofcode.com/2015")
            driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS)
            val session = File(javaClass.classLoader.getResource("cookie.txt").toURI()).readText()
            driver.manage().addCookie(Cookie("session", session))
            driver.navigate().refresh()

            val js = driver as JavascriptExecutor
            js.executeScript("document.querySelector('.user').innerHTML = document.querySelector('.user').innerHTML.replace('Flaie', 'Alexandre Grison <a href=\"/2021/support\" class=\"supporter-badge\">(AoC++)</a>');")
            js.executeScript("document.querySelector('#sidebar').remove();")
            js.executeScript("document.body.style.zoom = '1.5';")

            Thread.sleep(15000)
            driver.quit()
            screenRecorder.stop()
            System.exit(0)
        }

        if (args.isNotEmpty()) {
            val (year, day) = when ("/" in args[0]) {
                true -> Pair(args[0].before("/").toInt(), args[0].after("/"))
                false -> Pair(defaultYear, args[0])
            }
            println("🎅 $YELLOW=== Advent of Code $year ===$RESET 🎅\n")

            reflections = Reflections("me.grison.aoc.y$year")

            if (day == "*") {
                val allDayClasses = getAllDayClasses()
                if (allDayClasses != null) {
                    var total = 0L
                    allDayClasses.sortedBy { dayNumber(it.simpleName) }.forEach {
                        val start = System.nanoTime()
                        printDay(it)
                        total += System.nanoTime() - start
                    }
                    println("\nTotal time: ${CYAN}${total / 1000000}ms$RESET")
                } else {
                    printError("Couldn't find day classes - make sure you're in the right directory and try building again")
                }

            } else {
                val dayClass = getAllDayClasses()?.find { dayNumber(it.simpleName) == day.toInt() }
                if (dayClass != null) {
                    printDay(dayClass)
                } else {
                    printError("Day $day not found")
                }
            }
        } else {
            println("\uD83C\uDF85 === Advent of Code (all years) === \uD83C\uDF85\n")
            allYears = true
            val allDayClasses = getAllDayClasses()
            if (allDayClasses != null) {
                allDayClasses.sortedWith(compareBy(
                    { it.`package`.name.afterLast(".") }, { dayNumber(it.simpleName) })
                ).forEach { printDay(it) }
            } else {
                printError("Couldn't find day classes - make sure you're in the right directory and try building again")
            }
        }
    }

    private fun getAllDayClasses(): MutableSet<Class<out Day>>? {
        return reflections.getSubTypesOf(Day::class.java)
    }

    private fun printDay(dayClass: Class<out Day>) {
        val day = dayClass.constructors[0].newInstance() as Day
        println("\n🎄 $WHITE---$YELLOW Day ${if (allYears) day.year.toString() + "/" else ""}${day.dayNumber}: ${day.title()} $WHITE---$RESET")

        try {
            val partOne = measureTimedValue { day.partOne() ?: "empty" }
            val partTwo = measureTimedValue { day.partTwo() ?: "empty" }
            printParts(partOne, partTwo)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun printParts(partOne: TimedValue<Any>, partTwo: TimedValue<Any>) {
        val padding = max(
            partOne.value.toString().length,
            partTwo.value.toString().length
        ) + 14        // 14 is 8 (length of 'Part 1: ') + 6 more
        println(" 🌟 Part 1: ${partOne.value}".padEnd(padding, ' ') + "($CYAN${partOne.duration}$RESET)")
        println(" 🌟 Part 2: ${partTwo.value}".padEnd(padding, ' ') + "($CYAN${partTwo.duration}$RESET)")
    }

    private fun printError(message: String) {
        System.err.println("\n=== ERROR ===\n$message")
    }

    private fun dayNumber(dayClassName: String) = dayClassName.replace("Day", "").toInt()
}

const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val BLUE = "\u001B[34m"
const val PURPLE = "\u001B[35m"
const val CYAN = "\u001B[36m"
const val WHITE = "\u001B[37m"


class SpecializedScreenRecorder(
    cfg: GraphicsConfiguration?,
    captureArea: Rectangle?,
    fileFormat: Format?,
    screenFormat: Format?,
    mouseFormat: Format?,
    audioFormat: Format?,
    movieFolder: File?,
    private val name: String
) :
    ScreenRecorder(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder) {
    @Throws(IOException::class)
    override fun createMovieFile(fileFormat: Format): File {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs()
        } else if (!movieFolder.isDirectory) {
            throw IOException("\"$movieFolder\" is not a directory.")
        }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH.mm.ss")
        return File(
            movieFolder,
            name + "-" + dateFormat.format(Date()) + "." + Registry.getInstance().getExtension(fileFormat)
        )
    }
}