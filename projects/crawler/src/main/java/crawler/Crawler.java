package crawler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dao.Project;
import dao.ProjectDAO;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Crawler {
    private OkHttpClient okHttpClient = new OkHttpClient();
    private List<String> blackUrl = new ArrayList<>();
    private Gson gson = new GsonBuilder().create();

    {
        blackUrl.add("https://github.com/events");
        blackUrl.add("https://github.com/site/terms");
        blackUrl.add("https://github.com/site/privacy");
        blackUrl.add("https://github.com/security");
        blackUrl.add("https://github.com/contact");
        blackUrl.add("https://github.com/pricing");
        blackUrl.add("https://github.com/about");
    }

    public static void main(String[] args) throws IOException {
        //1.获取入口页面
        Crawler crawler = new Crawler();
        String respBody = crawler.getPage("https://github.com/akullpp/awesome-java/blob/master/README.md");
        //System.out.println(respBody);

        //2.解析入口页面，获取项目列表
        List<Project> projects = crawler.getProjectList(respBody);

        //3.遍历项目列表，调用 GitHub api获取项目信息
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            //获取仓库摘要
            String repoName = crawler.getRepoName(project.getUrl());
            //获取 JSON形式的仓库信息
            String jsonStr = crawler.getRepoInfo(repoName);
//            System.out.println(jsonStr);
//            System.out.println("============");

            //4.解析 JSON 数据，得到 star 等信息
            crawler.parseRepoInfo(jsonStr, project);
        }

        //5.把 project 保存到数据库中
        ProjectDAO dao = new ProjectDAO();
        for (Project project : projects) {
            dao.save(project);
        }

//        crawler.Crawler crawler = new crawler.Crawler();
//        System.out.println(crawler.getRepoName("https://github.com/doov-io/doov"));
//        System.out.println(crawler.getRepoName("https://github.com/networknt/light-4j/"));
//        System.out.println(crawler.getRepoName("https://github.com/codenameone/CodenameOne/tree/master/vm"));
    }

    //通过 OkHttp 获取到网页的 HTML 结构
    public String getPage(String url) throws IOException {
        //1.创建 Client 对象 （该对象只要一个程序包含一个就行）

        //2.创建一个 Request 对象
        Request request = new Request.Builder().url(url).build();

        //3.创建一个 Call 对象，负责进行一次网络访问操作
        Call call = okHttpClient.newCall(request);

        //4.发送请求给服务器，获取到 Response 对象
        Response response = call.execute();

        //5.判断响应是否成功
        if (!response.isSuccessful()) {
            System.out.println("请求失败");
            return null;
        }
        return response.body().string();
    }

    //使用 Jsoup分析页面结构，得到项目的信息
    public List<Project> getProjectList(String html) {
        List<Project> res = new ArrayList<>();
        //1.创建 Document 对象，把 HTML 字符串转换为 Document 对象
        Document document =  Jsoup.parse(html);

        //2.使用 getElementByTag 方法获取到所有的 li 标签
        Elements elements = document.getElementsByTag("li");

        //3.再获取 li 标签中的 a 标签
        for (Element li : elements) {
            Elements allLink = li.getElementsByTag("a");
            if (allLink == null || allLink.size() != 1) {
                continue;
            }
            Element link = allLink.get(0);
            String url = link.attr("href");
            if (!url.startsWith("https://github.com/")) {
                continue;
            }
            if (blackUrl.contains(url)) {
                continue;
            }
//            System.out.println(link.text());
//            System.out.println(link.attr("href"));
//            System.out.println(li.text());

            //4.获取标签中的内容保存在 project 对象中
            Project project = new Project();
            project.setName(link.text());
            project.setUrl(link.attr("href"));
            project.setDescription(li.text());
            res.add(project);
        }
        return res;
    }

    //根据项目链接提取项目的仓库摘要（即仓库名和作者名）
    public String getRepoName(String url) {
        int first = ("https://github.com/").length();
        int tmp = url.indexOf("/", first+1);
        int second = url.indexOf("/", tmp+1);
        if (first != -1 && tmp != -1) {
            if (second == -1) {
                return url.substring(first);
            } else {
                System.out.println("特殊url = " + url);
                return url.substring(first, second);
            }
        }
        System.out.println("当前的url不是标准的：" + url);
        return null;
    }

    //调用GitHub API 根据仓库摘要获取 JSON形式的仓库信息
    public String getRepoInfo(String repoName) throws IOException {
        String userName = "Zhoulan-Jan";
        String passwd = "Nina752426";
        String credential = Credentials.basic(userName, passwd);
        String url = "https://api.github.com/repos/" + repoName;
        Request request = new Request.Builder().url(url).header("Authorization", credential).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            System.out.println("访问 GitHub API 失败");
            return null;
        }
        return response.body().string();
    }

    public void parseRepoInfo(String jsonStr, Project project) {
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> map = gson.fromJson(jsonStr, type);

        Double starCnt = (Double) map.get("stargazers_count");
        project.setStarCnt(starCnt.intValue());
        Double forkCnt = (Double)map.get("forks_count");
        project.setForkCnt(forkCnt.intValue());
        Double issuesCnt = (Double)map.get("open_issues_count");
        project.setIssueCnt(issuesCnt.intValue());
    }

}
