package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

import java.sql.Connection;
public class MemberService {
	private MemberDao memberDao = new MemberDao(); 
	
	
	public Member selectMemberOne(String memberId) {
		Connection conn = getConnection();
		Member m= memberDao.selectMemberOne(conn, memberId);
		close(conn);
		return m;
	}
	
	
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		int result =0;
		Connection conn = getConnection();
		try{
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

}
