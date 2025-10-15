<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. 동작
      test.jsp ======> test_jsp.java
                            |
                       test_jsp.class
                            |
                       메모리에 HTML 적재 => 브라우저
 	2. 지시자
      page : JSP에 대한 정보
       |
      속성
       = contentType : 변환 (브라우저에서 출력 형식)
         text/html
         text/xml
         text/plain
       = import : 라이브러리 읽기
       = errorPage : 에러시 이동하는 파일 지정
      include : JSP안에 다른 JSP를 읽어올때 사용
         |
        속성
         = file => 고정 => <jsp:include>
                        ----
      taglib : <% %> => 삭제
                 | 제어문, 메소드, 화면이동
                 | MVC / Spring / Spring-Boot
      <% %> => 가독성이 떨어진다 : 요지보수가 편리
      JSP : Model 1 => 개인 사이트 (간단한 사이트 => 요지보수가 없는)
            => JAVA + HTML
      MVC : Model 2 => 대규모 사이트 (JAVA/HTML분리)
      -------------------------- Front / Back
	3. 내장객체
	   JSP가 서블릿으로 변환될때 자동으로 생성되는 객체
	   => **HttpServletRequest request,
	      **HttpServletResponse response,
	      **PageContext pageContext;
	      **HttpSession session = null;
	      **ServletContext application;
	      ServletConfig config;
	      JspWriter out = null;
	      Object page = this;
	      Throwable exception
	   1) request 객체
	      HttpServletRequest
	      => 클라이언트의 정보 : IP / PORT
	      => 서버 정보 : 서버 IP / 프로토콜
	      => 클라이언트의 요청 정보 (form data)
	      => 데이터 추가
	      *** 메소드
	      <input type=text name=id> => ?id=""
	      ?id=admin&pwd=1234
	      사용자 요청 정보
	      String getParameter(String name)
	             : 요청 데이터를 받는 경우(단일값)
	      String[] getParameterValues(String name)
	             : 여러개를 동시에 받는 경우
	             : checkbox
	      getParameterNames() : **** Spring
	      getSession() : 세션 생성
	      getCookie() : 쿠키 읽기
	      -------------------------------
	      서버 정보
	      getMethod() : GET / POST 확인
	      getRequestURI() : 사용자 보낸 파일 위치
	      http://localhost/JSPBasicProject_4/jsp/basic_1.jsp
	      			       ================================= URI
	      ================================================== URL
	      getRequestURL() : 사이트 전체 주소
	                      ==============
	                      : ContextPath
	      getContextPath(): /JSPBasicProject_4
	      getServerInfo() : 서버 IP
	      getProtocol() : http
	      getPort() : 80
	      
	      클라이언트 정보
	      getRemoteAddr() : 클라이언트의 IP => log
	      => 조회수
	      setCharacterEncoding("UTF-8") => Decoding
	      -------------------------------
	   2) response 객체
	      => 응답 정보
	      => 헤더 정보 : 다운로드
	      => 서버에서 화면 이동 : redirect
	      
	4. 액션태그
	5. 자바빈
	6. Session VS Cookie
	7. JSTL / EL
	8. MVC => SpringFramework
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Request 예제</h1>
  <form method="get" action="aaa.jsp">
    <input type="text" name="id"><br>
    <input type="text" name="name">
    <button>전송</button>
  </form>
</body>
</html>