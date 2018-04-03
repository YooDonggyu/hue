package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class HolidayDAO {
	private static HolidayDAO instance = new HolidayDAO();
	private DataSource dataSource;
	
	private HolidayDAO () {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static HolidayDAO getInstance() {
		return instance;
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		closeAll(null, pstmt, con);
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
	
	/**
	 * holiday db table에서 p_num에 해당하는 row를 삭제.
	 *
	 * 1. 삭제 권한을 가지고 있는 자는 점장이므로 '점장'에 해당하는 p_num=2를 체크한다.
	 * 1.1. p_num이 2이면 점장이므로
	 * 1.2. 받아온 h_num에 해당하는 row를 삭제한다.
	 * 
	 * p_num이
	 * @param p_num
	 * @param id
	 * @throws SQLException 
	 */
	public boolean deleteHoliday(int h_num, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE holiday");
			sql.append(" WHERE h_num=? AND");
			sql.append(" (SELECT p_num");
			sql.append(" 	FROM staff");
			sql.append(" 	WHERE id=?");
			sql.append(" )=2");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, h_num);
			pstmt.setString(2, id);
			int result = pstmt.executeUpdate();
			if(result==1) {
				return true;
			}
		}finally {
			closeAll(pstmt, con);
		}
		return false;
	}
}
