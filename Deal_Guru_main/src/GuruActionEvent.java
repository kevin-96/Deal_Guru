import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuruActionEvent implements ActionListener{
	
	public String search;
	
	public GuruActionEvent(String search)
	{
		this.search=search;
		System.out.println(this.search);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("YYYY");
	}

}
