package it.trace.nebula.attendance;

import java.util.Date;

public class Attendance {

    private long id;
    private long personId;
    private Date date = null;
    private State stae = null;
    private Date startTime = null;
    private Date endTime = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getStae() {
        return stae;
    }

    public void setStae(State stae) {
        this.stae = stae;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
