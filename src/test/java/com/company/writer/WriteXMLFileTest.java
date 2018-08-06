package com.company.writer;

        import com.company.AppTest;
        import com.company.reader.CSVReader;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import java.io.File;

        import static org.junit.Assert.*;

public class WriteXMLFileTest {

    private CSVReader calculateRecord;

    @Before
    public void setUp() throws Exception {
        calculateRecord = new CSVReader(AppTest.EVENT_DATA,AppTest.args[0]);
    }

    @Test
    public void write() {
        final WriteXMLFile writeXMLFile = new WriteXMLFile(AppTest.args[1]);
        writeXMLFile.write(calculateRecord.getRecords());
        assertTrue(new File(AppTest.args[1]).exists());
    }
}