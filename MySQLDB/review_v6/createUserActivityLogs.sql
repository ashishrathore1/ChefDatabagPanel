CREATE TABLE `user_activity_logs` (
  `username` varchar(80) NOT NULL,
  `databag` varchar(80) NOT NULL,
  `item` varchar(80) NOT NULL,
  `keyname` varchar(80) NOT NULL,
  `prev_value` varchar(80) NOT NULL,
  `new_value` varchar(80) NOT NULL,
  `action` varchar(80) NOT NULL,
  `actiontimestamp` datetime NOT NULL
)
