CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password TEXT NOT NULL,
    tags TEXT,
    bio TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_upload_works (
    user_id INT NOT NULL,
    work_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE user_saved_works (
    user_id INT NOT NULL,
    work_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE user_liked_works (
    user_id INT NOT NULL,
    work_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE user_followers (
    user_id INT NOT NULL,
    follower_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (follower_id) REFERENCES user(id)
);

CREATE TABLE user_following (
    user_id INT NOT NULL,
    following_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (following_id) REFERENCES user(id)
);

CREATE TABLE work (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tags TEXT,
    description TEXT,
    likes INT DEFAULT 0,
    saved_count INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES user(id)
);

CREATE TABLE work_img_urls (
    work_id INT NOT NULL,
    img_url TEXT NOT NULL,
    FOREIGN KEY (work_id) REFERENCES work(id)
);