package ifpr.atividade3;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private int enters;

    public void init() {
        message = "Hello World!";
        enters=0;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        enters++;
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println(request.getMethod());

        out.println("<h3> O usuario carregou essa pagina " + enters + " vezes </h3>");
        out.println("</body></html>");


    }

    public void destroy() {
        System.out.println("Entrou no destroy");
    }
}