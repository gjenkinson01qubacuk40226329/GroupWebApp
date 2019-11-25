package views;

import model.Article;
import model.HomePage;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class HomeView extends DynamicWebPage{

	public HomeView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	
	public boolean process(WebRequest toProcess)
	{
	if(toProcess.path.equalsIgnoreCase("HomeView"))
	{
		HomePage Article1 = new HomePage();
		Article1.title = "VW Announce well anticipated Hot SUV";
		Article1.description = "Volkswagen debut new T-ROC R.";
		Article1.img = "vw.png";
		Article1.body = "Weeks just after rumours surface regarding Volkswagens new flagship hot crossover an announcement was made at the Wolfsburg headquarters today, the new car will pack the famous EA222 engine found in the Golf R and will pack the same meaty 296bhp. Expect a guide up soon!";
		
		HomePage Article2 = new HomePage();
		Article2.title = "F1 kicks off for once again another exciting season";
		Article2.description = "Hamilton battles it out for another world title.";
		Article2.img = "f1.png";
		Article2.body = "Following the first race of the season Bottas takes a convincing win followed by his teammate Hamilton for Mercedes and Verstappen takes third on the pole for Red Bull. Next up is Bahrain.";
		
		HomePage Article3 = new HomePage();
		Article3.title = "Classic Audi Guide Coming Soon!";
		Article3.description = "Audi's Quattro Rally joins the pack.";
		Article3.img = "audi.png";
		Article3.body = "30 Years on from Audi's revolutionary game changing Quattro series for the World Rally Championships, How Its Made will have the truly spectacular car's guides and articles for the rally enthusiasts starting April 1st.";
	
	
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
			"<body class=\"bg-light\" style=\"	background-image: linear-gradient(to bottom, rgba(250,250,246,0.2), rgba(250,246,246,0.2));	background-position: top left, top left;	background-size: 100%, 100%;	background-repeat: repeat, repeat;\">\r\n" + 
			"  <nav class=\"navbar navbar-expand-md navbar-dark bg-primary\">\r\n" +
			"    <div class=\"container justify-content-center\"> <button class=\"navbar-toggler navbar-toggler-right border-0\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar15\" style=\"\">\r\n" +
			"        <span class=\"navbar-toggler-icon\"></span>\r\n" + "      </button>\r\n" +
			"      <div class=\"collapse navbar-collapse text-center justify-content-center\" id=\"navbar15\">\r\n" +
			"        <ul class=\"navbar-nav\">\r\n" +
			"<li class=\"nav-item mx-2 text-dark\"> <a class=\"nav-link\" href=\"AddView\">Add Article </a> </li>\r\n" +
			"          <li class=\"nav-item mx-2 text-dark\"> <a> </a><a class=\"nav-link\" href=\"Login\">My Account</a> </li>\r\n" +
			"<li class=\"nav-item mx-2\" > <a class=\"nav-link navbar-brand mr-0 text-white\" href=\"HomeView\"><i class=\"fa d-inline fa-lg fa-stop-circle-o\"></i><b> How its Made</b></a> </li>" +
			"          <li class=\"nav-item mx-2 text-dark\"> </li>\r\n" +
			"         <li class=\"nav-item mx-2 text-dark\"> </li>" + " <li> " +
			AllResults40226329view.embedSearchBox() + "</li>\r\n" + "        </ul>\r\n" + "      </div>\r\n" +
			"    </div>\r\n" + "  </nav>\r\n"+
			"  <div class=\"py-3\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <h1 class=\"text-black text-center\">Welcome to How its Made!</h1>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-0\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <p class=\"text-center text-black\">Welcome to our new site, here you will find the most up to date and latest vehicle guides on where and how these exquisite machines are made!<br><br>Please choose your desired subsection to explore our site!</p>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-2\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-4\"><a class=\"btn text-black w-100 btn-primary shadow-sm\" href=\"/AllResults40226329view?searchstring=Car\">Cars</a></div>\r\n" + 
			"        <div class=\"col-md-4\"><a class=\"btn btn-primary text-black w-100 shadow-sm\" href=\"/AllResults40226329view?searchstring=Plane\">Planes</a></div>\r\n" + 
			"        <div class=\"col-md-4\"><a class=\"btn btn-primary text-black w-100 shadow-sm\" href=\"/AllResults40226329view?searchstring=Boat\">Boats</a></div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-2\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <h1 class=\"text-black text-center\">Got any Questions?</h1>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-1\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-10\">\r\n" + 
			"          <p class=\"text-black text-center\">If you wish to find out any information please visit our FAQ section and if you have any further questions please make sure to contact us and we will aim to reply back within 1-2 working days.</p>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"col-md-2\"><a class=\"btn btn-primary\" href=\"contactus\" ><i class=\"fa fa-fw fa-1x py-1 fa-envelope\"></i></a></div>\r\n" + 
			"      </div>\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\" style=\"\">\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-2\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <div class=\"row\">\r\n" + 
			"            <div class=\"col-md-4\"></div>\r\n" + 
			"            <div class=\"col-md-4\"><a class=\"btn btn-primary btn-lg btn-block\" href=\"contactus\">Frequently Asked Questions</a></div>\r\n" + 
			"            <div class=\"col-md-4\"></div>\r\n" + 
			"          </div>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-3\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <h1 class=\"text-black text-center\">Latest News!</h1>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <div class=\"py-1\">\r\n" + 
			"    <div class=\"container\">\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-lg-4 p-4 text-center\">\r\n" + 
			"          <img class=\"img-fluid d-block mx-auto w-25\" src=\""+Article1.img+"\" >" +
			"          <p class=\"mb-1 text-black\"> <b>"+Article1.title+"</b></p>\r\n" + 
			"          <p class=\"mb-0 text-black\">"+Article1.description+"</p>\r\n" + 
			"          <p class=\"mb-3 text-black\">"+Article1.body+"</p>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"col-lg-4 p-4 text-center\">\r\n" + 
			"          <img class=\"img-fluid d-block mx-auto w-25\" src=\""+Article2.img+"\" >"+
			"          <p class=\"mb-1 text-black\"> <b>"+Article2.title+"</b></p>\r\n" + 
			"          <p class=\"mb-0 text-black\">"+Article2.description+"</p>\r\n" + 
			"          <p class=\"mb-3 text-black\">"+Article2.body+"</p>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"col-lg-4 p-4 text-center\">\r\n" + 
			"          <img class=\"img-fluid d-block mx-auto w-25\" src=\""+Article3.img+"\" >"+
			"          <p class=\"mb-1 text-black\"><b>"+Article3.title+"</b></p>\r\n" + 
			"          <p class=\"mb-0 text-black\">"+Article3.description+"</p>\r\n" + 
			"          <p class=\"mb-3 text-black\">"+Article3.body+"</p>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"      <div class=\"row\">\r\n" + 
			"        <div class=\"col-md-12\">\r\n" + 
			"          <div class=\"row\">\r\n" + 
			"            <div class=\"col-md-10\" style=\"\">\r\n" + 
			"              <p class=\"text-center text-black\">Psst over here! Spot something that isn't right? Let our geek squad know and we will get our monkeys to have a look!</p>\r\n" + 
			"            </div>\r\n" + 
			"            <div class=\"col-md-2\" style=\"\"><a class=\"btn btn-primary\" href=\"bugreporting\"#\"><i class=\"fa fa-fw fa-1x py-1 fa-cogs\"></i></a></div>\r\n" + 
			"          </div>\r\n" + 
			"        </div>\r\n" + 
			"      </div>\r\n" + 
			"    </div>\r\n" + 
			"  </div>\r\n" + 
			"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\r\n" + 
			"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>";
	     
	toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
	return true;
	}
	return false;
	}
}