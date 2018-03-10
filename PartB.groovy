import java.sql.Statement
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class PartB {
    static def writeToDB(java.sql.Connection conn, Section sec) {
        def fields = "courseID, department, crn, discipline, courseNumber, section, type, title," +
                " hours, days, time, room, instructor, maximumEnrollment, seatsAvailable, message, term, beginDate, " +
                "endDate, fees, feeTitle, feeType url";
        def valueString = "$sec.courseID, $sec.department, $sec.crn, $sec.discipline, $sec.courseNumber," +
                " $sec.section, $sec.type, $sec.title, $sec.hours, $sec.days, $sec.time, $sec.room, $sec.instructor, " +
                "$sec.maximumEnrollment, $sec.seatsAvailable, $sec.message, $sec.term, $sec.beginDate, $sec.endDate, $sec.url";
        valueString = valueString.replaceALL(/%/, "\\%")
        def queryString = "INSERT INTO sections ($fields) values ($valueString)"
        Statement stmt = conn.createStatment()
        stmt.executeUpdate(queryString)
    }

    static def getSections(String department) {
        Section sec = null
        def baseURL = "https://aps2.missouriwestern.edu/schedule/Default.asp?tck=201910"

        Document doc = null;

        /*Connection.Response response = Jsoup.connect(baseURL)
        .timeout(60 * 1000)
        .method(Connection.Method.POST)
        .data("course_number","")
        .data("subject","ALL")
        .data("department", department)
        .data("display_closed", "yes")
        .data("course_type","ALL")
        .followRedirects(true)
        .execute();

        doc = response.parse()
        println(doc)*/
        doc = Jsoup.parse(new File(department + ".html"), "UTF-8")
        Elements rows = doc.select("tr")
        println("There are ${rows.size()} rows")
        if (rows.size() > 4) {
            rows.each { row ->
                def className = row.attr("class")
                Elements cells = row.select("td")
                cells.each { cell ->
                    //println(cell)
                }
                if (className == "list_row") {
                    if (sec != null) {
                        println(sec) //database add

                    }
                    def cellCount = cells.size()
                    switch(cellCount) {
                        case 10:
                            sec = new Section ()
                            sec.department = department
                            sec.crn = cells [ 0 ].text ().trim ()
                            sec.courseID = cells [ 1 ].text ().trim ()
                            sec.crn = cells [ 0 ].text ().trim ()
                            def course = cells [ 1 ].text ().trim ()
                            sec.discipline = course.take (3)
                            sec.courseNumber = course.drop (3)
                            sec.section = cells [ 2 ].text ().trim ()
                            sec.type = cells [ 3 ].text ().trim ()
                            sec.title = cells [ 4 ].text ().trim ()
                            sec.hours = Integer.parseInt(cells[5].text().trim())
                            sec.days = cells[6].text().trim()
                            sec.time = cells [7].text().trim()
                            sec.room = cells[8].text().trim()
                            sec.instructor = cells[9].text().trim()

                            break
                        case 5:
                            sec.days += "|" + cells[1].text().trim()
                            sec.time += "|" + cells[2].text().trim()
                            sec.room += "|" + cells[3].text().trim()
                            break
                        default:
                            println"DID NOT HANDLE ROWS WITH $cellCount CELLS!"
                            System.exit(1)
                    }
                }//end list row
                else if(className == "detail_row"){
                    Elements tags = row.select("*")
                    tags.each{tag->
                        className = tag.attr("class").toString()
                        switch(className){
                            case "course_fees":
                                def feetxt = tag.text().trim()
                                feetxt = feetxt.drop("ADDITIONAL FEES:".length())
                                feetxt = feetxt.replaceAll("\\(.*?\\)","")
                                String []contents = feetxt.split(/\s+/)
                                sec.fees = Double.parseDouble(contents[contents.length-2])
                                sec.feeType = contents[contents.length-1]
                                String feeTitle = contents[0..contents.length-3]
                                feeTitle = feeTitle.replaceAll(",","")
                                feeTitle = feeTitle.replaceAll("]","")
                                feeTitle = feeTitle.replaceAll("\\[","")
                                println(feeTitle)
                            case "course_messages":
                                sec.message += tag.text().trim()
                                break
                            case "course_ends":
                                def d = tag.text()
                                sec.endDate = d.drop("Course Ends: ".length())
                                break
                            case "course_begins":
                                def d = tag.text()
                                sec.beginDate = d.drop("Course Begins: ".length())
                                break
                            case "course_term":
                                sec.term = tag.text().trim()
                                break
                            case "course_seats":
                                def contents = tag.text()
                                contents.replace("<br />", " ")
                                String [] parts = contents.split(/\s+/)
                                if(parts.length > 6){
                                    def max = parts[2]
                                    def available = parts[6]
                                    sec.maximumEnrollment = Integer.parseInt(max)
                                    sec.seatsAvailable = Integer.parseInt(available)
                                }
                                break
                        }
                    }
                }
            }
        }
        //write to database
    }
}