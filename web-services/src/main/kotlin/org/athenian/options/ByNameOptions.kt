package org.athenian.options

import com.beust.jcommander.Parameter

class ByNameOptions(argv: Array<String>) : BaseOptions("CustomerByName", argv) {

    @Parameter(names = arrayOf("-n", "--name"), description = "Customer Name", required = true)
    private var nameVal: String? = null

    val name: String
        get() = this.nameVal ?: ""
}