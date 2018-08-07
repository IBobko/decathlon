package com.company.reader;

import com.company.Record;

import java.util.List;

public interface RecordReader {
    /**
     * Return records about sportsmen by POJO format.
     * @return list of records.
     */
    List<Record> getRecords();

    /**
     * If It is necessary this method sorts records by the total score.
     */
    void sortByTotalScore();
}
