<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../resources/cssjs/head.jsp" %>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
	<div class="container">
		<h1>글보기</h1>
		<div class="row" style="height:400px;">
			<form action="insertproc.ws">
				<table class="table">
					<tr>
						<th>제목</th>
						<td><input class="form-control" type="text" name="title"/></td>
					</tr>
					<tr>
						<th colspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="2"><textarea class="form-control" name="content"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<input class="btn-primary" type="submit" value="저장"/>
							<input class="btn-primary" type="reset" value="취소"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="row">
			<button id="insert" class="btn-primary">글목록</button>
		</div>
	</div>
</body>
</html>













