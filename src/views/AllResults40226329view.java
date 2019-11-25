package views;

import java.util.List;
import org.h2.mvstore.MVMap;
import model.Article;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class AllResults40226329view extends DynamicWebPage {

	public AllResults40226329view(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);

	}
	
	public static String embedSearchBox() {
		
		
		String stringToSendToWebBrowser = 	"    <div class=\"container\">\r\n" + 
				"      <div class=\"row\">\r\n" + 
				"        <div class=\"col-md-12\">\r\n" + 
				"          <form class=\"form-inline\" role=\"form\" method=\"GET\" action=\"AllResults40226329view\">\n" + 
				"            <div class=\"input-group\">\r\n" + 
				"              <input type=\"text\" class=\"form-control\" id=\"searchbox\" name=\"searchstring\" placeholder=\"Search\">\r\n" + 
				"              <div class=\"input-group-append\"><button class=\"btn btn-primary\" type=\"submit\"><i class=\"fa fa-search\"></i></button></div>\r\n" + 
				"            </div>\r\n" + 
				"          </form>\r\n" + 
				"        </div>\r\n" + 
				"      </div>\r\n" + 
				"  </div>";
		
		return stringToSendToWebBrowser;
	}
	
	public boolean process(WebRequest toProcess)
	{
	   if(toProcess.path.equalsIgnoreCase("AllResults40226329view"))
       {
	
		   String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					 "<title>Results</title>\r\n" +
					"  <meta charset=\"utf-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css\">\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body class=\"bg-light\" data-gr-c-s-loaded=\"true\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\">\r\n"  
					+ "  <nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\r\n"
					+ "    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\r\n"
					+ "        <span class=\"navbar-toggler-icon\"></span>\r\n" + "      </button>\r\n"
					+ "      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\r\n"
					+ "        <ul class=\"navbar-nav\">\r\n"
					+ "<li class=\"nav-item mx-2 text-dark\"> <a class=\"nav-link\" href=\"AddView\">Add Article </a> </li>\r\n"
					+ "          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"Login\">My Account</a> </li>\r\n"
					+ "<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>"
					+ "          <li class=\"nav-item mx-2 text-dark\"> </li>\r\n"
					+ "         <li class=\"nav-item mx-2 text-dark\"> </li>" + " <li> "
					+ AllResults40226329view.embedSearchBox() + "</li>\r\n" + "        </ul>\r\n" + "      </div>\r\n"
					+ "    </div>\r\n" + "  </nav>\r\n";
		   
		   
		   String searchstring = toProcess.params.get("searchstring").toLowerCase();
			MVMap<String, Article> article = db.s.openMap("Articles");
			List<String> articleKeys = article.keyList();
			for(int i = 0; i< articleKeys.size(); i++) {
				
				
					String articleUniqueId = articleKeys.get(i);
					Article allArticles = article.get(articleUniqueId);
				
					
					
					if(allArticles.articleTitle.toLowerCase().contains(searchstring) || allArticles.articleDescription.toLowerCase().contains(searchstring) || allArticles.type.toLowerCase().contains(searchstring))
					{					
						stringToSendToWebBrowser += "  <div class=\"py-5\">\r\n" + 
								"    <div class=\"container\">\r\n" + 
								"      <div class=\"row\">\r\n" + 
								"        <div class=\"col-md-12\">\r\n"+
								"          <div class=\"row\">\r\n" ;
								if(!allArticles.filepathToImages.equals("NoImageUploaded")) {
									stringToSendToWebBrowser+="            <div class=\"col-md-4\"><img class=\"img-fluid d-block\" src=\"" + allArticles.filepathToImages +"\"></div>\r\n" ; 
								}
						
								stringToSendToWebBrowser+="            <div class=\"col-md-8\">\r\n" + 
								"              <div class=\"row\">\r\n" + 
								"                <div class=\"col-md-12\">\r\n" + 
								"                  <h1 class=\"\"><a href=\"/SearchResultsPage40226329view?entry="+allArticles.uniqueString+"\">" + allArticles.articleTitle + "</a></h1>\r\n" + 
								"                </div>\r\n" + 
								"              </div>\r\n" + 
								"              <div class=\"row\">\r\n" + 
								"                <div class=\"col-md-12\">\r\n" + 
								"                  <h2 class=\"\"> " + allArticles.articleDescription + "</h2>\r\n" + 
								"                </div>\r\n" + 
								"              </div>\r\n" + 
								"            </div>\r\n" + 
								"          </div>\r\n" + 
								"        </div>\r\n" + 
								"      </div>\r\n" + 
								"    </div>\r\n" + 
								"  </div>\r\n" ;
						
				}
					
					else if(allArticles.type.toLowerCase().contains(searchstring))
					{					
						stringToSendToWebBrowser += "  <div class=\"py-5\">\r\n" + 
								"    <div class=\"container\">\r\n" + 
								"      <div class=\"row\">\r\n" + 
								"        <div class=\"col-md-12\">\r\n"+
								"          <div class=\"row\">\r\n" ;
								if(!allArticles.filepathToImages.equals("NoImageUploaded")) {
									stringToSendToWebBrowser+="            <div class=\"col-md-4\"><img class=\"img-fluid d-block\" src=\"https://static.pingendo.com/img-placeholder-1.svg\"></div>\r\n" ; 
								}
						
								stringToSendToWebBrowser+="            <div class=\"col-md-8\">\r\n" + 
								"              <div class=\"row\">\r\n" + 
								"                <div class=\"col-md-12\">\r\n" + 
								"                  <h1 class=\"\"><a href=\"/SearchResultsPage40226329view?entry="+allArticles.uniqueString+"\">" + allArticles.articleTitle + "</a></h1>\r\n" + 
								"                </div>\r\n" + 
								"              </div>\r\n" + 
								"              <div class=\"row\">\r\n" + 
								"                <div class=\"col-md-12\">\r\n" + 
								"                  <h2 class=\"\"> " + allArticles.articleDescription + "</h2>\r\n" + 
								"                </div>\r\n" + 
								"              </div>\r\n" + 
								"            </div>\r\n" + 
								"          </div>\r\n" + 
								"        </div>\r\n" + 
								"      </div>\r\n" + 
								"    </div>\r\n" + 
								"  </div>\r\n" ;
						
				}
				
				
					
				
				
			}
			
			stringToSendToWebBrowser +=	"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\" style=\"\"></script>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
				
				
       		
       	toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

       	return true;
       }
       return false;
	}
}

	


