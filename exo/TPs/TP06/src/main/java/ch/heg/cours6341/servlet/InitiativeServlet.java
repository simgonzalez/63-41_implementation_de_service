package ch.heg.cours6341.servlet;

import ch.heg.cours6341.initiative.Initiative;
import ch.heg.cours6341.initiative.InitiativeManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/initiatives")
public class InitiativeServlet extends HttpServlet {
  private InitiativeManager im = new InitiativeManager();
  private ObjectMapper objectMapper = new ObjectMapper();

  public InitiativeServlet() {
    super();
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter respWriter = resp.getWriter();
    long id = 0;
    try {
      id = Long.parseLong(req.getParameter("id"));
    } catch (NumberFormatException ignored) {
    }

    if (id < 1) {
      im.findAll()
          .forEach(
              initiative -> {
                try {
                  respWriter.print(objectMapper.writeValueAsString(initiative));
                } catch (JsonProcessingException e) {
                  throw new RuntimeException(e);
                }
              });
    } else {
      respWriter.print(objectMapper.writeValueAsString(im.findById(id)));
    }
    respWriter.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json");
    PrintWriter respWriter = resp.getWriter();

    try {
      Initiative initiative = objectMapper.readValue(req.getInputStream(), Initiative.class);
      im.insert(initiative);
      resp.setStatus(HttpServletResponse.SC_CREATED);
      respWriter.print("{\"message\": \"Initiative created\"}");
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      respWriter.print("{\"error\": \"" + e.getMessage() + "\"}");
    }
    respWriter.close();
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json");
    PrintWriter respWriter = resp.getWriter();

    try {
      Initiative initiative = objectMapper.readValue(req.getInputStream(), Initiative.class);
      boolean ok = im.update(initiative);
      if (ok) {
        respWriter.print("{\"message\": \"Initiative updated\"}");
      } else {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        respWriter.print("{\"error\": \"Initiative not found\"}");
      }
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      respWriter.print("{\"error\": \"" + e.getMessage() + "\"}");
    }
    respWriter.close();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String idStr = req.getParameter("id");
    PrintWriter respWriter = resp.getWriter();
    try {
      long id = Long.parseLong(idStr);
      boolean ok = im.delete(id);
      if (ok) {
        respWriter.print("Initiative deleted");
      } else {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        respWriter.print("Initiative not found");
      }
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      respWriter.print("Error deleting initiative: " + e.getMessage());
    }
    respWriter.close();
  }
}
