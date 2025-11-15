import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/listTasks")
public class ListTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>All Tasks</h1>");
        out.println("<a href='index.html'>Add New Task</a><br><br>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo_app","root","password"
            );

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

            out.println("<table>");
            out.println("<tr><th>ID</th><th>Task</th><th>Date</th><th>Actions</th></tr>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getDate("date") + "</td>");
                out.println("<td>"
                        + "<a href='editTask?id=" + rs.getInt("id") + "'>Edit</a> | "
                        + "<a href='deleteTask?id=" + rs.getInt("id") + "'>Delete</a>"
                        + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
