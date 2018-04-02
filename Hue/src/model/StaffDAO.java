package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class StaffDAO {
	private static StaffDAO instance = new StaffDAO();
	private DataSource dataSource;

	private StaffDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static StaffDAO getInstance() {
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

	public StaffVO login(String id, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StaffVO vo = null;

		try {
			con = dataSource.getConnection();
			// con
			// =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			String sql = "select * from staff where id=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new StaffVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						new PositionVO(rs.getInt(6)));
			}

		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	public void createUser(StaffVO vo) throws SQLException {
		String defaultImagePath = "/upload/image/default.png";
		int defaultPositionNumber = 3;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO staff(id,password,name,mail,image_path,p_num)");
			sql.append(" VALUES(?,?,?,?,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getMail());
			pstmt.setString(5, defaultImagePath);
			pstmt.setInt(6, defaultPositionNumber);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public boolean checkId(String id) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next() && (rs.getInt(1) > 0))
				flag = true;
		} finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}

}
