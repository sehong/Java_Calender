
class Member extends FileIO {

	private String fId; //���Ͽ� ����� ���̵�
	private String fPw; //���Ͽ� ����� ��й�ȣ
	private String fname; //���Ͽ� ����� �̸�
	private String fp_n; // ���Ͽ� ����� �޴��� ��ȣ
	private String fe_m;//���Ͽ� ����� �̸���

	public String getFe_m() {
		return fe_m;
	}

	public void setFe_m(String fe_m) {
		this.fe_m = fe_m;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFp_n() {
		return fp_n;
	}

	public void setFp_n(String fp_n) {
		this.fp_n = fp_n;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getfPw() {
		return fPw;
	}

	public void setfPw(String fPw) {
		this.fPw = fPw;
	}




	public Member()
	{

	}


}