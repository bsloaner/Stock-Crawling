import java.util.Scanner;
import java.net.*;
import java.util.*;
public class test1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String s = "";
		String longString = "";
		
		try{
		URL url = new URL("http://www.makeuseof.com/tag/build-basic-web-crawler-pull-information-website/");
		
		Scanner input = new Scanner(url.openStream());
		while(input.hasNext()){
			s = input.nextLine();
			System.out.println(s);
		}
		
		}catch(Exception ex){
			System.out.println("you messed up");
		}
		
	}

}
