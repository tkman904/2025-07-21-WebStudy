<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td width="30%" class="text-center" rowspan="5">
            <table class="table">
              <tr>
                <td colspan="4">
                  <img src="${vo.poster }" style="width: 420px; height: 300px;">
                </td>
              </tr>
            </table>
          </td>
          <td colspan="2">
            <h3>${vo.title }&nbsp;<span style="color: red;">${vo.idcrement} ${vo.state eq '상승'?'▲':(vo.state eq '하강'?'▼':'-')}</span></h3>
          </td>
        </tr>
        <tr>
          <td width="10%" style="color: gray;">순위</td>
          <td width="50%">${vo.rank }</td>
        </tr>
        <tr>
          <td width="10%" style="color: gray;">아티스트</td>
          <td width="50%">${vo.singer }</td>
        </tr>
        <tr>
          <td width="10%" style="color: gray;">장르</td>
          <td width="50%">
            <c:choose>
              <c:when test="${vo.cno==1 }">TOP100</c:when>
              <c:when test="${vo.cno==2 }">가요</c:when>
              <c:when test="${vo.cno==3 }">POP</c:when>
              <c:when test="${vo.cno==4 }">OST</c:when>
              <c:when test="${vo.cno==5 }">트롯</c:when>
              <c:when test="${vo.cno==6 }">JAZZ</c:when>
              <c:when test="${vo.cno==7 }">CLASIC</c:when>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td width="10%" style="color: gray;">앨범명</td>
          <td width="50%">${vo.album }</td>
        </tr>
        <table class="table">
          <tr>
            <td class="text-right">
              <a href="../music/list.do?page=${curpage }" class="btn btn-xs btn-success">목록</a>
            </td>
          </tr>
        </table>
      </table>
    </div>
  </div>
</body>
</html>