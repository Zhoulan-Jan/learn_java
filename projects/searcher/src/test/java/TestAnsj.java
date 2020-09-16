import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

public class TestAnsj {
    public static void main(String[] args) {
        String str = "请各位同学今天按时在易班上进行健康信息填报，大家每天一定不要把这件事给忘了，可以设置个闹钟提醒。" +
                " 非常时期，感谢大家对学校工作的支持！另外提醒一下，大家一定要确认提交后再退出。" +
                "因为每天提交得数据是作为开学返校前的重要参考数据，因个人不按时数据不完整造成影响顺利返校责任自负。";
        List<Term> terms = ToAnalysis.parse(str).getTerms();
        for (Term term : terms) {
            System.out.println(term);
        }
    }
}
