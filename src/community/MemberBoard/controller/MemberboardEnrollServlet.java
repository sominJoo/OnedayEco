package community.MemberBoard.controller;

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
import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;
import community.MemberBoard.model.vo.MemberboardAttachment;

@WebServlet("/community/MemberboardEnroll")
public class MemberboardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberboardService memberboardService = new MemberboardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		int challengeId = Integer.parseInt(multipartRequest.getParameter("challenge_id"));
		String title = memberboardService.selectChallengeTitle(challengeId);
		String memberId = multipartRequest.getParameter("writer");
		int sTeamCount = "".equals(multipartRequest.getParameter("s_team_count")) ? 10 : Integer.parseInt(multipartRequest.getParameter("s_team_count"));
		
		String aContent = multipartRequest.getParameter("content");	
		
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile"); 
		
		Memberboard memberboard = new Memberboard();
		memberboard.setChallengeId(challengeId);
		memberboard.setaTitle(title);
		memberboard.setMemberId(memberId);
		memberboard.setaContent(aContent);
		memberboard.setsTeamCount(sTeamCount);
		
		if(originalFilename != null) {
			MemberboardAttachment memberboardAttachment = new MemberboardAttachment();
			memberboardAttachment.setOriginalFilename(originalFilename);
			memberboardAttachment.setRenamedFilename(renamedFilename);
			memberboard.setMemberboardAttachment(memberboardAttachment);
		}
		
		memberboardService.insertMemberboard(memberboard);
		String location = request.getContextPath();
		location += "/Community/MemberboardView?no=" + memberboard.getaId();

		HttpSession session = request.getSession();
		session.setAttribute("msg", "팀원찾기 등록완료");
		response.sendRedirect(location);
		
	} catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
}
}
