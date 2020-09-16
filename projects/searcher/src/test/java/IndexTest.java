import common.DocInfo;
import index.Index;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexTest {
    public static void main(String[] args) throws IOException {
        Index index = new Index();
        index.build("f://data.txt");
        List<Index.Weight> invertedList = index.getInverted("arraylist");
        for (Index.Weight weight : invertedList) {
            System.out.println("weight.docId " + weight.docId);
            System.out.println("weight.word " + weight.word);
            System.out.println("weight.weight " + weight.weight);
            DocInfo docInfo = index.getDocInfo(weight.docId);
            System.out.println("docInfo.getTitle  " + docInfo.getTitle());
            System.out.println("=====================================");
        }
    }


//    @Test
//    public void getDocInfo() {
//    }
//
//    @Test
//    public void getWeight() {
//    }
//
//    @Test
//    public void build()  {
//
//    }
//
    @Test
    public void buildForward() {
        Index index = new Index();

        String line = "hello \3 happy \3 exit happy exit hello hello world \3";
        DocInfo docInfo = index.buildForward(line);
        System.out.println(docInfo);
    }

    @Test
    public void buildInverted() {
        Index index = new Index();
//        index.forwardIndex = new ArrayList<common.DocInfo>();
//        index.invertedIndex = new HashMap<String, ArrayList<index.Index.Weight>>();

        String line = "exut \3 happy \3 exit happy exit  hello world \n";
        DocInfo docInfo = index.buildForward(line);
        index.buildInverted(docInfo);
        System.out.println(docInfo);

        String line2 = "exit \3 happy \3 exit  hello hello world \n";
        DocInfo docInfo2 = index.buildForward(line2);
        index.buildInverted(docInfo2);
        System.out.println(docInfo2);

        String line3 = "exit \3 happy \3 exit  hello world \n";
        DocInfo docInfo3 = index.buildForward(line3);
        index.buildInverted(docInfo3);
        System.out.println(docInfo3);

        ArrayList<Index.Weight> weights = index.getInverted("hello"); //只有第一个位置不显示
        for (Index.Weight weight : weights) {
            System.out.println(weight);
        }

    }
}