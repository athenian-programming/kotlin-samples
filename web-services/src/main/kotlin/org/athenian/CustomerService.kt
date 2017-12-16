package org.athenian

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {
    @GET("customers")
    fun allCustomers(): Call<List<Customer>>

    @GET("customers/{id}")
    fun customerById(@Path("id") id: Int): Call<Customer>

    @GET("customer_query")
    fun customerByName(@Query("name") name: String): Call<List<Customer>>
}
