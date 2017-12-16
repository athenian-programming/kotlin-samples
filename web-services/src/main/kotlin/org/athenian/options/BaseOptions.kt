package org.athenian.options

import com.beust.jcommander.*
import org.athenian.CustomerServer
import org.athenian.VersionAnnotation
import org.slf4j.LoggerFactory
import java.lang.String.format


class VersionValidator : IParameterValidator {
    fun getVersionDesc(asJson: Boolean): String {
        val annotation = CustomerServer::class.java.getPackage().getAnnotation(VersionAnnotation::class.java)
        return if (asJson)
            """{"Version": "${annotation.version}", "Release Date": "${annotation.date}"}"""
        else
            "Version: ${annotation.version} Release Date: ${annotation.date}"
    }

    override fun validate(name: String, value: String) {
        val console = JCommander.getConsole()
        console.println(getVersionDesc(false))
        System.exit(0)
    }
}

abstract class BaseOptions protected constructor(private val progName: String, private val argv: Array<String>) {

    @Parameter(names = arrayOf("-v", "--version"),
               description = "Print version info and exit",
               validateWith = arrayOf(VersionValidator::class))
    private var version = false
    @Parameter(names = arrayOf("-u", "--usage"), help = true)
    private var usage: Boolean = false
    @DynamicParameter(names = arrayOf("-D"), description = "Dynamic property assignment")
    private var dynamicParams: Map<String, String> = mutableMapOf()

    init {
        this.parseArgs(argv)

        this.dynamicParams.forEach { key, value ->
            // Strip quotes
            val prop = format("%s=%s",
                              key,
                              if (value.startsWith('"') && value.endsWith('"'))
                                  value.substring(1, value.length - 1)
                              else
                                  value)
            System.setProperty(key, prop)
        }
    }

    private fun parseArgs(argv: Array<String>?) {
        with(JCommander(this)) {
            try {
                programName = progName
                setCaseSensitiveOptions(false)
                parse(*argv ?: arrayOf<String>())

                if (usage) {
                    usage()
                    System.exit(0)
                }
            } catch (e: ParameterException) {
                logger.error(e.message)
                usage()
                System.exit(1)
            }
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BaseOptions::class.java)
    }
}
