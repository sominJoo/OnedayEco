package onedayeco.confirm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;

/**
 * Servlet implementation class ConfirmTeamListServlete
 */
@WebServlet("/community/confirmTeamList")
public class ConfirmTeamListServlete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//0. 인코딩처리는 EncodingFilter가 선처리 
				//1. 사용자 입력값
				final int numPerPage = 10;
				int cPage = 1;
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));
				}catch(NumberFormatException e) {}
				
				//2. 업무로직
				//a. contents영역 : start ~ end
				int end = cPage * numPerPage;
				int start = (cPage -1)*numPerPage + 1;
				List<Confirm> list = confirmService.selectList(start, end);
				System.out.println("list@servlet = " + list);
				
				
				//b. pageBar영역 
				String type = request.getParameter("type");
				int totalContents = confirmService.selectDTTBoardCount(type);
				System.out.println("totalContents@servlet = " + totalContents);

				String url = request.getRequestURI();
				String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
				
				
				//3. 응답 html처리 jsp에 위임.
				request.setAttribute("list", list);
				request.setAttribute("pageBar", pageBar);
				request.getRequestDispatcher("/WEB-INF/views/community/confirmTeamList.jsp")
					   .forward(request, response);
				
			}catch(Exception e) {
				//로깅
				e.printStackTrace();
				//다시 예외를 던져서 WAS가 정한 에러페이지에서 응답메세지를 작성
				throw e;
			}
				
			}

       


}
