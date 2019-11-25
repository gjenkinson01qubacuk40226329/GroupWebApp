package views;

import java.awt.List;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;

import org.h2.mvstore.MVMap;

import model.Article;
import model.ArticleSubSection;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.*;

public class AddEditView extends DynamicWebPage {

	int noSections=1;
	Article ArticleInfo=new Article();
	ArrayList<ArticleSubSection> subSections = new ArrayList<ArticleSubSection>();
	public AddEditView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	public boolean process(WebRequest toProcess){
		if(toProcess.path.equalsIgnoreCase("AddEditView"))
		{
			String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"  <meta charset=\"utf-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.2.1.css\">\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" +
					"<script>"+
					"function addSection()\r\n" + 
					"{\r\n" + 
					"Document.getElementById('appendHere').innerHTML = \"<div class=\\\"form-group\\\"> <label>Add Heading For This Section</label> <input type=\\\"text\\\" placeholder=\\\"Section Heading\\\" name=\\\"SectionHeading1\\\" class=\\\"form-control\\\"> </div>\\r\\n\" + \r\n" + 
					"					\"            <div class=\\\"form-group\\\"> <label>Section Text</label> <input type=\\\"text\\\" name=\\\"ArticleBody1\\\" class=\\\"form-control h-25 flex-grow-1\\\" placeholder=\\\"Add the text for this section off your article here.\\\">\\r\\n\" + \r\n" + 
					"					\"            </div>\";\r\n" + 
					"}"+
					"</script>"+
					"  <div class=\"py-5\" style=\"\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row\">\r\n" + 
					"        <div class=\"col-md-12\">\r\n" + 
					"          <h1 class=\"display-4\" style=\"\">Add &amp; Edit<br></h1>\r\n" + 
					"          <nav class=\"navbar navbar-expand-md navbar-light\">\r\n" + 
					"            <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar3\">\r\n" + 
					"                <span class=\"navbar-toggler-icon\"></span>\r\n" + 
					"              </button>\r\n" + 
					"              <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar3\">\r\n" + 
					"                <ul class=\"navbar-nav\">\r\n" + 
					"                  <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"home40238855.html\">Home Page</a> </li>\r\n" + 
					"                  <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"createaccount40234553.html\">Account Page</a> </li>\r\n" + 
					"                  <li class=\"nav-item mx-2\"> <a class=\"nav-link navbar-brand mr-0 text-primary\" href=\"#\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i>\r\n" + 
					"                      <b> HOW ITS MADE</b></a> </li>\r\n" + 
					"                  <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"#\">About us</a> </li>\r\n" + 
					"                  <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"#\" contenteditable=\"true\">Add &amp; Edit Articles</a> </li>\r\n" + 
					"                </ul>\r\n" + 
					"              </div>\r\n" + 
					"            </div>\r\n" + 
					"          </nav>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" +
					
					"  <form action=\"\" method=\"GET\">\r\n" + 
					"    <div class=\"py-5\">\r\n" + 
					"      <div class=\"container\">\r\n" + 
					"        <div class=\"row\">\r\n" + 
					"          <div class=\"col-md-12\">\r\n" ;
						
					if(noSections==1) {
								stringToSendToWebBrowser+="            <div class=\"form-group\"> <label contenteditable=\"true\">Add Title Of New Topic</label> <input type=\"text\" name=\"Title\" class=\"form-control\" placeholder=\"Topic Title\"> </div>\r\n" + 
										"            <div class=\"form-group\"> <label>Topic Description</label> <input type=\"text\" name=\"ArticleDescription\" class=\"form-control h-25\" placeholder=\"Add a short description of your article here.\" style = \" height:420px; \">\r\n" + 
										"            </div>\r\n"+
										"            <div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" name = \"SectionHeading1\" class=\"form-control\" placeholder=\"Section Heading\"> </div>\r\n" + 
										"            <div class=\"form-group\"> <label>Section Text</label> <input type=\"text\" name=\"SectionBody1\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" style = \" height:420px \">\r\n"; 
							}
							else {
								stringToSendToWebBrowser+="            <div class=\"form-group\"> <label contenteditable=\"true\">Add Title Of New Topic</label> <input type=\"text\" name=\"Title\" class=\"form-control\" value=\""+ArticleInfo.articleTitle +"\"> </div>\r\n" + 
										"            <div class=\"form-group\"> <label>Topic Description</label> <input type=\"text\" name=\"ArticleDescription\" class=\"form-control h-25\" value=\""+ArticleInfo.articleDescription+"\"style = \" height:420px; \">\r\n" + 
										"            </div>\r\n";
								for(int i = 0; i<noSections-1;i++) {
									stringToSendToWebBrowser+="            <div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" name = \"SectionHeading"+(i+1)+"\" class=\"form-control\" value=\""+subSections.get(i).sectionHeading+"\"> </div>\r\n" + 
											"            <div class=\"form-group\"> <label>Section Text</label> <input type=\"text\" name=\"SectionBody"+(i+1)+"\" class=\"form-control h-25 flex-grow-1\" value=\""+subSections.get(i).sectionText+"\" style = \" height:420px; \">\r\n"; 
								}
							
							stringToSendToWebBrowser+="            <div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" name = \"SectionHeading"+noSections+"\" class=\"form-control\" placeholder=\"Section Heading\" name=\"Heading\" > </div>\r\n" + 
							"            <div class=\"form-group\"> <label>Section Text</label> <input type=\"text\" name=\"SectionBody"+noSections+"\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" style = \" height:420px; \">\r\n"; 
							}
							stringToSendToWebBrowser+="<button type=\"submit\" class=\"btn btn-primary\" contenteditable=\"false\" formaction= \"/addNewSection\">Add New Section</button><button type=\"submit\" class=\"btn btn-primary\" contenteditable=\"false\" formaction=\"/addArticleProcess\">Submit Topic</button>\r\n"+ 
							"          </div>\r\n" + 
							"        </div>\r\n" + 
							"      </div>\r\n" + 
							"    </div>\r\n" + 
							"  </form>\r\n" + 
							"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
							"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
							"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
							"</body>\r\n" + 
							"\r\n" + 
							"</html>";
			toProcess.r = new WebResponse( WebResponse.HTTP_OK,WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		else if(toProcess.path.equalsIgnoreCase("addArticleProcess")) {
			ArticleSubSection ASS = new ArticleSubSection();
			Article article1 = new Article();
			article1.uniqueString="Article_"+System.currentTimeMillis();
			article1.articleTitle=toProcess.params.get("Title");
			article1.articleDescription=toProcess.params.get("ArticleDescription");
			
			//Add Article To The Database
			MVMap<String, Article> articles = db.s.openMap("Articles");
			articles.put(article1.uniqueString, article1);
			MVMap<String, ArticleSubSection> subsections= db.s.openMap("SubSection");
			subSections.clear();
			for(int i = 1; i<=noSections;i++) {
				ArticleSubSection articleSubSection = new ArticleSubSection();
				articleSubSection.UniqueString = "Sub_Section_"+System.currentTimeMillis();
				articleSubSection.sectionHeading=toProcess.params.get("SectionHeading"+i);
				articleSubSection.sectionText=toProcess.params.get("SectionBody"+i);
				articleSubSection.ArticleIDString=article1.uniqueString;
				articleSubSection.sectionNumber=i;
				subSections.add(articleSubSection);
				int size = subSections.size();
			}
			for (int i = 0; i < subSections.size(); i++) {
				ASS.UniqueString = "Sub_Section_"+System.currentTimeMillis();
				ASS.ArticleIDString=article1.uniqueString;
				ASS.sectionHeading=subSections.get(i).sectionHeading;
				ASS.sectionText=subSections.get(i).sectionText;
				ASS.sectionNumber=subSections.get(i).sectionNumber;
				subsections.put(ASS.UniqueString, ASS);
			}
			db.commit();
			String stringToSendToWebBrowser = "<html><body><p>Object added</p><a href=\"output40232871\">Display All Articles</a></body></html>";
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		else if(toProcess.path.equalsIgnoreCase("addNewSection")) {
			
			ArticleInfo.uniqueString="Article_"+System.currentTimeMillis();
			ArticleInfo.articleTitle=toProcess.params.get("Title");
			ArticleInfo.articleDescription=toProcess.params.get("ArticleDescription");
			subSections.clear();
			for(int i = 1; i<=noSections;i++) {
				ArticleSubSection articleSubSection = new ArticleSubSection();
				articleSubSection.ArticleIDString = ArticleInfo.uniqueString;
				articleSubSection.UniqueString = "Sub_Section_"+System.currentTimeMillis();
				articleSubSection.sectionHeading=toProcess.params.get("SectionHeading"+i);
				articleSubSection.sectionText=toProcess.params.get("SectionBody"+i);
				articleSubSection.sectionNumber=i;
				subSections.add(articleSubSection);
			}
			noSections+=1;
			String url = toProcess.header.get("referer");
			toProcess.r = new WebResponse( WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
									   "<html><body>Redirected: <a href=\"" + url + "\">" +
									   url + "</a></body></html>");
			toProcess.r.addHeader( "Location", url );
			return true;
		}
		return false;
	}
}
