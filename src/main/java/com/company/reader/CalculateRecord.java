package com.company.reader;

import com.company.Record;

import java.util.*;


public abstract class CalculateRecord implements RecordReader {
    private final List<Record> records = new ArrayList<>();

    @Override
    public List<Record> getRecords() {
        return records;
    }

    /**
     * Sorts list by total score.
     */
    public void sortByTotalScore() {
        records.sort(Comparator.comparing(Record::getTotalScore));
    }

    /**
     * Abstract method for getting data about event by short name. Depends on source data format.
     *
     * @param name Short name.
     * @return Data about Event.
     */
    protected abstract EventData getEventDataByScoreName(final String name);

    /**
     * Apply formula fot getting score by some discipline.
     *
     * @param shortName Short name of discipline.
     * @param score     Score.
     * @return Score.
     */
    protected Double calculateScore(final String shortName, final Double score){
        if (shortName == null || score == null || score <= 0) {
            throw new RuntimeException("Incorrect score.");
        }
        final EventData event = getEventDataByScoreName(shortName);
        return event.getA() * Math.pow(Math.abs(score - event.getB()), event.getC());
    }

    /**
     * Summarize of scores.
     *
     * @param scores List of scores.
     * @return Sum.
     */
    protected Double generateTotalScore(final Map<String, Double> scores) {
        if (scores == null) return null;
        return scores.entrySet().stream().mapToDouble(value -> calculateScore(value.getKey(), value.getValue())).sum();
    }
}
