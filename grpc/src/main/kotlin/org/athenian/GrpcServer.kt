package org.athenian

import com.google.common.base.Strings
import com.google.common.util.concurrent.AbstractIdleService
import com.google.common.util.concurrent.MoreExecutors
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptors
import io.grpc.inprocess.InProcessServerBuilder
import org.athenian.common.GenericServiceListener

class GrpcServer(val port: Int, private val serverName: String?) : AbstractIdleService() {
    private val inProcessServer: Boolean = !Strings.isNullOrEmpty(serverName)
    private val grpcServer: Server

    init {
        val demoService = GrpcServiceImpl()
        val serviceDef = ServerInterceptors.intercept(demoService.bindService())
        this.grpcServer = if (this.inProcessServer)
            InProcessServerBuilder.forName(this.serverName)
                    .addService(serviceDef)
                    .addTransportFilter(GrpcServerTransportFilter())
                    .build()
        else
            ServerBuilder.forPort(this.port)
                    .addService(serviceDef)
                    .addTransportFilter(GrpcServerTransportFilter())
                    .build()
        this.addListener(GenericServiceListener(this), MoreExecutors.directExecutor())
    }

    override fun startUp() {
    }

    override fun shutDown() {
    }

}