package ch.heg.initiative.web;

import ch.heg.initiative.service.Initiative;
import ch.heg.initiative.service.InitiativeService;
import ch.heg.initiative.service.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/write/initiative")
public class InitiativeWriterServlet extends InitiativeServlet {

    private static final Logger log = Logger.getLogger(InitiativeWriterServlet.class);

    private final InitiativeService service = new InitiativeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = ServiceHelper.getObjectMapper();
        Initiative i = mapper.readValue(req.getInputStream(), Initiative.class);
        service.create(i);
        resp.setStatus(201);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getWriter(), i);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ObjectMapper mapper = ServiceHelper.getObjectMapper();
            Initiative data = mapper.readValue(req.getInputStream(), Initiative.class);
            Initiative updated = service.update(data);
            if (updated == null) {
                resp.setStatus(404);
                resp.getWriter().write("{\"error\":\"Not found\"}");
                return;
            }
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getWriter(), updated);
        } catch (Exception e) {
            log.error(e);
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"Bad request\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String param = req.getParameter("id");
            Long id = Long.valueOf(param);
            boolean d = service.delete(id);
            resp.setStatus(d ? 204 : 404);
        } catch (Exception e) {
            resp.setStatus(400);
        }
    }
}
