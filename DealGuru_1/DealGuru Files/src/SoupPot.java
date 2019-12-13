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
    public Elements deals;
    public Document doc;
    public String link;

    public SoupPot(String searchTerm) throws IOException {
        link = "https://dealsea.com/search?q=" + searchTerm + "&search_mode=Deals";
        Document doc = Jsoup.connect(link).get();
        refresh();
    }

    public void refresh() throws IOException {
        doc = Jsoup.connect(link).get();
        deals = doc.select(".dealbox:not(:contains(expired))");
        titles = deals.select(".dealcontent > strong");
        images = deals.select(".dealbox > .prodimage img[src]");
        links = deals.select(".dealcontent > strong a");
    }

}
