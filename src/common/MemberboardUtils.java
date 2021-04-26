package common;

public class MemberboardUtils {
	// xss공격방지 처리
	public static String escapeHtml(String str) {
		// 시작 꺽쇠, 닫는 꺽쇠만 처리해줘도 태그로서 작동할 수 없게됨
		return str.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public static String convertLineFeedtoBr(String content) {
		return content.replaceAll("\\n", "<br/>")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public static String getPageBar(int cPage, int numPerPage, int totalContents, String url) {
		// 리터럴이 계속해서 변경될 경우, StringBuilder사용하기
		// StringBuilder는 값이 바뀌면 실제 문자열을 수정할 수 있음
		// String의 경우는 실제 문자열을 변경하는게 아니라, 값이 바뀔때마다 문자열을 생성해내야 해서 메모리 낭비
		StringBuilder pageBar = new StringBuilder();
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage); // 나머지까지 페이지 처리 -> 올림
		// 105 -> 105/10 -> 10.5 -> 11.0 -> 11
		int pageBarSize = 5;
		// cPage속성 추가전 키워드 작업
		// url에 ?가 이미 있는지 없는지 여부를 따짐
		// cPage이외에도 다른 사용자 입력값이 있는 경우를 대비
		// /mvc/admin/memberFinder?type=id&kw=abc일 경우 &cPage=
		url = url.indexOf("?") > -1 ? url + "&" : url + "?";
		/**
		 * pageStart
		 * 1p - 1 2 3 4 5 --> 1 (0 * pageBarSize + 1)
		 * 2p - 6 7 8 9 10 --> 6 (1 * pageBarSize + 1)
		 * 3p - 11 12 13 14 15 --> 11 (2 * pageBarSize + 1)
		 */
		int pageStart = (cPage - 1) / pageBarSize * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		// 증감변수는 pageStart부터 시작
		int pageNo = pageStart;
		
		// 이전파트, pageNo파트, 다음파트로 나누어서 처리
		// 이전 1 2 3 4 5 다음
		// 이전 6 7 8 9 10 다음
		// 이전 11
		// 1. 이전
		if(pageNo == 1) {
			// 1페이지에는 prev버튼 생성x
		} else {
			// pageNo가 6 또는 11일 때 -> 5페이지, 10페이지로 감
			pageBar.append("<a style='color:white'href='" + url + "cPage=" + (pageNo - 1) + "'/>prev</a>\n");
			//mvc/admin/memberList?cPage=5 (물음표는 따로 처리)
		}
		// 2. pageNo
		// 1, 6, 11로 시작
		// 1 ~ 5, 6 ~ 10, 11 ~ 12
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				// 현재페이지라면 링크로 이동할 필요 없음 -> 그냥 span태그
				pageBar.append("<span class='cPage'>" + pageNo + "</span>\n");
			}
			else {
				// 현재페이지가 아니면 링크 걸기
				pageBar.append("<a style='color:white' href='" + url + "cPage=" + pageNo + "'/>" + pageNo +"</a>\n");
			}
			pageNo++;
			// pageEnd를 마치거나, totalPage(last페이지)까지 다하면 탈출
		}
		// 3. 다음
		if(pageNo > totalPage) {
			// 12페이지까지만 있다면, 위에서 13일때 탈출하니까, 마지막 페이지가 포함된 페이지바인 경우 next를 만들지 않음
		}
		else {
			// 이미 1이 더해진채로 탈툴하니까 +1 필요없음
			pageBar.append("<a style='color:white' href='" + url + "cPage=" + (pageNo) + "'/>next</a>\n");		
		}
		/*
		 *
		<이전>
		<a href='/mvc/admin/memberList?cPage=5'/>prev</a>
		<pageNo>
		<a href='/mvc/admin/memberList?cPage=6'/>6</a>
		<a href='/mvc/admin/memberList?cPage=7'/>7</a>
		<a href='/mvc/admin/memberList?cPage=8'/>8</a>
		<a href='/mvc/admin/memberList?cPage=9'/>9</a>
		<span class='cPage'>10</span>
		<다음>
		<a href='/mvc/admin/memberList?cPage=11'/>next</a>
		*/
		return pageBar.toString();
	}
}
