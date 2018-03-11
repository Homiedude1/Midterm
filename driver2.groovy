package edu.missouriwestern.blessing.CSC346

class driver2 {
    static void main(args) {
        def departments = "AF ART BIO BUS CHE CST CSMP CJLS EPSS EDU ET EFLJ HPER HPG HON MIL MUS NUR PSY FINE"
        departments = departments.split(/\s+/)
        PartB.clearTable(SQLiteConnector.connect("C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/midterm.db"))
        for (int i = 0; i<departments.length-1; i++){
            PartB.getSections(departments[i]
                    ,SQLiteConnector.connect("C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/midterm.db"))
        }



        //SummaryReport.makeSummaryReport("CSC","discipline","C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/midterm.db")
        //SummaryReport.makeSummaryReport("CSMP","department","C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/midterm.db")
        //SummaryReport.makeSummaryReport("","instructor","C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/midterm.db")

    }
}