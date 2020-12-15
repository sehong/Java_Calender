
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


abstract class Files{

	
	public abstract String FileRead();
	public abstract void FileWrite();

}
class FileIO extends Files{
	
	private String FilePath; //파일 경로
	
	public String getFilePath(){
		return FilePath;
	}


	@Override
	public String FileRead() {

		String M = new String(); //읽은 파일 내용이 저장될 문자열

		try {

			FileReader member = new FileReader(FilePath);
			char []c = new char [1024];
			member.read(c);
			member.close();

			M =  String.valueOf(c);

		}
		catch(IOException e)
		{
			System.out.println("파일 열기 실패");
		}
		return M;

	}
	@Override
	public void FileWrite() {
		try {
			FileWriter fout = new FileWriter(FilePath,true);

			fout.write(((Member)this).getFname()+" "+((Member)this).getfId() + " " + ((Member)this).getfPw() +" "+ ((Member)this).getFp_n() +" "+((Member)this).getFe_m() +"\n" );
			fout.close();
		}
		catch(Exception e)
		{
			System.out.printf("파일 쓰기 실패");
		}
	}
	public FileIO(){
		this.FilePath ="./member.txt";
	}


}