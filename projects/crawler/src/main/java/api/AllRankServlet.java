package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Project;
import dao.ProjectDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet("/allRank")
public class AllRankServlet extends HttpServlet {
   private Gson gson = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String date = req.getParameter("date");
        if (date == null || date.equals("")) {
            resp.setStatus(404);
            resp.getWriter().println("日期错误");
            return;
        }
        ProjectDAO dao = new ProjectDAO();
        List<Project> projects = dao.selectProjectByDate(date);

        String  respStr = gson.toJson(projects);
        resp.getWriter().write(respStr);
    }
}
