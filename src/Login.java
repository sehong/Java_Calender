
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel loginPanel = new JPanel(new GridLayout(3, 3));

	private JLabel idLabel = new JLabel("ID");
	private JLabel pwLabel = new JLabel("Password ");
	private JTextField idText = new JTextField();
	private JPasswordField pwText = new JPasswordField();
	private JButton loginBtn = new JButton("로그인");
	private JButton idpwSearchBtn = new JButton("ID/Password 찾기");
	private JButton joinBtn = new JButton("회원가입");
	private JLabel Label = new JLabel(" ");
	private JLabel Label1 = new JLabel(" ");

	public Login(Member m) {
		this.setContentPane(loginPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginPanel.add(idLabel);
		loginPanel.add(idText);
		loginPanel.add(Label1);

		loginPanel.add(pwLabel);
		loginPanel.add(pwText);
		loginPanel.add(Label);

		loginPanel.add(joinBtn);
		loginPanel.add(idpwSearchBtn);
		loginPanel.add(loginBtn);




		//라벨 가운데 정렬

		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);

		//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!

		setSize(350,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		joinBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Join(m);

			}});

		//로그인 버튼을 눌렀을때

		loginBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String id = idText.getText().trim();
				String pw = pwText.getText().trim();

				if(id.length()==0 || pw.length()==0) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				/*if(id.equals("test") && pw.equals("test1")) {

               JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
               return;
            }*/

				String FilePath = m.getFilePath();
				try {

					String M = m.FileRead();
					String []word = M.split("\n");
					for(int j =0 ;j<word.length-1; j++)
					{
						String []s = word[j].split(" ");
						//name+" "+id + " " + pw+" "+ p_n+" "+email+"\n" 
						m.setfId(s[1]);
						m.setfPw(s[2]);


						if(id.equals(m.getfId())&& pw.equals(m.getfPw()))
						{
							new FullFrame();
							return;
						}

					}
				
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인해주세요!!", "", JOptionPane.DEFAULT_OPTION);
						return;
					

				} catch (Exception e1) {
					// TODO 자동 생성된 catch 블록
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}





			}

		});




		//아이디 비밀번호 찾기 버튼을 눌렀을때

		idpwSearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new ID_PWSearch(m);
			}

		});


	}

	public static void main(String[] args) {
		Member m = new Member();
		new Login(m);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} //
		catch (Exception e) {
		}
	}


}

class ID_PWSearch extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel ID_PWSearchPanel= new JPanel(new GridLayout(2,1));

	private JButton idSearchBtn = new JButton("아이디 찾기");
	private JButton pwSearchBtn = new JButton("비밀번호 찾기");

	public ID_PWSearch(Member m)
	{

		setContentPane(ID_PWSearchPanel);

		ID_PWSearchPanel.add(idSearchBtn);
		ID_PWSearchPanel.add(pwSearchBtn);

		//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!

		setSize(350,150);

		this.setLocationRelativeTo(null); //프레임 창을 화면 정중안에 띄움 
		this.setVisible(true);

		idSearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new IDsearch(m);
			}
		});
		pwSearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new PWsearch(m);
			}
		});


	}

}

class Join extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JoinPanel= new JPanel(new GridLayout(6,2));
	private JLabel idLabel = new JLabel("아이디");
	private JTextField idText = new JTextField();

	private JLabel pwLabel = new JLabel("비밀번호");
	private JTextField pwText = new JTextField();

	private JLabel nameLabel = new JLabel("이름");
	private JTextField nameText = new JTextField();

	private JLabel p_nLabel = new JLabel("휴대폰");
	private JTextField p_nText = new JTextField();

	private JLabel EmailLabel = new JLabel("이메일");
	private JTextField EmailText = new JTextField();

	private JButton joinbtn = new JButton("확인");
	private JLabel Label = new JLabel("");
	public Join(Member m){
		setContentPane(JoinPanel);

		JoinPanel.add(nameLabel);
		JoinPanel.add(nameText);

		JoinPanel.add(idLabel);
		JoinPanel.add(idText);

		JoinPanel.add(pwLabel);
		JoinPanel.add(pwText);

		JoinPanel.add(p_nLabel);
		JoinPanel.add(p_nText);

		JoinPanel.add(EmailLabel);
		JoinPanel.add(EmailText);

		JoinPanel.add(Label);
		JoinPanel.add(joinbtn);

		//라벨 가운데 정렬
		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);
		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);

		//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!
		setSize(350,150);

		this.setLocationRelativeTo(null);
		this.setVisible(true);

		joinbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String id = idText.getText().trim();
				String pw = pwText.getText().trim();
				String name = nameText.getText().trim();
				String p_n = p_nText.getText().trim();
				String email = EmailText.getText().trim();

				if(name.length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(id.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(pw.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}

				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "이메일 주소를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}

				String FilePath = m.getFilePath();

				try {

					String M = m.FileRead();
					if(M.length() != 0)
					{


						String []word = M.split("\n");

						for(int j =0 ;j<word.length-1; j++)
						{
							String []s = word[j].split(" ");
							//name+" "+id + " " + pw+" "+ p_n+" "+email+"\n" 
							m.setFname(s[0]);
							m.setfId(s[1]);
							m.setFp_n(s[3]);

							if(id.equals(m.getfId()))
							{
								JOptionPane.showMessageDialog(null, "아이디 중복!!", "", JOptionPane.DEFAULT_OPTION);
								return;
							}
							if(name.equals(m.getFname())&&p_n.equals(m.getFp_n())) {
								JOptionPane.showMessageDialog(null, "이미 가입된 회원입니다!!", "", JOptionPane.DEFAULT_OPTION);
								return;
							}

						}
					}
					m.setfId(id);
					m.setfPw(pw);
					m.setFname(name);
					m.setFp_n(p_n);
					m.setFe_m(email);

					m.FileWrite();

					JOptionPane.showMessageDialog(null, "회원가입 완료!!", "", JOptionPane.DEFAULT_OPTION);
					dispose();
					return;
				}catch (Exception e1) {
					// TODO 자동 생성된 catch 블록
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
	}
}
class IDsearch extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel IDSearchPanel= new JPanel(new GridLayout(4,2));
	private JButton SearchBtn = new JButton("아이디 찾기");
	private JLabel nameLabel = new JLabel("이름");
	private JTextField nameText = new JTextField();
	private JLabel p_nLabel = new JLabel("휴대폰");
	private JTextField p_nText = new JTextField();
	private JLabel EmailLabel = new JLabel("이메일");
	private JTextField EmailText = new JTextField();
	private JLabel Label = new JLabel("");

	public IDsearch(Member m) {


		setContentPane(IDSearchPanel);

		IDSearchPanel.add(nameLabel);
		IDSearchPanel.add(nameText);

		IDSearchPanel.add(p_nLabel);
		IDSearchPanel.add(p_nText);

		IDSearchPanel.add(EmailLabel);
		IDSearchPanel.add(EmailText);
		IDSearchPanel.add(Label);
		IDSearchPanel.add(SearchBtn);


		//라벨 가운데 정렬

		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);

		//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!

		setSize(350,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		SearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String name = nameText.getText().trim();

				String p_n = p_nText.getText().trim();

				String email = EmailText.getText().trim();

				if(name.length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "이메일 주소를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}

				String FilePath =  m.getFilePath();
				try {

					String M = m.FileRead();
					String []word = M.split("\n");
					for(int j =0 ;j<word.length-1; j++)
					{
						String []s = word[j].split(" ");
						//name+" "+id + " " + pw+" "+ p_n+" "+email+"\n" 
						m.setFname(s[0]);
						m.setFp_n(s[3]);
						m.setFe_m(s[4]);

						if(name.equals(m.getFname())&& p_n.equals(m.getFp_n())&& email.equals(m.getFe_m()))
						{
							JOptionPane.showMessageDialog(null,"아이디: "+ s[1], "", JOptionPane.DEFAULT_OPTION);
							dispose();
							return;
						}

					}


					JOptionPane.showMessageDialog(null, "회원정보가 존재하지 않습니다!!", "", JOptionPane.DEFAULT_OPTION);
					return;

				} catch (Exception e1) {
					// TODO 자동 생성된 catch 블록
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}

			}
		});



	}
}
class PWsearch extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel PWSearchPanel= new JPanel(new GridLayout(5,2));
	private JLabel idLabel = new JLabel("아이디");
	private JTextField idText = new JTextField();
	private JButton SearchBtn = new JButton("비밀번호 찾기");
	private JLabel nameLabel = new JLabel("이름");
	private JTextField nameText = new JTextField();
	private JLabel p_nLabel = new JLabel("휴대폰");
	private JTextField p_nText = new JTextField();
	private JLabel EmailLabel = new JLabel("이메일");
	private JTextField EmailText = new JTextField();
	private JLabel Label = new JLabel("");
	public PWsearch(Member m) {


		setContentPane(PWSearchPanel);


		PWSearchPanel.add(idLabel);
		PWSearchPanel.add(idText);

		PWSearchPanel.add(nameLabel);
		PWSearchPanel.add(nameText);

		PWSearchPanel.add(p_nLabel);
		PWSearchPanel.add(p_nText);

		PWSearchPanel.add(EmailLabel);
		PWSearchPanel.add(EmailText);

		PWSearchPanel.add(Label);
		PWSearchPanel.add(SearchBtn);


		//라벨 가운데 정렬
		idLabel.setHorizontalAlignment(NORMAL);
		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);



		//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!

		setSize(350,150);

		this.setLocationRelativeTo(null); //실행시 프레임이 화면 정중앙에 나오게 함


		this.setVisible(true);




		//아이디 비번 확인 테스트 코드~



		SearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id = idText.getText().trim();

				String name = nameText.getText().trim();

				String p_n = p_nText.getText().trim();

				String email = EmailText.getText().trim();


				if(id.length() == 0){
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(name.length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "이메일 주소를 입력해 주세요.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}



				String FilePath =  m.getFilePath();
				try {

					String M = m.FileRead();

					String []word = M.split("\n");

					for(int j =0 ;j<word.length-1; j++)
					{
						String []s = word[j].split(" ");
						//name+" "+id + " " + pw+" "+ p_n+" "+email+"\n" 
						m.setFname(s[0]);
						m.setfId(s[1]);
						m.setFp_n(s[3]);
						m.setFe_m(s[4]);

						if(name.equals(m.getFname())&& p_n.equals(m.getFp_n())&& email.equals(m.getFe_m()))
						{
							JOptionPane.showMessageDialog(null,"비밀번호: "+ s[2], "", JOptionPane.DEFAULT_OPTION);
							dispose();
							return;
						}
					}

					JOptionPane.showMessageDialog(null, "회원정보가 존재하지 않습니다!!", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				catch (Exception e1) {
					// TODO 자동 생성된 catch 블록
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}

			}
		});



	}
}