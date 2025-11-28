package ch.heg.cours;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class MyServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" >>>>>>>>>>>>>>    ça marche ! ");
        PrintWriter respWriter = resp.getWriter();
        String nom = req.getParameter("name");
        String salutation = "Salut";
        if (nom != null && !nom.isBlank()) {
            salutation = nom + " Tu es ou?";
        }
        respWriter.print(salutation);
        respWriter.close();
    }
}
