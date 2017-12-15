package org.athenian.options

import com.beust.jcommander.Parameter
import com.google.common.collect.Iterables

class ByIdOptions(argv: Array<String>) : BaseOptions("CustomerByIdK", argv) {

    @Parameter(names = arrayOf("-i", "--id"), description = "Customer Id", required = true)
    private var idVal: Int? = null

    val id: Int
        get() = this.idVal ?: -1

    constructor(args: List<String>) : this(Iterables.toArray<String>(args, String::class.java)) {
    }
}