import static net.sf.dynamicreports.report.builder.DynamicReports.*;
public class SummaryReport {
    String parameter;
    String pType;


    public SummaryReport(String parameter, String pType) {
        if (pType.toLowerCase().equals("discipline")) {
            buildDisc();
        }
        else{
            buildDept();
        }
    }
    public void buildDisc(){
        try{
            report()
                    .columns(col.column("Discipline","discipline",type.stringType()))
                    .groupBy()
                    .subtotalsAtSummary()
        }
    }
    public void buildDept(){
        try{
            report()
                    .columns(col.column("Department","department",type.stringType()))
                    .groupBy()
                    .subtotalsAtSummary()
        }
    }

}
