import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo_app","root","password"
            );

            PreparedStatement ps = con.prepareStatement("DELETE FROM tasks WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            response.sendRedirect("listTasks");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
