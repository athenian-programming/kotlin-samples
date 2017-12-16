package org.athenian.options

import com.beust.jcommander.Parameter

class CreateOptions(argv: Array<String>) : BaseOptions("CreateCustomer", argv) {

    @Parameter(names = arrayOf("-n", "--name"), description = "Customer name", required = true)
    private var nameVal: String? = null

    @Parameter(names = arrayOf("-a", "--address"), description = "Customer address")
    private var addressVal: String? = null

    @Parameter(names = arrayOf("-p", "--paid"), description = "Customer paid")
    var paid: Boolean = false

    val name: String
        get() = this.nameVal ?: ""

    val address: String
        get() = this.addressVal ?: ""
}