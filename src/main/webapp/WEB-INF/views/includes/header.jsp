<%@page import="himedia.myhome.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page session="true" %>
<%
UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
      <div id="header">
      <h1>My Homepage</h1>
      <%
      if (authUser != null) {	// 로그인 함
    	  
    	  
      
      %>
      <ul> 
       <li><a href="<%= request.getContextPath() %>/users?a=logout" >로그아웃</a></li>
      <li><%= authUser.getName() %>님 환영합니다.</li>
     
      <!--  로그인 한 사용자 -->
      <!--  웰컴 메세지, 로그아웃 링크 -->
      </ul>
      
      <%
      } else {
      %>
      <ul>
      <!--  로그인 안한 사용자 -->
      <!--  가입 링크, 로그인 폼 링크 -->
      	<li><a href="<%= request.getContextPath() %>/users?a=joinform">회원가입</a></li>
     	<li><a href="<%= request.getContextPath() %>/users?a=loginform">로그인</a></li>
      </ul>
      <%
      }
      %>
      </div> <!-- /header -->
      <!--  
      <h3>Params</h3>
      <ul>
      	<li>param1 : <--%= request.getParameter("param1") %> </li>
      	<li>param2 : <--%= request.getParameter("param2") %></li>
      </ul-->
 	  
 	  
 	  