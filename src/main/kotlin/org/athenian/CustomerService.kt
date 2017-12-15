package org.athenian

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CustomerService {
    @GET("customers")
    fun allCustomers(): Call<List<Customer>>

    @GET("customers/{id}")
    fun customerById(@Path("id") id: Int): Call<Customer>
}
