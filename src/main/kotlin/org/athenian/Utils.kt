package org.athenian

import com.beust.jcommander.IParameterValidator
import com.beust.jcommander.JCommander

fun getVersionDesc(asJson: Boolean): String {
    val annotation = CustomerServer::class.java.getPackage().getAnnotation(VersionAnnotation::class.java)
    return if (asJson)
        "{\"Version\": \"$annotation.version\", \"Release Date\": \"$annotation.date\"}"
    else
        "Version: $annotation.version Release Date: $annotation.date"
}

class VersionValidator : IParameterValidator {
    override fun validate(name: String, value: String) {
        val console = JCommander.getConsole()
        console.println(getVersionDesc(false))
        System.exit(0)
    }
}
