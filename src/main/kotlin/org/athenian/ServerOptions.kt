package org.athenian

import com.beust.jcommander.Parameter
import com.google.common.collect.Iterables

class ServerOptions(argv: Array<String>) : BaseOptions(CustomerServer::class.java.getSimpleName(), argv) {

    @Parameter(names = arrayOf("-p", "--server_port"), description = "Listen port for server")
    private var portVal: Int? = null

    val port: Int
        get() = this.portVal ?: 8080

    constructor(args: List<String>) : this(Iterables.toArray<String>(args, String::class.java)) {
    }
}