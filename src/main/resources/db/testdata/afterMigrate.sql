########## USER TABLE ##############
INSERT IGNORE INTO user (id, username, password, email, active)
VALUES (1, 'user', '$2a$10$XUNDtM8r1v6pIjZFy8Ov7.qw4G9rj7DdqKcS36uVtQNWFosdnw/hu', 'user@user.user', TRUE);

########## Club TABLE ##############
INSERT IGNORE INTO club (id, name, city)
VALUES (1, 'KaGe Fasching Hinterderburg', 'Hinterderburg');

INSERT IGNORE INTO club (id, name, city)
VALUES (2, 'Kerscheblotzer Hinnadupfinge', 'Hintertupfingen');

########## Classification TABLE ##############
INSERT IGNORE INTO classification (id, name, description)
VALUES (1, 'Minis Showtanz', 'Altersgruppe 2-4 für den Showtanz');

INSERT IGNORE INTO classification (id, name, description)
VALUES (2, 'Kleine Showtanz', 'Altersgruppe 5-8 für den Showtanz');

########## Show-Rating TABLE ##############
INSERT IGNORE INTO show_rating (id, group_id)
VALUES (1, 1);

########## Rating TABLE ##############
INSERT IGNORE INTO rating (id, value, show_rating_id)
VALUES (1, 4.5, 1);

INSERT IGNORE INTO rating (id, value, show_rating_id)
VALUES (2, 6, 1);

INSERT IGNORE INTO rating (id, value, show_rating_id)
VALUES (3, 8, 1);

########## Club-Group TABLE ##############
INSERT IGNORE INTO club_group (id, name, age, time, club_id)
VALUES (1, 'Mini KaGeler', '2-4', null, 1);

INSERT IGNORE INTO club_group (id, name, age, time, club_id)
VALUES (2, 'Kleine KaGeler', '5-8', null, 1);

INSERT IGNORE INTO club_group (id, name, age, time, club_id)
VALUES (3, 'Mini Kerscheblotzer', '2-4', null, 2);

INSERT IGNORE INTO club_group (id, name, age, time, club_id)
VALUES (4, 'Kleine Kerscheblotzer', '5-8', null, 2);
