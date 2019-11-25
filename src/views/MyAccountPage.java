package views;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.h2.mvstore.MVMap;

import model.AccountModel;
import model.Article;
import model.comments;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class MyAccountPage extends DynamicWebPage
{
	public MyAccountPage(DatabaseInterface db, FileStoreInterface fs)
	{
		super(db, fs);
	}

	public boolean process(WebRequest toProcess)
	{
		if(toProcess.path.equalsIgnoreCase("MyAccount"))
		{
			AccountModel Account = new AccountModel();
			MVMap<String, AccountModel> Accounts = db.s.openMap("Accounts");

			
			Account = Accounts.get(toProcess.cookies.get("username"));

			String stringToSendToWebBrowser = "";
			
			if(toProcess.cookies.get("username") == null || toProcess.cookies.get("username").equals(""))
			{
				stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" +
						"<html>\r\n" + 
						"<body onload=\"noCookies()\">"+
						"</body>\n"+					
						"  <script>\n" +
						"      function noCookies()\n" +					
						"      {\n" +
						"window.location.replace(\"login\");\r\n" + 
						"      }\n" +
						"  </script>\n" +	
						"</html>"; 

				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
						stringToSendToWebBrowser);
				
						return true;
			}
			else
			{
				System.out.println(Account.pathToProfilePicture+"\n\n\n\n\n");
				System.out.println("tesing because testing");
				System.out.println(Account.pathToProfilePicture+"\n\n\n\n\n\n\\n\n\n\n");
				if(toProcess.params.containsKey("UpdatedProfilePicture"))
				{
					System.out.println("testing thest test");
					if(!(Account.pathToProfilePicture.equals("DefaultProfilePictures.png")))
					{
						try 
						{
							System.out.println("httpdocs/"+Account.pathToProfilePicture+"\n\n\n\n\n\n\\n\n\n\n");
							Files.delete(Paths.get("httpdocs/"+Account.pathToProfilePicture));
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Account.pathToProfilePicture = toProcess.params.get("ProfilePictureUpload");
					
					File uploaded = new File(Account.pathToProfilePicture);
					int ind = Account.pathToProfilePicture.lastIndexOf('.');
					String extension = Account.pathToProfilePicture.substring(ind);
					uploaded.renameTo(new File("httpdocs/"+Account.username + "ProfilePicture" +extension));
					Account.pathToProfilePicture = Account.username + "ProfilePicture" +extension;
					
					Accounts.put(Account.username, Account);
					db.commit();
				}
				
				stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"\r\n" + 
						"<head>\r\n" + 
						"<title> How its made</title>"+
						"  <meta charset=\"utf-8\">\r\n" + 
						"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css\">\r\n" + 
						"</head>\r\n" + 
						"\r\n" + 
						"<body class=\"bg-light\" data-gr-c-s-loaded=\"true\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\">\r\n" + 
						"  <nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\r\n"+
						"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\r\n"+
						"        <span class=\"navbar-toggler-icon\"></span>\r\n" + "      </button>\r\n"+
						"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\r\n" +
						"        <ul class=\"navbar-nav\">\r\n" +
						"<li class=\"nav-item mx-2 text-dark\"> <a class=\"nav-link\" href=\"AddView\">Add Article </a> </li>\r\n" +
						"          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"Login\">My Account</a> </li>\r\n"+
						"<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>"+
						"          <li class=\"nav-item mx-2 text-dark\"> </li>\r\n"+
						"         <li class=\"nav-item mx-2 text-dark\"> </li>" + " <li> " +
						AllResults40226329view.embedSearchBox() + "</li>\r\n" + "        </ul>\r\n" + "      </div>\r\n"+
						"    </div>\r\n" + "  </nav>\r\n" +
						"  <div class=\"py-3\">\r\n" + 
						"    <div class=\"container\">\r\n" + 
						"      <div class=\"row\">\r\n" + 
						"        <div class=\"col-md-12\">\r\n" + 
						"          <ul class=\"nav nav-pills\">\r\n" + 
						"            <li class=\"nav-item\" > <a href=\"\" class=\"nav-link show active\" data-toggle=\"pill\" data-target=\"#tabone\">My account</a> </li>\r\n" + 
						"            <li class=\"nav-item\"> <a class=\"nav-link show\" href=\"\" data-toggle=\"pill\" data-target=\"#tabtwo\">My comments</a> </li>\r\n" + 
						"            <li class=\"nav-item\"> <a href=\"\" class=\"nav-link show\" data-toggle=\"pill\" data-target=\"#tabthree\">My posts</a> </li>\r\n" + 
						"          </ul>\r\n" + 
						"          <div class=\"tab-content mt-2\">\r\n" + 
						"            <div class=\"tab-pane fade active show\" id=\"tabone\" role=\"tabpanel\">\r\n" + 
						"              <div class=\"row\">\r\n" + 
						"                <div class=\"col-md-6\">\n"+
						"				<img class=\"img-fluid d-block h-75 w-75\" src=\""+ Account.pathToProfilePicture +"\">\n";
				
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+Account.pathToProfilePicture);
				
				stringToSendToWebBrowser +=
						"            <form role=\"form\" action=\"/MyAccount?UpdatedProfilePicture=true\" method=\"post\" enctype='multipart/form-data'>\n" + 
						"		<input type=\"file\" accept=\"image/*\" class=\"form-control w-50\" name=\"ProfilePictureUpload\" onchange=\"form.submit()\" w-50>\n" +
					//	"                <button type=\"btn-file\" class=\"btn btn-primary\">Click here to change your profile picture</button>\r\n" + 
						"					</form>\r\n"+
						"              </div>\r\n" + 
						"                <div class=\"col-md-6\">\r\n";
				if(Account.addressLine2.trim().equals(""))
				{
					stringToSendToWebBrowser +=
							"            <h3><b>Username:</b> <i>" + Account.username + "</i> </h3>\r\n" + 
							"            <h3><b>Email:</b> <i> " + Account.email + " </i></h3>\r\n" + 
							"            <h3><b>Address:</b> <i> " + Account.addressLine1 + ",<br/>" + 
										 Account.postCode + " </i></h3>\r\n" + 
							" 			 </div>\r\n" + 
							"            </div>\r\n" + 
							"            </div>\r\n" + 
							"            <div class=\"tab-pane fade\" id=\"tabtwo\" role=\"tabpanel\">\r\n" ;
				}
				else
				{
					stringToSendToWebBrowser +=
						"            <h3><b>Username:</b> <i>" + Account.username + "</i> </h3>\r\n" + 
						"            <h3><b>Email:</b> <i> " + Account.email + " </i></h3>\r\n" + 
						"            <h3><b>Address:</b> <i> " + Account.addressLine1 + ",<br/>" + 
						Account.addressLine2 + ",<br/>" + Account.postCode + " </i></h3>\r\n" + 
						" 				</div>\r\n" + 
						"            </div>\r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"tab-pane fade\" id=\"tabtwo\" role=\"tabpanel\">\r\n" ;
				}
						comments comment = new comments();
						//OPENING UP DATANASES
						MVMap<String, comments> commentdbMap = db.s.openMap("Comments");
						MVMap<String, Article> articledbMap = db.s.openMap("Articles");
						
						
						//CODE TO SHOW ALL COMMENTS MADE BY USER	
						List<String> commentsKeysList = commentdbMap.keyList();
						List<String> articleKeysList = articledbMap.keyList();

						int count = 0;
						for (String key : commentsKeysList) 
						{
							Article myArticle = articledbMap.get(commentdbMap.get(key).postId.trim());
							System.out.println(commentdbMap.get(key).postId);
							System.out.println(myArticle.uniqueString);
							if(myArticle != null)
							{
							if(commentdbMap.get(key).user != null)
							{
								if(commentdbMap.get(key).user.equals(Account.username))
								{
								stringToSendToWebBrowser += 
										"              <div class=\"list-group\">\r\n" + 
										"                <a href=\"/SearchResultsPage40226329view?entry="+myArticle.uniqueString +"\" class=\"list-group-item list-group-item-action flex-column align-items-start ";
								if(count % 2 == 0)
									stringToSendToWebBrowser += "active";
										stringToSendToWebBrowser += "\">\r\n" + 
										"                  <div class=\"d-flex w-100 justify-content-between\">\r\n"+
										"					<h5 class=\"mb-1\"><b>Post title:</b><br> "+ myArticle.articleTitle 	+" </h5>\r\n" + 
										"                  </div>\r\n" + 
										"                  <p class=\"mb-1\"><b>Comment:</b><br> "+ commentdbMap.get(key).comments + "</p>\r\n" + 
										"                </a>\r\n" +
										"            </div>\r\n" ;
										count++;
								}
							}
							}
						}
						
						if(count == 0)
						{
							stringToSendToWebBrowser+= "<h3>You have submitted no comments";
						}
						count = 0; //reseting the counter for the posts to show in 
						
						stringToSendToWebBrowser += 
						"            </div>\r\n" + 
						"            <div class=\"tab-pane fade\" id=\"tabthree\" role=\"tabpanel\">\r\n" ;
						
						//CODE FOR POSTINGS BY USER
						for (String key : articleKeysList)
						{
							if(articledbMap.get(key).username != null)
							{
								if(articledbMap.get(key).username.equals(Account.username))
								{
								stringToSendToWebBrowser += 
										"              <div class=\"list-group\">\r\n" + 
										"                <a href=\"/SearchResultsPage40226329view?entry="+ articledbMap.get(key).uniqueString +"\" class=\"list-group-item list-group-item-action flex-column align-items-start ";
								if(count % 2 == 0)
									stringToSendToWebBrowser += "active";
								
										stringToSendToWebBrowser += "\">\r\n" + 
										"                  <div class=\"d-flex w-100 justify-content-between\">\r\n"+
										"					<h5 class=\"mb-1\"> <b>Title of post:</b> <br>"+ articledbMap.get(key).articleTitle +" </h5>\r\n" + 
										"                  </div>\r\n" + 
										"                  <p class=\"mb-1\"><b>Post description:</b> <br>"+ articledbMap.get(key).articleDescription + "</p>\r\n" + 
										"                </a>\r\n" +
										"            </div>\r\n" ;
										count++;
								}
							}
						}
						if(count == 0)
						{
							stringToSendToWebBrowser+= "<h3>You have submitted no comments";
						}
						count = 0;
						stringToSendToWebBrowser += 
						"          </div>\r\n" + 
						"        </div>\r\n" +
						"      </div>\r\n" + 
						"    </div>\r\n" + 
						"    <div class=\"bg-light pb-5\">\r\n" + 
						"      <div class=\"container\">\r\n" + 
						"        <div class=\"row\">\r\n" + 
						"        </div>\r\n" + 
						"      </div>\r\n" + 
						"      <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
						"      <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
						"      <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
						"    </div>\r\n" + 
						"      <div class=\"row\">\r\n" + 
						"          <div class=\"col-md-12\"><a class=\"btn btn-primary\" onClick=\"clearLoginCookie()\" a href=\"login\">Logout</a></div>" +
						"      </div>\r\n" + 
						"  </div>\r\n" + 
						"  <div class=\"py-5\">\r\n" + 
						"    <div class=\"container\">\r\n" + 
						"    </div>\r\n" + 
						"  </div>\r\n" + 
						"  <script>\n" +
						"      function clearLoginCookie()\n" +					
						"      {\n" +
						"          document.cookie='username=;expires=' + new Date(0).toGMTString();\n" +
						"          document.cookie='password=;expires=' + new Date(0).toGMTString();\n" +
						"      }\n" +
						"  </script>\n" +
						"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\" style=\"\"></script>\r\n" + 
						"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\" style=\"\"></script>\r\n" + 
						"</body>\r\n" + 
						"\r\n" + 
						"</html>";
	
				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
						stringToSendToWebBrowser);
				
				return true;
			}
		}
		return false;
	}
}
