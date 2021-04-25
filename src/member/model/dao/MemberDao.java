package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;
import static common.JDBCTemplate.*;
public class MemberDao {
	Properties prop = new Properties();
	
	public MemberDao(){
		String fileName = "/sql/member/member-query.properties";
		String absPath = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public Member selectMemberOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMemberOne");
		Member m = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
				m.setMemberNickname(rs.getString("member_nickname"));
				m.setMemberEmail(rs.getString("member_email"));
				m.setMemberPhone(rs.getString("member_phone"));
				m.setMemberTeam(rs.getString("member_team"));
				m.setMemberPicture(rs.getString("member_picture"));
				m.setMemberRole(rs.getString("member_role"));
			}
		} catch (SQLException e) {
			throw new MemberException("회원 조회 오류",e);
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return m;
	}

	public int insertMember(Connection conn, Member member) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		System.out.println("sql="+sql);
		try {
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());	//sns �α��ν� null
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberNickname());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePassword(Connection conn, Member member, String password, String newPassword) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		System.out.println("sql="+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPassword);
			pstmt.setString(2, member.getMemberId());	
			pstmt.setString(3, password);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("비밀번호 변경 오류",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		System.out.println("sql="+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberEmail());	
			pstmt.setString(3, member.getMemberPhone());	
			pstmt.setString(4, member.getMemberId());	
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 정보 변경 오류",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		System.out.println("sql="+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 탈퇴 오류",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
