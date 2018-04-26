package org.grobid.Chinese;

import org.grobid.core.data.BiblioItem;
import org.grobid.core.engines.Engine;
import org.grobid.core.factory.GrobidFactory;
import org.grobid.core.main.GrobidHomeFinder;
import org.grobid.core.utilities.GrobidProperties;

import java.util.Arrays;

public class MyGrobid {
    private static Engine engine;

    public static String runGrobid(String pdfPath) {
        String tei = "";
        try {
            String pGrobidHome = "/Users/vinci/lib/grobid/grobid-home";

            // The GrobidHomeFinder can be instantiate without parameters to verify the
            // grobid home in the standard
            // location (classpath, ../grobid-home, ../../grobid-home)

            // If the location is customised:
            GrobidHomeFinder grobidHomeFinder = new GrobidHomeFinder(Arrays.asList(pGrobidHome));

            // The GrobidProperties needs to be instantiate using the correct
            // grobidHomeFinder or it will use the default
            // locations
            GrobidProperties.getInstance(grobidHomeFinder);

            System.out.println(">>>>>>>> GROBID_HOME=" + GrobidProperties.get_GROBID_HOME_PATH());

            Engine engine = GrobidFactory.getInstance().createEngine();
            // Biblio object for the result
            BiblioItem resHeader = new BiblioItem();
//			engine.batchProcessFulltext("/Users/vinci/lib/Papers/Input/","/Users/vinci/lib/Papers/Output_fulltext",false,false);
            tei = engine.processHeader(pdfPath, false, resHeader);
//			File pdf = new File(pdfPath);
//			String tei = engine.segmentAndProcessHeader(pdf	, false, resHeader);
            System.out.println(tei);

        } catch (Exception e) {
            // If an exception is generated, print a stack trace
            e.printStackTrace();
        }
        return tei;

    }

    public static void main(String[] args) {
        runGrobid("/Users/vinci/lib/Papers/Input/CAR001.pdf");
//		runGrobid("/Users/vinci/lib/保险文档/中宏附加康健逸生重大疾病保险_中宏人寿[2017]疾病保险010号.pdf");
    }

}