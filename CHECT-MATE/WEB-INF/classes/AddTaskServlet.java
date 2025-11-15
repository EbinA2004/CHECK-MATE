import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String taskName = request.getParameter("taskName");
        String taskDate = request.getParameter("taskDate");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo_app","root","password"
            );

            String query = "INSERT INTO tasks (name, date) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, taskName);
            ps.setString(2, taskDate);
            ps.executeUpdate();

            response.sendRedirect("listTasks");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
