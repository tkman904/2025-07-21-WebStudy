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
p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row text-center">
      <a href="../music/type.do?cno=1" class="btn btn-sm btn-success">종합차트</a>
      <a href="../music/type.do?cno=2" class="btn btn-sm btn-primary">가요</a>
      <a href="../music/type.do?cno=3" class="btn btn-sm btn-success">POP</a>
      <a href="../music/type.do?cno=4" class="btn btn-sm btn-primary">OST</a>
      <a href="../music/type.do?cno=5" class="btn btn-sm btn-success">트롯</a>
      <a href="../music/type.do?cno=6" class="btn btn-sm btn-primary">JAZZ</a>
      <a href="../music/type.do?cno=7" class="btn btn-sm btn-success">CLASIC</a>
    </div>
    <div style="height: 20px;">
      <div class="row">
      <c:forEach var="vo" items="${list }">
        <div class="col-md-3">
          <div class="thumbnail">
            <a href="../music/detail.do?no=${vo.no }&page=${curpage }">
              <img src="${vo.poster }" style="width:230px; height: 150px;">
              <div class="caption">
                <p>${vo.title }</p>
              </div>
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
    <div class="row text-center" style="margin-top: 10px;">
      <ul class="pagination">
        <c:if test="${startPage>1 }">
          <li><a href="../music/type.do?page=${startPage-1 }&type=${type}">&lt;</a></li>
        </c:if>
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
          <li ${i==curpage?"class=active":"" }><a href="../music/type.do?page=${i }&type=${type}">${i }</a></li>
        </c:forEach>
        <c:if test="${endPage<totalpage }">
          <li><a href="../music/type.do?page=${endPage+1 }&type=${type}">&gt;</a></li>
        </c:if>
      </ul>
    </div>
    </div>
  </div>
</body>
</html>