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
	 * 휴가 목록에서 휴가 번호를 눌렀을 때 그에 따른 휴가 정보 객체로 담는 메소드.
	 * 
	 * @param pNo 휴가 목록에서 클릭한 휴가번호
	 * @return vo pNo에 해당하는 휴가 정보를 담은 객체
	 * @throws SQLException 
	 */
	
	public HolidayVO findDetailHolidayByPno(int hNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HolidayVO vo = null;

		
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select h_no, h_start_date, h_end_date, h_req_date, h_content, h_flag, id ");
			sql.append("from holiday ");
			sql.append("where h_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, hNo);
			rs = pstmt.executeQuery();
			if(rs.next())
				vo = new HolidayVO(rs.getInt("h_no"), rs.getString("h_start_date"), rs.getString("h_end_date"), rs.getString("h_req_date"), rs.getString("h_content"), rs.getString("h_flag"), findStaffVOById(rs.getString("id")));
			
		}finally {
			closeAll(rs, pstmt, con);
		}
		
		return vo;
	}
	
	
	/**
	 * 아이디에 따른 회원 정보를 담은 객체.
	 * 
	 * @param id 회원 아이디
	 * @return vo 회원 정보를 담은 객체
	 * @throws SQLException
	 */
	public StaffVO findStaffVOById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StaffVO vo = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, name, mail, image_path, p_num ");
			sql.append("from staff ");
			sql.append("where id = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
				vo = new StaffVO(id, rs.getString("password"), rs.getString("name"), rs.getString("mail"), rs.getString("image_path"), findPostionVOByPnum(rs.getInt("p_num")));
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
	
	/**
	 * position테이블의 번호에 해당하는 position 객체 반환. 
	 * @param pNum position의 PK
	 * @return vo pNum에 해당하는 객체
	 * @throws SQLException
	 */
	private PositionVO findPostionVOByPnum(int pNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PositionVO vo = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select p_num, p_name, p_holiday_count ");
			sql.append("from postion ");
			sql.append("where p_num = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			if(rs.next())
				vo = new PositionVO();
			}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
}
