package org.athenian

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

@Throws(IOException::class)
fun main(args: Array<String>) {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val service = retrofit.create(CustomerService::class.java)
    val custs = service.allCustomers().execute().body()
    println(custs)
}
