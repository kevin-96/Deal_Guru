import javax.swing.Timer;

public class SearchInitializer {
	
	public Timer timer;
	public String keyword;
	public int minCycle;
	
	public SearchInitializer(String keyword, int minCycle)
	{
		this.keyword=keyword;
		if(minCycle>=300000)
		{
			this.minCycle=minCycle;
		}
		else
		{
			this.minCycle=300000;
		}
		this.minCycle=1;
		this.timer=new Timer(100, new GuruActionEvent(this.keyword));
		this.timer.setDelay(10000);
		this.timer.start();
		this.timer.restart();
		System.out.println(this.timer.isRunning());
	}

}
