package com.company.reader;

import com.company.AppTest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculateRecordTest {
    private CalculateRecord calculateRecord;

    @Before
    public void setUp() throws Exception {
        calculateRecord = new CSVReader(AppTest.EVENT_DATA,AppTest.args[0]);
    }

    @Test
    public void sortByTotalScore() {
        calculateRecord.sortByTotalScore(calculateRecord.getRecords());
        assertEquals("Jane Doe",calculateRecord.getRecords().get(0).getName());
    }

    @Test
    public void getEventDataByScoreName() {
        final EventData eventData = calculateRecord.getEventDataByScoreName("d100m");
        assertEquals("100 m",eventData.getName());
        assertEquals("d100m",eventData.getShortName());
        assertEquals(25.4347,eventData.getA(),0.1);
        assertEquals(18.,eventData.getB(),0.1);
        assertEquals(1.81,eventData.getC(),0.1);
    }

    @Test
    public void generateTotalScore() {
        final Map<String, Double> scores = new HashMap<>();
        scores.put("d100m",1.);
        scores.put("longJump",1.);
        scores.put("shotPut",1.);
        scores.put("highJump",1.);
        scores.put("d400m",1.);
        scores.put("d110m",1.);
        scores.put("discusThrow",1.);
        scores.put("poleVault",1.);
        scores.put("javelinThrow",1.);
        scores.put("d1500m",1.);
        assertEquals(16356, Math.round(calculateRecord.generateTotalScore(scores)));
    }
}