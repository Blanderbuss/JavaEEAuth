import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        RequestUser user = RequestUser.fromRequestParameters(request);
        user.setAsRequestAttributes(request);
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            if(user.login.equals("admin") && user.pass.equals("admin")){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Авторизація</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Ласкаво просимо," + user.login + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }else{
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Авторизація</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Невірний пароль або логін</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        processRequest(request,response);
    }

    private static class RequestUser {

        final String login;
        final String pass;

        private RequestUser(String login, String pass) {
            this.login = login;
            this.pass = pass;
        }

        static RequestUser fromRequestParameters(
                HttpServletRequest request) {
            return new RequestUser(
                    request.getParameter("login"),
                    request.getParameter("pass"));
        }

        void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("login", login);
            request.setAttribute("pass", pass);
        }

    }

}