package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class HolidayDAO {
	private static HolidayDAO instance = new HolidayDAO();
	private DataSource dataSource;

	private HolidayDAO() {
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

	public void createHoliday(HolidayVO vo) throws SQLException {
		  	String defaultHolidayStatus="미승인";
		  	Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            con = dataSource.getConnection();
	            StringBuilder sql = new StringBuilder();
	            sql.append("INSERT INTO holiday(h_num,h_start_date,h_end_date,h_req_date,h_content,h_status,id)");
	            sql.append(" VALUES(h_num_seq.nextval, ?, ?, sysdate, ?, ?, ?)");
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, vo.gethStartDate());
	            pstmt.setString(2, vo.gethEndDate());
	            pstmt.setString(3, vo.gethContent());
	            pstmt.setString(4, defaultHolidayStatus);
	            pstmt.setString(5, vo.getStaffVO().getId());
	            pstmt.executeUpdate();
	        } finally {
	            closeAll(pstmt, con);
	        }
	    }
	
	public int readCountHoliday(String id) throws SQLException {
	  	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int countHoliday=0;
        try {
        	con = dataSource.getConnection();
        	String sql="select sum((h_end_date+1)-h_start_date) from holiday where id=?";
        	pstmt=con.prepareStatement(sql);
        	pstmt.setString(1, id);
        	pstmt.executeUpdate();
        	rs=pstmt.executeQuery();
        	while(rs.next()) {
        		countHoliday=rs.getInt(1);
        	} 
        }finally {
        	closeAll(rs, pstmt, con);
        }
        return countHoliday;
	}
	
	public int readRemainHoliday(String id) throws SQLException {
	  	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int remainHoliday=0;
        try {
        	con = dataSource.getConnection();
        	StringBuilder sql = new StringBuilder();
        	sql.append("SELECT p.p_holiday_count-(SELECT sum((h_end_date+1)-h_start_date) ");
        	sql.append(" FROM holiday ");
        	sql.append(" WHERE id=?) ");
        	sql.append("FROM position p, staff s ");
        	sql.append("WHERE p.p_num = s.p_num  ");
        	sql.append("AND id=? ");
        	pstmt = con.prepareStatement(sql.toString());
        	pstmt.setString(1, id);
        	pstmt.setString(2, id);
        	pstmt.executeUpdate();
        	rs=pstmt.executeQuery();
        	while(rs.next()) {
        		remainHoliday=rs.getInt(1);
        	} 
        }finally {
        	closeAll(rs, pstmt, con);
        }
        return remainHoliday;
	}

	public int readInitialHoliday(String id) throws SQLException {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int initialHoliday=0;
        try {
        	con = dataSource.getConnection();
        	String sql="SELECT p.p_holiday_count from position p, staff s where p.p_num=s.p_num and s.id=?";
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1, id);
        	pstmt.executeUpdate();
        	rs=pstmt.executeQuery();
        	while(rs.next()) {
        		initialHoliday=rs.getInt(1);
        	} 
        }finally {
        	closeAll(rs, pstmt, con);
        }
        return initialHoliday;
	}

	public int readCountHolidayNum(String id) throws SQLException {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int count=0;
        try {
        	con = dataSource.getConnection();
        	StringBuilder sql = new StringBuilder();
        	sql.append("select count(*) from holiday where id=?");
        	pstmt = con.prepareStatement(sql.toString());
        	pstmt.setString(1, id);
        	pstmt.executeUpdate();
        	rs=pstmt.executeQuery();
        	while(rs.next()) {
        		count=rs.getInt(1);
        	} 
        }finally {
        	closeAll(rs, pstmt, con);
        }
        return count;
	}
}
