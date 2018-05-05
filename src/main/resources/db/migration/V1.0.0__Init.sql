CREATE TABLE ATTENDANCE(
    employeeId integer not null,
    empName varchar(200) not null,
    date timestamp not null,
    event varchar(50) not null,
    CONSTRAINT UC_Person UNIQUE (employeeId,empName,date, event)
);

create index idx_attendance_name on ATTENDANCE(empName);
