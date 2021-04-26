<%@page import="member.model.vo.Member"%>
<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	Member loginMember2 =  (Member)session.getAttribute("loginMember");
	System.out.println("session LoginMember = "+ loginMember2);
	%>    

 <div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left"><a href="<%=request.getContextPath()%>/community/community"><span class="title">환경정보</span></a></li>
                <li><a href="<%=request.getContextPath()%>/community/communityBoardList"> <span class="title">커뮤니티</span></a></li>
                <li><a href="Memberboard.html"> <span class="title">팀원찾기</span></a></li>
                <li><a href="TeamMemberBoard.html"> <span class="title">팀게시판</span></a></li>
                <li class="Container-bar-li-Right"><a href="<%=request.getContextPath() %>/community/confirmList"><span>인증게시판</span></a></li>
            </ul>
        </div>