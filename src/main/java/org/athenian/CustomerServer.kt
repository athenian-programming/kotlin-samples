package org.athenian

import com.google.common.util.concurrent.AbstractIdleService
import com.squareup.moshi.Moshi
import spark.Service


data class Customer(val id: Int, val name: String, val address: String, val paid: Boolean)

class CustomerServer(val port: Int = 8080) : AbstractIdleService() {

    private val http = Service.ignite()
    private val moshi = Moshi.Builder().build()

    override fun startUp() {
        this.http.port(this.port)

        this.http.get("/") { _, res ->
            res.type("text/plain")
            "This is the root"
        }

        this.http.get("/plain-hello") { _, res ->
            res.type("text/plain")
            "Hello World!"
        }

        this.http.get("/html-hello") { _, res ->
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

        var customers = listOf<Customer>(Customer(1, "Bill Smith", "123 Main St", false),
                                         Customer(2, "Jane Jackson", "1245 Birch Ave", true),
                                         Customer(3, "Steve Stillwell", "433 Peach Lane", true),
                                         Customer(4, "Mary McKenna", "3454 Apple St", false))

        this.http.get("/customers") { _, res ->
            res.type("application/json")
            val jsonAdapter = this.moshi.adapter<Map<String, List<Customer>>>(Map::class.java)
            jsonAdapter.toJson(mapOf("customers" to customers))
        }

        this.http.get("/customers/:id") { req, res ->
            res.type("application/json")
            val jsonAdapter = this.moshi.adapter<Map<String, Customer?>>(Map::class.java)
            val id = req.params(":id").toInt()
            val cust: Customer? = customers.find { it.id == id }
            if (cust == null)
                res.status(404)
            jsonAdapter.toJson(mapOf("customer" to cust))
        }

        this.http.get("/customer_query") { req, res ->
            res.type("application/json")
            val name: String? = req.queryParams("name")
            if (name == null) {
                res.status(400)
                "{}"
            }
            else {
                val matches: List<Customer>? = customers.filter { it.name.contains(name) }
                if (matches == null)
                    res.status(404)

                val jsonAdapter = this.moshi.adapter<Map<String, List<Customer>?>>(Map::class.java)
                jsonAdapter.toJson(mapOf("customers" to matches))
            }
        }

        this.http.post("/customers") { req, res ->
            val name: String? = req.queryParams("name")
            val r = req.raw()
            val body = req.body()
            res.type("application/json")
            val jsonAdapter = this.moshi.adapter<Map<String, List<Customer>>>(Map::class.java)
            jsonAdapter.toJson(mapOf("customers" to customers))
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
