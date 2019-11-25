package views;

import java.util.List;

import org.h2.mvstore.MVMap;

import model.ContactForm;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class ContactUs extends DynamicWebPage {

	public ContactUs(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}

	public boolean process(WebRequest toProcess) {
		if (toProcess.path.equalsIgnoreCase("ContactUs"))

		{

			String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "\r\n" + "<head>\r\n"
					+ "  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"theme.css\" type=\"text/css\">\r\n" + "</head>\r\n" + "\r\n"
					+ "<body class=\"bg-light\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\" >" 
+ "<nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\r\n" + 
"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\r\n" + 
"        <span class=\"navbar-toggler-icon\"></span>\r\n" + 
"      </button>\r\n" + 
"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\r\n" + 
"        <ul class=\"navbar-nav\">\r\n" + 
"<li class=\"nav-item mx-2 text-dark\"> <a class=\"nav-link\" href=\"AddView\">Add Article </a> </li>\r\n" + 
"          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"Login\">My Account</a> </li>\r\n" + 
"<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>          <li class=\"nav-item mx-2 text-dark\"> </li>\r\n" + 
"         <li class=\"nav-item mx-2 text-dark\"> </li> <li>     <div class=\"container\">\r\n" + 
"      <div class=\"row\">\r\n" + 
"        <div class=\"col-md-12\">\r\n" + 
"          <form class=\"form-inline\" role=\"form\" method=\"GET\" action=\"AllResults40226329view\">\r\n" + 
"            <div class=\"input-group\">\r\n" + 
"              <input type=\"text\" class=\"form-control\" id=\"searchbox\" name=\"searchstring\" placeholder=\"Search\">\r\n" + 
"              <div class=\"input-group-append\"><button class=\"btn btn-primary\" type=\"button\"><i class=\"fa fa-search\"></i></button></div>\r\n" + 
"            </div>\r\n" + 
"          </form>\r\n" + 
"        </div>\r\n" + 
"      </div>\r\n" + 
"  </div></li>\r\n" + 
"        </ul>\r\n" + 
"      </div>\r\n" + 
"    </div>\r\n" + 
"  </nav>\r\n" + 
""
					+ "  <div class=\"py-3\">\r\n" + "    <div class=\"container\">\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\">\r\n"
					+ "          <h1 class=\"text-black text-center\">Got some questions?</h1>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n"
					+ "  <div class=\"py-1\">\r\n" + "    <div class=\"container\">\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\" style=\"\">\r\n"
					+ "          <p class=\"text-black text-center\">Here you can find all the answers (hopefully) to your questions! We have a dedicated FAQ section just below or if you prefer you can even send us a message and we can email you back as soon as we can with an answer!</p>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n"
					+ "  <div class=\"py-2\">\r\n" + "    <div class=\"container\">\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-md-12\">\r\n"
					+ "          <h1 class=\"text-black text-center\">FAQs</h1>\r\n" + "        </div>\r\n"
					+ "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "  <div class=\"py-2\">\r\n"
					+ "    <div class=\"container\">\r\n" + "      <div class=\"row\">\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6><b>Can I submit my own guide?</b></h6>\r\n"
					+ "              <p class=\"text-center\" contenteditable=\"true\">Yes! Head over to the <a href=\"AddEditView\" target=\"_blank\">Add &amp; Edit</a> section and there you can get started!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n" + "              <h6><b>How do I signup?</b></h6>\r\n"
					+ "              <p>To sign up to our site visit the <a href=\"SigninView\">Signup</a> page located at the top!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\" style=\"\">\r\n"
					+ "              <h6 class=\"text-center\"><b>I can't find the right guide</b></h6>\r\n"
					+ "              <p class=\"text-center\">Not all guides cover every vehicle but if you contact us we may be able to help!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6><b>Where can I find my guides?</b></h6>\r\n"
					+ "              <p>Use the <a href=\"SearchResultsPage40226329view\">search page</a> to search for your correct guides!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6 class=\"text-center\"><b class=\"text-center\">How can I login?</b></h6>\r\n"
					+ "              <p class=\"text-center\">To login to your account head over to the login page and enter your details!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6 class=\"text-center\"><b class=\"text-center\">I haven't received my email?</b></h6>\r\n"
					+ "              <p class=\"text-center\">Verification emails can take up to 15 minutes so hang tight or let us know below!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6 class=\"text-center\" contenteditable=\"true\"><b>How often do guides go up?</b></h6>\r\n"
					+ "              <p class=\"text-center\">New guides are uploaded up to 25 times a day so keep checking to see new ones!</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n"
					+ "        <div class=\"col-md-3\" style=\"\">\r\n" + "          <div class=\"card\">\r\n"
					+ "            <div class=\"card-body\">\r\n"
					+ "              <h6 class=\"text-center\"><b>My question isnt listed here.</b></h6>\r\n"
					+ "              <p class=\"text-center\">Use the contact form to reach out to us and we will get back to you A.S.A.P</p>\r\n"
					+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n"
					+ "    </div>\r\n" + "  </div>\r\n" + "  <div class=\"py-2\">\r\n"
					+ "    <div class=\"container\">\r\n" + "      <div class=\"row\">\r\n"
					+ "        <div class=\"col-md-12\">\r\n"
					+ "          <h2 class=\"text-black text-center\">Contact Form</h2>\r\n" + "        </div>\r\n"
					+ "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "  <div class=\"py-1\">\r\n"
					+ "    <div class=\"container\">\r\n" + "      <div class=\"row\">\r\n"
					+ "        <div class=\"col-md-12\">\r\n"
					+ "           <form  action = \"/ContactForm1\" method = \"GET\">\r\n"
					+ "            <div class=\"form-group\" style=\"\"> <label class=\"text-light\">Email*:</label> <input type=\"email\" name=\"Email*\"class=\"form-control\" placeholder=\"Email:\" required=\"required\"> <small class=\"form-text text-muted\">We'll never share your email with anyone else.</small> </div>\r\n"
					+ "            <div class=\"form-group\"> <label class=\"text-black\">Forename:</label> <input type=\"text\" name=\"Forename\"  class=\"form-control\" placeholder=\"Forename\"> </div>\r\n"
					+ "            <div class=\"form-group\"> <label class=\"text-black\">Surname:</label> <input type=\"text\" name=\"Surname\" class=\"form-control\" placeholder=\"Surname\" >\r\n"
					+ "              <div class=\"form-group\"><label class=\"text-black\">What's the problem*:</label><input type=\"text\" name=\"What's-the-problem*\"class=\"form-control form-control-lg h-25 w-100\"></div>\r\n"
					+ "            </div>\r\n"
					+ "            <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n"
					+ "          </form><label class=\"text-black\" contenteditable=\"true\">Fields marked with * must be completed.</label>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n"
					+ "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\r\n"
					+ "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n"
					+ "</body>\r\n" + "\r\n" + "</html>";

			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
			return true;
		} else if (toProcess.path.equalsIgnoreCase("ContactForm1")) {
			ContactForm contact = new ContactForm();
			contact.uniqueid = "query_" + System.currentTimeMillis();
			contact.surname = toProcess.params.get("Surname");
			contact.email = toProcess.params.get("Email*");
			contact.query = toProcess.params.get("What's-the-problem*");
			contact.forename = toProcess.params.get("Forename");
			
			MVMap<String, ContactForm> queries = db.s.openMap("Queries");
			queries.put(contact.uniqueid, contact);
			db.commit();
			String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"  <meta charset=\"utf-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
					"  <link rel=\"stylesheet\" href=\"theme.css\" type=\"text/css\">\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body class=\"bg-light\" >\r\n" + 
					"  <nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\r\n" + 
					"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\r\n" + 
					"        <span class=\"navbar-toggler-icon\"></span>\r\n" + 
					"      </button>\r\n" + 
					"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\r\n" + 
					"        <ul class=\"navbar-nav\">\r\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"AddEdit40232871.html\">Add &amp; Edit</a> </li>\r\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"SearchResultPage40226329.html\">Search</a> </li>\r\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"homeview\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i>\r\n" + 
					"              <b> How its Made</b></a> </li>\r\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"login40234553.html\">Signup</a> </li>\r\n" + 
					"          <li class=\"nav-item mx-2\"> <a class=\"nav-link\" href=\"login40234553.html\">Login</a> </li>\r\n" + 
					"        </ul>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </nav>\r\n" + 
					"  <div class=\"\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row\">\r\n" + 
					"        <div class=\"col-md-12\">\r\n" + 
					"          <h1 class=\"text-center text-dark\">Thank you for your submission.</h1>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"  <div class=\"\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row text-dark\">\r\n" + 
					"        <div class=\"mx-auto\"><i class=\"fa mx-auto text-center px-0 text-dark fa-envelope fa-2x\"></i></div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"  <div class=\"py-1\">\r\n" + 
					"    <div class=\"container\">\r\n" + 
					"      <div class=\"row\">\r\n" + 
					"        <div class=\"col-md-12\" style=\"\">\r\n" + 
					"          <p class=\"text-center text-dark\" style=\"\">One of our customer support members will reach out to you in the next 1-2 working days.</p>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" style=\"\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <pingendo onclick=\"window.open('https://pingendo.com/', '_blank')\" style=\"cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white\">Made with Pingendo Free&nbsp;&nbsp;<img src=\"https://pingendo.com/site-assets/Pingendo_logo_big.png\" class=\"d-block\" alt=\"Pingendo logo\" height=\"16\"></pingendo>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);

			return true;

		}
		return false;

	}

}