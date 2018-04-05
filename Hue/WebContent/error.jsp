<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
  <c:when test="${requestScope.message!=null}">
  	<h4><c:out value="${message}"/></h4>
  	<h3><a href="index.jsp">뻘짓말고 돌아가자^^</a></h3>
  </c:when>
  <c:otherwise>
	<h4>지못미..</h4>
	<h3><a href="index.jsp">너도 그만 돌아가자..</a></h3>  
  </c:otherwise>
</c:choose>