package billboard;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

@WebServlet("/billboard/search")
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // paramsの取得
        String keyword = request.getParameter("keyword");

        // レスポンスの取得準備
        StringBuilder builder = new StringBuilder();
        Spotify spotify = Spotify.getInstance();
        try {
            spotify.crateAccessToken();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // レスポンスの取得
        builder.append(spotify.search(keyword));

        // jsonの格納
        String json = builder.toString();

        // レスポンスの送信
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.append(json);
        writer.flush();

    }

}
