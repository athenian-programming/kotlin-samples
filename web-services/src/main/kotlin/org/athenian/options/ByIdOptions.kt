package org.athenian.options

import com.beust.jcommander.Parameter
import org.athenian.common.BaseOptions

class ByIdOptions(argv: Array<String>) : BaseOptions("CustomerById", argv) {

    @Parameter(names = arrayOf("-i", "--id"), description = "Customer id", required = true)
    var id: Int = -1

    init {
        this.parseArgs()
    }
}