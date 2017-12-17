package org.athenian

import com.google.common.util.concurrent.Service
import org.slf4j.LoggerFactory

class GenericServiceListener(private val service: Service) : Service.Listener() {

    override fun starting() {
        super.starting()
        logger.info("Starting ${this.service}")
    }

    override fun running() {
        super.running()
        logger.info("Running ${this.service}")
    }

    override fun stopping(from: Service.State) {
        super.stopping(from)
        logger.info("Stopping ${this.service}")
    }

    override fun terminated(from: Service.State) {
        super.terminated(from)
        logger.info("Terminated ${this.service}", this.service)
    }

    override fun failed(from: Service.State, t: Throwable) {
        super.failed(from, t)
        logger.info("Failed on ${from} ${this.service} ${t}")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GenericServiceListener::class.java)
    }
}
