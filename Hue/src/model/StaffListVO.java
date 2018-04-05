package model;

import java.util.ArrayList;

/*
 * 직원 목록 화면에 보여질 직원 정보와
 * 페이지 처리 로직 정보 객체를 저장하는 클래스
 */
public class StaffListVO {
	private ArrayList<StaffVO> list;
	private PagingBean pagingBean;
	public StaffListVO(ArrayList<StaffVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	
	public ArrayList<StaffVO> getList() {
		return list;
	}
	public void setList(ArrayList<StaffVO> list) {
		this.list = list;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	
	@Override
	public String toString() {
		return "StaffListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
}
