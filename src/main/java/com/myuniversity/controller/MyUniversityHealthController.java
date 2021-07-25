package com.myuniversity.controller;

import com.myuniversity.entities.ServiceHealth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Health Controller class
 * Checks the Health of Microservice System
 * Used in Kubernetes Readiness Probe and Liveness Probe
 */
@RestController
@RequestMapping("health")
public class MyUniversityHealthController {

    @GetMapping
    public Object getHealthStatus() {
        return getHealthReport();
    }

    protected Object getHealthReport() {
        /**
         * Dummy Implementation.
         * This service Health Object should be fetched from service.conf file
         */
        ServiceHealth serviceHealth = new ServiceHealth();
        serviceHealth.setServiceVersion("1.0");
        serviceHealth.setServiceHealth("OK");
        return serviceHealth;
    }
}
