package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * 100 m	10.395	10.827	11.278	11.756	Seconds
 * Long jump	7.76	7.36	6.94	6.51	Metres
 * Shot put	18.4	16.79	15.16	13.53	Metres
 * High jump	2.20	2.10	1.99	1.88	Metres
 * 400 m	46.17	48.19	50.32	52.58	Seconds
 * 110 m hurdles	13.8	14.59	15.419	16.29	Seconds
 * Discus throw	56.17	51.4	46.59	41.72	Metres
 * Pole vault	5.28	4.96	4.63	4.29	Metres
 * Javelin throw	77.19	70.67	64.09	57.45	Metres
 * 1500 m	3:53.79	4:07.42	4:21.77	4:36.96	Minutes:Seconds
 */


public class Record {
    private String name;
    private Map<String, Double> scores;
    private Double totalScore;

    public Record(String name) {
        this.name = name;
        scores = new HashMap<>();
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getScores() {
        return scores;
    }
}
