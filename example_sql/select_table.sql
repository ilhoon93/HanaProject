select * from tab;
-- 가장 최근 데이터 가져오기
select * from t_user WHERE user_no = (SELECT max(user_no) from t_user);

-- 특정 모임계좌에 참여중인 모든 유저 정보
SELECT * FROM t_user WHERE user_no IN (SELECT member_no FROM t_moim_user WHERE moim_no = 1000000001);


-- 특정 유저가 참여하는 모든 모임계좌 정보
SELECT * FROM t_account WHERE account_no IN (SELECT moim_no FROM t_moim_user WHERE member_no = 2);

-- 특정 유저가 가진 친구목록 조회
SELECT * FROM t_user WHERE user_no IN 
(SELECT decode(my_no,3,friends_no) || decode(friends_no,3,my_no) as friends_no FROM (SELECT my_no, friends_no FROM t_friends WHERE my_no = 3 OR friends_no = 3));

-- 2번이 가진, 참여하는 모든 계좌 출력

SELECT * FROM t_account WHERE account_owner_no = 2;

SELECT * FROM t_moim_user WHERE member_no = 2;



SELECT (my_no) FROM t_friends WHERE friends_no IN(1,2,3);


SELECT user_name FROM t_user WHERE user_no IN (1,2,3);

desc t_friends;


-- 전체 유저 조회
select * from t_user;

-- 친구 관계 목록 조회
select * from t_friends;

-- 결제 그룹 테이블
select * from t_paygroup;

-- 모임 구성원 테이블
select * from t_moim_user;

-- 전체 계좌 조회
select * from t_account;


COMMIT;
-- 모임 계좌 정보 목록 조회
select * from t_moim_account;

-- 전체 거래 내역 조회
select * from t_trc_history;

-- 회비 요청 기록 조회
select * from t_money_request;

select * from t_user;

select * from t_money_request_list;
desc t_account;

SELECT trc_no, SUBSTR(trc_date,0,10), trc_amount, trc_send_account, trc_receive_account, trc_status FROM t_trc_history
    WHERE trc_receive_account = 500000001;

-- 내가 받은 모든 요청
SELECT r_no as trcNo, r_title as trcTitle, r_account_no as trcAccountNo,
				r_amount as trcAmount, r_date as trcDate
		FROM t_money_request
		WHERE r_no IN (SELECT r_no FROM t_money_request_list WHERE r_user_no = 1);

select t.* 
		from (
				select * 
				  from t_money_request 
				  order by r_no desc
			 ) t
		 where rownum = 1;
         
desc t_money_request;
