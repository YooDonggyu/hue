package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class StaffDAO {
  private static StaffDAO instance = new StaffDAO();
  private DataSource dataSource;

  private StaffDAO() {
    //dataSource = DataSourceManager.getInstance().getDataSource();
  }

  public static StaffDAO getInstance() {
    return instance;
  }

  public PositionVO findPositionByPnum(int pNum) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    PositionVO vo=null;
    try {
      //con = dataSource.getConnection();
      con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
      String sql = "select * from position where p_num=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, pNum);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        vo= new PositionVO(pNum, rs.getString("p_name"), rs.getInt("p_holiday_count"));
      }
      return vo;
    } finally {
      closeAll(rs, pstmt, con);
    }
  }

  public StaffVO findStaffById(String id) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StaffVO vo=null;
    try {
      //con = dataSource.getConnection();
      con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
      String sql = "select * from staff where id=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        vo=new StaffVO(id, rs.getString("password"), rs.getString("name"), rs.getString("mail"), rs.getString("image_path"), findPositionByPnum(rs.getInt("p_num")));
      }
      return vo;
    } finally {
      closeAll(pstmt, con);
    }
  }

  public void updateStaff(StaffVO staffVO) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    try {
      //con = dataSource.getConnection();
      con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
      String sql = "update staff set password=?, mail=?, image_path=? where id = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, staffVO.getPassword());
      pstmt.setString(2, staffVO.getMail());
      pstmt.setString(3, staffVO.getImagePath());
      pstmt.setString(4, staffVO.getId());
      pstmt.executeUpdate();
    } finally {
      closeAll(pstmt, con);
    }
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

}
