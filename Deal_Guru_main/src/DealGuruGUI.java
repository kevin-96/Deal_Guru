import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class DealGuruGUI extends JFrame {
  
    // JFrame 
    static JFrame frame;
  
    // JButton 
    static JButton exit, leftBtn, rightBtn;
  
    // label to display text
    static JLabel description;
    static JLabel url;
    
    // textfields
    static JTextField descriptionText;
    static JTextField urlText;
    static JLabel label;

    //timer
    static int dealNum = 0;
    
    
    //Class used for making a JPanel that can hold an image

    class ImagePanel extends JPanel {
    	private Image img;
    	
    	public ImagePanel(String img) {
    		this(new ImageIcon(img).getImage());
    	}
    	
    	public ImagePanel(Image img) {
    		this.img = img;
    		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    		setPreferredSize(size);
    		setMinimumSize(size);
    		setMaximumSize(size);
    		setSize(size);
    		setLayout(null);
    	}
    	public void paintComponent(Graphics g) {
    		g.drawImage(img, 0, 0, null);
    	}
    }


  
    // MAIN METHOD
    public static void main(String[] args) throws IOException {
    	//INSTANTIATING ALL COMPONENTS
    	SoupPot soup = new SoupPot();

        //instantiating JFrame that will hold the various panels
        frame = new JFrame("Deal Guru");

        
        //instantiating the JLabels and JTextFields that give info on the product
        description = new JLabel("Description: ");
        url = new JLabel("URL: ");
        descriptionText = new JTextField(soup.titles.get(dealNum).text());
        urlText = new JTextField("url output goes here");
  
        //Instantiating the buttons

        exit = new JButton("Exit");
        leftBtn = new JButton("<");
        rightBtn = new JButton(">");
        //Close program when "exit" JButton is pressed (Adding ActionListener)
        exit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.exit(0);
        	}
        });

        rightBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
              if (getNum() < soup.titles.size() - 1) {
                  setNum(getNum() + 1);
                  descriptionText.setText(soup.titles.get(dealNum).text());
                  urlText.setText(soup.links.get(getNum()).absUrl("href"));
                  String ImageUrl = soup.images.get(getNum()).absUrl("src");
                  URL link = null;
                  try {
                      link = new URL(ImageUrl);
                  } catch (MalformedURLException e) {
                      e.printStackTrace();
                  }
                  System.out.println(url);
                  Image display = null;
                  try {
                      display = ImageIO.read(link);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  ImageIcon icon = new ImageIcon(display);
                  icon.getImage().flush();
                  label.setIcon(icon);

                  frame.validate();
                  frame.repaint();
              }
            }
        });
        leftBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (getNum() > 0) {
                    setNum(getNum() - 1);
                    descriptionText.setText(soup.titles.get(dealNum).text());
                    urlText.setText(soup.links.get(getNum()).absUrl("href"));
                    String ImageUrl = soup.images.get(getNum()).absUrl("src");
                    URL link = null;
                    try {
                        link = new URL(ImageUrl);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    System.out.println(url);
                    Image display = null;
                    try {
                        display = ImageIO.read(link);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ImageIcon icon = new ImageIcon(display);
                    icon.getImage().flush();
                    label.setIcon(icon);

                    frame.validate();
                    frame.repaint();
                }
            }
        });
  
        //creating panels for image, info, and buttons
        JPanel imgPanel = new JPanel();
        JPanel infoPanel = new JPanel(); //Responsible for displaying product price, description, store name, url
        JPanel btnPanel = new JPanel(); //Responsible for displaying JButtons

        urlText.setText(soup.links.get(getNum()).absUrl("href"));

        infoPanel.setLayout(new GridLayout(2,2)); //infoPanel is setup as a Grid
        String ImageUrl = soup.images.get(getNum()).absUrl("src");
            URL link = new URL(ImageUrl);
            System.out.println(url);
            Image display = null;
            try {
                display = ImageIO.read(link);
            } catch (IOException e) {
                e.printStackTrace();
            }

           label = new JLabel(new ImageIcon(display));
  
        // add buttons and textfields to the panels
        imgPanel.add(label);
//        infoPanel.add(label);
        infoPanel.add(description);
        infoPanel.add(descriptionText);
        description.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(url);
        infoPanel.add(urlText);
        url.setHorizontalAlignment(JLabel.CENTER);
        btnPanel.add(exit);
        btnPanel.add(leftBtn);
        btnPanel.add(rightBtn);
  
        //setting backgrounds of panel 
        imgPanel.setBackground(Color.white);
        infoPanel.setBackground(Color.white);
        btnPanel.setBackground(Color.white);
  
        // add panel to frame
        frame.add(imgPanel, "North");
        frame.add(infoPanel, "Center");
        frame.add(btnPanel, "South");
  
        // set the size of frame 
        frame.setSize(900, 300);
  
        frame.show(); 
    }

    static public int getNum(){
        return dealNum;
    }
    static public void setNum(int x){
        dealNum = x;
    }

} 