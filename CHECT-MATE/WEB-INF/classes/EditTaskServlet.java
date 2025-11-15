import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo_app","root","password"
            );

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tasks WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                out.println("<h1>Edit Task</h1>");
                out.println("<form action='updateTask' method='post'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<input type='text' name='taskName' value='" + rs.getString("name") + "' required>");
                out.println("<input type='date' name='taskDate' value='" + rs.getDate("date") + "'>");
                out.println("<button type='submit'>Update Task</button>");
                out.println("</form>");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
