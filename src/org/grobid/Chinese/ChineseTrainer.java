package org.grobid.Chinese;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

import org.grobid.core.*;
import org.grobid.core.data.*;
import org.grobid.core.factory.*;
import org.grobid.core.main.*;
import org.grobid.core.utilities.*;
import org.grobid.trainer.*;
import org.grobid.core.engines.Engine;

/**
 * @author Vinci
 */
public class ChineseTrainer {


    /**
     * 保险文档地址
     */
//    public ChineseTrainer() {
//        new ChineseTrainer("/Users/vinci/lib/grobid_insurance/grobid-home");
//    }

    /**
     * 默认GROBID-HOME地址：/Users/vinci/lib/grobid/grobid-home
     * 中文论文地址
     */
    public ChineseTrainer() {
        new ChineseTrainer("/Users/vinci/lib/grobid/grobid-home");
    }

    /**
     * 自定义Grobid-home 地址
     *
     * @param grobidHome
     */
    public ChineseTrainer(String grobidHome) {
        try {
            GrobidHomeFinder grobidHomeFinder = new GrobidHomeFinder(Arrays.asList(grobidHome));
            // The GrobidProperties needs to be instantiate using the correct
            // grobidHomeFinder or it will use the default locations
            GrobidProperties.getInstance(grobidHomeFinder);
            System.out.println(">>>>>>>> GROBID_HOME=" + GrobidProperties.get_GROBID_HOME_PATH());

        } catch (Exception e) {
            // If an exception is generated, print a stack trace
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        // 初始化工具（加载配置文件）
        new ChineseTrainer();
        // 初始化引擎
        Engine engine = GrobidFactory.getInstance().createEngine();
//         通过批处理获取训练数据
        int i = engine.batchCreateTraining("/Users/vinci/lib/Papers/Input", "/Users/vinci/lib/Papers/Output_保险", -1);
        System.out.println("有" + i + "个文件被处理");
//		AbstractTrainer.runTraining(new HeaderTrainer());
//		AbstractTrainer.runTraining(new SegmentationTrainer());
//      AbstractTrainer.runTraining(new FulltextTrainer());
//		AbstractTrainer.runTraining(new TableTrainer());
//		AbstractTrainer.runTraining(new FigureTrainer());
//		AbstractTrainer.runTraining(new AffiliationAddressTrainer());
//		AbstractTrainer.runTraining(new DateTrainer());
//		AbstractTrainer.runTraining(new NameHeaderTrainer());
//		AbstractTrainer.runTraining(new ReferenceSegmenterTrainer());

//		AbstractTrainer.runEvaluation(new HeaderTrainer());

    }

}
