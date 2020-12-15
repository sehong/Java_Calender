
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;

//import com.ibm.icu.util.ChineseCalendar;


///*
//import org.apache.poi.hssf.usermodel.HSSFCell; 
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// */
interface Money_Book{  
    public void File_W_R();
}




class Full extends JFrame{
	String today_s = "날짜를 클릭해 주세요.";

	private static String converLunarToSolar(String date){//음력파일의 값들을 받아와 양력날짜로 변환하는 함수
		//ChineseCalendar cc = new ChineseCalendar();
		Calendar cal = Calendar.getInstance();
		
		//cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0,4))+2637);//년도 양력으로 변환
		//cc.set(ChineseCalendar.MONTH, Integer.parseInt(date.substring(4,6))-1);//월 양력으로 변환
		//cc.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));//일 양력으로 변환
		
		//cal.setTimeInMillis(cc.getTimeInMillis());//get으로 저장된 값을 저장
		int y = cal.get(Calendar.YEAR);//저장한 년 값을 y에 저장
		int m = cal.get(Calendar.MONTH)+1;//저장한 년 값을 m에 저장
		int d = cal.get(Calendar.DAY_OF_MONTH);//저장한 년 값을 d에 저장
		
		StringBuffer sb = new StringBuffer();//데이터 타임을 개수를 맞추기위해 쓰이는 함수
		if( y< 1000) sb.append("0");//만약 999일 떄 앞에 0을 붙여 데이터타입의 개수를 맞춤
		else if ( y<100) sb.append("00");//100미만일 떄 앞에 00을 붙여 데이터타입의 개수를 맞춤
		else if ( y<10 ) sb.append("000");//10 미만 일 때 앞에 000을 붙여 데이터타입의 개수를 맞춤
		sb.append(y); //저장한 값들을 저장
		
		if(m<10) sb.append("0");//월도 10미만 일 때 0을 붙혀 데이터타입 맞춰준다
		sb.append(m);
		
		if(d<10) sb.append("0");//일을 10미만 일 때 0을 붙혀 데이터타입을 맞춰준다
		sb.append(d);
		
		
		return sb.toString();//스트링형태로 변환하여 리턴
		
	}
   class MakeCalendar extends FileMemo implements Money_Book {
	   
       Importation i_p = new Importation();
       expense e_p = new expense();
       Calendar cal = Calendar.getInstance(); //Calendar 객체


       int year = cal.get(Calendar.YEAR); //년도 받아오기
       int month = cal.get(Calendar.MONTH)+1; //달 받아오기 (0월 부터 11월로 설정돼 있으므로 +1을 해줌)
       int day = cal.get(Calendar.DAY_OF_MONTH); //일 받아오기
  
       final String dayOfTheWeek[] = { "일", "월", "화", "수", "목", "금", "토" }; //요일 배열

       JPanel topButton; //달력이동 버튼과 현재 달/년이 표시되는 패널
       JPanel weekLabel; //달력 요일이 있는 패널
       JPanel dayButton; //달력 날짜가 있는 패널

       JTextField today = new JTextField();
       JTextField today1 = new JTextField();
       JLabel dates = new JLabel("날      짜");
       JButton days;

       JPanel newP =new JPanel();
       JPanel datetoday = new JPanel(null);

       JLabel Empty= new JLabel("    "); 
       JLabel Empty1= new JLabel("    "); 
       Date al = new Date();

       JLabel now = new JLabel(); //현재 달과 년을 표시하는 라벨

       JPanel top = new JPanel(new BorderLayout()); //topButton패널과 weekLabel패널을 BorderLayout으로 top패널에 배치

       JPanel calendar = new JPanel(new BorderLayout());

       JButton date = new JButton();
       public MakeCalendar() {

          newP.add(dates);
          newP.add(Empty);
          newP.add(today);


          today.setPreferredSize(new Dimension(120,20));
          today.setEnabled(false);
          today1.setPreferredSize(new Dimension(120,20));
          today1.setEnabled(false);

          //datetoday.add(newP,"North");


          topButton = new JPanel();

          JButton prevYearBut=(new JButton("◀◀")); //1년 전으로 가는 버튼

          JButton prevMonBut=(new JButton("◀")); //1달 전으로 가는 버튼

          topButton.add(prevYearBut);

          topButton.add(prevMonBut);


          now=(new JLabel(month+"/"+year)); //현재 달/년이 표시되는 라벨
          now.setFont(new Font("Dialog",Font.BOLD,12)); //now라벨 폰트설정

          topButton.add(now); 

          JButton nextMonBut=(new JButton("▶")); //1달 후로 가는 버튼

          JButton nextYearBut=(new JButton("▶▶")); //1년 후로 가는 버튼

          topButton.add(nextMonBut); 

          topButton.add(nextYearBut);

          topButton.setLayout(new GridLayout(1,7,11,11)); //달력이동 버튼과 현재 달/년이 표시되는 패널을 

          top.setLayout(new GridLayout(2,1));
          
          top.add(topButton);

          prevYearBut.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e) {

                moveMonth(-12);
                now.setText(month + "/" + year);
             }

          }); //1년 전으로 가는 버튼에 액션 추가


          prevMonBut.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e) {

                moveMonth(-1);
                now.setText(month + "/" + year);
             }

          }); //1달 전으로 가는 버튼에 액션 추가

          nextYearBut.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e) {

                moveMonth(12);
                now.setText(month + "/" + year);
             }

          }); //1년 후로 가는 버튼에 액션 추가

          nextMonBut.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e) {

                moveMonth(1);
                now.setText(month + "/" + year);
             }

          }); //1달 후로 가는 버튼에 액션 추가


          weekLabel = new JPanel(new GridLayout());

          for(int i=0; i<dayOfTheWeek.length; i++){

             JButton week = new JButton(dayOfTheWeek[i]); //요일이 저장된 배열을 버튼에 추가

             if(i == 0){

                week.setForeground(Color.red);

             } //일요일이면 글자를 빨간색으로 변경
             else if(i == 6){

                week.setForeground(Color.blue);
             } ////토요일이면 글자를 파란색으로 변경

             week.setFont(new Font("Dialog",Font.PLAIN,10)); 
             week.setSize(30,30); 
             weekLabel.add(week);
          }

          top.add(weekLabel);
          top.setPreferredSize(new Dimension(30, 30));

          dayButton = new JPanel(new GridLayout(0, 7));

          dayPrint(cal);
          dayButton.setPreferredSize(new Dimension(3, 3));


          calendar.add(dayButton, BorderLayout.CENTER);

          calendar.add(top,BorderLayout.NORTH);


       }

       public void dayPrint(Calendar cal){

    	   String day[] = {"0101","0301","0505","0606","0815","1003","1009","1225"};//양력공휴일
     	  String day_change[] = {"1230","0101","0102","0408","0814","0815","0816"}; //음력 공휴일
           String day_name[] = {"신정","삼일절","어린이날","현충일","광복절","개천절","한글날","성탄절"};
           String day_change_name[] = {" ","설날"," ","석가탄신일"," ","추석"," "};
           
    
     	  cal.set(year, month-1, 1);   //출력할 첫날의 객체 만든다
           int week = cal.get(Calendar.DAY_OF_WEEK);   //일요일=1 .... 토요일=7 
           int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);   //그 달의 마지막 날

           for(int i=1; i<week; i++){   
              dayButton.add(new JLabel(" ")); //날짜 출력 전까지의 공백 출력
           }

           for(int i=1; i<=lastDate; i++){

              date = new JButton(String.valueOf(i)); //그 달의 마지막 날까지 날짜를 date버튼에 추가


              cal.set(year, month-1, i); //출력할 달의 객체 생성 

              int outweek = cal.get(Calendar.DAY_OF_WEEK); //일요일=1 .... 토요일=7

              if(outweek==1){
                 date.setForeground(Color.red);            
              } //일요일이면 일요일 날짜 글자색을 빨간색으로

              else if(outweek==7){
                 date.setForeground(Color.BLUE);
              } //토요일이면 일요일 날짜 글자색을 파란색으로
              for(int j =0; j < day.length; j++)
              {
             	 String temp_month = day[j].substring(0,2); //달 출력
             	 String temp_day = day[j].substring(2,4);//일 출력
             	 if(month == Integer.parseInt(temp_month) && i == Integer.parseInt(temp_day))
             	 { //월 ,일이랑 비교하여 같으면 버튼 빨간색으로 변환

                      JLabel date_Name = new JLabel(); //공휴일 이름을 라벨에 저장
                      date_Name.setFont(new Font("Dialog", Font.PLAIN, 8)); //공휴일 이름 글꼴,폰트,글자크기설정
                      
             		 date.setForeground(Color.RED);
             		 date_Name.setText(day_name[j]);//공휴일 이름을 버튼에 저장
             		 date_Name.setForeground(Color.RED);
              		
             		 date.add(date_Name);//버튼 밑에 이름뜨게하기
             	 }
              }
              
              for(int k=0; k<day_change.length; k++){
             	 String temp_Date ;
             	 String Change_Date ;
             	 if(k==0){
                 	 temp_Date = Integer.toString(year-1) + day_change[k];//첫번 째 12월 31일은 2018년기준으로 년,월,일 합친 값 저장
                 	 Change_Date = converLunarToSolar(temp_Date);
                 	 
             	 } else  {//첫번 째 주소값 말고 나머지는 년도랑 붙여 temp_Date에 저장
             		 temp_Date = Integer.toString(year) + day_change[k];
                 	 Change_Date = converLunarToSolar(temp_Date);
                 	 	 
             	 }
             	 
               	 String temp_month = Change_Date.substring(4,6);//월 만 서브스트링으로 잘라 저장 
             	 String temp_day = Change_Date.substring(6);//일만 서브스트링으로 잘라 저장
             	 
             	 if(month == Integer.parseInt(temp_month) && i == Integer.parseInt(temp_day))
             	 {//월이랑 일이랑 비교하여 버튼 색 변경

                      JLabel date_Name = new JLabel();
                      
                      date_Name.setFont(new Font("Dialog", Font.PLAIN,9));//공휴일 이름 글꼴,폰트,글자크기설정
                     
             		 date.setForeground(Color.RED);
             		 date_Name.setText(day_change_name[k]);//공휴일 이름을 버튼에 저장
             		 date_Name.setForeground(Color.RED);
             		 
             		 date.add(date_Name);//캘린더에 뜨기하기
             		 
             	 }
              }
              
              
              date.setSize(300,300);
              date.setVerticalAlignment(SwingConstants.TOP); //날짜 버튼의 텍스트를 버튼 위에 배치
              date.setHorizontalAlignment(SwingConstants.LEFT); //날짜 버튼의 텍스트를 버튼 왼쪽에 배치
              date.addActionListener(al); //버튼에 액션 추가
              dayButton.add(date);
              
              File_W_R();//저장된 일정 파일 읽어오기
          }
       }
       @Override
       public void File_W_R() {//파일 받아오는것 재정의
    	   try {
               super.Fileread(super.getFilePath1());// Fileread 함수를 사용 하여 일정 파일 받아옴
               String []fmemo = super.getFmemo();//  끊어서 문자를 읽는것을 가져옴
               
               for(int j = 0; j<fmemo.length-1; j++) //저장된 일정 수 만큼 포문 실행 여기서 -1은 마지막 줄은 마지막 파일을 입력하면서 개행문이 들어가 빈값이기 때문에 빼고 실행한다.
               {
            	 
                  String []fdate = fmemo[j].split("\t"); //일정마다 탭으로 구분
                  String []fdate1 = fdate[0].split("/");//0번째 원소는 year/month/day로 날짜를 "/"로 구분 되어져있기때문에 년,월,일을 받아오기위해 "/"로 구분
                  if(fdate1[2].equals(date.getText().trim()) && fdate1[1].equals(month+"") &&fdate1[0].equals(year+"")) //저장된 일정의 년,월,일과 현재 생성중인 버튼 날짜들이 일치하면
                  {
                     date.setBackground(Color.RED); //일정이 존재하므로 백그라운드 색상을 레드로 설정
                  }
               }

            }
            catch(ArrayIndexOutOfBoundsException e)
            {
               System.out.printf("일정이 저장된 날짜의 버튼 색 변경 실패 ");//파일 열기에 실패했을 때
            }
       }
       public void moveMonth(int mon){ 

          month += mon;

          if(month>11)  

             while(month>12){

                year++;

                month -= 12;

             } //달이 11보다 커지면 그동안은 년을 1증가, 달은 -12

          else if (month<1) 

             while(month<1){

                year--;

                month += 12;

             } //달이 11보다 크지 않고 1보다 작은 동안은 년을 1감소, 달은 +12

          dayButton.setVisible(false);   

          dayButton.removeAll(); //달력 요일을 지우고,

          dayPrint(cal); //달력을 다시 출력

          dayButton.setVisible(true);

       }//달과 년을 전으로 가거나 후로 가서 달력을 출력하는 함수
      class Date implements ActionListener{
         public void actionPerformed(ActionEvent e) {
            days = (JButton)e.getSource(); //day 일자 버튼클릭시  그 버튼 일자 소스를 받아옴 
            String c_day = days.getText(); //일자 버튼의 일자 텍스트 값을 저장 
            //days.setBackground(Color.white);
            today_s = year+"/"+month+"/"+c_day;
            today.setText(today_s);
            today1.setText(today_s);
            DefaultTableModel model =(DefaultTableModel)i_p.table.getModel();
            DefaultTableModel model1 =(DefaultTableModel)e_p.table.getModel();
            model.setNumRows(0);
            model1.setNumRows(0);
            i_p.File_W_R();
            e_p.File_W_R();
         }
      }
   }
   class Dday implements Money_Book{
      Calendar cal = Calendar.getInstance();

      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH)+1;
      int day = cal.get(Calendar.DAY_OF_MONTH);
      int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 



      int dayday;

      JPanel dday = new JPanel(null);
      JLabel today = new JLabel(year +"/"+month+"/"+day);

      String[] menu = {"D-day","날짜","일정","시간","내용" };  
      DefaultTableModel model = new DefaultTableModel(menu,0);

      JTable table = new JTable(model);
      JScrollPane sp = new JScrollPane(table);
      JButton save = new JButton("수정");
      
      FileMemo file_M = new FileMemo();
      
      public Dday() {
         table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         table.setRowSorter(new TableRowSorter(model));
         table.getColumn("D-day").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("내용").setPreferredWidth(500);
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

         dday.add(today);
         today.setBounds(30,20,200,20);
         today.setForeground(Color.DARK_GRAY);
         today.setFont(new Font("Dialog",Font.BOLD,15));
         //btn[0].setFont(new Font("btn",Font.PLAIN,10));
         dday.add(save);
         save.setBounds(340,10,100,30);
         dday.add(sp);
         sp.setBounds(20,50,420,300);
         save.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent bh) {

                try{
                   FileWriter file = new FileWriter(file_M.getFilePath1(),false);


                   String []s = new String[4];

                   for(int i = 0;  i<table.getRowCount(); i++)
                   {
                      for(int j = 0; j<table.getColumnCount()-1; j++)
                      {      
                         s[j] = (String)(table.getValueAt(i,j+1));
                         s[j] = s[j].trim();
                      }
                      file.write(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+s[3]+"\n");
                   }
                   file.close();
                }
                catch(IOException e)
                {
                   System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
                }
             }
          });
         File_W_R();


         //table.setPreferredScrollableViewportSize(new Dimension(200,100));
      }
      public int getDDay(int _year,int _month,int _day)
      {
         try {
            Calendar today = Calendar.getInstance();
            Calendar d_day = Calendar.getInstance();

            d_day.set(_year,_month-1,_day);

            long I_dday = d_day.getTimeInMillis()/(24*60*60*1000);
            long I_today = today.getTimeInMillis()/(24*60*60*1000);

            long substract = I_today - I_dday;
            return (int)substract;
         }
         catch(Exception e) {
            return -1;
         }
      }
      @Override
      public void File_W_R()//파일 받아오는것 재정의
      {
         try
         {   
        	 file_M.Fileread(file_M.getFilePath1());
             String []fmemo = file_M.getFmemo();
             String []d = new String[5];
            
            for(int i =0; i<fmemo.length-1; i++)
            {
               String []word = fmemo[i].split("\t"); 


               String []date = word[0].split("/");


               int I_dday = getDDay(Integer.parseInt(date[0].trim()),Integer.parseInt(date[1].trim()),Integer.parseInt(date[2].trim()));
               d[0] = "D"+ I_dday;
               d[1] = word[0];
               d[2] = word[1];
               d[3] = word[2];
               d[4] = word[3];
               if(I_dday < 0)
               {
                  model.addRow(d);
               }
               else if(I_dday == 0)
               {
                  d[0] = "D-0";
                  model.addRow(d);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다. - 일정관리 파일");
         }  
      }
   }
   class Report implements Money_Book {
	      JPanel mainPan = new JPanel();   //다 합친 패널
	      JPanel br = new JPanel();//맨위 라벨, 버튼 패널
	      //JPanel br1 = new JPanel();
	      JPanel tbl = new JPanel(); //테이블 패널
	      JLabel gn = new JLabel("■  가계부 내역        ");
	      JButton f = new JButton("새로고침"); //새로고침 버튼
	      JButton delete = new JButton("삭제"); //삭제 버튼
	      JButton deleteAll = new JButton("전체삭제"); //전체삭제 버튼
	      JButton excel = new JButton("엑셀"); //엑셀에 저장하는 버튼
	      JButton save = new JButton("저장");  //수정내역을 저장하는 버튼
	      String[] hangmok = {"항목별","교통","식비","주거비","의류","기타","저축"};      
	      JComboBox<String> hang = new JComboBox<String>(hangmok); //항목별 콤보박스 추가
	      
	      FileMemo file_M = new FileMemo();
	      
	      String[] gagyenaeyeok = {"No","날짜","항목","적요","결제 수단","구분", "금액" };  
	      DefaultTableModel model = new DefaultTableModel(gagyenaeyeok,0);

	      JTable table = new JTable(model); //테이블생성
	      JScrollPane sp = new JScrollPane(table); //스크롤팬에 테이블 넣기

	      public Report() {
	         table.setRowSorter(new TableRowSorter(model));  //JTable 정렬함수
	         table.getColumn("No").setPreferredWidth(30);  //No 헤더칼럼 크기설정
	         table.getColumn("날짜").setPreferredWidth(100);  //날짜 헤더칼럼 크기설정

	         try {
	            File text = new File(file_M.getFilePath());//file_M==filememo클래스  

	            File_W_R(); //파일읽기

	            f.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent fe) {
	                  DefaultTableModel model =(DefaultTableModel)table.getModel();
	                  model.setNumRows(0); //테이블 행 0으로 설정
	                  
	                  File_W_R(); //새로읽은 파일 테이블에 출력
	               }
	            }); //새로고침


	            delete.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent bh) {

	                  try{
	                     FileWriter file = new FileWriter(file_M.getFilePath(),false);
	                     String []s = new String[6];
	                     if (table.getSelectedRow() != -1) {
	                        // remove selected row from the model
	                        model.removeRow(table.getSelectedRow()); //선택된 행 삭제
	                        for(int i = 0;  i<table.getRowCount(); i++)
	                        {
	                           for(int j = 0; j<table.getColumnCount()-1; j++)
	                           {                                   
	                                 s[j] = (String)(table.getValueAt(i,j+1));          
	                           }
	                           file.write(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+s[3]+"\t"+s[4]+"\t"+s[5].replaceAll("\\,","" )+"\n");  //파일에 s배열 출력
	                        }
	                     }
	                     file.close(); //파일 닫기
	                  }
	                  catch(IOException e)
	                  {
	                     System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
	                  }
	               }
	            }); //삭제
	            
	            
	            excel.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent bm) {
	            	   file_M.ExcelOut();
	                  /* try {
	                     XSSFWorkbook workbook = new XSSFWorkbook();//WorkBook을 생성
	                     XSSFSheet sheet = workbook.createSheet();//Workbook내에 시트를 생성
	                     XSSFCell cell; //cell 선언
	                     FileReader text = new FileReader(FilePath); //가계부 내역 파일 위치
	                     char[] c = new char[5000];

	                     text.read(c);//텍스트 파일을 읽어 char형 c에 저장
	                     text.close();

	                     String str = new String(c);
	                     String[] a = str.split("\n");  //"\n"으로 구분

	                     String[] menu = {"날짜","항목","적요","결제 수단","구분","금액"}; //가계부 내역 항목 배열
	                     XSSFRow row = sheet.createRow(0); //첫번쨰 행
	                     for(int k=0; k<menu.length; k++) {   
	                     cell = row.createCell(k);
	                     cell.setCellValue(menu[k]);
	                     }//첫번째 행의 각 셀 마다 메뉴 요소 하나씩 넣기

	                     for(int i=0; i<a.length-1; i++) {
	                     row = sheet.createRow(i+1); //두번째 행부터 for문 크기까지 행 생성
	                     String [] w = a[i].split("\t"); //"\n"으로 나눈 문자열을 다시 "\t"로 나누어 저장
	                     for(int j=0; j<w.length; j++) {
	                     cell=row.createCell(j); 
	                     cell.setCellValue(w[j]);//각 셀마다 배열의 요소를 셀에  입력한다.
	                     }
	                     }
	                     FileOutputStream fs = new FileOutputStream("C:\\Users\\JunYoung\\Desktop\\파일\\money.xlsx");//데이터를 저장하기 위한 파일을 생성
	                     workbook.write(fs);//파일을 열어 데이터를 저장
	                     fs.close();
	                     JOptionPane.showMessageDialog(f, "money.xlsx에 저장되었습니다.");
	                     }
	                      catch(Exception e){
	                         JOptionPane.showMessageDialog(f, "저장실패! 기존 파일을 제거 후 다시 시도하십시오.");
	                      }*/
	               }
	            }); //엑셀 내보내기

	            deleteAll.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent e) {
	                  if(text.exists())  //파일이 존재하면 
	                  { 
	                     if(text.delete()) //파일이 존재하고 파일이 삭제되면
	                     { 
	                        System.out.println("파일삭제 성공"); 
	                        DefaultTableModel model =(DefaultTableModel)table.getModel();
	                        model.setNumRows(0);  //파일이 지워지면 테이블의 행 0으로 설정
	                        try{
	   	                     FileWriter file = new FileWriter(file_M.getFilePath(),false);
	   	                     file.write("");
	   	                     file.close(); //파일 닫기
	   	                  }
	   	                  catch(IOException e1)
	   	                  {
	   	                     System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
	   	                  }
	                     }
	                     else
	                     {
	                        System.out.println("파일삭제 실패");
	                     } 
	                  }
	                  else{ System.out.println("파일이 존재하지 않습니다."); 
	                  } //파일이 없을시 

	               }
	            });  //전체삭제
	            
	            
	            save.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent b) {

	                  try{
	                     FileWriter file = new FileWriter(file_M.getFilePath(),false);  //파일쓰기 모드
	                     String []s = new String[6];  //크기가 6인 배열 s선언

	                     for(int i = 0;  i<table.getRowCount(); i++) //테이블 열부터
	                     {
	                        for(int j = 0; j<table.getColumnCount()-1; j++) //테이블 행까지
	                        {                    
	                            s[j] = (String)(table.getValueAt(i,j+1));  
	                        }
	                        file.write(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+s[3]+"\t"+s[4]+"\t"+s[5].replaceAll("\\,","" )+"\n");  //파일에 저장
	                     }
	                     file.close(); //파일 닫기
	                  }

	                  catch(IOException e)
	                  {
	                     System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
	                  }
	               }
	            }); //저장
	            save.setForeground(Color.RED);
	            
	            
	            hang.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent e){
	                  JComboBox<String> cb=(JComboBox<String>)e.getSource();
	                  int index = cb.getSelectedIndex();
	                  if(hangmok[1]==hangmok[index]) {
	                     new traffic();
	                  }
	                  else if(hangmok[2]==hangmok[index]) {
	                     new foodexpenses();
	                  }
	                  else if(hangmok[3]==hangmok[index]) {
	                     new homeexpenses();
	                  }
	                  else if(hangmok[4]==hangmok[index]) {
	                     new clothes();
	                  }
	                  else if(hangmok[5]==hangmok[index]) {
	                     new etc();
	                  }
	                  else if(hangmok[6]==hangmok[index]) {
	                     new saving();
	                  }
	               }                                                                               
	            });

	            br.add(gn);
	            //gn.setBounds(360,30,40,30);

	            br.add(f);
	            //f.setBounds(390,10,40,30);
	            br.add(delete);
	            //delete.setBounds(420,10,40,30);
	            br.add(deleteAll);
	            //deleteAll.setBounds(450,10,40,30);
	            br.add(excel);
	            //excel.setBounds(480,10,40,30);
	            br.add(save);          //라벨, 버튼 있는 패널
	            //save.setBounds(510,10,40,30);
	            br.add(hang);

	            mainPan.add(br);//다합친 패널에 boder로 위치 배치
	            mainPan.add(tbl);  //다합친 패널에 boder로 위치 배치

	         }
	         catch(Exception e)
	         {
	            System.out.print("실패");
	         }

	      }
	      @Override
	      public void File_W_R() {//파일 받아오는것 재정의

	         try
	         {   
	            file_M.Fileread(file_M.getFilePath());
	            String []fmemo = file_M.getFmemo();
	            String []M_b = new String[7];  //가계부 내역 테이블 헤더칼럼 배열
	          

	            int j = 0;
	            for(int i =0; i < fmemo.length-1; i++)
	            {
	               String [] word = fmemo[i].split("\t");   //"\n"으로 분리한 문자열 a를 다시 "\t"로 분리해 word배열에 저장

	               M_b[0] = new String((++j)+""); //No,  줄이 추가될때 마다 No 1씩 증가
	               M_b[1] = word[0]; //날짜
	               M_b[2] = word[1]; //항목
	               M_b[3] = word[2]; //적요
	               M_b[4] = word[3]; //결제 수단
	               M_b[5] = word[4]; //구분(수입,지출)
	               M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//금액
	               model.addRow(M_b); //JTable 행 추가
	          
	            }
	         }
	         catch(ArrayIndexOutOfBoundsException e)
	         {
	            System.out.println("텍스트에 저장된 값이 없습니다");
	         }  //테이블 만드는 함수

	         table.setPreferredScrollableViewportSize(new Dimension(600,270));  //테이블 크기 설정
	         tbl.add(sp);   //테이블 패널에 테이블 추가
	      }
	   }

   class schedule1 extends FileMemo implements Money_Book {
	      JTextField jaymok = new JTextField(23);//제목
	      JTextArea memojang = new JTextArea(15,25);//메모장
	      Calendar cal = Calendar.getInstance();//캘린더 기능
	      JButton but=new JButton("저장");
	     
	      JPanel schedule_Panel = new JPanel(new GridLayout(4,1)); //총 패널 
	      Dday d_d = new Dday();//D-day 객체 생성
	      MakeCalendar m = new MakeCalendar();//MakeCalender 객체 생성

	      JPanel f2;
	      JPanel f3;
	      JPanel f4;

	      HashMap<String,Integer> h1= new HashMap<>();


	      String[] time = {"01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"};

	      JComboBox<String> startcombo = new JComboBox<String>(time);//시작일 시간
	      JComboBox<String> endcombo = new JComboBox<String>(time);//끝나는일 시간
	      public schedule1(String namenight) {
	         makeField(namenight);
	         }
	  	@Override
		public void File_W_R() {
			 File file = new File(super.getFilePath1());
             
             try {
                FileWriter file1 =new FileWriter(file,true);
               

                Set<String> set = h1.keySet();
                Iterator<String> it =set.iterator();

                String Title =jaymok.getText();
                String startTime=startcombo.getSelectedItem().toString();
                String endTime= endcombo.getSelectedItem().toString();
                String meMo=memojang.getText();


                file1.write(today_s+ "\t" +Title+ "\t" + startTime +"~"+endTime+"\t"+ meMo +"\n");
               
                jaymok.setText("");
                memojang.setText("");
                startcombo.setSelectedIndex(0);

                file1.close();
                DefaultTableModel model =(DefaultTableModel)d_d.table.getModel();
                model.setNumRows(0);
                d_d.File_W_R(); 
                m.days.setBackground(Color.RED);
             }
             catch (IOException e1)
             {
                e1.printStackTrace();
             }
		  }
	      void makeField(String namenight) {

	         f2 = new JPanel();//패널2
	         f2.setLayout(new GridLayout(0,1));
	         f2.add(new JLabel("                            일정 제목"));
	         f2.add(jaymok);//제목 패널 추가

	         f2.add(new JLabel("시작 시간"));//시작시간 레이블 추가     
	         f2.add(startcombo);//시작시간 콤보박스 추가
	         f2.add(new JLabel("종료 시간 "));//종료시간 레이블 추가
	         f2.add(endcombo);//종료시간 콤보박스 추가
	         f2.add(new JLabel(" 날짜"));//날짜 레이블 추가
	         f3 = new JPanel(null);//패널3
	         f3.setLayout(new GridLayout(0,1,0,5));//배치

	         f3.add(new JScrollPane(memojang));//메모장
	         f3.setPreferredSize(new Dimension(200,100));
	         f4 = new JPanel();//패널4
	         f4.setLayout(null);
	         f4.add(but=new JButton("저장"));
	         but.setBounds(70,10,70,30);
	         f4.setPreferredSize(new Dimension(100,100));
	         but.addActionListener(new ActionListener() {       
	            @Override
	            public void actionPerformed(ActionEvent e) {

	               if(jaymok.getText().equals("")){
	                  JOptionPane .showMessageDialog(null,"일정 제목을 입력하세요","오류",JOptionPane.PLAIN_MESSAGE);
	                  return;
	               }
	               else if(memojang.getText().equals("")) {
	                  JOptionPane.showMessageDialog(null,"메모를 입력해주세요","오류",JOptionPane.PLAIN_MESSAGE);
	                  return;
	               }
	               else if(today_s.equals("날짜를 클릭해 주세요")) {
	                  JOptionPane.showMessageDialog(null,"날짜를 지정해주세요","오류",JOptionPane.PLAIN_MESSAGE);
	                  return;
	               }
	               File_W_R();
	              
	               JOptionPane.showMessageDialog(null,"저장완료","일정메모",JOptionPane.PLAIN_MESSAGE);

	            }

	            boolean isDigit(String text) {
	               // TODO Auto-generated method stub
	               return false;
	            }   });

	         schedule_Panel.add(f2,BorderLayout.NORTH);
	         schedule_Panel.add(f3,BorderLayout.CENTER);
	         schedule_Panel.add(f4,BorderLayout.PAGE_END);   

	         schedule_Panel.setPreferredSize(new Dimension(280,640));
	      }

	}
   class expense extends FileMemo implements Money_Book//지출 관리 테이블
   {      
      JPanel panel = new JPanel();
      String[] menu = {"No","항목","적요","결제수단","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0); //DefaultTable에 menu배열의 정보를 저장
      JTable table =  new JTable(model); //테이블에 디폴트테이블 객체를 저장
      public expense()       
      {
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//자동으로 새로운 값이 들어올 시  테이블사이즈를 조정
         table.getColumn("No").setPreferredWidth(30);//"No"크기 30으로 조절
         table.getColumn("항목").setPreferredWidth(50);//"항목"크기 50으로 조절
         table.getColumn("적요").setPreferredWidth(80);//"적요"크기 80으로 조절
         table.getColumn("결제수단").setPreferredWidth(60);//"결제수단"크기 60으로 조절
         table.getColumn("금액").setPreferredWidth(80);//"금액"크기 80으로 조절
         JScrollPane scr = new JScrollPane(table);//JScrollPane에 table의 내용을 저장
         table.setPreferredScrollableViewportSize(new Dimension(280,95));//table의 가로 세로 길이를 280,95으로 지정
         panel.add(scr); //패널에 JScrollPane의 정보를 저장
      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의
         try
         {   
        	super.Fileread(super.getFilePath());
        	String []fmemo = super.getFmemo();
            String []M_b = new String[5];
            
            int j = 0;
            for(int i =0; i < fmemo.length-1; i++) //배열의 주소가 끝일 때까지 돌아갈 떄
            {
               String [] word = fmemo[i].split("\t"); // 끝이면 다음 첫번 째 줄에 텍스트파일의 내용이 저장
               if(today_s.equals(word[0]))
               {
                  if(word[4].trim().equals("지출"))//테이블위에 지출 레이블 뜨기
                  {                               //차례대로 정보가 저장
                     M_b[0] = new String((++j)+""); //No.
                     M_b[1] = word[1];//항목
                     M_b[2] = word[2];//적요
                     M_b[3] = word[3];//결제수단
                     M_b[4] = String.format("%,d",Integer.parseInt(word[5]));//금액
                     model.addRow(M_b);
                  }
               }
            }


         }
         catch(ArrayIndexOutOfBoundsException e)//텍스트파일의 정보가 없을 시 예외처리
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  
      }
   }
   class Importation extends FileMemo implements Money_Book
   {
      JPanel panel = new JPanel();
      String[] menu = {"No","항목","적요","결제수단","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);//DefaultTable에 menu배열의 정보를 저장   
      JTable table =  new JTable(model);//테이블에 디폴트테이블 객체를 저장

      public Importation()       
      {

         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//자동으로 새로운 값이 들어올 시  테이블사이즈를 조정
         table.getColumn("No").setPreferredWidth(30);//"No"크기 30으로 조절
         table.getColumn("항목").setPreferredWidth(50);//"항목"크기 50으로 조절
         table.getColumn("적요").setPreferredWidth(80);//"적요"크기 80으로 조절
         table.getColumn("결제수단").setPreferredWidth(60);//"결제수단"크기 60으로 조절
         table.getColumn("금액").setPreferredWidth(80);//"금액"크기 80으로 조절
         JScrollPane scr = new JScrollPane(table);//JScrollPane에 table의 내용을 저장
         table.setPreferredScrollableViewportSize(new Dimension(280,95));//table의 가로 세로 길이를 280,95으로 지정
         panel.add(scr); //패널에 JScrollPane의 정보를 저장
      }
      @Override
      public void File_W_R()//파일 받아오는것 재정의
      {
         try
         {   
            super.Fileread(super.getFilePath());
            String []fmemo = super.getFmemo();
            String []M_b = new String[5];
          
            int j= 0;
            for(int i =0; i < fmemo.length-1; i++)//배열의 주소의 끝앞에까지 배열지속
            {
               String [] word = fmemo[i].split("\t"); //주소가 끝일 떄 다음 첫번 째 줄에 텍스트파일의 내용이 저장
               if(today_s.equals(word[0]))
               {
                  if(word[4].trim().equals("수입"))//수입의 공백 문자열 생성
                  {
                     M_b[0] = new String((++j)+""); //No.
                     M_b[1] = word[1];//항목
                     M_b[2] = word[2];//적요
                     M_b[3] = word[3];//결제수단
                     M_b[4] = String.format("%,d",Integer.parseInt(word[5]));//금액
                     model.addRow(M_b);
                  }
               }
            }

         }
         catch(ArrayIndexOutOfBoundsException e) //만약 텍스트파일의 정보가 없을 시 예외처리
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }
      }
   }

  
     class total implements Money_Book
   	{
		HashMap<String, Integer> hm = new HashMap<>();
		String []s;
		int[] data = {0,0,0,0,0,0}; // 차트의 값 저장배열
		double[] arcAngle = new double[6]; 
		String[] re1;
		CircleChart circle = new CircleChart();

	
		int sumint;  //정수형으로 지출의 총합

		FileMemo file_M = new FileMemo();

		
		Color[] color = {Color.RED, Color.BLUE, // 색상 
				Color.MAGENTA, Color.ORANGE,Color.pink,Color.GREEN};

		String[] itemName = { "교통비","식비", "주거비", "의류","기타","저축"};


		JTextField[] tf  = new JTextField[6]; // 텍스트필드
		ChartPanel chartPanel = new ChartPanel(); // 차트패널
		

		//////
	
		JFrame t2 = new JFrame();

		String year;
		String month;
		String weeks;
		String nal;//날자
		String name; //지출 또는 수입
		String what;//항목 
		String imformation; //적요 
		String money;//금액
		String how;//결제수단

		String nightname;   
		String jang;
		String jang1;

		private String [] whatmonth= {"달","1","2","3","4","5","6","7","8","9","10","11","12"};
		private String [] whatyear= {"년","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
		JComboBox<String> combo;//달
		JComboBox<String> combo1;//년
		JPanel panel = new JPanel(null);

		String[] re;

		String check1;
		String check,check2;

		JLabel cheers,cheers1,cheers2,cheers3,cheers4,cheers5,cheers6;
		JTextField cheer,cheer1,cheer2,cheer3,cheer4,cheer5,cheer6;

		public total()
		{   

			//con.setLayout(new FlowLayout());

			combo1=new JComboBox<String>(whatyear);
			panel.add(combo1).setBounds(520,30,90,30);

			combo=new JComboBox<String>(whatmonth);
			panel.add(combo).setBounds(620,30,80,30);




			cheers6=new JLabel("지출 총 합계");
			panel.add(cheers6).setBounds(530,65,70,20);

			cheer6=new JTextField(10);



			panel.add(cheer6).setBounds(620, 65, 70, 20);
			cheers=new JLabel("교통 합계");
			panel.add(cheers).setBounds(530,100,70,20);
			cheer=new JTextField(10);
			panel.add(cheer).setBounds(620, 100, 70, 20);

			cheers1=new JLabel("식비 합계");
			panel.add(cheers1).setBounds(530,135,70,20);
			cheer1=new JTextField(10);
			panel.add(cheer1).setBounds(620, 135, 70, 20);
			cheers2=new JLabel("주거비합계");
			panel.add(cheers2).setBounds(530,170,70,20);
			cheer2=new JTextField(10);
			panel.add(cheer2).setBounds(620, 170, 70, 20);
			cheers3=new JLabel("의류 합계");
			panel.add(cheers3).setBounds(530,205,70,20);
			cheer3=new JTextField(10);
			panel.add(cheer3).setBounds(620, 205, 70, 20);

			cheers4=new JLabel("기타 합계");
			panel.add(cheers4).setBounds(530,240,70,20);
			cheer4=new JTextField(10);
			panel.add(cheer4).setBounds(620, 240, 70, 20);
			cheers5=new JLabel("저축 합계");
			panel.add(cheers5).setBounds(530,275,70,20);
			cheer5=new JTextField(10);
			panel.add(cheer5).setBounds(620, 275, 70, 20);

			t2.add(panel);

			cheer.setEditable(false);
			cheer1.setEditable(false);
			cheer2.setEditable(false);
			cheer3.setEditable(false);
			cheer4.setEditable(false);
			cheer5.setEditable(false);
			cheer6.setEditable(false);

			try 
			{   


				file_M.Fileread(file_M.getFilePath());


				combo.addActionListener(new ActionListener() { //월

					public void actionPerformed(ActionEvent e) {

						


						jang = combo.getSelectedItem().toString();
						jang1 = combo1.getSelectedItem().toString();//year



						re= new String[10]; 
						String []word =  file_M.getFmemo();
						for(int j =0 ;j<word.length-1; j++)
						{
							s = word[j].split("\t");
							nightname=s[0];      
							what=s[1];
							imformation=s[2];
							how=s[3];
							name=s[4];
							money=s[5]; 

							String date[]=s[0].split("/");
							for(int i=0;i<date.length;i++)
							{
								re[i]=date[i];
							}

							//re[0] 년도    
							//re[1] 달
							//re[2]   일


							for(int qwer=2015;qwer<=2023;qwer++)
							{
								check1=Integer.toString(qwer);  
								if(re[0].equals(check1) && jang1.equals(check1)&& s[4].equals("지출"))
								{

									for(int i=1;i<=12;i++)
									{
										check=Integer.toString(i);
										if(re[1].equals(check) && jang.equals(check)&& s[4].equals("지출"))
										{

											if(name.equals("지출"))   //지출 도는 수입 게산
											{
												int sum= Integer.parseInt(money);
												circle.setTotal(circle.getTotal()+sum); //수출
											}
											else if(name.equals("수입"))
											{
												int sum= Integer.parseInt(money);
												circle.setTotal1(circle.getTotal1()+sum);
											}

											if(what.equals("교통"))   //항목 게산
											{
												int sum= Integer.parseInt(money);
												circle.setTraffic_total(circle.getTraffic_total()+sum);
											}
											else if(what.equals("식비"))
											{
												int sum= Integer.parseInt(money);
												circle.setEat_total(circle.getEat_total()+sum);
											}
											else if(what.equals("주거비"))
											{
												int sum= Integer.parseInt(money);
												circle.setHouse_total(circle.getHouse_total()+sum);
											}

											else if(what.equals("의류"))
											{
												int sum= Integer.parseInt(money);
												circle.setDress_total(circle.getDress_total()+sum);
											}
											else if(what.equals("기타"))
											{
												int sum= Integer.parseInt(money);
												circle.setOther_total(circle.getOther_total()+sum);
											}

											else if(what.equals("저축"))
											{
												int sum= Integer.parseInt(money);
												circle.setSaving_total(circle.getSaving_total()+sum);
											}

											//카드 또는 현금 총합 비교
											if(how.equals("카드"))
											{
												int sum= Integer.parseInt(money);
												circle.setCard_total(circle.getCard_total()+sum);
											}

											else if(how.equals("현금"))
											{
												int sum= Integer.parseInt(money);
												circle.setMoney_total(circle.getMoney_total()+sum);
											}


										}

									}//달 for문
								}
								else if(jang1.equals("년") )                                    //까지

								{   
									JOptionPane.showMessageDialog(null, "년을 입력하세요", "", JOptionPane.DEFAULT_OPTION); 
									return ;

								}
								else if(jang.equals("달") )                                    //까지

								{   
									JOptionPane.showMessageDialog(null, "달을 입력하세요", "", JOptionPane.DEFAULT_OPTION);

									return ;

								}

							}//년도 for문 닫는거


						}//for문


						circle.setTotal(circle.getTraffic_total()+ circle.getEat_total() + circle.getHouse_total()+ circle.getDress_total()+ circle.getOther_total()+ circle.getSaving_total());
						circle.setTotal_money(String.format("%,d",circle.getTotal()));//지출의 총합
						circle.setTraffic_money(String.format("%,d",circle.getTraffic_total()));//교통의 총합
						circle.setEat_money(String.format("%,d",circle.getEat_total()));//식비의 총합
						circle.setHouse_money(String.format("%,d",circle.getHouse_total()));//주거비의 총합
						circle.setDress_money(String.format("%,d",circle.getDress_total()));//의류의 총합
						circle.setOther_money(String.format("%,d",circle.getOther_total()));//기타의 총합
						circle.setSaving_money(String.format("%,d",circle.getSaving_total()));//저축의 총합

						if(circle.getTotal() == 0)
						{
							JOptionPane.showMessageDialog(null, "내역이 없습니다", "", JOptionPane.DEFAULT_OPTION); 
							for(int i=0;i<arcAngle.length;i++)
							{
								arcAngle[i]=0;
							}
						}
						circle.drawChart(circle,data,arcAngle,chartPanel); // 차트 메소드 호출
						panel.add(chartPanel).setBounds(20,0,500,600);
						cheer6.setText(circle.getTotal_money());
						cheer.setText(circle.getTraffic_money());
						cheer1.setText(circle.getEat_money());
						cheer2.setText(circle.getHouse_money());
						cheer3.setText(circle.getDress_money());
						cheer4.setText(circle.getOther_money());
						cheer5.setText(circle.getSaving_money());

					}});//리스너 닫는부분 

			} // try마지막);

			catch (ArrayIndexOutOfBoundsException e) 
			{
				// TODO 자동 생성된 catch 블록
				JOptionPane.showMessageDialog(null, "Error!!", "", JOptionPane.DEFAULT_OPTION);
			}
			t2.setResizable(false);
			t2.setSize(730,450);
			t2.setVisible(true);
			t2.setLocationRelativeTo(null);
		}

		

		 class CircleChart {
				private String total_money;//지출의 총합
				private String traffic_money;//교통비지출합
				private String eat_money;//식비합
				private String house_money;//주거비합
				private String dress_money;//의류함
				private String other_money;//기타합
				private String saving_money;//저축합
				private String fyear; //텍스파일에서 받아올 년도
				private String fmonth;//텍스트파일에서 받아올 월 
				private int sumint;  //정수형으로 지출의 총합


				private int total;//지출 의총합
				private int total1;//수입의 총합
				private int traffic_total;// 교통비의총합
				private int eat_total;// 식비의 총합
				private int house_total;// 주거비총합
				private int dress_total;//의류총합
				private int other_total;//기타총합
				private int saving_total;//저축의 총합
				private int card_total;//카드의 총합
				private int money_total;//현금의 총합

				public int getTotal() {
					return total;
				}
				public void setTotal(int total) {
					this.total = total;
				}
				public int getTotal1() {
					return total1;
				}
				public void setTotal1(int total1) {
					this.total1 = total1;
				}
				public int getTraffic_total() {
					return traffic_total;
				}
				public void setTraffic_total(int traffic_total) {
					this.traffic_total = traffic_total;
				}
				public int getEat_total() {
					return eat_total;
				}
				public void setEat_total(int eat_total) {
					this.eat_total = eat_total;
				}
				public int getHouse_total() {
					return house_total;
				}
				public void setHouse_total(int house_total) {
					this.house_total = house_total;
				}
				public int getDress_total() {
					return dress_total;
				}
				public void setDress_total(int dress_total) {
					this.dress_total = dress_total;
				}
				public int getOther_total() {
					return other_total;
				}
				public void setOther_total(int other_total) {
					this.other_total = other_total;
				}
				public int getSaving_total() {
					return saving_total;
				}
				public void setSaving_total(int saving_total) {
					this.saving_total = saving_total;
				}
				public int getCard_total() {
					return card_total;
				}
				public void setCard_total(int card_total) {
					this.card_total = card_total;
				}
				public int getMoney_total() {
					return money_total;
				}
				public void setMoney_total(int money_total) {
					this.money_total = money_total;
				}
				public String getTotal_money() {
					return total_money;
				}
				public void setTotal_money(String total_money) {
					this.total_money = total_money;
				}
				public String getTraffic_money() {
					return traffic_money;
				}
				public void setTraffic_money(String traffic_money) {
					this.traffic_money = traffic_money;
				}
				public String getEat_money() {
					return eat_money;
				}
				public void setEat_money(String eat_money) {
					this.eat_money = eat_money;
				}
				public String getHouse_money() {
					return house_money;
				}
				public void setHouse_money(String house_money) {
					this.house_money = house_money;
				}
				public String getDress_money() {
					return dress_money;
				}
				public void setDress_money(String dress_money) {
					this.dress_money = dress_money;
				}
				public String getOther_money() {
					return other_money;
				}
				public void setOther_money(String other_money) {
					this.other_money = other_money;
				}
				public String getSaving_money() {
					return saving_money;
				}
				public void setSaving_money(String saving_money) {
					this.saving_money = saving_money;
				}
				public String getFyear() {
					return fyear;
				}
				public void setFyear(String fyear) {
					this.fyear = fyear;
				}
				public String getFmonth() {
					return fmonth;
				}
				public void setFmonth(String fmonth) {
					this.fmonth = fmonth;
				}
				public int getSumint() {
					return sumint;
				}
				public void setSumint(int sumint) {
					this.sumint = sumint;
				}
				
				void drawChart(CircleChart circle ,int[] data,double[] arcAngle, ChartPanel chartPanel)
				{

					

					// 차트를 그린다
					double sum=0; // 초기값 0
					//for(int i=0;i<data.length;i++)
					// 데이터 값만큼 루프
					data[0]=circle.getTraffic_total();  //교통비
					data[1]=circle.getEat_total();//식비
					data[2]=circle.getHouse_total();//주거비
					data[3]=circle.getDress_total();//의루
					data[4]=circle.getOther_total();//기타
					data[5]=circle.getSaving_total();//저축
					sum=data[0]+data[1]+data[2]+data[3]+data[4]+data[5];


					for(int i=0;i<data.length;i++)
					{ 
						arcAngle[i] = ((double)data[i]/(double)sum*360);
						chartPanel.repaint(); // 차트패널의 PAINT호출
					}
				}
				
				public CircleChart()
				{
					total = 0;//지출 의총합
					total1 = 0;//수입의 총합
					traffic_total = 0;// 교통비의총합
					eat_total = 0;// 식비의 총합
					house_total = 0;// 주거비총합
					dress_total = 0;//의류총합
					other_total = 0;//기타총합
					saving_total = 0;//저축의 총합
					card_total = 0;//카드의 총합
					money_total = 0;//현금의 총합
				}

			}

		class ChartPanel extends JPanel{ // 차트 표시 패널

			public void paintComponent(Graphics g)
			{

				super.paintComponent(g);//부모 패인트호출

				double startAngle = 0;



				for(int i=0;i<data.length;i++)
				{
					g.setColor(color[i]);
					g.fillArc(50,80,200,200,(int)startAngle,(int)arcAngle[i]);
					startAngle = startAngle + arcAngle[i];
				}


				for(int i=0;i<data.length;i++){

					g.setColor(color[i]);       
					if(circle.getTotal()== 0)
					{
						g.drawString(itemName[i]+""+"0%", 4+i*80,350);//이게 %
					}
					else
					{
						g.drawString(itemName[i]+""+(String.format("%.2f",arcAngle[i]*100.0/360.0))+"%", 4+i*80,350);//이게 %
					}
				}


			}
		}

		@Override
		public void File_W_R() {}
	}


  
   class input extends FileMemo implements Money_Book{
      private JButton button = new JButton("입력");
      private JButton button1 = new JButton("취소");
      private String [] hang= {"선택사항","교통","식비","주거비","의류","기타","저축"};
      private JTextField a,b;
      private JRadioButton [] btn = new JRadioButton[4];//2개는 수입 지출 2개는 수단
      private String [] name = {"지출","수입"};  //처음 위부분
      private String [] name2 = {"카드","현금"}; //결제수단 
      HashMap<String, Integer> hm = new HashMap<>();
      JPanel panel=new JPanel();
      String todays;
      JComboBox<String> b1=new JComboBox<String>(hang);
      
      public input(String namenight) {////////요가 시작

         //setContentPane(new color());

         //c.setLayout(null);
         //
         panel.setLayout(null); 
         //저는 모든 크기를 지정해줘서 일단 null해놨습니다.



         ButtonGroup bg = new ButtonGroup();   

         a = new JTextField(15);
         b = new JTextField(15);   

       
         //b1 = new JTextField(15);


         btn[0] = new JRadioButton(name[0]);
         btn[1] = new JRadioButton(name[1]);
         btn[2] = new JRadioButton(name2[0]);
         btn[3] = new JRadioButton(name2[1]);

         ButtonGroup g=new ButtonGroup();
         ButtonGroup g1=new ButtonGroup();

         g1.add(btn[0]);  //그룹만드기
         g1.add(btn[1]);

         g.add(btn[2]);   //그룹만들기
         g.add(btn[3]);

         btn[0].setFont(new Font("btn",Font.PLAIN,10));
         btn[1].setFont(new Font("btn",Font.PLAIN,10));
         btn[2].setFont(new Font("btn",Font.PLAIN,10));
         btn[3].setFont(new Font("btn",Font.PLAIN,10));


         panel.add(new JLabel("구      분")).setBounds(45,75,120,20);



         panel.add(btn[0]).setBounds(120,60,50,50);
         panel.add(btn[1]).setBounds(185,60,50,50);

         panel.add(new JLabel("항      목")).setBounds(45,115,120,20);
         panel.add(b1).setBounds(115,115,120,20);

         panel.add(new JLabel("적      요"+ "\t")).setBounds(45,165,120,20);
         panel.add(b).setBounds(115,165,120,20);

         panel.add(new JLabel("금      액")).setBounds(45,215,120,20);
         panel.add(a).setBounds(115,215,120,20);

         panel.add(new JLabel("결제  수단")).setBounds(45,265,120,20);



         panel.add(btn[2]).setBounds(120,255,50,50);;
         panel.add(btn[3]).setBounds(185,255,50,50);;

         panel.add(button).setBounds(40,330,70,30);//입력

         button.addActionListener(new ActionListener() {   //////////////////////      
            @Override
            public void actionPerformed(ActionEvent e) {
            	
               if(today_s.equals("날짜를 클릭해 주세요."))   
               {
                  JOptionPane.showMessageDialog(null, "날짜를 입력하세요","입력 오류",JOptionPane.PLAIN_MESSAGE);
                  return;
               }
               else if(btn[0].isSelected()==false&btn[1].isSelected()==false)
               {   
                  JOptionPane.showMessageDialog(null, "수입 또는 지출을 선택하세요","입력 오류",JOptionPane.PLAIN_MESSAGE);   
                  return;
               }
               else if(b1.getSelectedItem().toString()=="선택사항")   
               {   
                  JOptionPane.showMessageDialog(null, "항목을 고르세요","입력오류",JOptionPane.PLAIN_MESSAGE);
                  return;
               }
               else if(b.getText().equals(""))   
               {   
                  JOptionPane.showMessageDialog(null, "적요를 입력하세요 ","입력오류",JOptionPane.PLAIN_MESSAGE);
                  return;
               }
               else if(a.getText().equals(""))   
               {   
                  JOptionPane.showMessageDialog(null, "금액을 입력하세요 ","입력오류",JOptionPane.PLAIN_MESSAGE);
                  return;
               }
               else if(btn[2].isSelected()==false&btn[3].isSelected()==false)   
               {
                  JOptionPane.showMessageDialog(null, "결제 수단을 입력하세요","입력 오류",JOptionPane.PLAIN_MESSAGE);
                  return;
               }
               else if(a.getText()!="")
               {
                  for(int i=0;i<a.getText().length();i++)
                  {
                     char c = a.getText().charAt(i);
                     if(c<48 || c> 57)
                     {//숫자가 아닌 경우
                        JOptionPane.showMessageDialog(null, " 금액에는 숫자만 입력하세요 ","입력오류",JOptionPane.PLAIN_MESSAGE);
                        return;
                     }             
                  }
               }
               
            	
               File_W_R();
              
               JOptionPane.showMessageDialog(null, "입력완료","Message",JOptionPane.PLAIN_MESSAGE);         
               
            }

           });



         panel.add(button1).setBounds(160,330,70,30);//취소
         button1.addActionListener(new ActionListener() 
         {
            public void actionPerformed(ActionEvent e)
            {
               today_s = "날짜를 클릭해 주세요.";
               b.setText("");
               a.setText("");
               b1.setSelectedIndex(0);
               //JOptionPane.showMessageDialog(null, "초기화 완료","초기화완료",JOptionPane.PLAIN_MESSAGE);   //있어도 되고 없어도 상관없는데 없는게 깔끔한

            }      
         });



      }
  	@Override
  	public void File_W_R() {//파일 받아오는것 재정의
  		 File file = new File(super.getFilePath());
           try {

              String Name,Name1;
              FileWriter fw = new FileWriter(file,true);                  

              Set<String> set = hm.keySet(); 
              Iterator<String> it = set.iterator();

              String key = b.getText(); // 적요
              String key1 = a.getText(); // 금액 추출
              String com=b1.getSelectedItem().toString();

              if(btn[0].isSelected())
                 Name= "지출";
              else
                 Name="수입";      

              if(btn[3].isSelected())
                 Name1= "현금";
              else
                 Name1= "카드";   

              fw.write(today_s+"\t"+com+"\t"+ key+"\t"+ Name1+"\t" +Name+"\t"+key1+"\n"); // 이름을 파일로 보냄
              b.setText("");
              a.setText("");
              b1.setSelectedIndex(0);
              fw.close();
           } 

           catch (IOException e1)
           {
              e1.printStackTrace();
           }
  	}

	
   }
   class traffic extends FileMemo implements Money_Book{
	   JFrame t1 = new JFrame();
      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);

      public traffic() 
      {
         
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();

         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);

      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("교통");
         try
         {   
            super.Fileread(super.getFilePath());
            String []fmemo = super.getFmemo();
            String []M_b = new String[8];
           
            int j = 0;
            for(int i =0; i < fmemo.length-1; i++)
            {
               String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("교통"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  //M_b[6] = word[6];//잔액
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수

         
      }
   }
   class foodexpenses extends FileMemo implements Money_Book{

      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);
      JFrame t1 = new JFrame();

      public foodexpenses() 
      {

         
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();

         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);

      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("식비");
         try
         {   
        	 super.Fileread(super.getFilePath());
             String []fmemo = super.getFmemo();
             String []M_b = new String[8];          

             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("식비"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수

      }
   }

   class homeexpenses extends FileMemo implements Money_Book {

      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);
      JFrame t1 = new JFrame();

      public homeexpenses() 
      {

        
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();

         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);

      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("주거비");
         try
         {   
        	 super.Fileread(super.getFilePath());
             String []fmemo = super.getFmemo();
             String []M_b = new String[8];
         
             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("주거비"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수

      }
   }

   class clothes extends FileMemo implements Money_Book {

      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);
      JFrame t1 = new JFrame();

      public clothes() 
      {

         
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();

         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);

      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("의류");
         try
         {   
        	 super.Fileread(super.getFilePath());
             String []fmemo = super.getFmemo();
             String []M_b = new String[8];
            
             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("의류"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수


      }
   }
   class etc extends FileMemo implements Money_Book{

	   JFrame t1 = new JFrame();
      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);


      public etc() 
      {
         
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();

         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);

      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("기타");
         try
         {   
        	 super.Fileread(super.getFilePath());
             String []fmemo = super.getFmemo();
             String []M_b = new String[8];
            
             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("기타"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수


      }
   }

   class saving extends FileMemo implements Money_Book {

      String[] menu = {"No","날짜","항목","적요","결제수단","구분","금액"};
      DefaultTableModel model = new DefaultTableModel(menu,0);   
      JTable table =  new JTable(model);
      JFrame t1 = new JFrame();

      public saving() 
      {
         
         table.setRowSorter(new TableRowSorter(model));
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.getColumn("No").setPreferredWidth(50);
         table.getColumn("날짜").setPreferredWidth(100);
         table.getColumn("항목").setPreferredWidth(50);
         table.getColumn("적요").setPreferredWidth(140);
         table.getColumn("결제수단").setPreferredWidth(70);
         table.getColumn("금액").setPreferredWidth(120);

         JScrollPane scr = new JScrollPane(table);
         //table.setPreferredScrollableViewportSize(new Dimension(280,120));
         t1.add(scr);
         File_W_R();
         t1.setSize(580,300);
         t1.setVisible(true);
         t1.setLocationRelativeTo(null);
      }
      @Override
      public void File_W_R() {//파일 받아오는것 재정의

         t1.setTitle("저축");
         try
         {   
        	 super.Fileread(super.getFilePath());
             String []fmemo = super.getFmemo();
             String []M_b = new String[8];
           
             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 


               if(word[1].trim().equals("저축"))
               {
                  M_b[0] = new String((++j)+"");
                  M_b[1] = word[0];//날짜
                  M_b[2] = word[1];//적요
                  M_b[3] = word[2]; //항목
                  M_b[4] = word[3];//결제수단
                  M_b[5] = word[4];//수입 수출
                  M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//잔액
                  model.addRow(M_b);
               }
            }
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            System.out.println("텍스트에 저장된 값이 없습니다");
         }  //테이블 만드는 함수


      }
   }
   ///가계부입력
   class show implements Money_Book
   {
	   JFrame f4 = new JFrame();
       JLabel b;
       JTextField s;
       JPanel mainPan = new JPanel();   //다 합친 패널
       JPanel br = new JPanel();//맨위 라벨, 버튼 패널
       JPanel br1 = new JPanel();
       JPanel tbl = new JPanel(); //테이블 패널
       JLabel gn = new JLabel("■  가계부 내역        ");
       JButton f = new JButton("새로고침");
      
       JButton delete = new JButton("삭제");
       JButton deleteAll = new JButton("전체삭제");
       JButton excel = new JButton("엑셀");
       JButton save = new JButton("저장");
       
       String[] gagyenaeyeok = {"No","날짜","항목","적요","결제 수단","구분", "금액" };  
       DefaultTableModel model = new DefaultTableModel(gagyenaeyeok,0);
       
       JTable table = new JTable(model);
       JScrollPane sp = new JScrollPane(table);
       FileMemo file_M = new FileMemo();
       public show() 
       {
           
           f4.setLayout(new FlowLayout());
          table.setRowSorter(new TableRowSorter(model));
          table.getColumn("No").setPreferredWidth(30);
          table.getColumn("날짜").setPreferredWidth(100);

          try {
             File text = new File(file_M.getFilePath());

             File_W_R();

             f.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent fe) {

                   DefaultTableModel model =(DefaultTableModel)table.getModel();
                   model.setNumRows(0);
                   File_W_R();
                }
                
             }); //새로고침

             delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent bh) {

                   try{
                      FileWriter file = new FileWriter(file_M.getFilePath(),false);
                      String []s = new String[6];
                      if (table.getSelectedRow() != -1) {
                         // remove selected row from the model
                         model.removeRow(table.getSelectedRow());
                         for(int i = 0;  i<table.getRowCount(); i++)
                         {
                            for(int j = 0; j<table.getColumnCount()-1; j++)
                            {                                   
                                  s[j] = (String)(table.getValueAt(i,j+1));          
                            }
                            file.write(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+s[3]+"\t"+s[4]+"\t"+s[5].replaceAll("\\,","" )+"\n");
                         }
                      }
                      file.close();
                   }
                   catch(IOException e)
                   {
                      System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
                   }
                }
             });
             //엑셀 내보내기
             excel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent bm) {
                	file_M.ExcelOut();
                   /* try {
                      XSSFWorkbook workbook = new XSSFWorkbook();//WorkBook을 생성
                      XSSFSheet sheet = workbook.createSheet();//Workbook내에 시트를 생성
                      XSSFCell cell; //cell 선언
                      FileReader text = new FileReader(FilePath); //가계부 내역 파일 위치
                      char[] c = new char[3000];

                      text.read(c);
                      text.close();

                      String str = new String(c);
                      String[] a = str.split("\n");  //"\n"으로 구분

                      String[] menu = {"날짜","항목","적요","결제 수단","구분","금액"}; //가계부 내역 항목 배열
                      XSSFRow row = sheet.createRow(0); //첫번쨰 행
                      for(int k=0; k<menu.length; k++) {   
                      cell = row.createCell(k);
                      cell.setCellValue(menu[k]);
                      }//첫번째 행의 각 셀 마다 메뉴 요소 하나씩 넣기

                      for(int i=0; i<a.length-1; i++) {
                      row = sheet.createRow(i+1); //두번째 행부터 for문 크기까지 행 생성
                      String [] w = a[i].split("\t"); //"\n"으로 나눈 문자열을 다시 "\t"로 나누어 저장
                      for(int j=0; j<w.length; j++) {
                      cell=row.createCell(j); 
                      cell.setCellValue(w[j]);//각 셀마다 배열의 요소를 셀에  입력한다.
                      }
                      }
                      FileOutputStream fs = new FileOutputStream("C:\\Users\\JunYoung\\Desktop\\파일\\money.xlsx");//데이터를 저장하기 위한 파일을 생성
                      workbook.write(fs);//파일을 열어 데이터를 저장
                      fs.close();
                      JOptionPane.showMessageDialog(f, "money.xlsx에 저장되었습니다.");
                      }
                       catch(Exception e){
                          JOptionPane.showMessageDialog(f, "저장실패! 기존 파일을 제거 후 다시 시도하십시오.");
                       }*/
                }
             }); 

             deleteAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   if(text.exists())
                   { 
                      if(text.delete())
                      { 
                         System.out.println("파일삭제 성공"); 
                         DefaultTableModel model =(DefaultTableModel)table.getModel();
                         model.setNumRows(0);
                      }
                      else
                      {
                         System.out.println("파일삭제 실패");
                      } 
                   }
                   else{ System.out.println("파일이 존재하지 않습니다."); 
                   }

                }
             });
             save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent b) {

                   try{
                      FileWriter file = new FileWriter(file_M.getFilePath(),false);
                      String []s = new String[6];

                      for(int i = 0;  i<table.getRowCount(); i++)
                      {
                         for(int j = 0; j<table.getColumnCount()-1; j++)
                         {                    
                             s[j] = (String)(table.getValueAt(i,j+1));  
                         }
                         file.write(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+s[3]+"\t"+s[4]+"\t"+s[5].replaceAll("\\,","" )+"\n");

                      }
                      file.close();
                   }

                   catch(IOException e)
                   {
                      System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
                   }
                }
             });
             save.setForeground(Color.RED);
             br.add(gn);
             //gn.setBounds(360,30,40,30);

             br.add(f);
             //f.setBounds(390,10,40,30);
             br.add(delete);
             //delete.setBounds(420,10,40,30);
             br.add(deleteAll);
             //deleteAll.setBounds(450,10,40,30);
             br.add(excel);
             //excel.setBounds(480,10,40,30);
             br.add(save);          //라벨, 버튼 있는 패널
             //save.setBounds(510,10,40,30);
             mainPan.add(br);//다합친 패널에 boder로 위치 배치
             mainPan.add(tbl);  //다합친 패널에 boder로 위치 배치

          }
          catch(Exception e)
          {
             System.out.print("실패");
          }
          
         
         s=new JTextField("ex) 2019/11/11(날짜), 헬스장(적요), 주거비(항목), 지출",60);
         s.setForeground(Color.gray);
         s.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
             s.setText("");  
             
         } });
          JButton bts=new JButton("찾기");
          
         bts.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent bh) {
                
           
                 try
                  {   
                    model.setNumRows(0);
                    FileReader text = new FileReader(file_M.getFilePath());
                      char[] c = new char[3000];

                      text.read(c);
                      text.close();


                      String str = new String(c);

                      String []M_b1 = new String[7];
                      String[] a = str.split("\n");

                     int j = 0;
                     
                     for(int i =0; i < a.length-1; i++)
                     {
                        
                        String [] word = a[i].split("\t"); 

                        M_b1[0] = new String((++j)+"");
                        M_b1[1] = word[0]; //날짜
                        M_b1[2] = word[1]; //항목
                        M_b1[3] = word[2]; //적요
                        M_b1[4] = word[3]; //결제 수단
                        M_b1[5] = word[4]; //구분(수입,지출)
                        M_b1[6] = String.format("%,d",Integer.parseInt(word[5]));//금액
    
                        DefaultTableModel model =(DefaultTableModel)table.getModel();
                       
                        
                        if(s.getText().equals(M_b1[1]))
                        {
                           model.addRow(M_b1);     
                        }
                        else if(s.getText().equals(M_b1[2]))
                        {
                           model.addRow(M_b1);
                        }
                        else if(s.getText().equals(M_b1[3]))
                        {
                           model.addRow(M_b1);
                        }
                        else if(s.getText().equals(M_b1[4]))
                        {
                           model.addRow(M_b1);
                        }
                        else if(s.getText().equals(M_b1[5]))
                        {
                           model.addRow(M_b1);
                        }
                        else
                        {
                           
                        }
                     }
                     s.setText("ex) 2019/11/11(날짜), 헬스장(적요), 주거비(항목), 지출");///
                     
                  }
                 catch(IOException e)
                 {
                    System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
                 }


              }
           });
               
         f4.add(s);
         f4.add(bts);     
         f4.add(br);
         f4.add(tbl);
         f4.setResizable(false);
         f4.setSize(800,800);
         f4.setVisible(true);
         f4.setLocationRelativeTo(null);
                                     
       }
       @Override
       public void File_W_R() {//파일 받아오는것 재정의

          try
          {   
             file_M.Fileread(file_M.getFilePath());
             String []fmemo = file_M.getFmemo();
             String []M_b = new String[7];
            

             int j = 0;
             for(int i =0; i < fmemo.length-1; i++)
             {
                String [] word = fmemo[i].split("\t"); 

                M_b[0] = new String((++j)+"");
                M_b[1] = word[0]; //날짜
                M_b[2] = word[1]; //항목
                M_b[3] = word[2]; //적요
                M_b[4] = word[3]; //결제 수단
                M_b[5] = word[4]; //구분(수입,지출)
                M_b[6] = String.format("%,d",Integer.parseInt(word[5]));//금액
                model.addRow(M_b);
             }
          }
          catch(Exception e)
          {
             System.out.println("텍스트에 저장된 값이 없습니다");
          }  //테이블 만드는 함수

          table.setPreferredScrollableViewportSize(new Dimension(600,600));  //테이블 크기 설정
          tbl.add(sp);   //테이블 패널에 테이블 추가
       }}
 
   class allday implements Money_Book{
       Calendar cal = Calendar.getInstance();
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH)+1;
       int day = cal.get(Calendar.DAY_OF_MONTH);
       int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
       
       FileMemo file_M = new FileMemo();
       JFrame f3 = new JFrame();

       int dayday;

       JPanel dday = new JPanel(null);
       JLabel today = new JLabel(year +"/"+month+"/"+day);

       String[] menu = {"D-day","날짜","일정","시간","내용" };  
       DefaultTableModel model = new DefaultTableModel(menu,0);

       JTable table = new JTable(model);
       JScrollPane sp = new JScrollPane(table);
       JButton newset = new JButton("새로고침");
       JButton setday2 = new JButton("입력");
       JTextField setday = new JTextField(23);

       public allday() {
          
          f3.setLayout(new FlowLayout());
          table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          table.setRowSorter(new TableRowSorter(model));
          table.getColumn("D-day").setPreferredWidth(50);
          table.getColumn("날짜").setPreferredWidth(100);
          table.getColumn("내용").setPreferredWidth(500);
          table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

          dday.add(today);
          today.setBounds(20,20,100,30);
          today.setForeground(Color.DARK_GRAY);
          today.setFont(new Font("Dialog",Font.BOLD,15));
          setday=new JTextField("ex) 2019/11/11(날짜),회의(일정),자바 프로젝트 캘린더만들기(내용)",60);
          setday.setForeground(Color.gray);
          setday.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e)
              {
               setday.setText("");  
               
           } });
           
         f3.add(setday);
         f3.add(setday2);
         f3.add(newset);
          
          setday2.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent bh) {
               //  setday.setText("ex) 2019/11/11(날짜),회의(일정),자바 프로젝트 캘린더만들기(내용)");///
                  try
                   {   
                     //model.setNumRows(0);
                	 
                	  file_M.Fileread(file_M.getFilePath1());
                	  String []fmemo = file_M.getFmemo();
                      String []d = new String[5];

                      model.setNumRows(0);
                      for(int i =0; i<fmemo.length-1; i++)
                      {
                         String []word = fmemo[i].split("\t"); 


                         String []date = word[0].split("/");


                         int I_dday = getDDay(Integer.parseInt(date[0].trim()),Integer.parseInt(date[1].trim()),Integer.parseInt(date[2].trim()));
                         d[0] = "D"+ I_dday;
                         d[1] = word[0];
                         d[2] = word[1];
                         d[3] = word[2];
                         d[4] = word[3];
                        
                         DefaultTableModel model =(DefaultTableModel)table.getModel();
                         
                        
                         if(setday.getText().equals(d[0])) {
                            model.addRow(d); 
                         }
                         else if(setday.getText().equals(d[1]))//날짜
                         {
                            model.addRow(d);     
                         }
                         else if(setday.getText().equals(d[2]))
                         {
                            model.addRow(d);
                         }
                         else if(setday.getText().equals(d[3]))
                         {
                            model.addRow(d);
                         }
                         else if(setday.getText().equals(d[4]))
                         {
                            model.addRow(d);
                         }
                            else
                            {
                               
                            }
                         }
                          setday.setText("ex) 2019/11/11(날짜),회의(일정),자바 프로젝트 캘린더만들기(내용)");
                         
                   }
          
                      catch(Exception e)
                      {
                         System.out.print("파일 열기에 실패 했습니다. - 가계부 전체 내역 파일");
                      }


                   }
                });
          f3.setResizable(false);
          f3.add(sp);
          f3.setSize(800,800);
          
          sp.setBounds(20,50,600,600);
          
          File_W_R();


          table.setPreferredScrollableViewportSize(new Dimension(800,800));
       }
       public int getDDay(int _year,int _month,int _day)
       {
          try {
             Calendar today = Calendar.getInstance();
             Calendar d_day = Calendar.getInstance();

             d_day.set(_year,_month-1,_day);

             long I_dday = d_day.getTimeInMillis()/(24*60*60*1000);
             long I_today = today.getTimeInMillis()/(24*60*60*1000);

             long substract = I_today - I_dday;
             return (int)substract;
          }
          catch(Exception e) {
             return -1;
          }
       }
       @Override
       public void File_W_R()//파일 받아오는것 재정의
       {
          try
          {   
             file_M.Fileread(file_M.getFilePath1());
             String []fmemo = file_M.getFmemo();
             String []d = new String[5];


             for(int i =0; i<fmemo.length-1; i++)
             {
                String []word = fmemo[i].split("\t"); 


                String []date = word[0].split("/");


                int I_dday = getDDay(Integer.parseInt(date[0].trim()),Integer.parseInt(date[1].trim()),Integer.parseInt(date[2].trim()));
                d[0] = "D"+ I_dday;
                d[1] = word[0];
                d[2] = word[1];
                d[3] = word[2];
                d[4] = word[3];
            
              
                  
                   model.addRow(d);
                
             }

             newset.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent fe) {

                    DefaultTableModel model =(DefaultTableModel)table.getModel();
                    model.setNumRows(0);
                    File_W_R();

                 }
                 
              }); //새로고침

          }
          catch(Exception e)
          {
             System.out.println("텍스트에 저장된 값이 없습니다. - 일정관리 파일");
          }  
          f3.setTitle("일정전체보기");
          f3.setSize(1000,1000);
          f3.setVisible(true);
          f3.setLocationRelativeTo(null);
       }
  }
   
}

class Frame extends JFrame{
   JPanel p = new JPanel();
   JTextField f = new JTextField();

   Full full = new Full();
   Full.schedule1 s = full.new schedule1(full.today_s);
   Full.input i = full.new input(full.today_s);
   Full.Report r = full.new Report();

   JPanel Sub1 = new JPanel(new GridLayout(1,3)); //전체 프레임의 윗칸을 가로로 3칸으로 나눔 
   JPanel Sub2 = new JPanel(null); //전체 프레임의 밑칸을 배치관리자를 없엠
   JPanel Sub1_Sub_1 = new JPanel(null);//윗칸의 
   JPanel Sub1_Sub_2 = new JPanel();
   JPanel Sub1_Sub = new JPanel(null);
   JPanel Sub1_Sub1 = new JPanel(null);
   JButton trans  = new JButton("일정 관리");
   JButton trans1  = new JButton("가계부");
   JLabel j1 = new JLabel("수입");
   JLabel j2 = new JLabel("지출");

   public Frame()
   {	
	  super("가계부 & 일정 관리");
      Container c = getContentPane();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridLayout(2,1)); //전체 프레임을 위아래로 두개로 나눔
      


      Sub1.add(s.m.calendar);
      Sub1_Sub_1.add(trans);
      trans.setBounds(40,0,90,20);
      Sub1_Sub_1.add(s.m.newP);
      s.m.newP.setBounds(40,30,200,30);
      Sub1_Sub_1.add(i.panel);
      i.panel.setBounds(0,0,300,400);
      Sub1_Sub_1.setBounds(0,0,300,400);
      s.f2.add(s.m.today1);
      s.m.today1.setPreferredSize(new Dimension(120,20));
      s.f4.add(trans1);
      trans1.setBounds(150,10,70,30);
      Sub1_Sub_2.add(s.schedule_Panel);
      Sub1_Sub_2.setBounds(0,0,300,600);
      Sub1_Sub_2.setVisible(false);
      //Sub1.add(Sub1_Sub_1);
      //Sub1.add(Sub1_Sub_2);

      Sub1_Sub.add(Sub1_Sub_1);
      Sub1_Sub.add(Sub1_Sub_2);

      Sub1_Sub1.add(j1);
      j1.setBounds(10,20,30,20);

      Sub1_Sub1.add(s.m.i_p.panel);//수입내역 패널 
      s.m.i_p.panel.setBounds(0,40,300,150);
      Sub1_Sub1.add(j2);
      j2.setBounds(10,200,30,20);
    
      Sub1_Sub1.add(s.m.e_p.panel);//지출내역 패널 
      s.m.e_p.panel.setBounds(0,220,300,150);


      Sub1.add(Sub1_Sub);
      Sub1.add(Sub1_Sub1);
      Sub2.add(s.d_d.dday);
      s.d_d.dday.setBounds(0,10,440,350);
      Sub2.add(r.mainPan);
      r.mainPan.setBounds(270,10,1050,800);

      trans.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Sub1_Sub_1.setVisible(false);
            Sub1_Sub_2.setVisible(true);
         }
      });
      trans1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Sub1_Sub_1.setVisible(true);
            Sub1_Sub_2.setVisible(false);
         }
      });

      JMenuBar bar=new JMenuBar();
      JMenu firstMenu=new JMenu("파일 ");
      JMenuItem item=new JMenuItem("소비습관 그래프");
      JMenuItem item1=new JMenuItem("일정찾기");
      JMenuItem item2=new JMenuItem("가계부찾기");
      
      
      firstMenu.add(item);
      firstMenu.add(item1);
      firstMenu.add(item2);
      item.addActionListener(new ActionListener()
      {
         
         @Override
         public void actionPerformed(ActionEvent e)
         {
            full.new total();
            
         }
      });
      item1.addActionListener(new ActionListener()
      {
         
         @Override
         public void actionPerformed(ActionEvent e)
         {
            full.new allday();
            
         }
      });
      item2.addActionListener(new ActionListener()
      {
         
         @Override
         public void actionPerformed(ActionEvent e)
         {
            full.new show();
            
         }
      });
      bar.add(firstMenu);
      setJMenuBar(bar);
      //Sub1.add(s.f1);
      c.add(Sub1);
      c.add(Sub2);
      setSize(1200,800);
      setResizable(false); //프레임창 크기 조절 불가
      setLocationRelativeTo(null); //프레임창을 화면 가운데 배치
      setVisible(true);
   }
}

public class FullFrame
{

  public FullFrame() {
      // TODO Auto-generated method stub
      try {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } //UIManager로 swing ui설정
      catch (Exception e) {
      }
      new Frame();

  }

}

