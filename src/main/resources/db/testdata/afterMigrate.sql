INSERT IGNORE INTO user (id, username, password, email, active)
VALUES (1, 'user', '$2a$10$XUNDtM8r1v6pIjZFy8Ov7.qw4G9rj7DdqKcS36uVtQNWFosdnw/hu', 'user@user.user', TRUE);

INSERT IGNORE INTO club (id, name, city)
VALUES (1, 'KaGe Fasching Hinterderburg', 'Hinterderburg');

INSERT IGNORE INTO club (id, name, city)
VALUES (2, 'Kerscheblotzer Hinnadupfinge', 'Hintertupfingen');

INSERT IGNORE INTO classification (id, name, description)
VALUES (1, 'Minis Showtanz', 'Altersgruppe 2-4 für den Showtanz');

INSERT IGNORE INTO classification (id, name, description)
VALUES (2, 'Kleine Showtanz', 'Altersgruppe 5-8 für den Showtanz');

INSERT IGNORE INTO club_group (id, name, age, club_id)
VALUES (1, 'Mini KaGeler', '2-4', 1);

INSERT IGNORE INTO club_group (id, name, age, club_id)
VALUES (2, 'Kleine KaGeler', '5-8', 1);

INSERT IGNORE INTO club_group (id, name, age, club_id)
VALUES (3, 'Mini Kerscheblotzer', '2-4', 2);

INSERT IGNORE INTO club_group (id, name, age, club_id)
VALUES (4, 'Kleine Kerscheblotzer', '5-8', 2);
