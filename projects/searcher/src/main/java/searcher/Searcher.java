package searcher;

import common.DocInfo;
import common.Result;
import index.Index;
import org.ansj.domain.Term;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.ansj.splitWord.analysis.DicAnalysis.parse;

public class Searcher {
    Index index = new Index();

    public Searcher() throws IOException {
        index.build("f:/data.txt");
    }

    public List<Result> search(String query) {
        List<Result> res = new ArrayList<>();

        //1.对查询词分词
        List<Term> queryTerms = parse(query).getTerms();
        //2.针对分词结果一次查找倒排索引
        List<Index.Weight> allToken = new ArrayList<>();
        for (Term term : queryTerms) {
            String word = term.getName();
            List<Index.Weight> invertedList = index.getInverted(word);
            allToken.addAll(invertedList);
        }

        //3.按照权重排序
        allToken.sort(((o1, o2) -> o2.weight-o1.weight));

        //4.封装成Result
        for (Index.Weight weight : allToken) {
            DocInfo docInfo = index.getDocInfo(weight.docId);
            Result result = new Result();
            result.setTitle(docInfo.getTitle());
            result.setShowUrl(docInfo.getUrl());
            result.setClickUrl(docInfo.getUrl());
            result.setDesc(genDesc(docInfo.getContent(), weight.word));
            res.add(result);
        }
        return res;
    }

    //获取正文的一部份内容作为描述
    public String genDesc(String content, String world) {
        int pos = content.toLowerCase().indexOf(world);
        if (pos == -1) {
            return "";
        }

        //描述开始：从 firstPos 开始往前找 60 个字符，若不足，则从正文开头开始
        int descBeg = pos-60>0?pos-60:0;
        //描述结束：从 firstPos 开始往后找 100 个字符，若不足，则以正文结尾结束
        int descEnd = pos+100>content.length()?content.length():pos+100;

        return content.substring(descBeg, descEnd);
    }
}
