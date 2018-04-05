
package model.holiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.DataSourceManager;
import model.PagingBean;
import model.PositionVO;
import model.staff.StaffVO;

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
        	sql.append(" WHERE id=? and (h_status = '승인' or h_status='미승인')) ");
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
        	sql.append("select count(*) from holiday where id=? and (h_status ='미승인' or h_status='승인')");
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
	
  public int findHolidayCountById(String id) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int result = 0;
    try {
      con = dataSource.getConnection();
      String sql = "select sum((h_end_date+1)-h_start_date) from holiday where id=? and (h_status='승인' or h_status='미승인')";
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

  public ArrayList<HolidayVO> getTotalHoliday(PagingBean bean) throws SQLException{
	  Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ArrayList<HolidayVO> list=new ArrayList<>();
      try{
        con=dataSource.getConnection();
        String sql="select h_num,start_date,end_date,req_date, h_content,h_status,h_reason,id from("+
        "select row_number() over(order by h_num desc) as r_num,h_num,to_char(h_start_date, 'YYYY-MM-DD') "+
        "as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"+
        "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,h_reason,id from holiday) "+
        "where r_num between ? and ? order by r_num desc";
        pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, bean.getStartRowNumber());
        pstmt.setInt(2, bean.getEndRowNumber());
        rs=pstmt.executeQuery();
        while(rs.next()){
          list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"),
              rs.getString("end_date"), rs.getString("req_date"),
              rs.getString("h_content"), rs.getString("h_status"), rs.getString("h_reason"),
              findStaffVOById(rs.getString("id"))));
        }
      } finally{
        closeAll(rs, pstmt, con);
	  }
      return list;
	}
  
  /**
   * calendar에 ajax로 보내줄 holidayList
   * @param date
   * @return
   * @throws SQLException
   */
  public ArrayList<HolidayVO> getTotalHoliday(String date) throws SQLException {
    ArrayList<HolidayVO> list=new ArrayList<HolidayVO>();
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    try {
       con=dataSource.getConnection();
       String sql="select h_num,to_char(h_start_date, 'YYYY-MM-DD') "+
        "as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"+
        "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,h_reason,id from holiday "+
        "where h_start_date between ? and (select add_months(?,1) from dual) and (h_status = '미승인' or h_status='승인') ";
       pstmt = con.prepareStatement(sql);
       pstmt.setString(1, date);
       pstmt.setString(2, date);
       rs=pstmt.executeQuery();
       while(rs.next()) {
          list.add(new HolidayVO(rs.getInt("h_num"),rs.getString("start_date"),rs.getString("end_date"),
              rs.getString("req_date"),rs.getString("h_content"),rs.getString("h_status"),
              rs.getString("h_reason"),findStaffVOById((rs.getString("id")))));
       }
    }finally {
       closeAll(rs,pstmt,con);
    }
    return list;
 }
  
  /**
   * 분류 조건이 추가된 전체 직원 휴가 목록.
   * @param condition 분류조건
   * @return list 분류가 된 직원 휴가 목록
   * @throws SQLException
   */
  public ArrayList<HolidayVO> getTotalHoliday(String condition,PagingBean bean) throws SQLException{
      Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ArrayList<HolidayVO> list=new ArrayList<>();
      try{
        con=dataSource.getConnection();
        String sql="select h_num,start_date,end_date,req_date, h_content,h_status,h_reason,id from("+
            "select row_number() over(order by h_num desc) as r_num,h_num,to_char(h_start_date, 'YYYY-MM-DD') "+
            "as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"+
            "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,h_reason,id from holiday where "+
            "h_status=?) where r_num between ? and ? order by r_num desc";
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1, condition);
        pstmt.setInt(2, bean.getStartRowNumber());
        pstmt.setInt(3, bean.getEndRowNumber());
        rs=pstmt.executeQuery();
        while(rs.next()){
          list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"), rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
        }
      } finally{
        closeAll(rs, pstmt, con);
      }
      return list;
    }
  
  public ArrayList<HolidayVO> findHolidayById(String id,PagingBean bean) throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    ArrayList<HolidayVO> list=new ArrayList<>();
    try{
      con=dataSource.getConnection();
      String sql="select h_num,start_date,end_date,req_date, h_content,h_status,h_reason,id from("+
          "select row_number() over(order by h_num desc) as r_num,h_num,to_char(h_start_date, 'YYYY-MM-DD') "+
          "as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"+
          "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,h_reason,id from holiday where id=?) "+
          "where r_num between ? and ? order by r_num desc";
      pstmt=con.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setInt(2, bean.getStartRowNumber());
      pstmt.setInt(3, bean.getEndRowNumber());
      rs=pstmt.executeQuery();
      while(rs.next()){
        list.add(new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"), rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
      }
    } finally{
      closeAll(rs, pstmt, con);
    }
    return list;
  }
  
  /**
   * 분류 조건이 추가된 나의 직원 휴가 목록.
   * @param condition 분류조건
   * @return list 분류가 된 직원 휴가 목록
   * @throws SQLException
   */
  public ArrayList<HolidayVO> findHolidayById(String id,String condition,PagingBean bean) throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    ArrayList<HolidayVO> list=new ArrayList<>();
    try{
      con=dataSource.getConnection();
      String sql="select h_num,start_date,end_date,req_date, h_content,h_status,h_reason,id from("+
          "select row_number() over(order by h_num desc) as r_num,h_num,to_char(h_start_date, 'YYYY-MM-DD') "+
          "as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date,"+
          "to_char(h_req_date, 'YYYY-MM-DD') as req_date, h_content,h_status,h_reason,id from holiday where id=? and h_status=?) "+
          "where r_num between ? and ? order by r_num desc";
      pstmt=con.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, condition);
      pstmt.setInt(3, bean.getStartRowNumber());
      pstmt.setInt(4, bean.getEndRowNumber());
      rs=pstmt.executeQuery();
      while(rs.next()){
        list.add(new HolidayVO(rs.getInt("h_num"),rs.getString("start_date"), rs.getString("end_date"), rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"), rs.getString("h_reason"), findStaffVOById(rs.getString("id"))));
      }
    } finally{
      closeAll(rs, pstmt, con);
    }
    return list;
  }

  /**
   * 아이디에 따른 회원 정보를 담은 객체.
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
        //con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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
        //con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
        StringBuilder sql = new StringBuilder();
        sql.append("select p_num, p_name, p_holiday_count ");
        sql.append("from position ");
        sql.append("where p_num = ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, pNum);
        rs = pstmt.executeQuery();
        if(rs.next())
           vo = new PositionVO(rs.getInt(1),rs.getString(2),rs.getInt(3));
        }finally {
        closeAll(rs, pstmt, con);
     }
     return vo;
  }
  
  public int getTotalPostCount() throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int count=0;
    try{
        con=dataSource.getConnection();
        //insert into board_inst(no,title,content,id,time_posted) values(board_inst_seq.nextval,?,?,?,sysdate)
        StringBuilder sql=new StringBuilder();
        sql.append("select count(*) from holiday");
        pstmt=con.prepareStatement(sql.toString());
        rs=pstmt.executeQuery();
        if(rs.next())
        count=rs.getInt(1);         
    }finally{
        closeAll(rs,pstmt,con);
    }
    return count;
}   
  
  public int getTotalPostCount(String condition) throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int count=0;
    try{
        con=dataSource.getConnection();
        //insert into board_inst(no,title,content,id,time_posted) values(board_inst_seq.nextval,?,?,?,sysdate)
        StringBuilder sql=new StringBuilder();
        sql.append("select count(*) from holiday where h_status=?");
        pstmt=con.prepareStatement(sql.toString());
        pstmt.setString(1, condition);
        rs=pstmt.executeQuery();
        if(rs.next())
        count=rs.getInt(1);         
    }finally{
        closeAll(rs,pstmt,con);
    }
    return count;
}   
  
  public int getUserPostCount(String id) throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int count=0;
    try{
        con=dataSource.getConnection();
        //insert into board_inst(no,title,content,id,time_posted) values(board_inst_seq.nextval,?,?,?,sysdate)
        StringBuilder sql=new StringBuilder();
        sql.append("select count(*) from holiday where id=?");
        pstmt=con.prepareStatement(sql.toString());
        pstmt.setString(1, id);
        rs=pstmt.executeQuery();
        if(rs.next())
        count=rs.getInt(1);         
    }finally{
        closeAll(rs,pstmt,con);
    }
    return count;
}   
  
  public int getUserPostCount(String id,String condition) throws SQLException{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int count=0;
    try{
        con=dataSource.getConnection();
        //insert into board_inst(no,title,content,id,time_posted) values(board_inst_seq.nextval,?,?,?,sysdate)
        StringBuilder sql=new StringBuilder();
        sql.append("select count(*) from holiday where id=? and h_status=?");
        pstmt=con.prepareStatement(sql.toString());
        pstmt.setString(1, id);
        pstmt.setString(2, condition);
        rs=pstmt.executeQuery();
        if(rs.next())
        count=rs.getInt(1);         
    }finally{
        closeAll(rs,pstmt,con);
    }
    return count;
}   

  /**
   * 휴가 목록에서 휴가 번호를 눌렀을 때 그에 따른 휴가 정보 객체로 담는 메소드.
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
          //con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
          StringBuilder sql = new StringBuilder();
          sql.append("select h_num,to_char(h_start_date, 'YYYY-MM-DD') as start_date,to_char(h_end_date, 'YYYY-MM-DD') as end_date, to_char(h_req_date, 'YYYY-MM-DD')as req_date, h_content, h_status,h_reason, id ");
          sql.append("from holiday ");
          sql.append("where h_num = ?");
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, hNo);
          rs = pstmt.executeQuery();
          if(rs.next())
              vo = new HolidayVO(rs.getInt("h_num"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("req_date"), rs.getString("h_content"), rs.getString("h_status"),rs.getString("h_reason"), findStaffVOById(rs.getString("id")));
          
      }finally {
          closeAll(rs, pstmt, con);
      }
      
      return vo;
  }

	/**
	 * holiday db table에서 h_num에 해당하는 row를 삭제.
	 *
	 * 1. h_num에 해당하는 row에서 h_status('승인'상태인지 확인을 위해)을 체크해서
	 * 		h_status가 '승인'이면 
	 * 		'holiday_archive' 테이블로 해당하는 row를 이동시킨다.
	 * 1.1. deleteHoliday를 실행시킨 
	 * 			'id'와 '삭제시간'도 함께 테이블에 저장한다. 
	 *
	 * 2. 전체 삭제 권한을 가지고 있는 자는 점장이므로 '점장'에 해당하는 p_num=2를 체크한다. 
	 * 2.1. p_num이 2이면 점장이므로
	 * 2.2. 받아온 h_num에 해당하는 row를 삭제한다.
	 * 
	 * 3. h_status가 '미승인' 상태인것은 p_num이 '3'인 직원도 삭제할 수 있다. 
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
			//'1'항목 수행
			sql.append("INSERT INTO holiday_archive");
			sql.append("	(h_num,h_start_date,h_end_date,h_req_date,h_content,h_status,h_reason,id,del_id)");
			sql.append(" SELECT ");
			sql.append("	h_num,h_start_date,h_end_date,h_req_date,h_content,h_status,h_reason,id,?");
			sql.append(" FROM holiday");
			sql.append(" WHERE h_num=? AND");
			sql.append(" 	h_status='승인' AND");
			sql.append(" 	(SELECT p_num");
			sql.append(" 		FROM staff");
			sql.append(" 		WHERE id=?");
			sql.append(" 	)=2");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, h_num);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			pstmt.close();
			sql.delete(0, sql.length());
			
			//'2'항목 수행
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
			pstmt.close();
			sql.delete(0, sql.length());
			//'3'항목 수행
			sql.append("DELETE holiday");
			sql.append(" WHERE h_num=? AND");
			sql.append("	h_status='미승인'");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, h_num);
			result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
		} finally {
			closeAll(pstmt, con);
		}
		return false;
	}

	/**
	 * 휴가 승인을 위한 메소드.
	 * 점잠이 휴가 목록에서 선택한 휴가를 승인하였을 때 동작하는 메소드 
	 * @param hno 휴가신청 게시글 번호
	 * @param id 휴가승인을 위해 로그인한 점장의 id
	 * @param status 
	 * @param reason 
	 * @return
	 * @throws SQLException
	 */
	public void updateHolidayFlagByHnum(int hno, String id, String status, String reason) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
			
		try {
			con = dataSource.getConnection();
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			String sql="update holiday set h_status=?, h_reason=? where h_num=? and (select p_num from staff where id=?)=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setString(2, reason);
			pstmt.setInt(3, hno);
			pstmt.setString(4, id);
			pstmt.executeQuery();
		} finally {
			closeAll(pstmt, con);
		}
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
			String sql="update holiday set h_start_date=?,h_end_date=?,h_content=?, h_req_date = sysdate where h_num=? ";
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

