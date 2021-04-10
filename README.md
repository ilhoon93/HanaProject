# HanaMoim

**프로젝트설명** : 유연하고 간편한 회비 관리, 모임원들 간 실시간 통신이 가능한 모임계좌 플랫폼

**프로젝트 기간** : 2019-08 (약 1개월)

**프로젝트목적** : 개인프로젝트 (융기원 교육 마지막)

**개발환경**

- **FrontEnd** : JavaScript, jsp, html/css

- **Backend** : Java, Spring, oracle, mybais

  

### 시스템 구조
- **시스템 구조도**

  <img src="https://github.com/ilhoon93/imageHub/blob/master/img/systemArchitecture.JPG?raw=true" width="800px"/>



- **DB구조**

  <img src="https://github.com/ilhoon93/imageHub/blob/master/img/%ED%85%8C%EC%9D%B4%EB%B8%94%EC%A0%95%EB%B3%B4.JPG?raw=true" width="800px"/>

- **ERD**

<img src="https://github.com/ilhoon93/imageHub/blob/master/img/ERD.JPG?raw=true" width="750px" />




### 주요 기능

1. 친구 초대 기능

```
- 웹소켓 통신으로 접속해 있는 친구에게 실시간 초대 메시지 전송
```

2. 회비 걷기 기능

```
- 모임장이 모임원 중 특정 인원, 특정 금액에 대해서 입금 요청
- 기한 날짜 설정
- 메시지 받은 모임원이 출금 승인 후 바로 연결계좌에서 출금 & 입금
```

3. 모임원 실시간 채팅 기능

```
- 입장, 퇴장 이름 알림
- 실시간 채팅
```

4. 친구 초대 기능

```
- 개인계좌, 참여계좌, 관리중인 계좌 한번에 모아보기
```

5. 입출금 내역 확인, 영수증 첨부 및 확인

```
- 모임계좌의 입출금 내역 실시간 업데이트
- 출금시 영수증 첨부 및 공유
```



### 보완할 점

수정 사항

```
- 그룹간 채팅방이 겹치는 현상 수정
- 마이너스 출금 처리
- 미접속시 초대 메시지 사라지는 현상 수정
```



추가 개발 사항

```
- 미납 회원 확인 및 독촉 기능
- 그룹 일정 관리 및 입출금 통계 시각화
- 시스템 관리자 페이지
- 회원가입
```
