package model.staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.DataSourceManager;
import model.PagingBean;
import model.PositionVO;

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

	/**
	 * 로그인 메소드.
	 * @param id 로그인 하는 id
	 * @param password 로그인 하는 id의 password
	 * @return StaffVO 로그인 성공시 id에 해당하는 StaffVO 객체를 반환
	 * @throws SQLException
	 */
	public StaffVO login(String id, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StaffVO vo = null;
		
		try {
			con = dataSource.getConnection();
			//con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			String sql="select * from staff where id=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo = new StaffVO(rs.getString("id"),rs.getString("password"),rs.getString("name"),rs.getString("mail"),rs.getString("image_path"),findPositionByPnum(rs.getInt("p_num")));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
	/**
	 * 관리자를 제외한 직책명을 가져오는 메서드.
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<PositionVO> getPositionList() throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    ArrayList<PositionVO> list=new ArrayList<PositionVO>();
	    try {
	      con = dataSource.getConnection();
	    //con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
	      String sql = "select p_num, p_name from position where p_num!=1";
	      pstmt = con.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	    	  list.add(new PositionVO(rs.getInt(1), rs.getString(2), 0));
	      }
	    } finally {
	      closeAll(rs, pstmt, con);
	    }
	    return list;
	  }
	/**
	 * 직책 번호에 따른 직책이름을 받아오는 메소드.
	 * @param pNum 직잭 번호
	 * @return PositionVO 직책 번호에 해당하는 직책 객체 반환
	 * @throws SQLException
	 */
	public PositionVO findPositionByPnum(int pNum) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    PositionVO vo=null;
	    try {
	      con = dataSource.getConnection();
	    //con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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
	/**
	 * 사용자 id에 따른 모든 정보 반환.
	 * @param id 사용자 id
	 * @return StaffVO 사용자 id에 따른 StaffVO 객체 
	 * @throws SQLException
	 */
	  public StaffVO findStaffById(String id) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    StaffVO vo=null;
	    try {
	      con = dataSource.getConnection();
	      //con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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

	  
	  /**
	   * id에 따른 직책 수정.
	   * @param id 수정할 id
	   * @param pNum 수정할 직책 (직책 번호가 foreign key로 되어 있기 때문에 직책번호 받음)
	   * @throws SQLException
	   */
	  public void updateStaffPositionById(String id, String pNum) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	      con = dataSource.getConnection();
	      //con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
	      String sql = "update staff set p_num=? where id = ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setInt(1, Integer.parseInt(pNum));
	      pstmt.setString(2, id);
	      pstmt.executeUpdate();
	    } finally {
	      closeAll(pstmt, con);
	    }
	  }
	  /**
	   * 사용자 정보 수정.
	   * @param staffVO 수정할 사용자 정보를 담은 객체
	   * @throws SQLException
	   */
	  public void updateStaff(StaffVO staffVO) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    try {
	      con = dataSource.getConnection();
	      //con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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


	  /**
	   * 회원가입 메소드.
	   * @param vo 회원가입할 직원 객체
	   * @throws SQLException
	   */
	  public void createUser(StaffVO vo) throws SQLException {
        String defaultImagePath = "/dist/img/default.png";
        int defaultPositionNumber = 3;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();
        	//con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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
	/**
	 * id 중복값을 ajax로 리턴해주기 위한 메서드.
	 * @param id 확인할 id
	 * @return boolean true or false
	 * @throws SQLException
	 */
    public boolean checkId(String id) throws SQLException {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            //con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
            String sql = "select count(*) from staff where id=?";
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
    
    /**
     * 사용자를 삭제하는 메서드.
     * @param id 삭제할 사용자 id
     * @throws SQLException
     */
	public void deleteUser(String id) throws SQLException {
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 try {
			con = dataSource.getConnection();
			//con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			String sql = "delete from staff where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeQuery();
		 } finally {
			closeAll(pstmt, con);
		 }

	}
	/**
	 * 전체 휴가 목록을 가져오기 위한 메서드.
	 * @param bean bean에 담겨있는 start와 end를 가져와 사용
	 * @return list 휴고 목록을 list에 담아 return
	 * @throws SQLException
	 */
	
    public ArrayList<StaffVO> getTotalStaff(PagingBean bean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<StaffVO> list = new ArrayList<StaffVO>();
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select p_num, id, name, mail, image_path");
			sql.append(" from");
			sql.append("	(select row_number() over(order by p_num desc) as r_num,");
			sql.append("		p_num,");
			sql.append("		id,");
			sql.append("		name,");
			sql.append("		mail,");
			sql.append("		image_path");
			sql.append("	from staff");
			sql.append("	)");
			sql.append("where r_num between ? and ? order by r_num desc");
			pstmt = con.prepareStatement(sql.toString());
	        pstmt.setInt(1, bean.getStartRowNumber());
	        pstmt.setInt(2, bean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StaffVO(rs.getString(2), null, rs.getString(3), rs.getString(4),
						rs.getString(5), findPositionByPnum(rs.getInt(1))));
			}

		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	/**
	 * 직원의 총 수를 구함.
	 * @return count 전체 수
	 * @throws SQLException
	 */
	public int getTotalStaffCount() throws SQLException{
	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    int count=0;
	    try{
	        con=dataSource.getConnection();
	        //insert into board_inst(no,title,content,id,time_posted) values(board_inst_seq.nextval,?,?,?,sysdate)
	        StringBuilder sql=new StringBuilder();
	        sql.append("select count(*) from staff  where p_num=2 or p_num=3");
	        pstmt=con.prepareStatement(sql.toString());
	        rs=pstmt.executeQuery();
	        if(rs.next())
	        count=rs.getInt(1);         
	    }finally{
	        closeAll(rs,pstmt,con);
	    }
	    return count;
	}

	
	
}
