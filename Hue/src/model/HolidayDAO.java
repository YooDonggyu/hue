package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public int findHolidayCountById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			String sql = "select sum((h_end_date+1)-h_start_date) from holiday where id=? and (h_status='승인' or h_status='거절')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	public ArrayList<HolidayVO> getTotalHoliday() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HolidayVO> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String sql = "select h_num,to_char(h_start_date, 'YYYY-MM-DD') as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"
					+ "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,id from holiday";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"),
						findStaffVOById(rs.getString("id"))));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public ArrayList<HolidayVO> findHolidayById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HolidayVO> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String sql = "select * from holiday where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("h_start_date"), rs.getString("h_end_date"),
						rs.getString("h_reg_date"), rs.getString("h_content"), rs.getString("h_status"),
						rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 분류 조건이 추가된 나의 직원 휴가 목록.
	 * 
	 * @param condition
	 *            분류조건
	 * @return list 분류가 된 직원 휴가 목록
	 * @throws SQLException
	 */
	public ArrayList<HolidayVO> findHolidayById(String id, String condition) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HolidayVO> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String sql = "select * from holiday where id=? and h_status=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, condition);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("h_start_date"), rs.getString("h_end_date"),
						rs.getString("h_reg_date"), rs.getString("h_content"), rs.getString("h_status"),
						rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 분류 조건이 추가된 전체 직원 휴가 목록.
	 * 
	 * @param condition
	 *            분류조건
	 * @return list 분류가 된 직원 휴가 목록
	 * @throws SQLException
	 */
	public ArrayList<HolidayVO> getTotalHoliday(String condition) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HolidayVO> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String sql = "select * from holiday where h_status=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, condition);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("h_start_date"), rs.getString("h_end_date"),
						rs.getString("h_reg_date"), rs.getString("h_content"), rs.getString("h_status"),
						rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 아이디에 따른 회원 정보를 담은 객체.
	 * 
	 * @param id
	 *            회원 아이디
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
			// con
			// =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, name, mail, image_path, p_num ");
			sql.append("from staff ");
			sql.append("where id = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				vo = new StaffVO(id, rs.getString("password"), rs.getString("name"), rs.getString("mail"),
						rs.getString("image_path"), findPostionVOByPnum(rs.getInt("p_num")));
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	/**
	 * position테이블의 번호에 해당하는 position 객체 반환.
	 * 
	 * @param pNum
	 *            position의 PK
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
			// con
			// =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			StringBuilder sql = new StringBuilder();
			sql.append("select p_num, p_name, p_holiday_count ");
			sql.append("from position ");
			sql.append("where p_num = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			if (rs.next())
				vo = new PositionVO(rs.getInt(1), rs.getString(2), rs.getInt(3));
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	public int getTotalPostCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			// insert into board_inst(no,title,content,id,time_posted)
			// values(board_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from holiday");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public int getTotalPostCount(String condition) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			// insert into board_inst(no,title,content,id,time_posted)
			// values(board_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from holiday where h_status=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, condition);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public int getUserPostCount(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			// insert into board_inst(no,title,content,id,time_posted)
			// values(board_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from holiday where id=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public int getUserPostCount(String id, String condition) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			// insert into board_inst(no,title,content,id,time_posted)
			// values(board_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from holiday where id=? and h_status=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, condition);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	/**
	 * 휴가 목록에서 휴가 번호를 눌렀을 때 그에 따른 휴가 정보 객체로 담는 메소드.
	 * 
	 * @param pNo
	 *            휴가 목록에서 클릭한 휴가번호
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
			// con
			// =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select h_num,to_char(h_start_date, 'YYYY-MM-DD') as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date, to_char(h_req_date, 'YYYY-MM-DD')as req_date, h_content, h_status,h_reason, id ");
			sql.append("from holiday ");
			sql.append("where h_num = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, hNo);
			rs = pstmt.executeQuery();
			if (rs.next())
				vo = new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"),
						rs.getString("h_reason"), findStaffVOById(rs.getString("id")));

		} finally {
			closeAll(rs, pstmt, con);
		}

		return vo;
	}
	
	/**
	 * holiday db table에서 p_num에 해당하는 row를 삭제.
	 *
	 * 1. 삭제 권한을 가지고 있는 자는 점장이므로 '점장'에 해당하는 p_num=2를 체크한다. 1.1. p_num이 2이면 점장이므로
	 * 1.2. 받아온 h_num에 해당하는 row를 삭제한다.
	 * 
	 * p_num이
	 * 
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
			if (result == 1) {
				return true;
			}
		} finally {
			closeAll(pstmt, con);
		}
		return false;
	}
}
