
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
	private JButton loginBtn = new JButton("�α���");
	private JButton idpwSearchBtn = new JButton("ID/Password ã��");
	private JButton joinBtn = new JButton("ȸ������");
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




		//�� ��� ����

		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);

		//���� ������ â ��� ���� setSize�� ���� ���־�� ���������� �������� ��� ������ ��!

		setSize(350,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		joinBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Join(m);

			}});

		//�α��� ��ư�� ��������

		loginBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String id = idText.getText().trim();
				String pw = pwText.getText().trim();

				if(id.length()==0 || pw.length()==0) {
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				/*if(id.equals("test") && pw.equals("test1")) {

               JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
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
				
						JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���!!", "", JOptionPane.DEFAULT_OPTION);
						return;
					

				} catch (Exception e1) {
					// TODO �ڵ� ������ catch ���
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}





			}

		});




		//���̵� ��й�ȣ ã�� ��ư�� ��������

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

	private JButton idSearchBtn = new JButton("���̵� ã��");
	private JButton pwSearchBtn = new JButton("��й�ȣ ã��");

	public ID_PWSearch(Member m)
	{

		setContentPane(ID_PWSearchPanel);

		ID_PWSearchPanel.add(idSearchBtn);
		ID_PWSearchPanel.add(pwSearchBtn);

		//���� ������ â ��� ���� setSize�� ���� ���־�� ���������� �������� ��� ������ ��!

		setSize(350,150);

		this.setLocationRelativeTo(null); //������ â�� ȭ�� ���߾ȿ� ��� 
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
	private JLabel idLabel = new JLabel("���̵�");
	private JTextField idText = new JTextField();

	private JLabel pwLabel = new JLabel("��й�ȣ");
	private JTextField pwText = new JTextField();

	private JLabel nameLabel = new JLabel("�̸�");
	private JTextField nameText = new JTextField();

	private JLabel p_nLabel = new JLabel("�޴���");
	private JTextField p_nText = new JTextField();

	private JLabel EmailLabel = new JLabel("�̸���");
	private JTextField EmailText = new JTextField();

	private JButton joinbtn = new JButton("Ȯ��");
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

		//�� ��� ����
		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);
		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);

		//���� ������ â ��� ���� setSize�� ���� ���־�� ���������� �������� ��� ������ ��!
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
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(id.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(pw.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}

				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "�޴��� ��ȣ�� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �ּҸ� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
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
								JOptionPane.showMessageDialog(null, "���̵� �ߺ�!!", "", JOptionPane.DEFAULT_OPTION);
								return;
							}
							if(name.equals(m.getFname())&&p_n.equals(m.getFp_n())) {
								JOptionPane.showMessageDialog(null, "�̹� ���Ե� ȸ���Դϴ�!!", "", JOptionPane.DEFAULT_OPTION);
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

					JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!!", "", JOptionPane.DEFAULT_OPTION);
					dispose();
					return;
				}catch (Exception e1) {
					// TODO �ڵ� ������ catch ���
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
	private JButton SearchBtn = new JButton("���̵� ã��");
	private JLabel nameLabel = new JLabel("�̸�");
	private JTextField nameText = new JTextField();
	private JLabel p_nLabel = new JLabel("�޴���");
	private JTextField p_nText = new JTextField();
	private JLabel EmailLabel = new JLabel("�̸���");
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


		//�� ��� ����

		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);

		//���� ������ â ��� ���� setSize�� ���� ���־�� ���������� �������� ��� ������ ��!

		setSize(350,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		SearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String name = nameText.getText().trim();

				String p_n = p_nText.getText().trim();

				String email = EmailText.getText().trim();

				if(name.length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "�޴��� ��ȣ�� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �ּҸ� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
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
							JOptionPane.showMessageDialog(null,"���̵�: "+ s[1], "", JOptionPane.DEFAULT_OPTION);
							dispose();
							return;
						}

					}


					JOptionPane.showMessageDialog(null, "ȸ�������� �������� �ʽ��ϴ�!!", "", JOptionPane.DEFAULT_OPTION);
					return;

				} catch (Exception e1) {
					// TODO �ڵ� ������ catch ���
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
	private JLabel idLabel = new JLabel("���̵�");
	private JTextField idText = new JTextField();
	private JButton SearchBtn = new JButton("��й�ȣ ã��");
	private JLabel nameLabel = new JLabel("�̸�");
	private JTextField nameText = new JTextField();
	private JLabel p_nLabel = new JLabel("�޴���");
	private JTextField p_nText = new JTextField();
	private JLabel EmailLabel = new JLabel("�̸���");
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


		//�� ��� ����
		idLabel.setHorizontalAlignment(NORMAL);
		nameLabel.setHorizontalAlignment(NORMAL);
		p_nLabel.setHorizontalAlignment(NORMAL);
		EmailLabel.setHorizontalAlignment(NORMAL);



		//���� ������ â ��� ���� setSize�� ���� ���־�� ���������� �������� ��� ������ ��!

		setSize(350,150);

		this.setLocationRelativeTo(null); //����� �������� ȭ�� ���߾ӿ� ������ ��


		this.setVisible(true);




		//���̵� ��� Ȯ�� �׽�Ʈ �ڵ�~



		SearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id = idText.getText().trim();

				String name = nameText.getText().trim();

				String p_n = p_nText.getText().trim();

				String email = EmailText.getText().trim();


				if(id.length() == 0){
					JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(name.length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(p_n.length() == 0) {
					JOptionPane.showMessageDialog(null, "�޴��� ��ȣ�� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				else if(email.length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �ּҸ� �Է��� �ּ���.", "", JOptionPane.DEFAULT_OPTION);
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
							JOptionPane.showMessageDialog(null,"��й�ȣ: "+ s[2], "", JOptionPane.DEFAULT_OPTION);
							dispose();
							return;
						}
					}

					JOptionPane.showMessageDialog(null, "ȸ�������� �������� �ʽ��ϴ�!!", "", JOptionPane.DEFAULT_OPTION);
					return;
				}
				catch (Exception e1) {
					// TODO �ڵ� ������ catch ���
					JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
				}

			}
		});



	}
}