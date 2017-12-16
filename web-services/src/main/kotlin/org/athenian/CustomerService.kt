package org.athenian

import retrofit2.Call
import retrofit2.http.*


interface CustomerService {
    @GET("customers")
    fun allCustomers(): Call<List<Customer>>

    @GET("customers/{id}")
    fun customerById(@Path("id") id: Int): Call<Customer>

    @GET("customer_query")
    fun customerByName(@Query("name") name: String): Call<List<Customer>>

    @FormUrlEncoded
    @POST("customers")
    fun createCustomer(@Field("name") name: String, @Field("address") address: String, @Field("paid") paid: Boolean): Call<Customer>
}

