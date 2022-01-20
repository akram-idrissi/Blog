CREATE TABLE User (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20),
    email VARCHAR(40),
    hashedpass VARCHAR(600),
    salt VARCHAR(150),
    register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY(id)
);

CREATE TABLE Post (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(100), 
    content TEXT,
    like_count INT,
    dislike_count INT,
    comment_count INT,
    register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Message (
    id INT NOT NULL AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    message_date TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Comment (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    content TEXT,
    ike_count INT,
    dislike_count INT,
    comment_date TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (post_id) REFERENCES Post(id)
);

CREATE TABLE PostInsight (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    is_like INT,
    is_dislike INT,
    postIns_date TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (post_id) REFERENCES Post(id)
);

CREATE TABLE CommentInsight (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    comment_id INT NOT NULL,
    is_commented INT,
    commentIns_date TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (post_id) REFERENCES Post(id),
    FOREIGN KEY (comment_id) REFERENCES Comment(id)
);






