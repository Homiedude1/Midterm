package edu.missouriwestern.blessing.CSC346;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.exception.DRException;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class SummaryReport {

    public static void makeSummaryReport(String param, String pType) {

        if (pType.toLowerCase().equals("discipline")) {
            buildDisc(param);
        } 
        else if (pType.toLowerCase().equals("department")) {
            buildDept(param);
            } 
        else if(pType.toLowerCase().equals("instructor")){
            buildInstructor(param);
        }
    }

    private static void buildInstructor(String param) {
    }

    public static void buildDisc(String param){
        String query = "SELECT distinct(courseNumber), title, discipline FROM sections WHERE discipline LIKE '"+ param+"'";
        TextColumnBuilder<String> disciplineColumn = col.column("Discipline","discipline",type.stringType());
        ColumnGroupBuilder disciplineGroup = grp.group(disciplineColumn).setTitleWidth(60).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
               .showColumnHeaderAndFooter();

        try{
            report().setShowColumnTitle(false)
                    .columns(col.column("Courses","courseNumber",type.stringType()),
                            col.column("Course Title", "title",type.stringType()))
                    .groupBy(disciplineGroup)
                    .setDataSource(query,SQLiteConnector.connect())
                    .show();

        }catch (DRException e) {
            e.printStackTrace();

        }

    }
    public static void buildDept(String param) {
        String query = "SELECT distinct(discipline),courseNumber, title, department FROM sections WHERE department LIKE '" + param + "'";
        TextColumnBuilder<String> departmentColumn = col.column("Department", "department", type.stringType());
        /*ColumnGroupBuilder departmentGroup = grp.group(departmentColumn).setTitleWidth(60).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
                .showColumnHeaderAndFooter();*/
        TextColumnBuilder<String> disciplineColumn = col.column("Discipline","discipline",type.stringType());
        ColumnGroupBuilder disciplineGroup = grp.group(disciplineColumn).setTitleWidth(60).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
                .showColumnHeaderAndFooter();

        try {
            java.sql.Connection conn = SQLiteConnector.connect();
            report().setShowColumnTitle(false)
                    .columns(col.column("Courses", "courseNumber", type.stringType()),
                            col.column("Course Title", "title", type.stringType()))
                    .title(cmp.text(param))
                    .groupBy(disciplineGroup)
                    .setDataSource(query, conn)
                    .show();


        } catch (DRException e) {
            e.printStackTrace();

        }
    }

}
