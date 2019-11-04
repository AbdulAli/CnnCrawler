package nuces.aim.web;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import nuces.aim.crawler.cnn.CnnData;
import nuces.aim.crawler.twitter.TwitterData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;


public class GenerateWebPage {
	
	List<CnnData> cnnDataList;
	List<TwitterData> twitterDataList;
	
	public GenerateWebPage(List<CnnData> cData, List<TwitterData> tData){
		cnnDataList = cData;
		twitterDataList = tData;
	}

    public GenerateWebPage() {
    	cnnDataList = new ArrayList<CnnData>(0);
    	twitterDataList = new ArrayList<TwitterData>(0);
	}

	public void generateHTML() throws Exception {

    	// 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        @SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(GenerateWebPage.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("cnnDataList", cnnDataList);
        input.put("twitterDataList", twitterDataList);

        // 2.2. Get the template
        System.out.println();
        Template template = cfg.getTemplate("TrumpNewsTemplate.ftl");

        // 2.3. Generate the output
        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
            System.out.println("Webpage succesfully generated .... output.html");
        }

    }
}