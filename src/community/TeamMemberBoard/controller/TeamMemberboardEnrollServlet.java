package community.TeamMemberBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.BoardFileRenamePolicy;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;
import community.TeamMemberBoard.model.service.TeamMemberboardService;
import community.TeamMemberBoard.model.vo.ATeamAttachment;
import community.TeamMemberBoard.model.vo.TeamMemberboard;

@WebServlet("/community/teamMemberboardEnroll")
public class TeamMemberboardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
	String saveDirectory = getServletContext().getRealPath("/upload/teammemberboard");
	int maxPostSize = 10 * 1024 * 1024;
	String encoding = "utf-8";
	
	FileRenamePolicy policy = new BoardFileRenamePolicy();
	
	MultipartRequest multipartRequest =
			new MultipartRequest(
							request, 
							saveDirectory,
							maxPostSize, 
							encoding, 
							policy 
							);
	String title = multipartRequest.getParameter("title");
	String memberId = multipartRequest.getParameter("writer");
	String aContent = multipartRequest.getParameter("content");
	int aId = teamMemberboardService.selectAId(memberId);
	
	String originalFilename = multipartRequest.getOriginalFileName("upFile");
	String renamedFilename = multipartRequest.getFilesystemName("upFile"); 
	
	TeamMemberboard teamMemberboard = new TeamMemberboard();
	teamMemberboard.setaTitle(title);
	teamMemberboard.setMemberId(memberId);
	teamMemberboard.setaContent(aContent);
	teamMemberboard.setaId(aId);
	
	if(originalFilename != null) {
		ATeamAttachment aTeamAttachment = new ATeamAttachment();
		aTeamAttachment.setOriginalFilename(originalFilename);
		aTeamAttachment.setRenamedFilename(renamedFilename);
		teamMemberboard.setaTeamAttachment(aTeamAttachment);
	}
	
	teamMemberboardService.insertTeamMemberboard(teamMemberboard);
	String location = request.getContextPath();
	location += "/community/detailedTeamMemberboardView?no=" + teamMemberboard.getTeamAId();
	System.out.println("teamMember_TEAMAID = " + teamMemberboard.getTeamAId());

	HttpSession session = request.getSession();
	session.setAttribute("msg", "팀게시판 게시글 등록완료");
	response.sendRedirect(location);
	
} catch(Exception e) {
	e.printStackTrace();
	throw e;
}}
}
