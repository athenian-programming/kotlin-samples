package org.athenian

import com.beust.jcommander.IParameterValidator
import com.beust.jcommander.JCommander
import java.lang.String.format

fun getVersionDesc(asJson: Boolean): String {
    val annotation = VersionAnnotation::class.java.getPackage().getAnnotation(VersionAnnotation::class.java)
    return if (asJson)
        format("{\"Version\": \"%s\", \"Release Date\": \"%s\"}", annotation.version, annotation.date)
    else
        format("Version: %s Release Date: %s", annotation.version, annotation.date)
}

class VersionValidator : IParameterValidator {
    override fun validate(name: String, value: String) {
        val console = JCommander.getConsole()
        console.println(getVersionDesc(false))
        System.exit(0)
    }
}
