<%@ page import="me.kobeshow.servlet.domain.member.MemberRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="me.kobeshow.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: junuihong
  Date: 2021/07/23
  Time: 6:40 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <%
    for (Member member : members) {
      out.write("    <tr>");
      out.write("       <td>" + member.getId() + "</td>");
      out.write("       <td>" + member.getUsername() + "</td>");
      out.write("       <td>" + member.getAge() + "</td>");
      out.write("    </tr>");
    }
  %>
  </tbody>
</table>
</body>
</html>