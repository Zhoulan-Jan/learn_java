package index;

import common.DocInfo;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//索引类 包括正排索引、倒排索引
//正排：根据文档 id 查到文档信息
//倒排：根据关键词找到 含关键词的全部文档
public class Index {
    //word 这个词在 docId 文档中对应的权重是多少
    static public class Weight {
        public String word;
        public int docId;
        public int weight; // weight 生成公式: 10 * 标题中出现的次数 + 正文中出现的次数

        @Override
        public String toString() {
            return "Weight{" +
                    "word='" + word + '\'' +
                    ", docId=" + docId +
                    ", weight=" + weight +
                    '}';
        }
    }

    //正排索引
    public ArrayList<DocInfo> forwardIndex = new ArrayList<>();
    //倒排索引
    public Map<String, ArrayList<Weight>> invertedIndex = new HashMap<>();

    //查正排
    public DocInfo getDocInfo(int docId) {
        return forwardIndex.get(docId);
    }

    //查倒排
    public ArrayList<Weight> getInverted(String term) {
        return invertedIndex.get(term);
    }

    //构建索引，把 raw_data.txt 文件内容读出来，加载到上述的数据结构中
    public void build(String inputPath) throws IOException {
        //1.打开文件，按行读取文件内容
        BufferedReader br = new BufferedReader(new FileReader(new File(inputPath)));
        //2.读取到的每一行，
        String line = "";
        while ((line = br.readLine()) != null) {
            //3.构造正排
            DocInfo docInfo = buildForward(line);
            //4.构造倒排
            buildInverted(docInfo);
        }
        br.close();
    }

    //构造正排：按照 \3 切分，并将结果构造成一个 DocInfo 对象，加入到正排索引
    public DocInfo buildForward(String line) {
        String[] tokens = line.split("\3");
        if (tokens.length != 3) {
            System.out.println("文档格式存在问题");
            return null;
        }

        DocInfo docInfo = new DocInfo();
        docInfo.setDocId(forwardIndex.size());
        docInfo.setTitle(tokens[0]);
        docInfo.setUrl(tokens[1]);
        docInfo.setContent(tokens[2]);
        forwardIndex.add(docInfo);

        return docInfo;
    }

    //构造倒排：把 DocInfo 对象里面内容（title、content）进一步处理，构造倒排索引
    public void buildInverted(DocInfo docInfo) {
        class WordCnt {
            int titleCnt;
            int contentCnt;

            public WordCnt(int titleCnt, int contentCnt) {
                this.titleCnt = titleCnt;
                this.contentCnt = contentCnt;
            }

        }

        HashMap<String, WordCnt> wordCntHashMap = new HashMap<>();

        //1.标题分词
        List<Term> titleTerms = ToAnalysis.parse(docInfo.getTitle()).getTerms();
        //2.遍历标题分词结果，统计每个词出现次数
        for (Term term : titleTerms) {
            String word = term.getName();
            WordCnt wordCnt = wordCntHashMap.get(word);
            if (wordCnt != null) { //当前这个词在哈希表中存在，修改 titleCnt 即可
                wordCnt.titleCnt++;
            } else { //当前这个词在哈希表中不存在
                wordCntHashMap.put(word, new WordCnt(1, 0));
            }
        }

        //3.正文分词
        List<Term> contentTerms = ToAnalysis.parse(docInfo.getContent()).getTerms();
        //4.遍历标题分词结果，统计每个词出现次数
        for (Term term : contentTerms) {
            String word = term.getName();
            WordCnt wordCnt = wordCntHashMap.get(word);
            if (wordCnt != null) {
                wordCnt.contentCnt++;
            } else {
                wordCntHashMap.put(word, new WordCnt(0, 1));
            }
        }

        //5.遍历 HashMap，依次构建 Weight 对象并更新倒排索引的映射关系
        for (HashMap.Entry<String, WordCnt> entry : wordCntHashMap.entrySet()) {

            Weight weight = new Weight();
            weight.docId = docInfo.getDocId();
            weight.word = entry.getKey();
            weight.weight = entry.getValue().titleCnt*10+entry.getValue().contentCnt;

            //Weight 加入到倒排索引中
            ArrayList<Weight> weights = invertedIndex.get(entry.getKey());
            if (weights != null) {
                weights.add(weight);
            } else {
                weights = new ArrayList<>();
                invertedIndex.put(entry.getKey(), weights);
            }

        }

    }
}

