import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

public class SoupPot {

    public Elements titles;
    public Elements images;
    public Elements links;

    public SoupPot() throws IOException {
        Document doc = Jsoup.connect("https://dealsea.com/search?q=apple&search_mode=Deals").get();
        titles = doc.select(".dealcontent > strong");
        images = doc.select(".dealbox > .prodimage img[src]");
        links = doc.select(".dealcontent > strong a");
    }
//
//    public static void main(String[] args) throws IOException {
//
//        Document doc = Jsoup.connect("https://dealsea.com/search?q=apple&search_mode=Deals").get();
//
//        Elements titles = doc.select(".dealcontent > strong");
//        System.out.println(MessageFormat.format("\nTitles: {0}", titles.size()) );
//        for (Element title : titles) {
//               System.out.println(title.text());
//        }
//
//        Elements images = doc.select(".dealbox > .prodimage img[src]");
//        System.out.println(MessageFormat.format("\nImageURLS: {0}", images.size()) );
//        //for (Element image : images) {
//            String url = images.get(5).absUrl("src");
////            URL link = new URL(url);
////            System.out.println(url);
////            Image display = null;
////            try {
////                display = ImageIO.read(link);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            JFrame frame = new JFrame();
////            frame.setSize(200, 200);
////            JLabel label = new JLabel(new ImageIcon(display));
////            frame.add(label);
////            frame.setVisible(true);
//       // }
//    }



}
