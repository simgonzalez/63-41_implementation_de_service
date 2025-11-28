package ch.heg.initiative.web;

import ch.heg.initiative.service.Initiative;
import ch.heg.initiative.service.InitiativeService;
import ch.heg.initiative.service.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet("/initiatives")
public class InitiativeServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(InitiativeServlet.class);

    private final InitiativeService service = new InitiativeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String param = req.getParameter("id");
        if (param == null) {
            ServiceHelper.getObjectMapper().writeValue(resp.getWriter(), service.findAll());
            return;
        }
        try {
            Long id = Long.valueOf(param);
            Initiative i = service.find(id);
            if (i == null) {
                resp.setStatus(404);
                resp.getWriter().write("{\"error\":\"Not found\"}");
                return;
            }
            ServiceHelper.getObjectMapper().writeValue(resp.getWriter(), i);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"Bad id\"}");
        }
    }
}
