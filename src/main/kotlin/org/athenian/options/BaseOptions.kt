package org.athenian.options

import com.beust.jcommander.DynamicParameter
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import org.athenian.VersionValidator
import org.slf4j.LoggerFactory
import java.lang.String.format
import java.util.*

abstract class BaseOptions protected constructor(private val programName: String, private val argv: Array<String>) {

    @Parameter(names = arrayOf("-r", "--admin"), description = "Admin servlets enabled")
    private var adminEnabled: Boolean? = null
    @Parameter(names = arrayOf("-i", "--admin_port"), description = "Admin servlets port")
    private var adminPort: Int? = null
    @Parameter(names = arrayOf("-e", "--metrics"), description = "Metrics enabled")
    private var metricsEnabled: Boolean? = null
    @Parameter(names = arrayOf("-m", "--metrics_port"), description = "Metrics listen port")
    private var metricsPort: Int? = null
    @Parameter(names = arrayOf("-v", "--version"), description = "Print version info and exit", validateWith = arrayOf(VersionValidator::class))
    private var version = false
    @Parameter(names = arrayOf("-u", "--usage"), help = true)
    private var usage: Boolean = false
    @DynamicParameter(names = arrayOf("-D"), description = "Dynamic property assignment")
    private var dynamicParams: Map<String, String> = HashMap()

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
        try {
            val jcom = JCommander(this)
            jcom.programName = this.programName
            jcom.setCaseSensitiveOptions(false)
            jcom.parse(*argv ?: arrayOf<String>())

            if (this.usage) {
                jcom.usage()
                System.exit(0)
            }
        } catch (e: ParameterException) {
            logger.error(e.message, e)
            System.exit(1)
        }
    }


    companion object {
        private val logger = LoggerFactory.getLogger(BaseOptions::class.java)
    }
}
