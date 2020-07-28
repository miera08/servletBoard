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
</head>
<body>
<!-- <h1>java 모드</h1> -->
<%-- <% --%>
//  	List<String> list = (List)request.getAttribute("list");
//  	for(String temp : list){
//  		out.println("temp = "+ temp);
//  	}
<%-- %> --%>
<!-- <h1>jstl 모드</h1> -->
<%-- <c:forEach items = "${list}" var="temp"> --%>
<%-- 	temp = <c:out value="${temp }"/> --%>
<%-- 	temp = ${temp } --%>
<%-- </c:forEach> --%>
<%-- ${a} --%>
<%-- ${param.a} --%>
<%-- <% --%>
// 	String parama = request.getParameter("a");
// 	String attributea=(String) request.getAttribute("a");
// 	out.println(parama);
// 	out.println(attributea);
<%-- %> --%>
</body>
</html>