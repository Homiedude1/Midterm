import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Midterm {

    public static void main(String[] args) throws IOException {
        String courseID;
        String semester;
        String crn;
        String discipline;
        String courseNumber;
        String section;
        String type;
        String title;
        int hours;
        String days;
        String time;
        String room;
        String building;
        String instructor;
        int maximumEntrollment;
        int seatsAvailable;
        String message;
        String term;
        String beginData;
        String endDate;
        String url;
        showMenu();
        Connection.Responce response =
                Jsoup.connect("https://aps2.missouriwestern.edu/schedule/?tck=201910")
                        .userAgent("Mozilla/5.0")
                        .timeout(10 * 1000)
                        .method(Connection.Method.POST)
                        .data("course_number", "CSC")
                        .data("txtloginpassword", "YOUR_PASSWORD")
                        .data("random", "123342343")
                        .data("task", "login")
                        .data("destination", "/welcome")
                        .followRedirects(true)
                        .execute();

        //parse the document from response
        Document document = response.parse();
        for (Element table : document.select("tr")) {
            for (Element row : table.select("td")) {
                String detail = document.select("detail_row")
                System.out.println(detail)


            }
        }
    }


    private static void showMenu() {
        char ch
        while (ch != 'Q') {
        }
        System.out.println(" A. Erase and Build the Subjects (Discipline) table");
        System.out.println(" B. Erase and Build the Departments table");
        System.out.println(" Q. Quit");
        Scanner input = new Scanner(System.in);
        System.out.println("Type a Letter: ");
        String s = input.next().toUpperCase().trim();
        ch = (s.length() > 0) ? s.charAt(0) : 'x';
        switch (ch) {
            case 'A':
                eraseSubjectsTable();
                break;
            case 'B':
                eraseDepartmentsTable();
                break;
            case 'Q':
                break;
            default:
                System.out.println("Type a letter from the menu!");
        }
    }

}
class GetSections{
    static def getSections(dept,semester,writeToDb){
        println "Processing $dept for semester $semester. Writing to database? $writeToDb"
        def baseURL = "https://aps2.missouriwester.edu/schedule/"
        document doc = Jsoup.parse(newFile("Csmp.html"),"UTF-8",baseURL)
        Elements rows = doc.select("tr")
        println("found ${rows.size()} rows");
        if(rows.size() > 4){
            rows.each{row->
                def className = row.attr("class")
                Element cells = row.select("td");
                if(className == "list_row") {
                    if (sec != null) {// would write to database here
                        println "Section: $sec"
                    }
                    def cellCount = cells.size()
                    switch (cellCount) {
                        case 10:
                            //sec = new Section();
                            sec.department = semester;
                            sec.crn = cells.get(0).text().trim()
                            def s = cells.get(1).text().trim()
                            println "Corse is $a"
                            sec.discipline = s.take(3)
                            sec.corseNumber = s.drop(3)
                            println("Discipline is ${sec.discipline} ${sec.courseNumber}")
                            sec.instructor = cells[9].text().trim
                            println("instructor ${sec.instructor}")
                            sec.room = cells[8].text().trim()
                            break;
                        case 5:
                            sec.room += "|" + cells[3].text().trim()
                            break;
                        default:
                            println " Did Not Handle Rows with $cellCount cells"
                            System.exit(1);



                    }
                }
                else if(className == "detail_row"){
                    sec.messages = ""
                    sec.term = ""
                    Elements tags = row.select("*")
                    tags.each{tag->
                    className = tag.attr("class")).toString()
                    switch(className) {
                        case "corse_messages":
                            sec.messages += tag.text().trim()
                            break;
                        case "course_ends":
                            def s = tag.text();
                            sec.endDate = s.drop("Course End: ".length());
                            sec.endDate = (s =~ /\d/\d+/\/\d+/)[0]
                        sec.endDate = sec.endDate.trim();
                            println("END Date: ${sec.endDate}")
                            break;
                        case "course_seats":
                            def contents = tag.text();
                            contents.replace("<br />"," ")
                            String[] parts = contents.split(/\s+/)
                            if(parts.length> 6){
                                def max = parts[2]
                                def available = parts[6]
                                sec.maximumEntrollment = Integer.parseInt(available)
                                println "Seats: %{sec.maximumEntrollment} and ${sec.seatsAvailable}"

                    }
                    }
                }
            }
        }
    }
}