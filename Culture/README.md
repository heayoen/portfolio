# 콘솔 프로젝트 : 문화생활 기록 관리   
  
  
  
  
## 1. 제작 기간 및 참여 인원
* 2022.03~04 (팀 프로젝트)
* 인원 : 5인
  
  
  
## 2. 개발 툴 및 언어
* Eclipse, Java11 
  
  
    
## 3. 담당업무 순서도 설계 / 데이터 관계도
1. 순서도
<img src=".\.\문서\03.순서도\erd.png" width="50%">  

2. 데이터관계도
<img src=".\.\문서\06.데이터\4조_데이터관계도.png" width="50%">
  
## 4. 핵심 기능
1. 로그인/ 계정 찾기 / 회원 가입
  
2. 작품 관람평 작성 / 카테고리별 조회 
  
3. 팔로우한 유저와의 DM / 상대방의 캘린더, 리뷰목록 열람
  
4. 모임 가입 후 개시물 작성 및 댓글달기
  
5. 관리자 : 회원 블랙리스트 추가, 삭제 / 회원정보 관리

  
  
  
## 5. 담당 업무 구현 화면
1. 이용자 개인 정보 수정 및 회원 탈퇴
  
2. 감상평 입력/ 삭제
  
3. 캘린더 연동

  
    
  


## 6. 회고
> * 파일입출력 기반으로 진행한 프로젝트였기 때문에 어떤 식으로 데이터 폴더를 구성할 것인지에 대해 팀원들과 많은 논의를 했던 것 같다. 
마이페이지/userid/info.txt의 정보와 user.txt의 정보를 동일하게 아이디/비밀번호/생년월일/성별/전화번호/공개여부/선호장르/본인확인정보/모임명(선택) 으로 구성하였으나, user.txt 파일의 경우 로그인, 회원탈퇴, 모임 기능에서만 사용한다는 점을 고려하였을 때, 위 3가지의 정보만 입력받도록 하는 것이 개인정보 수정 시 2개의 파일을 동시에 수정하지 않아도 되어서 좋았을 것 같다.  
> 
> * 사전 문서 작성에 시간을 적게 사용했어서 공통클래스, 공통 코드 등에 관한 논의가 부족했었고
이로 인해 후반 작업 통합 시 시간이 지체되었으며, Calendar의 날짜를 지정할 때 오류가 발생하기도 했다.  요구사항 작성 시 기능 구현에 관한 논의와 공통 코드에 관한 논의의 중요성 깨닫는 계기가 되었다.  
> 
> * 개인적으로는 스스로의 코드 관리 방식과 가독성에 대해 많은 고민을 하는 계기가 되었고, 이후 개인적으로 출력 메소드와 업무 메소드를 분리하여 다시 정리하는 작업을 진행하였다.   

