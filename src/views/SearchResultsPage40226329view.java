package views;

import java.util.ArrayList;
import java.util.List;
import org.h2.mvstore.MVMap;
import model.AccountModel;
import model.Article;
import model.ArticleSubSection;
import model.comments;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class SearchResultsPage40226329view extends DynamicWebPage {

	public SearchResultsPage40226329view(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);

	}
	
	public boolean process(WebRequest toProcess) {
		String entryuid = "";
				entryuid += toProcess.params.get("entry");

		if (toProcess.path.equalsIgnoreCase("SearchResultsPage40226329view")) {

			MVMap<String, comments> allComments = db.s.openMap("Comments");
			List<String> commentsKeys = allComments.keyList();
			MVMap<String, Article> articles = db.s.openMap("Articles");
			List<String> articleKeys = articles.keyList();

			Article getArticle = null;

			for (int j = 0; j < articleKeys.size(); j++) {

				String articleID = articleKeys.get(j);
				Article loadArticle = articles.get(articleID);
				if (loadArticle.uniqueString.equals(entryuid)) {
					getArticle = loadArticle;
				}

			}

			MVMap<String, ArticleSubSection> subSections = db.s.openMap("SubSection");
			List<String> subSectionKeys = subSections.keyList();

			String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "\r\n" + "<head>\r\n"
					+ "<title>" + getArticle.articleTitle +" </title>"
					+ "<script type=\"text/javascript\" src='Collapse.js'> </script>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + "<style>\r\n"
					+ "img {\r\n" + 
					"  width: 100%;\r\n" + 
					"  height: auto;\r\n" + 
					"}\r\n"
					+ ".collapsible {\r\n" + "  background-color: #BDB6B6;\r\n" + "  color: white;\r\n"
					+ "  cursor: pointer;\r\n" + "  padding: 18px;\r\n" + "  width: 100%;\r\n" + "  border: none;\r\n"
					+ "  text-align: left;\r\n" + "  outline: none;\r\n" + "  font-size: 15px;\r\n" + "}\r\n" + "\r\n"
					+ ".active, .collapsible:hover {\r\n" + "  background-color: #12bbad;\r\n" + "}\r\n" + "\r\n"
					+ ".collapsible:after {\r\n" + "  content: '\\002B';\r\n" + "  color: white;\r\n"
					+ "  font-weight: bold;\r\n" + "  float: right;\r\n" + "  margin-left: 5px;\r\n" + "}\r\n" + "\r\n"
					+ ".active:after {\r\n" + "  content: \"\\2212\";\r\n" + "}\r\n" + "\r\n" + ".content {\r\n"
					+ "  padding: 0 18px;\r\n" + "  max-height: 0;\r\n" + "  overflow: hidden;\r\n"
					+ "  transition: max-height 0.2s ease-out;\r\n" + "  background-color: #f1f1f1;\r\n" + "}\r\n"
					
					+ "</style>\r\n" + "  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css\">\r\n"
					+ "  <script>\r\n"
					+"function asyncLoad(){\r\n"
					+ "    if (window.XMLHttpRequest)\r\n"
					+ "    {// code for IE7+, Firefox, Chrome, Opera, Safari\r\n"
					+ "        xmlhttp=new XMLHttpRequest();\r\n"
					+"    }\r\n"
					+"    else\r\n"
					+"    {// code for IE6, IE5\r\n"
					+ "        xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\r\n"
					+"    }\r\n"
					+ "    xmlhttp.onreadystatechange=myDataLoadedFunction;\r\n"
					+ "    //This line actually makes the request to the server\r\n"
					+"    xmlhttp.open(\"GET\",'/SearchResultsPage40226329view/Async?comment='+document.getElementById(\"newComment\").value+'&entryuid='+document.getElementById(\"articleId\").value,true);\r\n"
					+"    xmlhttp.send();\r\n"
					+"}\r\n"
					+"function Collapse() {\r\n" + 
					"var i;\r\n" + 
					"var coll = document.getElementsByClassName(\"collapsible\");\r\n" + 
					"for (i = 0; i < coll.length; i++) {\r\n" + 
					"  coll[i].addEventListener(\"click\", function() {\r\n" + 
					"    this.classList.toggle(\"active\");\r\n" + 
					"    var content = this.nextElementSibling;\r\n" + 
					"    if (content.style.maxHeight){\r\n" + 
					"      content.style.maxHeight = null;\r\n" + 
					"    } else {\r\n" + 
					"      content.style.maxHeight = content.scrollHeight + \"px\";\r\n" + 
					"    } \r\n" + 
					"  });\r\n" + 
					"}\r\n" + 
					"}\r\n"
					+ "function myDataLoadedFunction()\r\n"
					+ "{\r\n"
					+ "  if (xmlhttp.readyState==4 && xmlhttp.status==200)\r\n"
					+ "  {\r\n"
					+ "    //This line will set the html between the <body> tags\r\n"
					+"    //with what was returned by the webserver\r\n"
					+ "    //this will make the page redraw\r\n"
					+ "      document.getElementById(\"SearchResultsPage40226329view\").innerHTML = xmlhttp.responseText;\r\n"
					+ "Collapse();\r\n"
					
					+ "  }\r\n"
					
					+ "}\r\n"
					
					+ "  </script>\r\n"
					+ "</head>\r\n" + "\r\n"
					+ "<body class=\"bg-light\" data-gr-c-s-loaded=\"true\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\">\r\n"
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
					+ "    </div>\r\n" + "  </nav>\r\n" + " <div class=\"py-5\" >\r\n" + "<div class=\"container\">\r\n"
					+ "      <div class=\"row\">";
			
					if(toProcess.cookies.get("username") != null) {
						
					stringToSendToWebBrowser += "        <div class=\"col-md-12\" ><a class=\"btn btn-primary\" href=\"/editView?entry="+ entryuid + " \">Edit</a></div>";
					
					} 
			
					stringToSendToWebBrowser += "      </div>\r\n" + "      </div>\r\n" + "    </div>";
					stringToSendToWebBrowser += " <div class=\"py-5\">\r\n" + 
							"    <div class=\"container\">\r\n" + 
							"      <div class=\"row\">\r\n" + 
							"        <div class=\"col-md-12\">\r\n" + 
							"          <h1 class >" +getArticle.articleTitle + "</h1>\r\n" + 
							"        </div>\r\n" + 
							"      </div>\r\n" + 
							"    </div>\r\n" + 
							"  </div>";

			for (int k = 0; k < subSectionKeys.size(); k++) {

				String articleSubSectionID = subSectionKeys.get(k);
				ArticleSubSection loadArticleSubSection = subSections.get(articleSubSectionID);
				
				

				if (loadArticleSubSection != null) {

					if (loadArticleSubSection.ArticleIDString.equals(entryuid)) {

						if (!loadArticleSubSection.filepathToImage.equals("NoImageUploaded")
								|| loadArticleSubSection.filepathToImage == null) {

							System.out.println(loadArticleSubSection.filepathToImage);

							stringToSendToWebBrowser +=  "    <div class=\"container\">\r\n"
									+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-6\">\r\n" +

									"          <h3 class=\"\" style=\"\"></h3><img class=\"img-fluid d-block\" src=\""
									+ loadArticleSubSection.filepathToImage + "\">\r\n" +

									"        </div>\r\n" + "        <div class=\"col-md-6\">\r\n"
									+ "          <h1 class=\"\">" + loadArticleSubSection.sectionHeading + "</h1>\r\n"
									+ "          <div class=\"row\">\r\n" + "            <div class=\"col-md-12\">\r\n"
									+ "            </div>\r\n" + "          </div>\r\n"
									+ "          <div class=\"row\">\r\n" + "            <div class=\"col-md-12\">\r\n"
									+ "              <p class=\"\">" + loadArticleSubSection.sectionText
									+ "</p><br>  </br>\r\n" + "            </div>\r\n" + "          </div>\r\n"
									+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "<br> </br>";
						}

						else {
							stringToSendToWebBrowser += "    <div class=\"container\">\r\n"
									+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\">\r\n"
									+ "          <div class=\"row\">\r\n" + "            <div class=\"col-md-12\">\r\n"
									+ "              <h3 class=\"\"> " + loadArticleSubSection.sectionHeading
									+ " </h3>\r\n" + "              <div class=\"row\">\r\n"
									+ "                <div class=\"col-md-12\">\r\n"
									+ "                  <p class=\"\"> " + loadArticleSubSection.sectionText
									+ "</p>\r\n" + "                </div>\r\n" + "              </div>\r\n"
									+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
									+ "      </div>\r\n" + "    </div>\r\n" + "<br> </br>";

						}

					}
				}

			}

			String link = getArticle.YoutubeLink;
			if (link != null ) {
				if (link.contains("embed")) {
			stringToSendToWebBrowser += "<div class=\"py-5\" >\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row\">\r\n" + 
					"        <div class=\"col-md-12\">\r\n" + 
					"          <div class=\"embed-responsive embed-responsive-16by9\">\r\n" + 
					"            <iframe src=\" " + getArticle.YoutubeLink + "\" allowfullscreen=\"\" class=\"embed-responsive-item\"></iframe>\r\n" + 
					"          </div>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>";
				}
		}
					
					if(toProcess.cookies.get("username") != null) {
						stringToSendToWebBrowser += "  <div class=\"py-5\" style=\"\">\r\n" + "    <div class=\"container\">\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\">\r\n"
					+ "          <div class=\"row\">\r\n" + "            <div class=\"col-md-12\">\r\n"
					+ "              <h2 class=\"\" contenteditable=\"true\">Any thoughts on this manufacturer?</h2>\r\n"
					+ "              <div class=\"row\">\r\n" + "                <div class=\"col-md-12\">\r\n"
					+ "                  <form action =\"/addCommentProcess\" method=\"GET\" class=\"\">\r\n"
					+ "                    <div class=\"col-sm-10\">\n"
					+ "                      <input type=\"hidden\" class=\"form-control\" id=\"articleId\" name=\"entryuid\"\n"
					+ "                      value=\" " + entryuid + "\">\n"
					+ "                    </div>\n"
					+ "                   <input type=\"text\" name=\"comments\"class=\"form-control\"id=\"newComment\" maxlength=\"255\" placeholder=\"Comments\" style=\"\"><br><span id=\"remainingC\">Remaining Characters : 255 </span><br> \r\n"
					+ "                </div>\r\n" + "                <div class=\"col-10\" style=\"\">\r\n"
					+ "                </div>\r\n" + "              </div>\r\n"
					+ "              <button type=\"submit\" class=\"btn btn-primary\" onclick='asyncLoad();return false;'>Submit  </button></form>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n"
					+ "    </div>\r\n" + "  </div>\r\n";}
					
					else {	
						stringToSendToWebBrowser += "  <div class=\"py-5\" style=\"\">\r\n" + "    <div class=\"container\">\r\n"
							+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\">\r\n"
							+ "          <div class=\"row\">\r\n" + "            <div class=\"col-md-12\">\r\n"
							+ "              <h2 class=\"\" contenteditable=\"true\">Any thoughts on this manufacturer?</h2>\r\n"
							+ "              <div class=\"row\">\r\n" + "                <div class=\"col-md-12\">\r\n"
							
							
							+ "<form>\r\n" + 
							"<fieldset disabled=\"disabled\">\r\n"
							
							+"                   <input type=\"readonly\" name=\"comments\"class=\"form-control\"id=\"newComment\" maxlength=\"255\" placeholder=\"Login or sign up to leave a comment\" style=\"\"> \r\n"
							+ "                </div>\r\n" + "                <div class=\"col-10\" style=\"\">\r\n"
							+ "                </div>\r\n" + "              </div>\r\n"
							
							+ "        <a class=\"btn btn-primary\" href=\"/Login\">Login</a>\r\n"
							+ "</fieldset>\r\n"
							+ "</form>\r\n"
							+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n"
							+ "    </div>\r\n" + "  </div>\r\n";
							}

			stringToSendToWebBrowser += "<div id=\"SearchResultsPage40226329view\">";
			
			ArrayList<comments> orderComments = new ArrayList<comments>();
			for (int i = 0; i < commentsKeys.size(); i++) {
				String commentsID = commentsKeys.get(i);
				comments loadComments = allComments.get(commentsID);
				
				
				orderComments.add(loadComments);
			}

			
			for (int i = 0; i < orderComments.size(); i++) {
				comments loadComments = orderComments.get(orderComments.size() - (i +1));
				if(loadComments != null)
				
					if ((loadComments.postId).trim().equalsIgnoreCase(entryuid.trim())) {
						
						
						stringToSendToWebBrowser += "    <div class=\"container\">\r\n"
								+ "      <div class=\"row\">\r\n"
								+ "        <div class=\"col-md-12\"><button class=\"collapsible\">" + loadComments.user
								+ "</button>\r\n" + "          <div class=\"content\">\r\n" + "            <p>"
								+ loadComments.comments + "</p>\r\n" + "          </div>\r\n" + "        </div>\r\n"
								+ "      </div>\r\n" +

								"  </div>\r\n"
								+"<br>  </br>";
					
						
					}
			}
					
				
			
			stringToSendToWebBrowser += "</div>";

			stringToSendToWebBrowser += "</a></div>" + "<div class=\"py-5\" >\r\n" + "    <div class=\"container\">\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\"></div>\r\n"
					+ "      </div>\r\n" + "    </div>\r\n" + "  </div>"
					+ "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n"
					+ "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n"
					+ "<script>\r\n" + 
					"function myFunction() {\r\n" + 
					"  location.reload();\r\n" + 
					"}\r\n" + 
					"</script>"
					+ " <script>\r\n" + "		$(document).ready(function() {\r\n" + "			var len = 0;\r\n"
					+ "			var maxchar = 255;\r\n" + "\r\n"
					+ "			$( '#newComment' ).keyup(function(){\r\n" + "				len = this.value.length\r\n"
					+ "				if(len > maxchar){\r\n" + "					return false;\r\n"
					+ "				}\r\n" + "				else if (len > 0) {\r\n"
					+ "					$(\"#remainingC\").html(\"Remaining characters: \" +(maxchar - len));\r\n"
					+ "				}\r\n" + "				else {\r\n"
					+ "					$(\"#remainingC\").html(\"Remaining characters: \" +(maxchar) );\r\n"
					+ "				}\r\n" + "			})\r\n" + "		});\r\n" + "	</script>\r\n" +

					"<script>\r\n" + "var coll = document.getElementsByClassName(\"collapsible\");\r\n" + "var i;\r\n"
					+ "\r\n" + "for (i = 0; i < coll.length; i++) {\r\n"
					+ "  coll[i].addEventListener(\"click\", function() {\r\n"
					+ "    this.classList.toggle(\"active\");\r\n" + "    var content = this.nextElementSibling;\r\n"
					+ "    if (content.style.maxHeight){\r\n" + "      content.style.maxHeight = null;\r\n"
					+ "    } else {\r\n" + "      content.style.maxHeight = content.scrollHeight + \"px\";\r\n"
					+ "    } \r\n" + "  });\r\n" + "}\r\n" + "</script>" + "</html>";

			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
			return true;
		} else if (toProcess.path.equalsIgnoreCase("SearchResultsPage40226329view/Async")) {

			AccountModel Account = new AccountModel();
			MVMap<String, AccountModel> Accounts = db.s.openMap("Accounts");

			String stringToSendToWebBrowser = "";

			Account = Accounts.get(toProcess.cookies.get("username"));
			if (Account != null && toProcess.params.get("comment").length() > 0) {

				comments commentsTest = new comments();
				commentsTest.uniqueid = "comment_" + System.currentTimeMillis();
				commentsTest.comments = toProcess.params.get("comment");
				commentsTest.user = Account.username;
				commentsTest.postId = toProcess.params.get("entryuid");

				MVMap<String, comments> allComments = db.s.openMap("Comments");
				allComments.put(commentsTest.uniqueid, commentsTest);
				db.commit();
			}
			
			MVMap<String, comments> allComments = db.s.openMap("Comments");
				List<String> commentsKeys = allComments.keyList();
				for (int i = 0; i < commentsKeys.size(); i++) {
					String commentsID = commentsKeys.get(i);
					comments loadComments = allComments.get(commentsID);
								
					
					if ((loadComments.postId).trim().equals((toProcess.params.get("entryuid")).trim())) {				

						stringToSendToWebBrowser += "    <div class=\"container\">\r\n"
								+ "      <div class=\"row\">\r\n"
								+ "        <div class=\"col-md-12\"><button class=\"collapsible\"> " + loadComments.user
								+ "</button>\r\n" + "          <div class=\"content\">\r\n" + "            <p>"
								+ loadComments.comments + "</p>\r\n" + "          </div>\r\n" + "        </div>\r\n"
								+ "      </div>\r\n" +

								"  </div>\r\n"
								+"<br>  </br>";
					
				
				}
			}
			
				
				
			
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);

			return true;
		}

		return false;

	}
}