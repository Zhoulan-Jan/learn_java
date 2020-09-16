package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Result;
import searcher.Searcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/search", loadOnStartup = 1)

public class SearcherServlet extends HttpServlet {
    Searcher searcher = new Searcher();
    private Gson gson = new GsonBuilder().create();

    public SearcherServlet() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; character=utf-8");
        String query = req.getParameter("query");

        if (query == null || query.equals("")) {
            resp.setStatus(404);
            resp.getWriter().println("参数错误");
            return;
        }

        List<Result> results = searcher.search(query);
        String respStr = gson.toJson(results);
        resp.getWriter().write(respStr);
    }
}
