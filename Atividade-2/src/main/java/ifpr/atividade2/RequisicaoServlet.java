package ifpr.atividade2;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/requisicao")
public class RequisicaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter resp = response.getWriter();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String protocol = request.getProtocol();
        String remoteAddr = request.getRemoteAddr();

        resp.println("<html>");
        resp.println("Metodo: "+method+"<br>URI: "+requestURI+"<br>Protocolo: "+protocol+"<br>Endereco Remoto: "+remoteAddr);
        resp.println("</html>");

    }
}
