package org.athenian.options

import com.beust.jcommander.DynamicParameter
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import org.athenian.VersionValidator
import org.slf4j.LoggerFactory
import java.lang.String.format
import java.util.*

abstract class BaseOptions protected constructor(private val progName: String, private val argv: Array<String>) {

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
