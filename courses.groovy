import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class courses {
    public static void main(String[] args) {
        Document document =Jsoup.connect("https://aps2.missouriwestern.edu/schedule/default.asp?tck=201910").get()

        String[] subpartments = new String[30];
        String[] departments = new String[30];
        String tempVal = null;
        String tempVal1  = null;
        String tempVal2  = null;
        String tempVal3  = null;
        Elements subject = document.getElementsByAttributeValue("name", "subject").get(0).children();
        Elements department = document.getElementsByAttributeValue("name", "department").get(0).children();
        for (Element option : department) {
            int i = 0;
            i++
            tempVal = option.val();
            tempVal1 = option.text();
            departments[i] = tempVal
            System.out.println(departments[i])



            Response response =
                    Jsoup.connect("https://aps2.missouriwestern.edu/schedule/default.asp?tck=201910")

                            .userAgent("Mozilla/5.0")
                            .method(Method.POST)
                            .timeout(60 * 1000)
                            .method(org.jsoup.Connection.Method.POST)
                            .data("course_number", "")
                            .data("subject", "ALL")
                            .data("department", departments[i])
                            .data("display_closed", "yes")
                            .data("course_type", "ALL")
                            .followRedirects(true)
                            .execute();
            Document doc = response.parse();
            System.out.println(doc.text())
            String temp = ""
            String[] tempx = new String[10]
            if (tempx.contains(temp)) {
                tempx[i] = temp
            }
                //int x=0;
                //subpartments[i] = departments[x] + tempx[x]
                //x++;



        }



        //Elements rows = doc.select("tr")
        //System.out.println(rows)
            }
        }

