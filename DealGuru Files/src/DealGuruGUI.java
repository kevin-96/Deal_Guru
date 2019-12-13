import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class DealGuruGUI extends JFrame {

    // JFrame 
    static JFrame frame;

    // JButton 
    static JButton exit, leftBtn, rightBtn,quit;

    // label to display text
    static JLabel description;
    static JLabel url;

    // textfields
    static JTextField descriptionText;
    static JTextField urlText;
    static JLabel label;

    static int time;
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
    public static void main(String[] args) throws IOException, InterruptedException {
        //INSTANTIATING ALL COMPONENTS
        System.out.println("Enter a keyword to search for deals:");
        String search = getSearch();
        System.out.println("How often do you want to be notified for deals? (in minutes): ");
        time = getTime();
        time = (time*60) * 1000;
        //System.out.println(time);
        SoupPot soup = new SoupPot(search);


        //instantiating JFrame that will hold the various panels
        frame = new JFrame("Deal Guru");


        //instantiating the JLabels and JTextFields that give info on the product
        description = new JLabel("Description: ");
        url = new JLabel("URL: ");
        try{
            descriptionText = new JTextField(soup.titles.get(dealNum).text());
        } catch (Exception e){
            System.out.println("There are no results for that keyword. Restart the app and try again");
            System.exit(0);
        }

        urlText = new JTextField("url output goes here");

        //Instantiating the buttons

        exit = new JButton("Keep Checking");
        leftBtn = new JButton("<");
        rightBtn = new JButton(">");
        quit = new JButton("Quit");
        //Close program when "exit" JButton is pressed (Adding ActionListener)
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {System.exit(0);}
        });
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.hide();
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    soup.refresh();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dealNum = 0;
                descriptionText.setText(soup.titles.get(dealNum).text());
                urlText.setText(soup.links.get(dealNum).absUrl("href"));
                String ImageUrl = soup.images.get(dealNum).absUrl("src");
                URL link = null;
                try {
                    link = new URL(ImageUrl);
                } catch (MalformedURLException k) {
                    k.printStackTrace();
                }
                //System.out.println(url);
                Image display = null;
                try {
                    display = ImageIO.read(link);
                } catch (IOException k) {
                    k.printStackTrace();
                }
                ImageIcon icon = new ImageIcon(display);
                icon.getImage().flush();
                label.setIcon(icon);

                frame.validate();
                frame.repaint();
                frame.show();
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
        btnPanel.add(quit);
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

    public void refresh(){
        frame.show();
    }
    static public String getSearch(){
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
        return search;
    }

    static public int getTime(){
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        while (time < 1){
            System.out.println("Time too short, pick again");
            time = scanner.nextInt();
        }
        return time;
    }
    static public int getNum(){
        return dealNum;
    }
    static public void setNum(int x){
        dealNum = x;
    }

} 