package org.grobid.Chinese;

import org.grobid.core.data.BiblioItem;
import org.grobid.core.engines.Engine;
import org.grobid.core.factory.GrobidFactory;
import org.grobid.core.main.GrobidHomeFinder;
import org.grobid.core.utilities.GrobidProperties;

import java.util.Arrays;

public class MyGrobid {
    private static Engine engine;

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
    public MyGrobid() {
        new MyGrobid("/Users/vinci/lib/grobid/grobid-home");
    }

    /**
     * 自定义Grobid-home 地址
     *
     * @param grobidHome
     */
    public MyGrobid(String grobidHome) {
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


    /**
     * 输入的文件默认解析头部
     *
     * @param pdfPath
     * @return
     */
    public static String runGrobid(String pdfPath) {
        String tei = "";
        try {
            engine = GrobidFactory.getInstance().createEngine();
            // Biblio object for the result
            BiblioItem resHeader = new BiblioItem();
//			engine.batchProcessFulltext("/Users/vinci/lib/Papers/Input/","/Users/vinci/lib/Papers/Output_fulltext",false,false);
            tei = engine.processHeader(pdfPath, false, resHeader);
//			File pdf = new File(pdfPath);
//			String tei = engine.segmentAndProcessHeader(pdf	, false, resHeader);
//          System.out.println(tei);

        } catch (Exception e) {
            // If an exception is generated, print a stack trace
            e.printStackTrace();
        }
        return tei;
    }


    public static void runGrobid(String pdfDir, String outputDir, boolean consolidateHeader, boolean consolidateCitations) {
        engine = GrobidFactory.getInstance().createEngine();
        int i = engine.batchProcessFulltext(pdfDir, outputDir, consolidateHeader, consolidateCitations);
        System.out.println(i + " 个文件被处理！！！！！！！！！！！！！！！！！！！！！！！");
        System.out.println(i + " 个文件被处理！！！！！！！！！！！！！！！！！！！！！！！");
        System.out.println(i + " 个文件被处理！！！！！！！！！！！！！！！！！！！！！！！");
    }


    public static void main(String[] args) {
//        MyGrobid.runGrobid();
        runGrobid("/Users/vinci/lib/Papers/Input/CAR001.pdf");
//		runGrobid("/Users/vinci/lib/保险文档/中宏附加康健逸生重大疾病保险_中宏人寿[2017]疾病保险010号.pdf");
    }

}