-- 유저 테이블 생성 + 고객 번호 시퀀스
CREATE TABLE t_user(
    user_no                  number(10)      PRIMARY KEY,
    user_name                varchar2(30)    NOT NULL,
    user_email               varchar2(50)    UNIQUE,
    user_password            varchar2(50)    NOT NULL,
    user_img_origin_file     varchar2(100),
    user_img_server_file     varchar2(100),
    user_phone_number        number(15)
);

-- 계좌 테이블 생성
CREATE TABLE t_account(
    account_no                  number(20)      PRIMARY KEY,
    account_owner_no            number(10)      NOT NULL,
    account_total_amount        number(20)      DEFAULT 0 NOT NULL,
    account_type                char(1)         DEFAULT '0' NOT NULL, -- 일반계좌 '0', 그룹계좌 '1', 기업계좌 '9' 
    account_rate                number(10,7)    DEFAULT 0 NOT NULL,
    CONSTRAINT fk_account_owner FOREIGN KEY(account_owner_no) REFERENCES t_user(user_no) -- 계좌 소유자는 유저 테이블의 번호 참조
--    CONSTRAINT fk_account_paygroup FOREIGN KEY(account_payment_group) REFERENCES t_paygroup(p_group_no)
);

-- 친구 관계 목록 테이블
CREATE TABLE t_friends(
    my_no                   number(10)  NOT NULL,
    friends_no              number(10)  NOT NULL,
    CONSTRAINT fk_my_no FOREIGN KEY (my_no) REFERENCES t_user(user_no),
    CONSTRAINT fk_friends_no FOREIGN KEY (friends_no) REFERENCES t_user(user_no),
    CONSTRAINT uniq_friends_relation UNIQUE (my_no,friends_no)
);

--모임 구성원 테이블
CREATE TABLE t_moim_user(
    moim_no                       number(20),
    member_no                     number(10),
    member_account                number(20), -- 연결 계좌
    CONSTRAINT fk_member_no FOREIGN KEY (member_no) REFERENCES t_user(user_no),
    CONSTRAINT uniq_moim_relation UNIQUE (moim_no,member_no)
);

-- 거래 내역 테이블 생성
CREATE TABLE t_trc_history(
    trc_no                      number(10)      PRIMARY KEY,
    trc_date                    VARCHAR2(50)    DEFAULT to_char(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS:FF3'),
    trc_amount                  number(20)      NOT NULL,
    trc_send_account            number(10)      NOT NULL,
    trc_receive_account         number(10)      NOT NULL,
    trc_status                  char(1)         NOT NULL,
    trc_sender_name             varchar2(20)    NOT NULL,
    trc_title                   varchar2(30)    NOT NULL,
    trc_receipt_server          varchar2(50),
    trc_receipt_origin          varchar2(50),
    CONSTRAINT fk_send_no FOREIGN KEY(trc_send_account) REFERENCES t_account(account_no),
    CONSTRAINT fk_receive_no FOREIGN KEY(trc_receive_account) REFERENCES t_account(account_no)
);


-- 회비 요청 기록
CREATE TABLE t_money_request(
    r_no                        number(10)      PRIMARY KEY,
    r_title                     varchar2(40)    NOT NULL,
    r_account_no                number(20)      NOT NULL,
    r_amount                    number(20)      NOT NULL,
    r_date                      varchar2(50)    NOT NULL,
    t_status                    char(1)         NOT NULL,
    CONSTRAINT fk_r_account_no  FOREIGN KEY (r_account_no) REFERENCES t_account(account_no)
);

-- 회비 요청 기록 수신유저 목록
CREATE TABLE t_money_request_list(
    r_no                        number(10),
    r_user_no                   number(10),
    r_status                    char(1),
    CONSTRAINT fk_r_l_no FOREIGN KEY (r_no) REFERENCES t_money_request(r_no),
    CONSTRAINT fk_r_u_no FOREIGN KEY (r_user_no) REFERENCES t_user(user_no)
);

COMMIT;
-- 결제 그룹 테이블 (은행, 계좌 정보)
--CREATE TABLE t_paygroup(
--    p_group_no                  number(20)      PRIMARY KEY,
--    p_group_name                varchar2(100)   NOT NULL,
--    p_group_commission          number(10)      DEFAULT 0           
--);

--모임 계좌 정보 테이블 
--CREATE TABLE t_moim_account(
--    moim_account_no             number(20)      PRIMARY KEY,
--    moim_account_name           varchar2(100)   NOT NULL,
--    moim_account_regdate        date            DEFAULT SYSDATE,
--    moim_account_cnt            number(10)      DEFAULT 0,
--    CONSTRAINT fk_moim_account_no FOREIGN KEY (moim_account_no) REFERENCES t_account(account_no) -- 모임계좌번호는 계좌 테이블 참조
--);


----시퀀스 생성
--유저 번호
CREATE SEQUENCE s_user_no
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1;
-- 계좌 번호
CREATE SEQUENCE s_account_no
    INCREMENT BY 1
    START WITH 1000000000
    MINVALUE 100000000;
    
-- 출금 요청 번호
CREATE SEQUENCE s_request_no
    INCREMENT BY 1
    START WITH 0001
    MINVALUE 0001;
    
-- 트랜잭션 번호
CREATE SEQUENCE s_transaction_no
    INCREMENT BY 1
    START WITH 1;

-- 고객, 계좌, 트랜잭션 테이블 생성
COMMIT;
--------------------
