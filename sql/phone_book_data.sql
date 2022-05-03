-- 테이블 생성
CREATE TABLE Address_book(
    id number(10) Primary KEY,
    name varchar2(20),
    hp varchar2(20),
    tel varchar2(20));

-- 테이블 조회  

SELECT * FROM Address_book ORDER BY id;
-- 테이블 삭제
DELETE FROM Address_book;

-- INSERT TEST
INSERT INTO Address_book VALUES(4,'도우너','010-1234-5678','070-0382-4823');
-- UPDATE : 레코드 변경
UPDATE Address_book SET name = '도우너', hp='010-4567-5923',tel='070-3258-2432');
COMMIT;
ROLLBACK;
-- DELETE 
DELETE FROM Address_book
WHERE name LIKE '또치';

-- 시퀀스 생성
CREATE SEQUENCE seq_Address_book
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10;

-- 시퀀스 조회
SELECT * FROM USER_SEQUENCES;

-- 시퀀스 삭제
DROP SEQUENCE seq_Address_book;


 