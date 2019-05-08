package calendar.servlets;

import calendar.utils.ParameterFromRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServletCalendar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        PrintWriter writer = resp.getWriter();

        int year = pfr.getInt("year");
        int month = pfr.getInt("month");

        Calendar calendar = Calendar.getInstance();

        DateFormat format = new SimpleDateFormat("M yyyy");

        try {
            calendar.setTime(format.parse(month + " " + year));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int startDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int getCountDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        writer.println("   Sun  Mon Tue   Wed Thu   Fri  Sat");

        for (int i = 0; i < startDayOfMonth; i++)
            writer.print("     ");
        for (int i = 1; i <= getCountDay; i++) {
            writer.printf(" %3d ", i);
            if (((i + startDayOfMonth) % 7 == 0) || (i == getCountDay)) writer.println();
        }
    }
}
