package org.athenian

import com.google.common.util.concurrent.AbstractIdleService
import spark.Service

class CustomerServer(val port: Int = 8080) : AbstractIdleService() {

    private val http = Service.ignite()

    override fun startUp() {
        this.http.port(this.port)

        this.http.get("/") { req, res ->
            res.type("text/plain")
            "This is the root"
        }

        this.http.get("/plain-hello") { req, res ->
            res.type("text/plain")
            "Hello World!"
        }

        this.http.get("/html-hello") { req, res ->
            res.type("text/html")
            """
            <html>
                <head>
                </head>
                <body>
                    <h1>Hello World!</h1>
                </body>
            </html>
            """
        }
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
