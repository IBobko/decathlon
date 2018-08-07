package com.company.reader;

import com.company.AppTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSVReaderTest {
    private CSVReader calculateRecord;

    @Before
    public void setUp() throws Exception {
        calculateRecord = new CSVReader(AppTest.EVENT_DATA,AppTest.args[0]);
    }

    @Test
    public void getEventDataByScoreName() {
        final EventData r = calculateRecord.getEventDataByScoreName("d100m");
        assertEquals("100 m",r.getName());
    }

    @Test
    public void getScoreNameByIndex() {
        assertEquals("d100m",calculateRecord.getScoreNameByIndex(1));
    }

    @Test
    public void getSeconds() {
        assertEquals(610.1,calculateRecord.getSeconds("10.10.100"),0.1);
    }
}