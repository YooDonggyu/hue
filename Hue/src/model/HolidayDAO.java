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
	 * 휴가 업데이트 메서드.
	 * 휴가일을 수정하고 싶을 때 사용하는 메서드이다
	 * @param hContent 간단한 내용
	 * @param hStartDate 휴가 시작일
	 * @param hEndDate 휴가 끝일
	 * @param hNo 게시글 번호
	 * @throws SQLException
	 */
	public void updateHoliday(String hContent, String hStartDate, String hEndDate,int hNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update holiday set h_start_date=?,h_end_date=?,h_content=? where h_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hStartDate);
			pstmt.setString(2, hEndDate);
			pstmt.setString(3, hContent);
			pstmt.setInt(4, hNo);
			pstmt.executeQuery();
		}finally {
			closeAll(pstmt,con);
		}
	}
}
