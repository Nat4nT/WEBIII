package ifpr.atividade2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cabecalhos")
public class CabecalhosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter resp = response.getWriter();

        String host = request.getHeader("Host");
        String userAgent = request.getHeader("User-Agent");
        String acceptEncoding = request.getHeader("Accept-Encoding");
        String language = request.getHeader("Accept-Language");

        resp.println("<html>");
        resp.println("Host:" + host + "<br>User-Agent: "+ userAgent + "<br>Accept-Encoding: " + acceptEncoding +"<br>Accpet-Language: "+language);
        resp.println("</html>");

    }
}
