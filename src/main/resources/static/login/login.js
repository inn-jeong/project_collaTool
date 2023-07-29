
    //페이지 로딩 후 파라미터 값에 따라 알림창 출력


///////////////////////////////로그아웃하기 위한 과정//////////////////////////////////////
var testPopUp;
//팝업 창을 만들어 거기에 네이버 계정을 로그아웃하게 하는 url을 입력함
function openPopUp() {
    //네이버 사이트에서 로그아웃하는 url
    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
    //testPopUp= window.open("https://accounts.kakao.com/logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}
function closePopUp(){
    testPopUp.close();
}

//위 기능들을 사용해 네이버 계정을 로그아웃하는 기능 구현
function naverLogout() {
    openPopUp();
    setTimeout(function() {
        closePopUp();
    }, 100);
    // closePopUp();
}

//비밀번호를 조회하기 위한 팝업창
function findPassword(){
    window.open("/loginT/findPassword_page","pop","width=570,height=420, scrollbars=no, resizable=no");
}

//아이디를 조회하기 위한 팝업창
function  findId(){
    window.open("/loginT/findId_page","pop","width=570,height=420, scrollbars=no, resizable=no");
}
