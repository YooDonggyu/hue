package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffDAO {
	private static StaffDAO instance = new StaffDAO();
	//private DataSource dataSource;

	private StaffDAO() {
		//dataSource = DataSourceManager.getInstance().getDataSource();
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
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	}
	public void createUser(StaffVO vo) throws SQLException {
		String defaultImagePath="/upload/image/default.png";
		int defaultPositionNumber=3;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=dataSource.getConnection();
			con=getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO staff(id,password,name,mail,image_path,p_num)");
			sql.append(" VALUES(?,?,?,?,?,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getMail());
			pstmt.setString(5, defaultImagePath);
			pstmt.setInt(6, defaultPositionNumber);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public boolean checkId(String id) throws SQLException{
		boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			String sql="select count(*) from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()&&(rs.getInt(1)>0))
			flag=true;			
		}finally{
			closeAll(rs,pstmt,con);
		}
		return flag;
	}

}
