
import java.io.IOException;
import java.util.Scanner;

public class DealGuruRunner {
	
	public SearchInitializer beginSearch;

	public static void main(String[] args) throws IOException, InterruptedException {
		DealGuruRunner deals=new DealGuruRunner();
	}
	
	public DealGuruRunner() throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		String keyword = scanner.next();
		scanner.nextLine();
		int minCycle = scanner.nextInt();
		DealGuruGUI.run(keyword,minCycle);
	}

}
