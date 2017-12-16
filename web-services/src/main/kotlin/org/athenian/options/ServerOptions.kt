package org.athenian.options

import com.beust.jcommander.Parameter
import org.athenian.CustomerServer

class ServerOptions(argv: Array<String>) : BaseOptions(CustomerServer::class.java.getSimpleName(), argv) {

    @Parameter(names = arrayOf("-p", "--server_port"), description = "Listen port for server")
    private var portVal: Int? = null

    val port: Int
        get() = this.portVal ?: 8080
}