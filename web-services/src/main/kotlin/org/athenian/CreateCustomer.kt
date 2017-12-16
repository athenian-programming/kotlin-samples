package org.athenian

import org.athenian.options.CreateOptions
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

@Throws(IOException::class)
fun main(args: Array<String>) {
    val options = CreateOptions(args)
    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val service = retrofit.create(CustomerService::class.java)
    val cust = service.createCustomer(options.name, options.address, options.paid).execute().body()
    println(cust)
}
