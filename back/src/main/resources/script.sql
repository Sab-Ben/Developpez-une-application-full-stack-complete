CREATE DATABASE project_mdd;

USE project_mdd;

CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(40),
  `email` VARCHAR(255),
  `password` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50),
  `description` VARCHAR(2000),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50),
  `description` VARCHAR(2000),
  `author_id` int,
  `topics_id` int,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `COMMENTS` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50),
  `description` VARCHAR(2000),
  `author_id` int,
  `posts_id` int,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `SUBSCRIPTIONS` (
  users_id INT NOT NULL,
  topics_id INT NOT NULL
);

ALTER TABLE `POSTS` ADD FOREIGN KEY (`author_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topics_id`) REFERENCES `TOPICS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`author_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`posts_id`) REFERENCES `POSTS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`users_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topics_id`) REFERENCES `TOPICS` (`id`);

INSERT INTO USERS (username, email, password)
VALUES ('Admin', 'admin@test.com', 'AdminTest12345!'); 

INSERT INTO TOPICS (title, description) VALUES 
('Java', 'is a popular programming language. This language is used to develop mobile apps, web apps, desktop apps, games and much more.'),
('JavaScript', 'is the programming language of the Web which is esay to learn'),
('TypeScript', 'is JavaScript with added syntax for types'),
('Angular', 'is perfect for Single Page Applications and easy to learn'),
('React', 'is a JavaScript library for building user interfaces, which is used to build single-page applications'),
('Python', 'is a popular programming language, which can be used on a server to create web applications'), 
('C', 'is a general-purpose programming language, developed in 1972, and still quite popular.'),
('C++', 'is used to create computer programs, and is one of the most used language in game development'),
('Object Oriented Programming', 'is a methodology or paradigm to design a program using classes and objects'),
('Algorithms', ' procedures for solving a mathematical problem in a finite number of steps that frequently involves recursive operations');

