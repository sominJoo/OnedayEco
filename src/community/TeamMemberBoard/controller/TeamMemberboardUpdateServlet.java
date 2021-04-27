package community.TeamMemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.BoardFileRenamePolicy;
import community.TeamMemberBoard.model.service.TeamMemberboardService;
import community.TeamMemberBoard.model.vo.ATeamAttachment;
import community.TeamMemberBoard.model.vo.TeamMemberboard;

/**
 * Servlet implementation class TeamMemberboardUpdateServlet
 */
@WebServlet("/community/teamMemberboardUpdate")
public class TeamMemberboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamMemberboardService teamMemberboardService = new TeamMemberboardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		// 2. 업무로직
		TeamMemberboard teamMemberboard = teamMemberboardService.selectOneTeamMemberboard(no);

		// 3. jsp포워딩
		request.setAttribute("teamMemberboard", teamMemberboard); // board객체와 첨부파일까지
		request.getRequestDispatcher("/WEB-INF/views/community/teamMemberboardUpdateForm.jsp")
				.forward(request, response);
	}

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
		int teamAId = Integer.parseInt(multipartRequest.getParameter("a_team_id"));		
		String title;
		if("".equals(multipartRequest.getParameter("title")) == false) {
			title = multipartRequest.getParameter("title");
		} else {
			title = multipartRequest.getParameter("title-hidden");
		}
		String memberId = multipartRequest.getParameter("writer");
		String aContent;
		if("".equals(multipartRequest.getParameter("content")) == false) {
			aContent = multipartRequest.getParameter("content");
		} else {
			aContent = multipartRequest.getParameter("content-hidden");
		}	
		
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile"); 
		String attachId = multipartRequest.getParameter("delFile");

		TeamMemberboard teamMemberboard = new TeamMemberboard();
		teamMemberboard.setTeamAId(teamAId);
		teamMemberboard.setMemberId(memberId);
		teamMemberboard.setaTitle(title);
		teamMemberboard.setaContent(aContent);
		
		if(originalFilename != null) {
		ATeamAttachment aTeamAttachment = new ATeamAttachment();
		aTeamAttachment.setTeamAId(teamAId);
		aTeamAttachment.setOriginalFilename(originalFilename);
		aTeamAttachment.setRenamedFilename(renamedFilename);
		teamMemberboard.setaTeamAttachment(aTeamAttachment);
		}
		int result;
		if(attachId != null)
			result = teamMemberboardService.deleteTeamMemberboardAttachment(attachId);
		
		result = teamMemberboardService.updateTeamMemberboard(teamMemberboard);
		String msg = (result > 0) ? "게시글을 수정하였습니다." : "게시글 수정에 실패했습니다.";
		String location = request.getContextPath();
		location += "/community/detailedTeamMemberboardView?no=" + teamMemberboard.getTeamAId();
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
		
	} catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
	}
}
