USE mysns;

INSERT INTO users VALUES("kim@abc.com", "111", "김채원", now());
INSERT INTO users VALUES("lee@abc.com", "111", "조정빈", now());
INSERT INTO users VALUES("kwon@abc.com", "111", "신지민", now());

INSERT INTO feed(id, content) VALUES("kim@abc.com", "Hello");
INSERT INTO feed(id, content) VALUES("kwon@abc.com", "Aloha");

