package community.MemberBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MemberboardUtils;
import community.MemberBoard.model.exception.MemberboardException;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Memberboard;

@WebServlet("/community/detailedMemberBoardView")
public class DetailedMemberBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberboardService memberboardService = new MemberboardService();		

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 : no
		try {
		int no  = 0; 
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(NumberFormatException e) {
			throw new MemberboardException("유효한 게시글 번호가 아닙니다.", e);
		}
		//2. 업무로직 : board객체 조회(첨부파일 조회)
		memberboardService.updateAReadCount(no);
		Memberboard memberboard = memberboardService.selectOneMemberboard(no);
		if(memberboard == null) {
			throw new MemberboardException("해당 게시글이 존재하지 않습니다.");
		}
		// xss공격 방지
		// 사용자가 입력할 수 있는 부분에는 다 처리해줘야 함
		memberboard.setaId(memberboard.getaId());
		memberboard.setMemberId(MemberboardUtils.escapeHtml(memberboard.getMemberId()));
		memberboard.setChallengeId(memberboard.getChallengeId());
		memberboard.setaTitle(MemberboardUtils.escapeHtml(memberboard.getaTitle()));
		memberboard.setaContent(MemberboardUtils.convertLineFeedtoBr(memberboard.getaContent()));
		memberboard.setaRegDate(memberboard.getaRegDate());
		memberboard.setaReadCount(memberboard.getaReadCount());
		memberboard.setsTeamCount(memberboard.getsTeamCount());
		
		
		//3. jsp forwarding
		request.setAttribute("memberboard", memberboard);
		request.getRequestDispatcher("/WEB-INF/views/community/detailedMemberBoardView.jsp")
			   .forward(request, response);
		} catch(Exception e) {
			// 로깅
			e.printStackTrace();
			// 관리자 이메일 알림
			// 다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메시지 작성
			throw e;
		}
	}

}
