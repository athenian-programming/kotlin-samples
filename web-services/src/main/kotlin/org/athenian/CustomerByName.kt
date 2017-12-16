package org.athenian

import org.athenian.options.ByNameOptions
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

@Throws(IOException::class)
fun main(args: Array<String>) {
    val options = ByNameOptions(args)
    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val service = retrofit.create(CustomerService::class.java)
    val custs = service.customerByName(options.name).execute().body() ?: listOf()
    for (cust in custs)
        println(cust)
}
