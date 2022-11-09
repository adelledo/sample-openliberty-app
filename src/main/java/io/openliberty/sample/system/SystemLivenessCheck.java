package io.openliberty.sample.system;

import jakarta.enterprise.context.ApplicationScoped;

import java.lang.management.MemoryMXBean;
import java.lang.management.ManagementFactory;

import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Liveness
@ApplicationScoped
public class SystemLivenessCheck implements HealthCheck {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public HealthCheckResponse call() {
        //logger.trace("This is a trace log");
        
        //logger.debug("This is a debug log");

        //logger.info("this is an info log");

        //logger.warn("this is a warn log");

        //logger.error("This is an error log");

        logger.fatal("this is a fatal log");
       System.out.println("This is a cool print statement");
        
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memBean.getHeapMemoryUsage().getUsed();
        long memMax = memBean.getHeapMemoryUsage().getMax();

        return HealthCheckResponse.named(
            SystemResource.class.getSimpleName() + " Liveness Check")
                                  .status(memUsed < memMax * 0.9).build();
    }

}