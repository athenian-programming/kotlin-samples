package org.athenian

import io.grpc.Attributes
import io.grpc.ServerTransportFilter
import org.slf4j.LoggerFactory

class GrpcServerTransportFilter() : ServerTransportFilter() {

    private fun getRemoteAddr(attributes: Attributes): String {
        val keyOptional = attributes.keys()
                .stream()
                .filter { key -> "remote-addr" == key.toString() }
                .findFirst()
        if (keyOptional.isPresent) {
            val key = keyOptional.get() as Attributes.Key<Any>
            val `val` = attributes.get(key)
            if (`val` != null)
                return `val`.toString()
        }
        return "Unknown"
    }

    override fun transportReady(attributes: Attributes): Attributes {
        val remoteAddr = this.getRemoteAddr(attributes)
        logger.info("Connected to ${remoteAddr}")
        return Attributes.newBuilder()
                .setAll<Any>(attributes)
                .build()
    }

    override fun transportTerminated(attributes: Attributes?) {
        logger.info("Disconnected")
        super.transportTerminated(attributes)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GrpcServerTransportFilter::class.java)
    }
}
