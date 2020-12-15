
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/*import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
*/
class FileMemo {
	 private String FilePath ;//가계부 파일
	 private String FilePath1;//일정 파일
	 
    private char []c; //파일에서 불러온 글자들을 저장할 char형 배열
    private String []fmemo;//split 함수로 개행문 단위로 구분하여 저장할 문자형 배열 
    private String s;//문자형 배열을 문자열로 변환
    
    public FileMemo() {
   	 fmemo = null;
   	 c = new char[5000];
   	 setFilePath("./moneybook1.txt");//가계부파일
   	 setFilePath1("./schedule1.txt");//일정파일
   	 
   	 
    	File file = new File(getFilePath()); //가계부파일을file에 객체생성
      	try {
      	FileWriter file1 =new FileWriter(file,true);//가계부 파일을 file1으로 덮어쓰는 true사용
      	file1.write("");
      	file1.close();//여기서가계부 파일 닫기
      	}
      	catch(IOException e1)
      	{
      		System.out.print("실패");
      	} 
      	
      	File file1 = new File(getFilePath1());//일정파일을 file1으로 객체생성// 위에 file1은 가비지 로 버려저서 재사용
     	try {
     	FileWriter file2 =new FileWriter(file1,true);//일정파일을 file2으로 사용하고 덮어쓰는 true사용
     	file2.write("");
     	file2.close();//여기서 일정 파일  닫기
     	}
     	catch(IOException e1)
     	{
     		System.out.print("실패");
     	} 
    }

   public String getFilePath() {//가계부파일 받기
		return FilePath;
	}
	public void setFilePath(String filePath) {//가계부 셋트
		FilePath = filePath;
	}
	public String getFilePath1() {//일정파일 받기
		return FilePath1;
	}
	public void setFilePath1(String filePath1) {//일정 세트
		FilePath1 = filePath1;
	}
	public String[] getFmemo(){
   	 return fmemo;
    }
    public void Fileread(String FilePath) {//텍스트파일 읽어 오는함수
   	 try {
   		 
   	 FileReader f = new FileReader(FilePath); //읽기형식으로 파일 열기
   	 char []c = new char[5000];
        f.read(c);//배열 c에 파일 내용을 한글자씩 모두 저장
        f.close();//파일 닫기
        s = new String(c);//문자형 배열을 문자열로 변환
        fmemo = s.split("\n");//split 함수로 개행문 단위로 구분
            
   	 }
   	 catch(IOException e)
   	 {
   		File file = new File(getFilePath());
         	try {
         	FileWriter file1 =new FileWriter(file,true);
         	file1.write("");
         	file1.close();
         	}
         	catch(IOException e1)
         	{
         		System.out.print("실패");
         	} 
   		 System.out.printf("파일 열기 실패");
   	 }
   	 
    }
    public void ExcelOut(){
		try {/*
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
            JOptionPane.showMessageDialog(null, "money.xlsx에 저장되었습니다.");*/
            }
             catch(Exception e){
                JOptionPane.showMessageDialog(null, "저장실패! 기존 파일을 제거 후 다시 시도하십시오.");
             }
	}
    
}