package org.athenian

import org.athenian.options.ByIdOptions
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

@Throws(IOException::class)
fun main(args: Array<String>) {
    val options = ByIdOptions(args)
    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val service = retrofit.create(CustomerService::class.java)
    val call = service.customerById(options.id)
    val resp = call.execute()
    val cust = resp.body()
    println(cust)
}
