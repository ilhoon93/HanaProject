-- 유저 정보 입력

INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (0, '더미', 'company', '123456', 'ori_file','server_file', '00');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (1, '강일훈', 'kohkhj902@naver.com', '123456', 'ori_file','server_file', '01038506747');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (2, '김동환', 'sublime_94@naver.com', 'rlaehdghks12', 'ori_file','server_file', '01012341234');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (3, '전준우', 'joonwo@naver.com', 'joon1234', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (4, '박재형', 'park92@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (5, '이승수', 'purechild@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (6, '정유진', 'jyj@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (7, '천준우', 'cjw@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (8, '홍길동', 'hgd@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (9, '박준웅', 'pjw@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (10, '김정연', 'kjy@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (11, '김상화', 'ksh@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (12, '박정원', 'pjw2@naver.com', '123456', 'ori_file','server_file', '01012345678');
INSERT INTO t_user (user_no, user_name, user_email, user_password, user_img_origin_file, user_img_server_file, user_phone_number)
    VALUES (13, '정창현', 'jch@naver.com', '123456', 'ori_file','server_file', '01012345678');



    
    
-- 계좌 정보 입력
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (500000001, 1, 10000, '1', 1.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000001, 1, 221012000, '0', 1.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000002, 2, 3000000, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000003, 3, 3000000, '0', 2.5);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (500000002, 4, 123213, '1', 1.5);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000004, 4, 123213, '0', 1.5);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000005, 5, 1230123143, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000006, 6, 23343, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000007, 7, 123143, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000008, 8, 100, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000009, 9, 39343, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000010, 10, 83043, '0', 1.23);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000011, 11, 9000, '0', 2.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000012, 12, 19000, '0', 1.0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (100000013, 13, 25000, '0', 1.5);


--기업계좌
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (900000001, 0, 0, '9', 0);    
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (900000002, 0, 0, '9', 0);
INSERT INTO t_account (account_no, account_owner_no, account_total_amount, account_type, account_rate)
    VALUES (900000003, 0, 0, '9', 0);

-- 친구 관계 목록 테이블
INSERT INTO t_friends (my_no, friends_no) VALUES (1,2);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,3);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,4);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,6);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,7);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,8);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,9);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,10);

INSERT INTO t_friends (my_no, friends_no) VALUES (1,11);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,12);
INSERT INTO t_friends (my_no, friends_no) VALUES (1,13);

INSERT INTO t_friends (my_no, friends_no) VALUES (2,3);
INSERT INTO t_friends (my_no, friends_no) VALUES (2,4);
INSERT INTO t_friends (my_no, friends_no) VALUES (2,7);
INSERT INTO t_friends (my_no, friends_no) VALUES (2,8);
INSERT INTO t_friends (my_no, friends_no) VALUES (3,5);
INSERT INTO t_friends (my_no, friends_no) VALUES (3,9);
INSERT INTO t_friends (my_no, friends_no) VALUES (3,11);
INSERT INTO t_friends (my_no, friends_no) VALUES (3,12);
INSERT INTO t_friends (my_no, friends_no) VALUES (5,4);
INSERT INTO t_friends (my_no, friends_no) VALUES (6,13);
INSERT INTO t_friends (my_no, friends_no) VALUES (6,11);
INSERT INTO t_friends (my_no, friends_no) VALUES (6,9);
INSERT INTO t_friends (my_no, friends_no) VALUES (7,4);
INSERT INTO t_friends (my_no, friends_no) VALUES (10,4);





-- 모임 구성원 테이블
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 1, 100000001);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 2, 100000002);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 3, 100000003);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000002, 2, 100000002);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000002, 4, 100000004);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000002, 5, 100000005);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000002, 6, 100000006);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 5, 100000005);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 6, 100000006);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 7, 100000007);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 8, 100000008);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 9, 100000009);
INSERT INTO t_moim_user (moim_no, member_no, member_account) VALUES (500000001, 10,100000010);
    
-- 더미 출금 내역
INSERT INTO t_trc_history (trc_no, trc_amount, trc_send_account, trc_receive_account, trc_status, trc_sender_name, trc_title)
    VALUES (1, 100000, 500000001, 900000001, 'C', '더미','일송정 회식');
INSERT INTO t_trc_history (trc_no, trc_amount, trc_send_account, trc_receive_account, trc_status, trc_sender_name, trc_title)
    VALUES (2, 50000, 500000001, 900000002, 'C', '더미','엔제리너스');
INSERT INTO t_trc_history (trc_no, trc_amount, trc_send_account, trc_receive_account, trc_status, trc_sender_name, trc_title)
    VALUES (3, 150000, 500000002, 900000002, 'C', '더미','교대이층집');
INSERT INTO t_trc_history (trc_no, trc_amount, trc_send_account, trc_receive_account, trc_status, trc_sender_name, trc_title)
    VALUES (4, 95000, 500000002, 900000002, 'C', '더미','미엔');

COMMIT;
--모임 계좌 정보 테이블
--INSERT INTO t_moim_account (moim_account_no, moim_account_name)
--    VALUES (500000001, 'TI 모임통장');
--INSERT INTO t_moim_account (moim_account_no, moim_account_name)
--    VALUES (500000002, '제주도 여행가즈아');

---- 결제 그룹 정보 입력
--INSERT INTO t_paygroup(p_group_no, p_group_name, p_group_commission) 
--    VALUES (1, '하나은행', 0);
--INSERT INTO t_paygroup(p_group_no, p_group_name, p_group_commission) 
--    VALUES (2, '국민은행', 100);
--INSERT INTO t_paygroup(p_group_no, p_group_name, p_group_commission)
--    VALUES (3, '기업계좌', 0);
    
COMMIT;

