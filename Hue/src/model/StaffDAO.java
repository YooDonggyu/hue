package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	//로그인 메소드
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
	
	//관리자를 제외한 직책명을 가져오는 메소드
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
	
	//직책 번호에 따른 직책이름을 받아오는 메소드
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

    public ArrayList<StaffVO> getTotalStaff(PagingBean bean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<StaffVO> list = new ArrayList<StaffVO>();
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select p_num, id, name, mail");
			sql.append(" from");
			sql.append("	(select row_number() over(order by p_num desc) as r_num,");
			sql.append("		p_num,");
			sql.append("		id,");
			sql.append("		name,");
			sql.append("		mail");
			sql.append("	from staff");
			sql.append("	)");
			sql.append("where r_num between ? and ? order by r_num desc");
			pstmt = con.prepareStatement(sql.toString());
	        pstmt.setInt(1, bean.getStartRowNumber());
	        pstmt.setInt(2, bean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StaffVO(rs.getString(2), null, rs.getString(3), rs.getString(4),
						null, findPositionByPnum(rs.getInt(1))));
			}

		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	//직원의 총 수
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
