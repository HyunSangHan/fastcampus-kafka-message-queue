CREATE TABLE my_table
(
    id         INT PRIMARY KEY AUTO_INCREMENT COMMENT '컨텐츠 id',
    user_id    INT COMMENT '유저 id',
    user_age   INT COMMENT '유저 나이',
    user_name  VARCHAR(255) COMMENT '유저 이름',
    content    TEXT COMMENT '컨텐츠 내용',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
);