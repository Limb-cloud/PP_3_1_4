INSERT roles (name)
 	   VALUES ('ROLE_ADMIN'),
              ('ROLE_USER');

INSERT users (age, email, enabled, first_name, last_name, password, username)
       VALUES ('22', 'admin@local.host', 1, 'Админ', 'Админ', '$2a$10$SzMPBbE6FJ7bT8WC460yV.h7kwLg5JTZKQrbzP/Nd5nJW7QnrANyy', 'admin'),
              ('20', 'user@local.host', 1, 'Пользователь', 'Пользователей', '$2a$10$Ga.hQSiMhkc7wq9Z9U2I8uEwYsvF5lSJw20C//txThMvPmP7eMayu', 'user');

INSERT user_roles (user_id, role_id)
       VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
              ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
              ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));