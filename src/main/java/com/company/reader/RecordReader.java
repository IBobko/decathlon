package com.company.reader;

import com.company.Record;

import java.util.List;

public interface RecordReader {
    /**
     * Return records about sportsmen by POJO format.
     * @return list of records.
     */
    List<Record> getRecords();
}
