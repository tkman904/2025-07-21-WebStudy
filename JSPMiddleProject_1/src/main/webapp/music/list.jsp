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
        <%--
        	if(startPage>1)
         --%>
        <c:if test="${startPage>1 }">
          <li><a href="../music/list.do?page=${startPage-1 }">&lt;</a></li>
        </c:if>
        <%--
        	for(int i=startPage;i<=endPage;i++)
         --%>
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
          <li ${i==curpage?"class=active":"" }><a href="../music/list.do?page=${i }">${i }</a></li>
        </c:forEach>
        <%--
        	if(endPage<totalpage)
         --%>
        <c:if test="${endPage<totalpage }">
          <li><a href="../music/list.do?page=${endPage+1 }">&gt;</a></li>
        </c:if>
      </ul>
    </div>
  </div>
</body>
</html>