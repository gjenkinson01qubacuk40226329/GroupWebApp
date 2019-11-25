package views;
import model.*;
import org.h2.mvstore.MVMap;

import model.AccountModel;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class CreateAccountView extends DynamicWebPage
{

	public CreateAccountView(DatabaseInterface db, FileStoreInterface fs) 
	{	
		super(db, fs);
	}
	
	static boolean validateUsername = false;
	static boolean validateEmail = false;
	static boolean validateAddressLine1 = false;
	static boolean validateAddressLine2 = false;
	static boolean validatepostcode = false;
	static boolean validatepassword = false;
	static boolean validatecnfmpassword = false;

	
	public boolean process(WebRequest toProcess)
	{
		String stringToSendToWebBrowser = "";
		AccountModel Account = new AccountModel();
		Account.email = toProcess.params.get("email");
		Account.username = toProcess.params.get("username");
		Account.addressLine1 = toProcess.params.get("AddressLine1");
		Account.addressLine2 = toProcess.params.get("AddressLine2");
		Account.postCode = toProcess.params.get("Postcode");
		Account.password = toProcess.params.get("Password");
		String passwordcnfrm = toProcess.params.get("PasswordConfirm");
	
		MVMap<String, AccountModel> Accounts = db.s.openMap("Accounts");


		if(toProcess.path.equalsIgnoreCase("CreateAccount"))
		{			

			if(toProcess.cookies.get("username") != null && !toProcess.cookies.get("username").equals(""))
			{
				stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" +
						"<html>\r\n" + 
						"<body onload=\"noCookies()\">"+
						"</body>\n"+					
						"  <script>\n" +
						"      function noCookies()\n" +					
						"      {\n" +
						"window.location.replace(\"myAccount\");\r\n" + 
						"      }\n" +
						"  </script>\n" +	
						"</html>"; 

				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
						stringToSendToWebBrowser);
				
						return true;
			}
			else
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
						"<body class=\"bg-light\" data-gr-c-s-loaded=\"true\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\" >\r\n" + 
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
						"          <h1 class=\"text-black text-center\">Create an account</h1>\r\n" + 
						"        </div>\r\n" + 
						"      </div>\r\n" + 
						"    </div>\r\n" + 
						"    <div class=\"bg-light pb-5\">\r\n" + 
						"      <div class=\"container\">\r\n" + 
						"        <div class=\"row\">\r\n" + 
						"          <div class=\"col-md-12\" style=\"\">\r\n" ;
				
				
				
						if(toProcess.params.containsKey("submitted"))
						{
							String test = "";
							stringToSendToWebBrowser += "<h3>"+test+"</h3>";
							
							
							if(validateNotNullAndEmpty(Account.username, toProcess) == false || Accounts.containsKey(Account.username) == true)
							{
								if(Accounts.containsKey(Account.username))
								{
								stringToSendToWebBrowser += "<h3> This username is already in use </h3>";
								test = "ERROR!";
								validateUsername = false;
								}
								else if(validateNotNullAndEmpty(Account.username, toProcess) == false)
								{
									stringToSendToWebBrowser += "<h3> Please Enter a Username </h3>";
									test = "ERROR!";
									validateUsername = false;
								}
							}
							else
							{
								validateUsername = true;
							}
							
							if(validateNotNullAndEmpty(Account.email, toProcess) == false || !Account.email.contains("@"))
							{
								stringToSendToWebBrowser += "<h3> Please Enter a valid email address </h3>";
								test = "ERROR!";
								validateEmail = false;
							}
							else
							{
								validateEmail = true;
							}
							
							if(validateNotNullAndEmpty(Account.addressLine1, toProcess) == false)
							{
								stringToSendToWebBrowser += "<h3> Please ensure you have entered the first line of your address </h3>";
								test = "ERROR!";
								validateAddressLine1 = false;
							}
							else
							{
								validateAddressLine1 = true;
							}
							
								validateAddressLine2 = true;
							
							if(validateNotNullAndEmpty(Account.postCode, toProcess) == false)
							{
								stringToSendToWebBrowser += "<h3> Please ensure you have entered a postcode </h3>";
								test = "ERROR!";
							}
							else
							{
								validatepostcode = true;
							}

							if(validateNotNullAndEmpty(Account.password, toProcess) == false)
							{
								stringToSendToWebBrowser += "<h3> Please ensure you have entered a Password </h3>";
								test = "ERROR!";
								validatepassword = false;
							}
							else
							{
								validatepassword = true;
							}

							if(!validateNotNullAndEmpty(passwordcnfrm, toProcess) == false && !passwordcnfrm.equals(Account.password) || validatepassword == false)
							{
								if(validatepassword == true)
								{
									stringToSendToWebBrowser += "<h3> Please ensure both passwords match </h3>";
									test = "ERROR!";
								}
								validatecnfmpassword = false;
								validatepassword = false;
							}
							else
							{
								validatecnfmpassword = true;
							}
						}
						
						stringToSendToWebBrowser += 
						"<h3><b>Please ensure any forms with an asterix* are completed</b></h3>"+
						"            <form role=\"form\" action=\"/CreateAccount?submitted=true\" method=\"post\">\r\n" + 
						"              <div class=\"form-group\"> <label>Create a Username*</label> " + 
						"				<input type=\"Text\" class=\"form-control\" placeholder=\"Enter Username\" name=\"username\"";
						if(toProcess.params.containsKey("submitted"))
						{
							stringToSendToWebBrowser += "value=\""+ Account.username + "\"";
							if(validateUsername != true)
							{	
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
								stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
							stringToSendToWebBrowser += "> <small class=\"form-text text-muted\"></small> </div>\r\n" +
						
									
						"              <div class=\"form-group\"> <label>Email address*</label> " + 	
						"				<input type=\"Text\" class=\"form-control\" placeholder=\"Enter email\" name=\"email\"";
						if(toProcess.params.containsKey("submitted"))
						{
							stringToSendToWebBrowser += "value=\""+ Account.email + "\"";
							if(validateEmail != true)
							{	
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
								stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						stringToSendToWebBrowser += "> <small class=\"form-text text-muted\"></small> </div>\r\n" + 
						"              <div>\r\n" + 
								
						
						"                <div class=\"form-group\"> <label>Address Line 1*</label>\r\n" + 
						"                  <input type=\"text\" class=\"form-control\" placeholder=\"Address Line 1\" name=\"AddressLine1\"";
						if(toProcess.params.containsKey("submitted"))
						{
							stringToSendToWebBrowser += "value=\""+ Account.addressLine1 + "\"";
							if(validateAddressLine1 != true)
							{	
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
									stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						stringToSendToWebBrowser += ">\r\n" + 
						"                </div>\r\n" + 
								
						
						"                <div class=\"form-group\"> <label>Address Line 2<br></label>\r\n" + 
						"                  <input type=\"text\" class=\"form-control w-50\" placeholder=\"Address Line 2\" name=\"AddressLine2\"";
						if(toProcess.params.containsKey("submitted"))
						{
							stringToSendToWebBrowser += "value=\""+ Account.addressLine2 + "\"";
							if(validateAddressLine2 != true)
							{
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
								stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						stringToSendToWebBrowser += ">\r\n" + 
						"                </div>\r\n" + 
								
						
						"                <div class=\"form-group\"> <label>Postcode*<br></label>\r\n" + 
						"                  <input type=\"text\" class=\"form-control w-25\" placeholder=\"Postcode\" name=\"Postcode\"";
						if(toProcess.params.containsKey("submitted"))
						{
							stringToSendToWebBrowser += "value=\""+ Account.postCode + "\"";
							if(validatepostcode != true)
							{	
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
								if(toProcess.params.containsKey("submitted"))
									stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						
						stringToSendToWebBrowser += ">\r\n" + 
						"                </div>\r\n" + 
						"              </div>\r\n" + 
						"              <div class=\"form-group\"> <label>Password*</label> <input type=\"password\" class=\"form-control\" placeholder=\"Password\" name=\"Password\"";
						if(toProcess.params.containsKey("submitted"))
						{							
							if(validatecnfmpassword == true)
							stringToSendToWebBrowser += "value=\""+ Account.password+ "\"";
							if(validatepassword != true)
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							else 
							{
								stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						stringToSendToWebBrowser += "> </div>\r\n" + 
								
								"<div class=\"form-group\"> <label>Confirm Password*</label> <input type=\"password\" class=\"form-control\" placeholder=\"Confirm Password\" name=\"PasswordConfirm\"";
						if(toProcess.params.containsKey("submitted"))
						{
							if(validatecnfmpassword == true)
							stringToSendToWebBrowser += "value=\""+ passwordcnfrm+ "\"";
							if(validatecnfmpassword != true)
							{	
								stringToSendToWebBrowser +="style=\"border:1px solid red;\"";
							}
							else 
							{
								stringToSendToWebBrowser +="style=\"border:1px solid green;\"";
							}
						}
						stringToSendToWebBrowser += "> </div>\r\n" + 
						"              <div class=\"form-group\">\r\n" + 
						"                <p class=\"lead\">Already have an account? Click <a href=\"login\">here</a> to login to your account</p>\r\n" + 
						"                <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + 
						"              </div>\r\n" + 
						"            </form>\r\n" + 
						"          </div>\r\n" + 
						"        </div>\r\n" + 
						"      </div>\r\n" + 
						"    </div>\r\n" + 
						"  </div>\r\n" + 
						"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
						"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n" + 
						"</body>\r\n" + 
						"\r\n" + 
						"</html>";
						
						if(validation() == true)
						{
								Accounts.put(Account.username, Account);
								db.commit();
								stringToSendToWebBrowser = "";

								stringToSendToWebBrowser += "<meta http-equiv=\"refresh\" content=\"0; url=login?creationsuccessful=true\" />";
								resetValidation();
								toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
								return true;
						}
						else
						{
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
		 validateUsername = false;
		 validateEmail = false;
		 validateAddressLine1 = false;
		 validateAddressLine2 = false;
		 validatepostcode = false;
		 validatepassword = false; 
		 validatecnfmpassword = false;
	}
	public static boolean validation()
	{
		if( validateUsername == false || validateEmail == false || validateAddressLine1 == false || validateAddressLine2 == false 
				|| validatepostcode == false || validatepassword == false || validatecnfmpassword == false)
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
		if(toProcess.params.containsKey("submitted"))
		{
			if(validatenull(Account) == false || validateempty(Account) == false)
				return false;
			else
				return true;
		}
		return false;
	}
}