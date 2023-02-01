create table logger (
    id BIGINT primary key,
    datelog timestamp,
    logger TEXT,
    levellog TEXT,
    message TEXT,
    exceptionlog TEXT
);