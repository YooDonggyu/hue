package model;

import java.util.ArrayList;

/*
 * 게시물 리스트 화면에 보여질 게시물 정보(회원정보 포함) 와
 * 페이지 처리 로직 정보 객체를 저장하는 클래스
 */
public class ListVO {
	private ArrayList<HolidayVO> list;
	private PagingBean pagingBean;
	public ListVO(ArrayList<HolidayVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	
	public ArrayList<HolidayVO> getList() {
		return list;
	}
	public void setList(ArrayList<HolidayVO> list) {
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
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
}
