CREATE TABLE IF NOT EXISTS Actions(
    ActionId INTEGER PRIMARY KEY,
    Name TEXT,
    Description TEXT,
    Category TEXT
);

CREATE TABLE IF NOT EXISTS Users(
    UserId INTEGER PRIMARY KEY,
    Username TEXT,
    Password TEXT,
    Name TEXT,
    RegisterDate TEXT,
    AccessList TEXT
);

CREATE TABLE IF NOT EXISTS Systems(
    SystemId INTEGER PRIMARY KEY,
    Name TEXT,
    Status TEXT,
    RegisterDate TEXT
);

CREATE TABLE IF NOT EXISTS Logs(
    LogId INTEGER PRIMARY KEY,
    ActionId INTEGER,
    UserId INTEGER,
    SystemId INTEGER,
    Date TEXT,
    Info TEXT
);
