package views;

import org.h2.mvstore.MVMap;

import model.BugForm;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class BugReporting extends DynamicWebPage {

	public BugReporting(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}

	public boolean process(WebRequest toProcess) {
		if (toProcess.path.equalsIgnoreCase("BugReporting"))

		{

			String stringToSendToWebBrowser = "<!DOCTYPE html>\n" + "<html>\n" + "\n" + "<head>\n"
					+ "  <meta charset=\"utf-8\">\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\n"
					+ "  <link rel=\"stylesheet\" href=\"theme.css\" type=\"text/css\">\n" + "</head>\n" + "\n"
					+ "<body class=\"bg-light\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\" >" +
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
"  <div class=\"\">\n" 
					+ "    <div class=\"container\">\n" + "      <div class=\"row\">\n"
					+ "        <div class=\"col-md-12\">\n"
					+ "          <h1 class=\"text-black text-center\">Found a bug?</h1>\n" + "        </div>\n"
					+ "      </div>\n" + "    </div>\n" + "  </div>\n" + "  <div class=\"\">\n"
					+ "    <div class=\"container\">\n" + "      <div class=\"row\">\n"
					+ "        <div class=\"mx-auto\"><i class=\"fa fa-bug mx-auto text-center px-0 text-black fa-3x\"></i></div>\n"
					+ "      </div>\n" + "    </div>\n" + "  </div>\n" + "  <div class=\"py-1\">\n"
					+ "    <div class=\"container\">\n" + "      <div class=\"row\">\n"
					+ "        <div class=\"col-md-12\" style=\"\">\n"
					+ "          <p class=\"text-black text-center\" contenteditable=\"true\" style=\"\">If you found that something isn't right on our site or even perhaps there is something you'd wish was different please let us know down below and we will let our geek squad know and make sure we get it fixed!!</p>\n"
					+ "        </div>\n" + "      </div>\n" + "    </div>\n" + "  </div>\n" + "  <div class=\"py-2\">\n"
					+ "    <div class=\"container\">\n" + "      <div class=\"row\">\n"
					+ "        <div class=\"col-md-12\">\n"
					+ "          <h2 class=\"text-black text-center\">Bug Reporting Form</h2>\r\n" + "        </div>\r\n"
					+ "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "  <div class=\"py-1\">\r\n"
					+ "    <div class=\"container\">\r\n" + "      <div class=\"row\">\r\n"
					+ "        <div class=\"col-md-12\">\r\n"
					+ "           <form  action = \"BugReport1\" method = \"GET\">\r\n"
					+ "            <div class=\"form-group\" style=\"\"> <label class=\"text-black\">Email*:</label> <input type=\"email\" name=\"Email\"class=\"form-control\" placeholder=\"Email:\" required=\"required\"> <small class=\"form-text text-muted\">We'll never share your email with anyone else.</small> </div>\r\n"
					+ "            <div class=\"form-group\"> <label class=\"text-black\">Forename:</label> <input type=\"text\" name=\"Forename\"  class=\"form-control\" placeholder=\"Forename\"> </div>\r\n"
					+ "            <div class=\"form-group\"> <label class=\"text-black\">Surname:</label> <input type=\"text\" name=\"Surname\" class=\"form-control\" placeholder=\"Surname\" >\r\n"
					+ "              <div class=\"form-group\"><label class=\"text-black\">What's the problem?*:</label><input type=\"text\" name=\"What's-the-bug?\"class=\"form-control form-control-lg h-25 w-100\"></div>\r\n"
					+ "            </div>\r\n"
					+ "            <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n"
					+ "          </form><label class=\"text-black\" contenteditable=\"true\">Fields marked with * must be completed.</label>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n"
					+ "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n"
					+ "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\n"
					+ "</body>\n" + "\n" + "</html>";

			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
			return true;
		} else if (toProcess.path.equalsIgnoreCase("BugReport1")) {
			BugForm BugReporting = new BugForm();
			BugReporting.uniqueid = "query_" + System.currentTimeMillis();
			BugReporting.surname = toProcess.params.get("Surname");
			BugReporting.email = toProcess.params.get("Email");
			BugReporting.query = toProcess.params.get("What's-the-bug?");
			BugReporting.forename = toProcess.params.get("Forename");

			// At this point you would normally add the contact query to the database
			MVMap<String, BugForm> bugs = db.s.openMap("Bugs");
			bugs.put(BugReporting.uniqueid, BugReporting);
			db.commit();
			
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
					"<body class=\"bg-light\" >\n" + 
					"  <nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\n" + 
					"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\n" + 
					"        <span class=\"navbar-toggler-icon\"></span>\n" + 
					"      </button>\n" + 
					"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\n" + 
					"        <ul class=\"navbar-nav\">\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"AddEdit40232871.html\">Add &amp; Edit</a> </li>\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"SearchResultPage40226329.html\">Search</a> </li>\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"homeview\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i>\n" + 
					"              <b> How its Made</b></a> </li>\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"login40234553.html\">Signup</a> </li>\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"login40234553.html\">Login</a> </li>\n" + 
					"        </ul>\n" + 
					"      </div>\n" + 
					"    </div>\n" + 
					"  </nav>\n" + 
					"  <div class=\"\">\n" + 
					"    <div class=\"container\">\n" + 
					"      <div class=\"row\">\n" + 
					"        <div class=\"col-md-12\">\n" + 
					"          <h1 class=\"text-center text-dark\">Thank you for your submission.</h1>\n" + 
					"        </div>\n" + 
					"      </div>\n" + 
					"    </div>\n" + 
					"  </div>\n" + 
					"  <div class=\"\">\n" + 
					"    <div class=\"container\">\n" + 
					"      <div class=\"row text-dark\">\n" + 
					"        <div class=\"mx-auto\"><i class=\"fa mx-auto text-center px-0 fa-3x text-dark fa-bug\"></i></div>\n" + 
					"      </div>\n" + 
					"    </div>\n" + 
					"  </div>\n" + 
					"  <div class=\"py-1\">\n" + 
					"    <div class=\"container\">\n" + 
					"      <div class=\"row\">\n" + 
					"        <div class=\"col-md-12\" style=\"\">\n" + 
					"          <p class=\"text-center text-dark\" style=\"\">Our team appreciate your bug busting efforts, if you find any more please do reach out! Thank you once again.</p>\n" + 
					"        </div>\n" + 
					"      </div>\n" + 
					"    </div>\n" + 
					"  </div>\n" + 
					"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\n" + 
					"  <pingendo onclick=\"window.open('https://pingendo.com/', '_blank')\" style=\"cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white\">Made with Pingendo Free&nbsp;&nbsp;<img src=\"https://pingendo.com/site-assets/Pingendo_logo_big.png\" class=\"d-block\" alt=\"Pingendo logo\" height=\"16\"></pingendo>\n" + 
					"</body>\n" + 
					"\n" + 
					"</html>";
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser); 

			return true;

		}
		return false;

	}

}