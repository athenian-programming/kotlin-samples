package org.athenian

import retrofit2.Call
import retrofit2.http.GET

interface CustomerService {
    @GET("customers")
    fun allCustomers(): Call<List<Customer>>
}
