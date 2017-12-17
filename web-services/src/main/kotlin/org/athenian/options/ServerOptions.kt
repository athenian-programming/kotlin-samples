package org.athenian.options

import com.beust.jcommander.Parameter
import org.athenian.CustomerServer
import org.athenian.common.BaseOptions

class ServerOptions(argv: Array<String>) : BaseOptions(CustomerServer::class.java.getSimpleName(), argv) {

    @Parameter(names = arrayOf("-p", "--server_port"), description = "Server listen port")
    var port: Int = 8090

    init {
        this.parseArgs()
    }
}