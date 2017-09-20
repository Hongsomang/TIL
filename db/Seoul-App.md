## Seoul-App

#### 1. 통신부분 정리

​	-로그인(facebook 연동)

​	-로그아웃

​	-홍보글 등록

​	-홍보글 열람

​	-홍보글 삭제

​	-홍보글 댓글

​	-홍보글 좋아요

#### 2.테이블 구조 정리

**2-1 유저 정보 (fk_token:varchar(50),name:varchar(10),email:varchar(20),photo:varchar(50))**

```
/*저장 데이터 : 페이스북토큰,이름,이메일,사진
CREATE TABLE user(
	fk_token varchar(50) not null,
	name varchar(10) not null,
    email varchar(20),
    photo:varchar(50),
	PRIMARY KEY(fk_token)
)
```

**2-2행사 정보(event_id: unsigned int,user_toke,event_title:varchar(80),start_date:varchar(20),finish_date:varchar(20),event_content:text,content_image:varchar(50),event_adress:varchar(80),now_data:varchar(20),good:unsigned int,adress_x: INT unsigned,andress_y: INT unsigned)**

```
/*저장 데이터: 행사 번호,유저 정보,행사 이름,행사 시작 날짜, 행사 끝나는 날짜,내용,이미지,주소,글 올린 시간,좋아요,x좌표,y좌표*/
CREATE TABLE envent_board(
	event_id INT unsigned NOT NULL,
	fk_token VARCHAR(50) NOT NULL,
	event_title VARCHAR(50) NOT NULL,
	start_data VARCHAR(20) NOT NULL,
	finish_data VARCHAR(20) NOT NULL,
	now_data VARCHAR(20) NOT NULL,
	event_content TEXT NOT NULL,
	content VARCHAR(50) NOT NULL,
	event_adress VARCHAR(80) NOT NULL,
	good  INT unsigned ,
	adress_x INT unsigned NOT NULL,
	adrees_y INT unsigned NOT NULL,
	PRIMARY KEY(event_id)	
);
```

**2-3 댓글 정보(event_id, comm_id, user_token,comm_date:VARCHAR(20), comm_content:varchar(500) )**

```
/*저장 데이터: 행사번호,댓글번호,유저정보, 쓴 시간,내용, y좌표, x좌표*/
CREATE TABLE comment(
	event_id INT unsigned NOT NULL,
	comm_id INT unsigned NOT NULL,
	fk_token VARCHAR(50) NOT NULL,
	comm_data VARCHAR(20) NOT NULL,
	comm_content VARCHAR(500) NOT NULL,
	PRIMARY KEY(comm_id)
); 
```







 
