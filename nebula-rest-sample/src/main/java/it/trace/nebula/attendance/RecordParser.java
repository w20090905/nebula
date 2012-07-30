package it.trace.nebula.attendance;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RecordParser {

    private final List<Record> recordList;

    public RecordParser(List<Record> recordList) {
        if (recordList == null) {
            throw new IllegalArgumentException();
        }
        Collections.sort(recordList, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        this.recordList = recordList;
    }

    public List<Attendance> parse(Date date) {
        return null;
    }



}
