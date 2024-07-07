## 도커 컴포즈 백그라운드 실행법
docker-compose up -d

## 디비 더미 데이터
```sql
use rootdb

CREATE TABLE person (
	id int PRIMARY KEY,
	name varchar(100)
)

INSERT INTO person(id, name) VALUES (1, "ssar");

SELECT * FROM person
```