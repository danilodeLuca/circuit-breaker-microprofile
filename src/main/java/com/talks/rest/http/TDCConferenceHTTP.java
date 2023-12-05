package com.talks.rest.http;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;

import java.util.concurrent.ConcurrentHashMap;

import static com.talks.rest.model.Conference.*;

@ApplicationScoped
public class TDCConferenceHTTP {

    @Inject
    private HttpHitCounter httpHitCounter;

    /**
     * @param: requestVolumeThreshold
     * The number of consecutive requests in a rolling window.
     * <p>
     * The circuit breaker will trip if the number of failures exceed the {@code failureRatio} within the rolling window
     * of consecutive requests. The value must be greater than or equal to {@code 1}.
     */
    /**
     * @param: failureRatio
     * The ratio of failures within the rolling window that will trip the circuit to open.
     * <p>
     * The circuit breaker will trip if the number of failures exceed the {@code failureRatio} within the rolling window
     * of consecutive requests. For example, if the {@code requestVolumeThreshold} is {@code 20} and
     * {@code failureRatio} is {@code .50}, ten or more failures in 20 consecutive requests will trigger the circuit to
     * open. The value must be between {@code 0} and {@code 1} inclusive.
     * @param: delay
     * The delay in milliseconds after the circuit opens until the circuit breaker policy checks
     * the availability of the main service. The default is 5000 ms.
     * @param: successThreshold
     * The number of consecutive successful invocations of the service that are required before
     * the circuit is closed. The default is 1 request.
     */
    @CircuitBreaker(requestVolumeThreshold = 25,
            failureRatio = 0.1,
            delay = 15000,
            successThreshold = 7)
    @Fallback(fallbackMethod = "conferenceFallback",
            applyOn = {ConnectionException.class, CircuitBreakerOpenException.class})
    public String findTDCFuture() {
        httpHitCounter.evaluateRequest(TDC_FUTURE);
        return "The Developers Conference " + TDC_FUTURE;
    }

    @CircuitBreaker(requestVolumeThreshold = 50,
            failureRatio = 0.1,
            delay = 10000,
            successThreshold = 5)
    public String findTDCBusiness() {
        httpHitCounter.evaluateRequest(TDC_BUSINESS);
        return "The Developers Conference " + TDC_BUSINESS;
    }

    @CircuitBreaker(requestVolumeThreshold = 20,
            failureRatio = 0.1,
            delay = 15000,
            successThreshold = 1,
            skipOn = ConnectionException.class)
    public String findTDCInnovation() {
        httpHitCounter.evaluateRequest(TDC_INNOVATION);
        return "The Developers Conference " + TDC_INNOVATION;
    }

    @CircuitBreaker(requestVolumeThreshold = 10, failureRatio = 0.1, delay = 15000, successThreshold = 1)
    @Fallback(fallbackMethod = "conferenceFallback",
            applyOn = {ConnectionException.class, CircuitBreakerOpenException.class})
    public String dumbExample() {
        System.out.print("Tryed to process!");
        throw new ConnectionException("");
    }

    public ConcurrentHashMap<String, Integer> reset() {
        return httpHitCounter.reset();
    }

    public String conferenceFallback() {
        return "Fallback: Esquenta TDC ";
    }

    public ConcurrentHashMap<String, Integer> hits() {
        return httpHitCounter.hits();
    }
}
