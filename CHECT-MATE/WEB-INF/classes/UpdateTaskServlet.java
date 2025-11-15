import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/updateTask")
public class UpdateTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String taskName = request.getParameter("taskName");
        String taskDate = request.getParameter("taskDate");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo_app","root","password"
            );

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE tasks SET name=?, date=? WHERE id=?"
            );
            ps.setString(1, taskName);
            ps.setString(2, taskDate);
            ps.setInt(3, id);
            ps.executeUpdate();

            response.sendRedirect("listTasks");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
