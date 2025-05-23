package by.javaguru.je.jdbc.servlet;

import by.javaguru.je.jdbc.service.FlightService;
import by.javaguru.je.jdbc.utils.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private static final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.setAttribute("flights", flightService.findAll());
        req.getRequestDispatcher(JSPHelper.getPath("flights")).forward(req,resp);

    }
}
