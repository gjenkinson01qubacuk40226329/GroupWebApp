package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.h2.mvstore.MVMap;

import model.Article;
import model.ArticleSubSection;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class Output40232871 extends DynamicWebPage
{
	public Output40232871(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
        if(toProcess.path.equalsIgnoreCase("Output40232871"))
        {
        	String stringToSendToWebBrowser = " ";
        	MVMap<String, Article> articles = db.s.openMap("Articles");
        	MVMap<String, ArticleSubSection> subSections = db.s.openMap("SubSection");
        	List<String> ArticleKeys = articles.keyList();
        	List<String> SubSectionKeys = subSections.keyList();
        	
        	for(int i =0; i<ArticleKeys.size();i++) {
        		String ArticleID= ArticleKeys.get(i);
        		ArrayList<ArticleSubSection> subSectionList = getSubSectionsForArticle(ArticleID,subSections,SubSectionKeys);
        		Article art1 = articles.get(ArticleID);
        		int size_subsect = subSectionList.size();
        		if(art1.filepathToImages.equals("NoImageUploaded")||!(art1.filepathToImages!=null)) {
					stringToSendToWebBrowser+="<h1>"+art1.articleTitle+"</h1><br><p>"+art1.articleDescription+"</p><br><br>";
				}
				else {
				
					stringToSendToWebBrowser+="<h1>"+art1.articleTitle+"</h1><br>";
					stringToSendToWebBrowser+="<img src=\"/"+art1.filepathToImages+"\"\n\r>";
					stringToSendToWebBrowser+="class\"img-responsive\">\n";
					stringToSendToWebBrowser+="<p>"+art1.articleDescription+"</p><br><br>";
				}
				for (ArticleSubSection articleSubSection : subSectionList) {
					if(articleSubSection.filepathToImage.equals("NoImageUploaded")||articleSubSection.filepathToImage==null){
					stringToSendToWebBrowser+="<h2>"+articleSubSection.sectionHeading +" </h2>";
					stringToSendToWebBrowser+="<p>"+articleSubSection.sectionText +"</p><br>";
					}
					else {
						stringToSendToWebBrowser+="<h2>"+articleSubSection.sectionHeading +" </h2>";
						stringToSendToWebBrowser+="<img src=\"/"+articleSubSection.filepathToImage+"\"\n\r>";
						stringToSendToWebBrowser+="class\"img-responsive\">\n";
						stringToSendToWebBrowser+="<p>"+articleSubSection.sectionText +"</p><br>";
					}
				}
        		stringToSendToWebBrowser+="<h3>END OF ARTICLE Number "+(i+1)+"</h3>";
        		
        	}
        	toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

        	return true;
        }
        return false;
	}

	private ArrayList<ArticleSubSection> getSubSectionsForArticle(String articleID, MVMap<String, ArticleSubSection> subSections, List<String> subSectionKeys) {
		// TODO Auto-generated method stub
		ArrayList<ArticleSubSection> tempSubSectionList = new ArrayList<ArticleSubSection>();
		ArticleSubSection articleSubSection1=new ArticleSubSection();
		ArrayList<ArticleSubSection> allArticleSubsections=new ArrayList<ArticleSubSection>();
		for (int i = 0; i < subSectionKeys.size(); i++) {
			articleSubSection1.UniqueString = subSectionKeys.get(i);
			articleSubSection1 = subSections.get(articleSubSection1.UniqueString);
			if(articleSubSection1.ArticleIDString.equals(articleID)) {
				tempSubSectionList.add(articleSubSection1);
			}
			allArticleSubsections.add(articleSubSection1);
		}/*
		Collections.sort(tempSubSectionList, new Comparator<ArticleSubSection>() {
			@Override public int compare(ArticleSubSection a1, ArticleSubSection a2) {
				return a1.sectionNumber - a2.sectionNumber;//sorts list into accending order
			}
		
		} );*/
		return tempSubSectionList;
	}

}
