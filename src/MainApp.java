import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp {

    private static String URL =
            "https://www.saramin.co.kr/zf_user/search?search_area=main&search_done=y&search_optional_item=n&recruitPageCount=40&searchType=search&";
                    
    static ArrayList<String> strArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String KEY_WORD = "java";
        String param = getParameter(KEY_WORD, 1);
        System.out.println(URL+param);


        //1. document 가져오기
        Document doc = Jsoup.connect(URL + param).get();


        
        //2. 목록 가져오기
        //System.out.println("doc.toString() = " + doc.toString());
        Elements elements = doc.select("h2.job_tit");


        //3. 배열에서 정보를 가져온다.
        System.out.println("before");
        int idx = 0;
        for(Element element : elements)
        {
            Element anchorElement = element.getElementsByTag("a").first();
            
            strArr.add("https://www.saramin.co.kr" + anchorElement.attr("href"));

        }
        System.out.println("after");

        int count = 0;
        for(String element: strArr)
        {
            System.out.println(++count);
            System.out.println("element = " + element);
        }


    }


    public static String getParameter(String KEY_WORD, int PAGE){

        String params = "searchword=" + KEY_WORD+
                "&recruitPage=" + PAGE;



        return params;

    }

}
