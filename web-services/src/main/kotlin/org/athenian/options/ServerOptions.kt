package org.athenian.options

import com.beust.jcommander.Parameter
import org.athenian.CustomerServer

class ServerOptions(argv: Array<String>) : BaseOptions(CustomerServer::class.java.getSimpleName(), argv) {

    @Parameter(names = arrayOf("-p", "--server_port"), description = "Serve listen port")
    var port: Int = 8090

    init {
        this.parseArgs()
    }
}