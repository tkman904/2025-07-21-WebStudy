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
      <h1>🎵음악 검색</h1>
      <form method="post" action="../music/find.do">
        <select name="column" class="input-sm">
          <option value="title">노래명</option>
          <option value="singer">가수명</option>
        </select>
        <input type="text" size="30" class="input-sm" name="ss">
        <input type="submit" value="검색" class="btn-sm btn-primary">
      </form>
    </div>
    <div class="row" style="margin-top: 20px;">
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
  </div>
</body>
</html>