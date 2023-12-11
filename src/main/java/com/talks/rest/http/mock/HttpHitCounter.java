package com.talks.rest.http.mock;

import com.talks.rest.http.ConnectionException;
import com.talks.rest.http.pojos.Condition;
import com.talks.rest.model.Conference;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class simulates a possible behavorial for a external communication to a new service.
 * @variables
 *      - httpHitCondition: represents the "htttp request" and the conditions on how it should respond,
 * simulating for example a rate limit that a endpoint can reach.
 *      - hits: represents the hit count for each request.
 *
 *
 */
@ApplicationScoped
public class HttpHitCounter {

    private static ConcurrentHashMap<String, Integer> hits;

    private static HashMap<String, Condition> httpHitCondition;

    @PostConstruct
    public void initialize() {
        hits = new ConcurrentHashMap<>();
        hits.put(Conference.TDC_FUTURE, 0);
        hits.put(Conference.TDC_BUSINESS, 0);
        hits.put(Conference.TDC_INNOVATION, 0);

        httpHitCondition = new HashMap<>();
        httpHitCondition.put(Conference.TDC_FUTURE, new Condition(20, 5, 0.9));
        httpHitCondition.put(Conference.TDC_BUSINESS, new Condition(50, 5, 0.7));
        httpHitCondition.put(Conference.TDC_INNOVATION, new Condition(25, 2, 0.5));
    }

    public Integer incrementAndGet(String key) {
        return hits.merge(key, 1, Integer::sum);
    }

    public Integer decrementAndGet(String key, Integer amount) {
        return hits.merge(key, -amount, Integer::sum);
    }

    public boolean evaluateRequest(String key) {
        Condition condition = httpHitCondition.get(key);
        Integer totalHits = hits.get(key);
        if (!condition.applicable(totalHits)) {
            decrementAndGet(key, condition.getDecrementCounter());
            throw new ConnectionException("It was not possible to reach server " + key + ", total Hits: " + totalHits);
        }
        incrementAndGet(key);
        return true;
    }

    public ConcurrentHashMap<String, Integer> reset() {
        hits.put(Conference.TDC_FUTURE, 0);
        hits.put(Conference.TDC_BUSINESS, 0);
        hits.put(Conference.TDC_INNOVATION, 0);
        return hits;
    }


    public ConcurrentHashMap<String, Integer> hits() {
        return hits;
    }
}

