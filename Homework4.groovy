
import java.io.IOException
import java.util.ArrayList

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class Scraping {
    static void main(String[] args) throws IOException{
        //Scanner sc = new Scanner(System.in);
        //String input = sc.nextLine()

        ArrayList<Parks> Scrapings = new ArrayList<Parks>()
        Document doc = Jsoup.connect("https://www.nationalpark-adventures.com/united-states-national-parks.html").get()
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                String htmlString = row.text()

                if (htmlString.contains("(")) {
                    String[] splitString = htmlString.split("\\(", 2)
                    String[] splitStrings = splitString[1].split(" ", 3)
                    String iter = Integer.parseInt(splitStrings[0].replaceAll("\\)", ""))
                    String name = splitString[0]
                    System.out.println(name)
                }
                if (!htmlString.contains("(")) {
                    String[] splitString = htmlString.split("   ", 2)
                    String[] splitStrings = splitString[0].split("[a-z]", 3)
                    String name = splitStrings[1];
                    System.out.println(htmlString);
                }
            }
        }

    }
    void findPark(ArrayList<Object> Scrapings,String string){
        for (int i = 0; i < Scrapings.size(); i++) {
            //index declartion of the array
            Parks val = (Parks) Scrapings.get(i);
            // assigns the ID to a temp variable
            String ID = val.getStudentID();
            //takes index and temp variable and removes the index
            if(ID.equalsIgnoreCase(string)){
                System.out.println(Scrapings.get(i))
            }//end of if statment
            else{}
        }	// end of for loop
    }// end


    @Override
    String toString() {
        return null
        //return ("State: "+ this.getState()+
        //          "Park Name: "+ this.getParkName() +
        //           " Year: "+ this.getYear();


    }//end of toString
}


class Parks {
    protected String state
    protected String Pname
    protected int year

    Parks(String state, String Pname, int year){
        this.state = state
        this.Pname = Pname
        this.year = year
    }

    String getState(){return state }

    String getParkName(){return Pname }

    int getYear(){return year }

}