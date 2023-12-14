import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    public static String CrawlBestMovies() {
            String url = "https://www.imdb.com/chart/boxoffice/?ref_=nv_ch_cht";
            try {
                Document doc = Jsoup.connect(url).get();
                Elements movies = doc.select("h3.ipc-title__text");
                String result="Top 10 Highest Grossing Films:\n";

                for (int i = 1; i <= 10; i++) {
                    Element movie = movies.get(i);

                    String title = movie.text();

                    result+=title+"\n";
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
        }
    }