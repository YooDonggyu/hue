package model;

public class StaffVO {
	private String id;
	private String password;
	private String name;
	private String mail;
	private String imagePath;
	private PositionVO positionVO;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public PositionVO getPositionVO() {
		return positionVO;
	}
	public void setPositionVO(PositionVO positionVO) {
		this.positionVO = positionVO;
	}
	public StaffVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StaffVO(String id, String password, String name, String mail) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.mail = mail;
	}
	public StaffVO(String id, String password, String name, String mail, String imagePath, PositionVO positionVO) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.mail = mail;
		this.imagePath = imagePath;
		this.positionVO = positionVO;
	}
	
	@Override
	public String toString() {
		return "StaffVO [id=" + id + ", password=" + password + ", name=" + name + ", mail=" + mail + ", imagePath="
				+ imagePath + ", positionVO=" + positionVO + "]";
	}
}
