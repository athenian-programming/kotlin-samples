package org.athenian.options

import com.beust.jcommander.Parameter

class ByNameOptions(argv: Array<String>) : BaseOptions("CustomerByName", argv) {

    @Parameter(names = arrayOf("-n", "--name"), description = "Customer name", required = true)
    var name: String = ""

    init {
        this.parseArgs()
    }
}