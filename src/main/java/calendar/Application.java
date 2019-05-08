package calendar;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import calendar.servlets.ServletCalendar;

public class Application {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new ServletCalendar()), "/calendar");

        Server server = new Server(80);

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
