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
        EventData r = calculateRecord.getEventDataByScoreName("d100m");
        assertEquals("100 m",r.getName());
    }
}