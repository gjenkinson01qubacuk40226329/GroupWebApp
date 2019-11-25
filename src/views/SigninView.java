package views;

import model.NewsEvent40234553;
import org.h2.mvstore.MVMap;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;
import model.AccountModel;

public class SigninView extends DynamicWebPage
{

	public SigninView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
		// TODO Auto-generated constructor stub
	}

	static boolean validateusername = false;
	static boolean validatepassword = false;

	public boolean process(WebRequest toProcess)
	{
		String stringToSendToWebBrowser = "";
		String username = toProcess.params.get("username");
		String password = toProcess.params.get("password");
		
		MVMap<String, AccountModel> Accounts = db.s.openMap("Accounts");
			
		AccountModel Account12 = Accounts.get(username);
		
			if(toProcess.path.equalsIgnoreCase("Login"))
			{
				if(toProcess.cookies.get("username") != null && !toProcess.cookies.get("username").equals(""))
				{
					stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" +
							"<html>\r\n" + 
							"<body onload=\"Cookies()\">"+
							"</body>\n"+					
							"  <script>\n" +
							"      function Cookies()\n" +					
							"      {\n" +
							"window.location.replace(\"MyAccount\");\r\n" + 
							"      }\n" +
							"  </script>\n" +	
							"</html>"; 

					toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
							stringToSendToWebBrowser);
					
							return true;
				}
				else
				{
					if(toProcess.params.containsKey("submit"))
						System.out.println(username);
				if((validateNotNullAndEmpty(username, toProcess) == true && Accounts.containsKey(username) == true ))
						validateusername = true;
				if(toProcess.params.containsKey("submit") && validateusername == true)
					System.out.println(Account12.password.toString());
				if((validateNotNullAndEmpty(password, toProcess) == true && validateusername == true && Account12.password.contentEquals(password) == true))
					validatepassword = true;
				
				if(validation() == false)
				{
					stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"<title> How its Made </title> " +
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
					"          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"MyAccount\">My Account</a> </li>\r\n"+
					"<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>"+
					"          <li class=\"nav-item mx-2 text-dark\"> </li>\r\n"+
					"         <li class=\"nav-item mx-2 text-dark\"> </li>" + " <li> " +
					AllResults40226329view.embedSearchBox() + "</li>\r\n" + "        </ul>\r\n" + "      </div>\r\n"+ 
					"    </div>\r\n" + "  </nav>\r\n" +					"  <div class=\"py-3\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row\">\r\n" + 
					"        <div class=\"col-md-12\">\r\n" + 
					"          <h1 class=\"text-black text-center\">Login to your account</h1>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"    <div class=\"\" >\r\n" + 
					"      <div class=\"container\">\r\n" + 
					"        <div class=\"row\">\r\n" + 
					"          <div class=\"col-md-12\">\r\n" ;
				if(toProcess.params.containsKey("creationsuccessful"))
				{
					stringToSendToWebBrowser += "<h1><b>Your account was created successfully</b></h1>";
				}
				else if(toProcess.params.containsKey("submit"))
				{
					if(validateusername != true)
						stringToSendToWebBrowser += "<h3><b>The username supplied does not match our records of any usernames</b></h3>";
					else if(validatepassword != true)
						stringToSendToWebBrowser += "<h3><b>Incorrect password</b></h3>";
				}
				else if(toProcess.params.containsKey("NoAccount"))
				{
					stringToSendToWebBrowser += "<h3><b>Please either create or login to an account before attempting to submitting an article</b></h3>";
				}
					stringToSendToWebBrowser +=	
					"            <form role=\"form\" action=\"/Login?submit=true\" method=\"post\">\r\n" + 
					"              <div class=\"form-group\"> <label>Username</label> <input type=\"Text\" class=\"form-control\" placeholder=\"Enter Username\" name=\"username\"";
					if(toProcess.params.containsKey("submit"))
					{
						stringToSendToWebBrowser += "value=\""+ username + "\"";
						if(validateusername != true)
						{	
							stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
						}
						else
						{	
							stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
						}
					}
					stringToSendToWebBrowser += "> <small class=\"form-text text-muted\"></small> </div>\r\n" + 
	
							
					"              <div class=\"form-group\"> <label>Password</label> <input type=\"password\" class=\"form-control\" placeholder=\"Password\" name=\"password\"";
					if(toProcess.params.containsKey("submit"))
					{
						if(validation() != true)
						{	
							stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
						}
					}
					stringToSendToWebBrowser += "> </div> <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + 
					"            </form>\r\n" + 
					"            <p class=\"lead\">Don't already have an account? Click <a href=\"createaccount\">here</a> to create an account</p>\r\n" + 
					"          </div>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
				
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
					stringToSendToWebBrowser);
			
			return true;
				}
				else
				{
					stringToSendToWebBrowser = "";
					stringToSendToWebBrowser = "  <script>\n";
					stringToSendToWebBrowser += "      function setCookie(cname, cvalue, exdays)\n";
					stringToSendToWebBrowser += "      {\n";
					stringToSendToWebBrowser += "          var d = new Date();\n";
					stringToSendToWebBrowser += "          d.setTime(d.getTime() + (exdays*24*60*60*1000));\n";
					stringToSendToWebBrowser += "          var expires = 'expires='+d.toUTCString();\n";
					stringToSendToWebBrowser += "          document.cookie = cname + '=' + cvalue + ';' + expires + ';path=/';\n";
					stringToSendToWebBrowser += "      }\n";

					stringToSendToWebBrowser += "      function saveLoginCookie()\n";
					stringToSendToWebBrowser += "      {\n";
					stringToSendToWebBrowser += "          setCookie('username','"+username+"',365);\n";
					stringToSendToWebBrowser += "          setCookie('password','"+password+"',365);\n";
					stringToSendToWebBrowser += "      }\n";
					stringToSendToWebBrowser += "  </script>\n";
					stringToSendToWebBrowser += "  <body onload='saveLoginCookie()'>\n";
					stringToSendToWebBrowser += "  </body>\n";
					stringToSendToWebBrowser += "<meta http-equiv=\"refresh\" content=\"0; url=myAccount\" />";
					resetValidation();
					toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
					return true;
				}
			}
		}
			return false;
	}
	
	public static void resetValidation()
	{
		validateusername = false;
		validatepassword = false;
	}
	
	public static boolean validation()
	{
		if( validateusername == false || validatepassword == false)
			return false;
		else
			return true;
	}

	public static boolean validatenull(String Account)
	{
		if(Account == null)
			return false;
		else
			return true;
	}
	
	public static boolean validateempty(String Account)
	{
		if(Account.equals(""))
			return false;
		else
			return true;
	}
	
	public static boolean validateNotNullAndEmpty(String Account, WebRequest toProcess)
	{
		if(toProcess.params.containsKey("submit"))
		{
			if(validatenull(Account) == false || validateempty(Account) == false)
				return false;
			else
				return true;
		}
		return false;
	}
}
