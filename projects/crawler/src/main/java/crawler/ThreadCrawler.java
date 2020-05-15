package crawler;

import dao.Project;
import dao.ProjectDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//使用多线程方式组织核心逻辑（访问 GitHub api 并行式）
public class ThreadCrawler extends Crawler {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        ThreadCrawler crawler = new ThreadCrawler();
        String html = crawler.getPage("https://github.com/akullpp/awesome-java/blob/master/README.md");
        List<Project> projects = crawler.getProjectList(html);

        long startCallApiTIme = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //Executors.newCachedThreadPool(); //线程数量没有上限
        //executorService 有两种提交任务的操作：
        //a）execute：不关注任务的结果
        //b）submit：关注任务的结果
        List<Future<?>> taskResults = new ArrayList<>();
        for (Project project : projects) {
            Future<?> taskRes = executorService.submit(new CrawlerTask(project, crawler));
            taskResults.add(taskRes);
        }
        //等待所有线程池的任务结束
        for (Future<?> taskRes : taskResults) {
            try {
                taskRes.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //所有线程执行完毕，结束线程池
        executorService.shutdown();

        long finishCallApiTime = System.currentTimeMillis();
        System.out.println("调用 api 的时间：" + (finishCallApiTime - startCallApiTIme) + " ms");

        //4.保存到数据库
        ProjectDAO projectDao = new ProjectDAO();
        for (Project project : projects) {
            projectDao.save(project);
        }

        long finishTime = System.currentTimeMillis();
        System.out.println("整体时间：" + (finishTime - startTime) + " ms");
    }

    static class CrawlerTask implements Runnable {
        private Project project;
        private ThreadCrawler threadCrawler;

        public CrawlerTask(Project project, ThreadCrawler threadCrawler) {
            this.project = project;
            this.threadCrawler = threadCrawler;
        }

        @Override
        public void run() {
            try {
                //1.调用 API 获取数据
                String repoName = threadCrawler.getRepoName(project.getUrl());
                String jsonStr = threadCrawler.getRepoInfo(repoName);
                //2.分析项目数据
                threadCrawler.parseRepoInfo(jsonStr, project);
                System.out.println("crawling " + repoName + " done!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
