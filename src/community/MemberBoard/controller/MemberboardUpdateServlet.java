package community.MemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class MemberboardUpdateServlet
 */
@WebServlet("/community/memberboardUpdate")
public class MemberboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberboardService memberboardService = new MemberboardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		// 2. 업무로직
		Memberboard memberboard = memberboardService.selectOneMemberboard(no);
		List<Memberboard> list = memberboardService.selectChallengeList();

		// 3. jsp포워딩
		request.setAttribute("list", list);	
		request.setAttribute("memberboard", memberboard); // board객체와 첨부파일까지
		request.getRequestDispatcher("/WEB-INF/views/community/MemberboardUpdateForm.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String saveDirectory = getServletContext().getRealPath("/upload/memberboard");
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
		int aId = Integer.parseInt(multipartRequest.getParameter("a_id"));
		int challengeId = Integer.parseInt(multipartRequest.getParameter("challenge_id"));						
		System.out.println("challengeId = " + challengeId);
		String title = memberboardService.selectChallengeTitle(challengeId);
		String memberId = multipartRequest.getParameter("writer");
		int sTeamCount = "".equals(multipartRequest.getParameter("s_team_count")) ? 10 : Integer.parseInt(multipartRequest.getParameter("s_team_count"));
		String aContent = multipartRequest.getParameter("content");	
		
		System.out.println("challengeId 출력 : " + challengeId);
		
		
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile"); 
		
		String attachId = multipartRequest.getParameter("delFile");
		
		Memberboard memberboard = new Memberboard();
		memberboard.setaId(aId);
		memberboard.setChallengeId(challengeId);
		memberboard.setaTitle(title);
		memberboard.setMemberId(memberId);
		memberboard.setaContent(aContent);
		memberboard.setsTeamCount(sTeamCount);
		
		System.out.println("aId = " + aId);
		
		if(originalFilename != null) {
			MemberboardAttachment memberboardAttachment = new MemberboardAttachment();
			memberboardAttachment.setaId(aId);
			memberboardAttachment.setOriginalFilename(originalFilename);
			memberboardAttachment.setRenamedFilename(renamedFilename);
			memberboard.setMemberboardAttachment(memberboardAttachment);
		}
		
		int result = 0;
		if(attachId != null) 
			result = memberboardService.deleteMemberboardAttachment(attachId);
	
		
		result = memberboardService.updateMemberboard(memberboard);
		String msg = (result > 0) ? "게시글을 수정하였습니다." : "게시글 수정에 실패했습니다.";
		String location = request.getContextPath();
		location += "/Community/MemberboardView?no=" + memberboard.getaId();
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
		
	} catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
	}
}
