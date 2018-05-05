package com.tools.workingdaysdashboard.batch;

import java.util.Date;

public class AttendanceRecord {

    private long employeeId;
    private String empName;
    private Date date;
    private String event;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceRecord that = (AttendanceRecord) o;

        if (employeeId != that.employeeId) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return event != null ? event.equals(that.event) : that.event == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "employeeId=" + employeeId +
                ", name='" + empName + '\'' +
                ", fecha=" + date +
                ", evento='" + event + '\'' +
                '}';
    }
}
