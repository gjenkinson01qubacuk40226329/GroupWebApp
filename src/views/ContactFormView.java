package views;

import java.util.List;

import org.h2.mvstore.MVMap;

import model.ContactForm;
import model.HomePage;
import model.NewsEvent40234553;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class ContactFormView extends DynamicWebPage{

	public ContactFormView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	
	
	public boolean process(WebRequest toProcess)
	{
		
	if(toProcess.path.equalsIgnoreCase("ContactFormView")) 
	{
	MVMap<String, ContactForm> queries = db.s.openMap("Queries"); 
	List<String> ContactFormKeys = queries.keyList();
	
	if(ContactFormKeys.size()==0)
	{
		ContactForm contact = new ContactForm();
		contact.uniqueid = "query_"+System.currentTimeMillis();
		contact.surname = "Surname"; 
		contact.email = "Email";
		contact.query = "What's the problem";
		contact.forename = "Forename";
		db.commit();
		ContactFormKeys = queries.keyList();
		
	}
	
	String stringToSendToWebBrowser = "<!DOCTYPE html>\n" + 
			"<html>\n" + 
			"\n" + 
			"<head>\n" + 
			"  <meta charset=\"utf-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\n" + 
			"  <link rel=\"stylesheet\" href=\"theme.css\" type=\"text/css\">\n" + 
			"</head>\n" + 
			"\n" + 
			"<body class=\"bg-light\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\" >\n" + 
"<nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\n" + 
"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\n" + 
"        <span class=\"navbar-toggler-icon\"></span>\n" + 
"      </button>\n" + 
"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\n" + 
"        <ul class=\"navbar-nav\">\n" + 
"<li class=\"nav-item mx-2 text-dark\"> <a class=\"nav-link\" href=\"AddView\">Add Article </a> </li>\n" + 
"          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"Login\">My Account</a> </li>\n" + 
"<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>          <li class=\"nav-item mx-2 text-dark\"> </li>\n" + 
"         <li class=\"nav-item mx-2 text-dark\"> </li> <li>     <div class=\"container\">\n" + 
"      <div class=\"row\">\n" + 
"        <div class=\"col-md-12\">\n" + 
"          <form class=\"form-inline\" role=\"form\" method=\"GET\" action=\"AllResults40226329view\">\n" + 
"            <div class=\"input-group\">\n" + 
"              <input type=\"text\" class=\"form-control\" id=\"searchbox\" name=\"searchstring\" placeholder=\"Search\">\n" + 
"              <div class=\"input-group-append\"><button class=\"btn btn-primary\" type=\"button\"><i class=\"fa fa-search\"></i></button></div>\n" + 
"            </div>\n" + 
"          </form>\n" + 
"        </div>\n" + 
"      </div>\n" + 
"  </div></li>\n" + 
"        </ul>\n" + 
"      </div>\n" + 
"    </div>\n" + 
"  </nav>\n" + 
			"  <div class=\"py-3\">\n" + 
			"    <div class=\"container\">\n" + 
			"      <div class=\"row\">\n" + 
			"        <div class=\"col-md-12\">\n" + 
			"          <h1 class=\"text-center text-dark\"><b>Contact Us Queries&nbsp;</b></h1>\n" + 
			"        </div>\n" + 
			"      </div>\n" + 
			"    </div>\n" + 
			"  </div>\n" + 
			"  <div class=\"py-1\">\n" + 
			"    <div class=\"container\">\n" + 
			"      <div class=\"row\">\n" + 
			"        <div class=\"col-md-12\" style=\"\">\n" + 
			"          <p class=\"text-center text-dark\">This is an internal use page only, admins and staff of How its Made may view contact queries below and respond to them using the given email addresses by the users. Do not share any of the information listed below as it contains personal data regarding the users and should not be shared under any circumstances unless authorised to do so by an admin.</p>\n" + 
			"        </div>\n" + 
			"      </div>\n" + 
			"    </div>\n" + 
			"  </div>";
	
	for (int index=0;index<ContactFormKeys.size(); index++) 
	{
		String contactUniqueID = ContactFormKeys.get(index);
		ContactForm contact = queries.get(contactUniqueID);
		
		stringToSendToWebBrowser +="  <div class=\"py-5 text-body\">\n" + 
				"    <div class=\"container\">\n" + 
				"      <div class=\"row\">\n" + 
				"        <div class=\"col-md-2\">\n" + 
				"          <h3 class=\"text-dark\">Forename</h3>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-2\">\n" + 
				"          <h3 class=\"text-dark\">Surname</h3>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-3\">\n" + 
				"          <h3 class=\"text-dark\">Email</h3>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-5 text-dark\">\n" + 
				"          <h3 class=\"text-dark\" contenteditable=\"true\">Query</h3>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-2\">\n" + 
				"          <h4 class=\"\">"+contact.forename+"</h4>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-2\">\n" + 
				"          <h4 class=\"\">"+contact.surname+"</h4>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-3\">\n" + 
				"          <h4 class=\"\">"+contact.email+"</h4>\n" + 
				"        </div>\n" + 
				"        <div class=\"col-md-5\">\n" + 
				"          <p class=\"\" style=\"\">"+contact.query+"</p>\n" + 
				"        </div>\n" + 
				"      </div>\n" + 
				"    </div>\n" + 
				"  </div>";
	}
	stringToSendToWebBrowser +="  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\n" + 
			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n" + 
			"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\" style=\"\"></script>\n" + 
			"  <pingendo onclick=\"window.open('https://pingendo.com/', '_blank')\" style=\"cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white\">Made with Pingendo Free&nbsp;&nbsp;<img src=\"https://pingendo.com/site-assets/Pingendo_logo_big.png\" class=\"d-block\" alt=\"Pingendo logo\" height=\"16\"></pingendo>\n" + 
			"</body>\n" + 
			"\n" + 
			"</html>";
	     
	toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
	return true;
	}
	return false;
	}
}