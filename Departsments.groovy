import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class Departments {
    public static void main(String[] args) {

        Document document = Jsoup.connect("https://aps2.missouriwestern.edu/schedule/default.asp?tck=201910").get();
        /*String[] sizesAvailable = null;
        String text = document.select("select").get(1).text();
        Elements sizes = document.select("select")
        sizesAvailable = new String[sizes.size()];
        for (int i = 0; i < sizes.size(); i++) {
            sizesAvailable[i] = sizes.get(i).text();
        }
        System.out.println(sizesAvailable[2]);
        */
        String[] subjects = new String[10];
        String tempVal = null;
        String tempVal1  = null;
        String tempVal2  = null;
        String tempVal3  = null;
        Elements subject = document.getElementsByAttributeValue("name", "subject").get(0).children();
        Elements department = document.getElementsByAttributeValue("name", "department").get(0).children();
        for (Element option : department) {
            tempVal = option.val();
            tempVal1 = option.text();
            String merge = tempVal + " is " + tempVal1
            System.out.println(merge)

        }
        System.out.println(" NEW TABLE")
        for (Element option : subject) {
            tempVal2 = option.val();
            tempVal3 = option.text();
            String merge = tempVal2 + " is " + tempVal3
            System.out.println(merge)

            }

    }
        }





