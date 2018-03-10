public class Section {
    String department;
    String crn;
    String courseID;
    String section;
    String type;
    String title;
    String room;
    String term;
    String discipline;
    String courseNumber;
    String message;
    String days;
    String time;
    String instructor;
    String beginDate;
    String endDate;
    int hours;
    int maximumEnrollment;
    int seatsAvailable;
    String url;
    double fees;
    String feeTitle;
    String feeType;
    public Section(){
        message = "";
        term = "";
        room = "";
    }
    @Override
    public String toString() {
        return "Section{" +
                "department='" + department + '\'' +
                ", crn='" + crn + '\'' +
                ", courseID='" + courseID + '\'' +
                ", section='" + section + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", room='" + room + '\'' +
                ", term='" + term + '\'' +
                ", discipline='" + discipline + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", message='" + message + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                ", instructor='" + instructor + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", hours=" + hours +
                ", maximumEnrollment=" + maximumEnrollment +
                ", seatsAvailable=" + seatsAvailable +
                ", fees=" + fees +
                ", feeTitle=" + feeTitle +
                ", feeType=" + feeType +
                ", url='" + url + '\'' +
                '}';
    }
}
