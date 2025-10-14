<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
					1) HTML => contentType="text/html"
					-----------------------------------
					   => 일반 JSP 번역
					2) JSON => contentType="text/plain"
					-----------------------------------
					   => Ajax / Vue / React
					3) XML => contentType="text/xml"
		= JSP 동작
		  test.jsp ==== text_jsp.java : 1회성
		     				| 컴파일(javac)
		     			test_jsp.class
		     			    | 실행(java)
		     			메모리에 out.write("<html>")
		     			      --------------------
		     			       HTML만 누적
		     			       ---------
		     			        | 브라우저에 읽기 => 화면에 출력
		= 실제 클래스 변환
		  public class test_jsp extends HttpJspBase {
		  
		  	  -------------------------------
		  	   멤버변수 / 메소드 등록
		  	   <%!
		  	   		int a = 100;
		  	   		public void add() {
		  	   		
		  	   		}
		  	   %>
		  	  -------------------------------
		  	   메소드
		  	    public void _jspInit() {}
		  	    public void _jspDestroy() {}
		  	    public void _jspService() 
		  	     => JSP : _jspService에 들어가는 코딩
		  	              ----------- 요청에 대한 처리
		  	        jsp=> 메소드에 추가되는 기능
		  	          | 메소드 영역 (_jspService())
		  	    {
		  	    	<%
		  	    		int b = 20;
		  	    	%>
		  	    	String html="
		  	    		<!DOCTYPE html>
						<html>
						<head>
						<meta charset="UTF-8">
						<title>Insert title here</title>
						</head>
						<body>
						</body>
						</html>
					"
					
					out.write(html);
		  	    }
		  	  -------------------------------
		  }
		  *** jsp는 서버를 거쳐서 동작 => 초기화가 된다
		      --------------------------------
		      | 이전의 파일은 종료 => 새로운 파일을 만든다
		        메모리 적재 = 메모리 해제
		        => 보완 (자바스크립트) => web2.0
		        => web3.0 : 보안 (개인 컨텐츠)
		                     | 블록체인
		      | jsp => 가독성이 많이 떨어진다
		               Front-End 연결하기 어렵다
		               .jsp => .html (ThymeLeaf)
		               <tr th:each()>VueJS
		               <tr v-for="">
		  ----------------------------------------
		  Servlet / JSP
		  -------
		  1) 서버에서 실행되는 Java파일 => .java
		  2) 생명주기 : init() = service() = destroy()
		               |         |
		              생성자       |
		             		 doGet/doPost
		             		 => @RequestMapping()
		             		 => @GetMapping()
		             		 => @PostMapping()
		  3) 보안 / 자바, HTML을 연결할때 주로 사용
		     JSP :  화면 UI
	-----------------------------------------------
	3장)
	    JSP의 구조
	    Java Resource
	     |
	     src/main/java => java파일 : 패키지단위
	     
	    webapp => 폴더 : 웹파일
	      | WEB-INF
	      |  |lib => 라이브러리 (.jar)
	      |  |config => xml파일, properties
	      | jsp => web파일
	       
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>