package org.athenian

import java.util.concurrent.atomic.AtomicInteger

data class Customer(val name: String, val address: String, val paid: Boolean) {
    val id = idCounter.getAndIncrement()

    override fun toString(): String {
        return "Customer(id=$id, name='$name', address='$address', paid=$paid)"
    }

    companion object {
        private val idCounter: AtomicInteger = AtomicInteger(1)
    }

}