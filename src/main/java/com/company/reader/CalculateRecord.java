package com.company.reader;

import com.company.Record;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public abstract class CalculateRecord implements RecordReader {
    /**
     * Sorts list by total score.
     * @param records sorted list.
     */
    protected void sortByTotalScore(final List<Record> records) {
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(final Record record1, final Record record2) {
                return record1.getTotalScore().compareTo(record2.getTotalScore());
            }
        });
    }

    /**
     * Abstract method for getting data about event by short name. Depends on source data format.
     * @param name Short name.
     * @return Data about Event.
     */
    protected abstract EventData getEventDataByScoreName(final String name);

    /**
     * Apply formula fot getting score by some discipline.
     * @param shortName Short name of discipline.
     * @param score Score.
     * @return Score.
     */
    private Double calculateScore(final String shortName, final Double score) {
        final EventData event = getEventDataByScoreName(shortName);
        if (event != null) {
            return event.getA() * Math.pow(Math.abs(score - event.getB()), event.getC());
        }
        return null;
    }

    /**
     * Summarize of scores.
     * @param scores List of scores.
     * @return Sum.
     */
    protected Double generateTotalScore(final Map<String, Double> scores) {
        if (scores == null) return null;
        Double sum = 0.0;
        for (final Map.Entry<String, Double> entry : scores.entrySet()) {
            sum += calculateScore(entry.getKey(), entry.getValue());
        }
        return sum;
    }
}
