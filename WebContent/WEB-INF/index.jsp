<%@page import="com.mh.org.freeboard.FreeBoardDAO"%>
<%@page import="com.mh.org.freeboard.FreeBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "../resources/cssjs/head.jsp" %>
<style type="text/css">
	.dto:hover{
		background-color: gray;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#insert").on("click",function(){
		window.location="insert.ws";
	});
	$("#delete").on("click",function(){
		$('#frm').submit();
	});
	$(".dto").on("click",function(){
// 		alert($(this).find("td").eq(0).text());
		var idx = $ (this).find("input").val();
		window.location="update.ws?idx="+idx;
	});
	$(".td_ck").on("click",function(e){
		//e.preventDefault(); checkbox 는 기본 이벤트 on
		e.stopPropagation(); //부모태그에 이벤트는 off
		var input = $(this).find("input").attr("checked",true);
	});
})
</script>
</head>
<body>
	<div class="container">
		<div class="row" style="height: 430px">
			<form action="delete.ws" id="frm">
				<table class="table">
					<tr>
						<th></th>
						<th>순번</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성일자</th>
						<th>수정일자</th>
					</tr>

					<c:forEach items="${list}" var="dto">
						<tr class="dto">
							<td class="td_ck"><input type="checkbox" class="checkbox" name="idx" value="${dto.idx}" /></td>
							<td>${dto.idx}</td>
							<td>${dto.title}</td>
							<td>${dto.content}</td>
							<td>${dto.reg_date}</td>
							<td>${dto.mod_date}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
		<div class="row text-center">
			<c:forEach begin="1" end="${pagecount}" var="i">
				<a href="index.ws?page=${i}">[ ${i} ]</a>
			</c:forEach>
<!-- 			<a href="index.ws?page=1">[ 1 ]</a> -->
<!-- 			<a href="index.ws?page=2">[ 2 ]</a> -->
<!-- 			<a href="index.ws?page=3">[ 3 ]</a> -->
		</div>
		<div class="row">
			<button id="insert" class="btn btn-primary">글쓰기</a>
			<button id="delete" class="btn btn-primary"> 삭제</a>
		</div>
	</div>
</body>
</html>