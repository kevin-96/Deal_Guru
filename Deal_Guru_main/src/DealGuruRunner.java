
import java.util.Scanner;

public class DealGuruRunner {
	
	public SearchInitializer beginSearch;

	public static void main(String[] args) {
		DealGuruRunner deals=new DealGuruRunner();
	}
	
	public DealGuruRunner(){
		Scanner scanner = new Scanner(System.in);
		String keyword = scanner.next();
		scanner.nextLine();
		int minCycle = scanner.nextInt();
		scanner.close();
		beginSearch=new SearchInitializer(keyword,minCycle);
	}

}
