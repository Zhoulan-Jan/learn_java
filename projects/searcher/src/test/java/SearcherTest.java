import common.Result;
import searcher.Searcher;

import java.io.IOException;
import java.util.List;

public class SearcherTest {
    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher();
        List<Result> results= searcher.search("Arraylist");
        for (Result result : results) {
            System.out.println(result);
            System.out.println("============");
        }
    }

}