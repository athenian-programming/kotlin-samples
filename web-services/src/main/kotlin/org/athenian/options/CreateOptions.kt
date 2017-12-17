package org.athenian.options

import com.beust.jcommander.Parameter
import org.athenian.common.BaseOptions

class CreateOptions(argv: Array<String>) : BaseOptions("CreateCustomer", argv) {

    @Parameter(names = arrayOf("-n", "--name"), description = "Customer name", required = true)
    var name: String = ""

    @Parameter(names = arrayOf("-a", "--address"), description = "Customer address")
    var address: String = ""

    @Parameter(names = arrayOf("-p", "--paid"), description = "Customer paid")
    var paid: Boolean = false

    init {
        this.parseArgs()
    }

}