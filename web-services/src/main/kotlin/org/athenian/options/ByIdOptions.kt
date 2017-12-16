package org.athenian.options

import com.beust.jcommander.Parameter

class ByIdOptions(argv: Array<String>) : BaseOptions("CustomerById", argv) {

    @Parameter(names = arrayOf("-i", "--id"), description = "Customer id", required = true)
    private var idVal: Int? = null

    val id: Int
        get() = this.idVal ?: -1
}