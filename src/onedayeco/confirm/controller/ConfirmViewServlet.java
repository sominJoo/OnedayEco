package onedayeco.confirm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import mypage.model.service.MypageService;
import mypage.model.vo.MypageBadge;
import mypage.model.vo.MypageChallenge;
import onedayeco.article.model.exception.ArticleException;
import onedayeco.article.model.vo.Article;
import onedayeco.article.model.vo.ArticleComment;
import onedayeco.confirm.model.exception.ConfirmException;
import onedayeco.confirm.model.service.ConfirmService;
import onedayeco.confirm.model.vo.Confirm;
import onedayeco.confirm.model.vo.ConfirmComment;

/**
 * Servlet implementation class ConfirmViewServlet
 */
@WebServlet("/community/confirmView")
public class ConfirmViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfirmService confirmService = new ConfirmService();
	 
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try{//1. 사용자 입력값:article_no 
				int article_id = 0;
				try {
					article_id = Integer.parseInt(request.getParameter("no"));
					System.out.println(article_id);
				}catch(NumberFormatException e) {
					throw new ConfirmException("유효한 게시글 번호가 아닙니다.",e);
				}
				System.out.println("ConfirmViewServlet@article_no = "+article_id);
			
				//2.업무로직 : article객체 조회(첨부파일 조회)
				Confirm confirm= confirmService.selectOne(article_id); 
				if(confirm == null) {
					throw new ConfirmException("해당 게시글이 존재하지 않습니다.");
				}
				//xss공격방지
				confirm.setA_title(MvcUtils.escapeHtml(confirm.getA_title()));
				confirm.setA_content(MvcUtils.escapeHtml(confirm.getA_content()));
				
				//\n개행문자를 <br/>태그로 변경
				confirm.setA_content(MvcUtils.convertLindeFeedToBr(confirm.getA_content()));
				
				//진행중인 challenge 가져오는 업무
				List<MypageChallenge> mChallengeList =  new MypageService().selectChallenge(confirm.getUser_id());
				for(MypageChallenge m : mChallengeList)
					System.out.println("mChallengeList : "+m);
				
				
				//댓글 가져오기
				List<ConfirmComment> commentList =
						confirmService.selectBoardCommentList(article_id);
				System.out.println("commentList@servlet ="+commentList);
				
				//3. jsp forwarding
				request.setAttribute("confirm", confirm);
				request.setAttribute("commentList",commentList);
				request.setAttribute("mChallengeList",mChallengeList);
				request.getRequestDispatcher("/WEB-INF/views/community/confirmView.jsp")
						.forward(request,response);
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}


	}
