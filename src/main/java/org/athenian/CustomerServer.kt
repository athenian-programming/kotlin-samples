package org.athenian

import com.google.common.util.concurrent.AbstractIdleService
import spark.Service

class CustomerServer(val port: Int = 8080) : AbstractIdleService() {

    private val http = Service.ignite()

    init {
        this.http.port(this.port)
    }

    override fun startUp() {
        this.http.get("/hello") { req, res -> "Hello World" }
    }

    override fun shutDown() {
        this.http.stop()
    }
}

fun main(args: Array<String>) {
    val options = ServerOptions(args)

    val server = CustomerServer(port = options.port)
    server.startAsync();
}
