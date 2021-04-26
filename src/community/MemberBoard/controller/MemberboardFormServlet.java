package community.MemberBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BoardFileRenamePolicy;
import community.MemberBoard.model.service.MemberboardService;
import community.MemberBoard.model.vo.Challenge;
import community.MemberBoard.model.vo.Memberboard;

@WebServlet("/community/MemberboardForm")
public class MemberboardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberboardService memberBoardService = new MemberboardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Memberboard> list = memberBoardService.selectChallengeList();
		request.setAttribute("list", list);
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("/WEB-INF/views/community/MemberboardForm.jsp");
		reqDispatcher.forward(request, response);
	}
}
