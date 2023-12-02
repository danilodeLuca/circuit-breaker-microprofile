package com.talks.rest.http;

public record Condition(Integer hitWindow, Integer decrementCounter, Double requestLimitPercentage) {


    /**
     * This garantees that it will applicable while it has less than 95% of the hit limit!
     * @param totalHits
     * @return
     */
    public boolean applicable(Integer totalHits) {
        return ((double) (totalHits * 100 / hitWindow)) / 100 < requestLimitPercentage;
    }

    public Integer getDecrementCounter() {
        return decrementCounter;
    }

    public boolean shouldReset(Integer newHitLimit) {
        return false;
    }
}
