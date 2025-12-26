INSERT OR IGNORE INTO Actions (ActionId, Name, Description, Category) VALUES
(1, 'LOGIN', 'Login Action', 'User'),
(2, 'UPDATE_NAME', 'Update Name Action', 'User'),
(3, 'UPDATE_PASSWORD', 'Update Password Action', 'User');

INSERT OR IGNORE INTO Users (UserId, Username, Password, Name, RegisterDate, AccessList) VALUES
(1, 'hadi', 'password', 'Hadi Agdam', '2025-01-05 14:30:00', '1,2');

INSERT OR IGNORE INTO Systems (SystemId, Name, Status, RegisterDate) VALUES
(1, 'Main Server', 'Active', '2025-01-01 08:00:00'),
(2, 'Backup Server', 'Inactive', '2025-01-02 10:15:00'),
(3, 'Local Machine', 'Active', '2025-01-03 12:00:00');
