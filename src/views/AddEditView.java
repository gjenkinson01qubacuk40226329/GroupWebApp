package views;

import java.util.List;
import java.awt.TrayIcon;
import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.file.Path;
import java.util.ArrayList;


import org.h2.mvstore.MVMap;
import org.omg.IOP.Codec;

import model.AccountModel;
import model.Article;
import model.ArticleSubSection;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.*;

public class AddEditView extends DynamicWebPage {


	public AddEditView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	public boolean process(WebRequest toProcess){
		AccountModel Account = new AccountModel();
		MVMap<String, AccountModel> Accounts = db.s.openMap("Accounts");


		
		Account = Accounts.get(toProcess.cookies.get("username"));
		String stringToSendToWebBrowser="";
		
		
		
		if(toProcess.path.equalsIgnoreCase("addview")||toProcess.path.equalsIgnoreCase("editview")) {
		
			stringToSendToWebBrowser += "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<script>\r\n" + 
					"function next_step1() {\r\n" + 
					"document.getElementById(\"first\").style.display = \"none\";\r\n" + 
					"document.getElementById(\"second\").style.display = \"block\";\r\n" + 
					"}\r\n" + 
					"// Function that executes on click of first previous button.\r\n" + 
					"function prev_step1() {\r\n" + 
					"document.getElementById(\"first\").style.display = \"block\";\r\n" + 
					"document.getElementById(\"second\").style.display = \"none\";\r\n" + 
					"}\r\n" + 
					"function next_step2() {\r\n" + 
					"document.getElementById(\"second\").style.display = \"none\";\r\n" + 
					"document.getElementById(\"third\").style.display = \"block\";\r\n" + 
					"}\r\n" + 
					"// Function that executes on click of first previous button.\r\n" + 
					"function prev_step2() {\r\n" + 
					"document.getElementById(\"second\").style.display = \"block\";\r\n" + 
					"document.getElementById(\"third\").style.display = \"none\";\r\n" + 
					"}\r\n" + 
					"function next_step3() {\r\n" + 
					"document.getElementById(\"third\").style.display = \"none\";\r\n" + 
					"document.getElementById(\"fourth\").style.display = \"block\";\r\n" + 
					"}\r\n" + 
					"// Function that executes on click of first previous button.\r\n" + 
					"function prev_step3() {\r\n" + 
					"document.getElementById(\"third\").style.display = \"block\";\r\n" + 
					"document.getElementById(\"fourth\").style.display = \"none\";\r\n" + 
					"}\r\n" + 
					"// Function that executes on click of first previous button.\r\n" + 
					"function prev_step4() {\r\n" + 
					"document.getElementById(\"third\").style.display = \"block\";\r\n" + 
					"document.getElementById(\"fourth\").style.display = \"none\";\r\n" + 
					"}\r\n" + 
					"//checks article form (first form) completed correctly\r\n" + 
					"function form1ValidationNext(){\r\n" + 
					"var articleTitle = document.getElementsByName(\"Title\")[0].value;\r\n" + 
					"var articleDescription = document.getElementsByName(\"ArticleDescription\")[0].value;\r\n" + 
					"var type = document.getElementById(\"typeDropDown\");\r\n" + 
					"var typeText= type.options[type.selectedIndex].text;\r\n" + 
					"var filePath = document.getElementsByName(\"fileToUpload\")[0].value;"+
					"var alertString ='';\r\n" + 
					"if( (articleTitle != null && articleTitle.trim() !== '')&&(articleDescription != null && articleDescription.trim() !== '')&&(typeText!=='Select Articles Subject')&&(validatePicture(filePath)==true)){\r\n" + 
					"	next_step1();\r\n" + 
					"}\r\n" + 
					"else{\r\n" + 
					"	if((articleTitle == null || articleTitle.trim() == '')){\r\n" + 
					"		alertString=alertString+'You must provide an Article Title\\n';\r\n" + 
					"	}\r\n" + 
					"	if(articleDescription == null || articleDescription.trim() == ''){\r\n" + 
					"		alertString=alertString+'You must provide an Article Description\\n';\r\n" + 
					"	}\r\n" + 
					"	if(typeText=='Select Articles Subject'){\r\n" + 
					"		alertString=alertString+'You must select the Articles Subject\\n';\r\n" + 
					"	}\r\n" + 
					"	if(validatePicture(filePath)!=true){\r\n"+
					"		alertString=alertString+'You must only upload a file of type image.';"+
					"}\r\n"+
					"	alert(alertString);\r\n" + 
					"}\r\n" + 
					"}\r\n" + 
					"function validatePicture(filePath){"+
					"var FileUploadPath = filePath;\r\n" + 
					"\r\n" + 
					"var Extension = FileUploadPath.substring(\r\n" + 
					"FileUploadPath.lastIndexOf('.') + 1).toLowerCase();\r\n" + 
					"\r\n" + 
					"//The file uploaded is an image\r\n" + 
					"\r\n" + 
					"if (Extension == \"gif\" || Extension == \"png\" || Extension == \"bmp\"\r\n" + 
					"                    || Extension == \"jpeg\" || Extension == \"jpg\"){"+
					"return true;}"+
					"if(FileUploadPath==''){\r\n"+
					"	return true;\r\n}\r\n"+
					"else{"
					+ "return false;}"+
					"}\r\n"+
					"function validateSubSection(subSectionTitle, subSectionBody, filePath){\r\n" + 
					"var alertString ='';\r\n" + 
					"if((subSectionTitle == null || subSectionTitle.trim() == '')){\r\n" + 
					"		alertString=alertString+'You must provide an Sub Section Heading or Title\\n';\r\n" + 
					"	}\r\n" + 
					"	if(subSectionBody == null || subSectionBody.trim() == ''){\r\n" + 
					"		alertString=alertString+'You must provide an Sub Section Body\\n';\r\n" + 
					"	}\r\n" +
					"	if(validatePicture(filePath)!=true){\r\n"+
					"		alertString=alertString+'The file you upload must be an image.';\r\n}\r\n"+
					"	return(alertString);\r\n" + 
					"}\r\n" + 
					"function ValidateSubSection1(option){\r\n" + 
					"var subSectionTitle = document.getElementsByName(\"SectionHeading1\")[0].value;\r\n" + 
					"var subSectionBody = document.getElementsByName(\"SectionBody1\")[0].value;\r\n" + 
					"var pathToImage = document.getElementsByName(\"fileToUpload1\")[0].value;\r\n"+
					"\r\n" + 
					"if( ((subSectionTitle != null && subSectionTitle.trim() !== '')&&( subSectionBody!= null && subSectionBody.trim() !== '')&&(validatePicture(pathToImage)==true))){\r\n" + 
					"	\r\n" + 
					"	if(option==\"next\"){\r\n" + 
					"	next_step2();\r\n" + 
					"	}\r\n" + 
					"	else if(option==\"prev\")\r\n" + 
					"	{\r\n" + 
					"		prev_step1();\r\n" + 
					"	}\r\n" + 
					"	else{\r\n" + 
					"		document.forms[\"form1\"].submit();\r\n" + 
					"	}\r\n" + 
					"}\r\n" +
					"else if((subSectionTitle == null || subSectionTitle.trim() == '')&&( subSectionBody == null || subSectionBody.trim() == '')&&(pathToImage == '')&&(option==\"prev\"))\r\n"+
					"{\r\n"+
					"	prev_step1();\r\n"+
					"}\r\n"+
					"else{\r\n" + 
					"	alert(validateSubSection(subSectionTitle, subSectionBody, pathToImage));\r\n" +
					"	\r\n" + 
					"}\r\n" + 
					"}\r\n" + 
					"function ValidateSubSection2(option){\r\n" + 
					"var subSectionTitle = document.getElementsByName(\"SectionHeading2\")[0].value;\r\n" + 
					"var subSectionBody = document.getElementsByName(\"SectionBody2\")[0].value;\r\n" + 
					"var pathToImage = document.getElementsByName(\"fileToUpload2\")[0].value;\r\n"+
					"\r\n" + 
					"if( ((subSectionTitle != null && subSectionTitle.trim() !== '')&&( subSectionBody!= null && subSectionBody.trim() !== '')&&(validatePicture(pathToImage)==true))){\r\n" + 
					"	\r\n" + 
					"	if(option==\"next\"){\r\n" + 
					"	next_step3();\r\n" + 
					"	}\r\n" + 
					"	else if(option==\"prev\"){\r\n" + 
					"	prev_step2();\r\n" + 
					"	}\r\n" + 
					"	else{\r\n" + 
					"		document.forms[\"form1\"].submit();\r\n" + 
					"	}\r\n" + 
					"}\r\n" + 
					"else if((validateSubSection(subSectionTitle, subSectionBody, pathToImage)!='')&&(pathToImage.trim() == ''||pathToImage == null)&&(option==\"prev\"))\r\n"+
					"{\r\n"+
					"	prev_step2();\r\n"+
					"}\r\n"+
					
					"else{\r\n" + 
					"alert(validateSubSection(subSectionTitle, subSectionBody, pathToImage));\r\n" +
					"	\r\n" + 
					"}\r\n" + 
					"}\r\n" + 
					"function ValidateSubSection3(option){\r\n" + 
					"var subSectionTitle = document.getElementsByName(\"SectionHeading3\")[0].value;\r\n" + 
					"var subSectionBody = document.getElementsByName(\"SectionBody3\")[0].value;\r\n" + 
					"var pathToImage = document.getElementsByName(\"fileToUpload3\")[0].value;\r\n"+
					"\r\n" + 
					"if( ((subSectionTitle != null && subSectionTitle.trim() !== '')&&( subSectionBody!= null && subSectionBody.trim() !== '')&&(validatePicture(pathToImage)==true))){\r\n" + 
					"	\r\n" + 
					"	if(option==\"prev\"){\r\n" + 
					"	prev_step3();\r\n" + 
					"	}\r\n" +
					"	else{\r\n" + 
					"		document.forms[\"form1\"].submit();\r\n" + 
					"	}\r\n" + 
					"}\r\n" + 
					"else if((validateSubSection(subSectionTitle, subSectionBody, pathToImage)!='')&&(pathToImage.trim() == ''||pathToImage == null)&&(option==\"prev\"))\r\n"+
					"{\r\n"+
					"	prev_step3();\r\n"+
					"}\r\n"+
					"else{\r\n" + 
					"alert(validateSubSection(subSectionTitle, subSectionBody, pathToImage));\r\n" + 
					"	\r\n" + 
					"}\r\n" + 
					"}\r\n" + 
					"function asyncLoad(){\r\n" + 
					"    if (window.XMLHttpRequest)\r\n" + 
					"    {// code for IE7+, Firefox, Chrome, Opera, Safari\r\n" + 
					"        xmlhttp=new XMLHttpRequest();\r\n" + 
					"    }\r\n" + 
					"    else\r\n" + 
					"    {// code for IE6, IE5\r\n" + 
					"        xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\r\n" + 
					"    }\r\n" + 
					"    xmlhttp.onreadystatechange=myDataLoadedFunction;\r\n" + 
					"    //This line actually makes the request to the server\r\n" + 
					"    xmlhttp.open(\"POST\",'/Index.html/Async?title='+document.getElementById(\"title\").value+'&description='+document.getElementById(\"description\").value+'&category='+document.getElementById(\"category\").value,true);\r\n" + 
					"    xmlhttp.send();\r\n" + 
					"}\r\n" + 
					"function myDataLoadedFunction()\r\n" + 
					"{\r\n" + 
					"  if (xmlhttp.readyState==4 && xmlhttp.status==200)\r\n" + 
					"  {\r\n" + 
					"    //This line will set the html between the <body> tags\r\n" + 
					"    //with what was returned by the webserver\r\n" + 
					"    //this will make the page redraw\r\n" + 
					"      document.getElementById(\"newseventsdiv\").innerHTML = xmlhttp.responseText;\r\n" + 
					"  }\r\n" + 
					"}\r\n" + 
					"</script>\r\n" + 
					"<style>\r\n" + 
					"input {\r\n" + 
					"    background-color:transparent;\r\n" + 
					"    border: 0px solid;\r\n" + 
					"    height:30px;\r\n" + 
					"    width:260px;\r\n" + 
					"}"+
					"fieldset{\r\n" + 
					"display:none;\r\n" + 
					"}\r\n" + 
					"#first{\r\n" + 
					"display:block;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css\">\r\n"
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
					+ "    </div>\r\n" + "  </nav>\r\n";
		}
		if(toProcess.path.equalsIgnoreCase("AddView"))
		{

			stringToSendToWebBrowser+="  <form method=\"post\" enctype=\"multipart/form-data\" action=\"/addArticleProcess\" id = \"form1\">\r\n" + 
					"    <div class=\"py-5\">\r\n" + 
					"      <div class=\"container\">\r\n" + 
					"        <div class=\"row\">\r\n" + 
					"          <div class=\"col-md-12\">\r\n" + 
					"<fieldset id=\"first\"><div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Creation</div>\r\n" + 
					"        <div class=\"card-body\">\r\n" + 
					"<div class=\"form-group\"> <label contenteditable=\"false\">Add Title Of New Topic</label> <input type=\"text\" name=\"Title\" class=\"form-control\" placeholder=\"Topic Title\" autocomplete = \"off\"> </div>\r\n" + 
					"            <div class=\"form-group\"> <label>Topic Description</label> <input type=\"text\" name=\"ArticleDescription\" class=\"form-control h-25\" placeholder=\"Add a short description of your article here.\" autocomplete =\"off\"></div>\r\n" + 
					"<select id=\"typeDropDown\" name =\"types\">\r\n" + 
					"<option>Select Articles Subject</option>\r\n" + 
					"<option value=\"Cars\">Cars</option>\r\n" + 
					"<option value=\"Boats\">Boats</option>\r\n" + 
					"<option value=\"Planes\">Planes</option>\r\n" + 
					"</select>"+
					"<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
					"<input type=\"text\" readonly id= \"customText\" name = \"customText\" value=\"No File Uploaded\">\r\n"+
					"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" + 
					"</div>\r\n" + 
					"			<div class=\"form-group\"> <label>Add a YouTube Link related to your article.</label> <input type=\"link\" autocomplete = \"off\" name = \"youtubeLink\"  class=\"form-control\" placeholder=\"Youtube Link\" ></div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\"  onclick= \"form1ValidationNext()\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Next</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"second\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 1</div>\r\n" + 
					"        <div class=\"card-body\">\r\n" + 
					"			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading1\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
					"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody1\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" +
					"<div><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
					"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\"No File Uploaded\">\r\n</div>"+
					"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\" accept = \"image/*\">		\r\n" + 
					"		</div>\r\n" + 
					"    </div>\r\n" + 
					"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection1('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Back</button><button class=\"btn btn-primary\" type=\"button\" contenteditable=\"false\" id = \"next1\" onclick= \"ValidateSubSection1('next');\" style=\"padding: 5px 10px; margin: 25px;\">Add Another Article Section</button><button class=\"btn btn-primary\" type = \"button\" id = \"submit1\" contenteditable=\"false\" onclick = \"ValidateSubSection1('submit');\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"third\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 2</div>\r\n" + 
					"        <div class=\"card-body\">\r\n" + 
					"			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading2\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
					"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody2\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" +
					"<div><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
					"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\" value=\"No File Uploaded\"></div>\r\n"+
					"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" id=\"fileToUpload2\" accept = \"image/*\">		\r\n" + 
					"		</div>\r\n" + 
					"    </div>\r\n" + 
					"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection2('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Back</button><button class=\"btn btn-primary\" type=\"button\" id = \"next2\" contenteditable=\"false\" onclick= \"ValidateSubSection2('next');\" style=\"padding: 5px 10px; margin: 25px;\">Add Another Article Section</button><button class=\"btn btn-primary\" type=\"button\" contenteditable=\"false\" id = \"submit2\" onclick=\"ValidateSubSection2('submit');\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"fourth\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 3 </div>\r\n" + 
					"        <div class=\"card-body\">\r\n" + 
					"			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading3\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
					"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody3\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" +
					"<div><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
					"<input type=\"text\" readonly id= \"customText3\" name = \"customText3\" value=\"No File Uploaded\"></div>\r\n"+
					"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\">		\r\n" + 
					"		</div>\r\n" + 
					"    </div>\r\n" + 
					"<button class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection3('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\" type=\"button\" >Back</button><button class=\"btn btn-primary\" contenteditable=\"false\" id = \"submit3\" onclick = \"ValidateSubSection3('submit');\" type=\"button\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"    </div>\r\n" + 
					"	</div>\r\n" + 
					"  </form>\r\n" + 
					"<script>\r\n"+
					"const customButton = document.getElementById(\"customFileButton\");\r\n"+
					"const oldFileButton = document.getElementById(\"fileToUpload\");\r\n"+
					"const customText = document.getElementById(\"customText\");\r\n"+
					
					"const customButton1 = document.getElementById(\"customFileButton1\");\r\n"+
					"const oldFileButton1 = document.getElementById(\"fileToUpload1\");\r\n"+
					"const customText1 = document.getElementById(\"customText1\");\r\n"+
					
					"const customButton2 = document.getElementById(\"customFileButton2\");\r\n"+
					"const oldFileButton2 = document.getElementById(\"fileToUpload2\");\r\n"+
					"const customText2 = document.getElementById(\"customText2\");\r\n"+
					
					"const customButton3 = document.getElementById(\"customFileButton3\");\r\n"+
					"const oldFileButton3 = document.getElementById(\"fileToUpload3\");\r\n"+
					"const customText3 = document.getElementById(\"customText3\");\r\n"+
					
					"customButton.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton.click();\r\n"+
					"});\r\n"+
					"oldFileButton.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton.value){ \r\n"+
					"		customText.value = oldFileButton.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton1.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton1.click();\r\n"+
					"});\r\n"+
					"oldFileButton1.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton1.value){ \r\n"+
					"		customText1.value = oldFileButton1.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText1.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton2.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton2.click();\r\n"+
					"});\r\n"+
					"oldFileButton2.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton2.value){ \r\n"+
					"		customText2.value = oldFileButton2.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText2.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton3.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton3.click();\r\n"+
					"});\r\n"+
					"oldFileButton3.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton3.value){ \r\n"+
					"		customText3.value = oldFileButton3.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText3.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					"</script>\r\n" +
					"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" ; 
					stringToSendToWebBrowser+="</body>\r\n" + 
					"\r\n" + 
					"</html>";
					if(toProcess.cookies.get("username") == null || toProcess.cookies.get("username").equals(""))
					{
						stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" +
								"<html>\r\n" + 
								"<body onload=\"noCookies()\">"+
								"</body>\n"+					
								"  <script>\n" +
								"      function noCookies()\n" +					
								"      {\n" +
								"window.location.replace(\"login?NoAccount=true\");\r\n" + 
								"      }\n" +
								"  </script>\n" +	
								"</html>"; 
						
						toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, 
								stringToSendToWebBrowser);
					}
			toProcess.r = new WebResponse( WebResponse.HTTP_OK,WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		else if(toProcess.path.equalsIgnoreCase("EditView")){
			String UniqueID  = toProcess.params.get("entry");
			MVMap<String, Article> articles = db.s.openMap("Articles");
			MVMap<String, ArticleSubSection> subSections = db.s.openMap("SubSection");
			Article articleToDisplay = articles.get(UniqueID);
			List<String> ArticleKeys = articles.keyList();
			List<String> SubSectionKeys = subSections.keyList();
			ArrayList<ArticleSubSection> SubSectionsForArticle = getSubSectionsForArticle(UniqueID, subSections, SubSectionKeys);
			stringToSendToWebBrowser+="  <form method=\"post\" enctype=\"multipart/form-data\" action=\"/amendArticleProcess\" id = \"form1\">\r\n" + 
					"    <div class=\"py-5\">\r\n" + 
					"      <div class=\"container\">\r\n" + 
					"        <div class=\"row\">\r\n" + 
					"          <div class=\"col-md-12\">\r\n" + 
					"<fieldset id=\"first\"><div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Creation</div>\r\n" + 
					"        <div class=\"card-body\">\r\n" ;
			try {
				String tempString="<div class=\"form-group\"> <label contenteditable=\"false\">Add Title Of New Topic</label> <input type=\"text\" name=\"Title\" class=\"form-control\" placeholder=\"Topic Title\" autocomplete = \"off\" value = \""+articleToDisplay.articleTitle+"\"> </div>\r\n" + 
						"            <div class=\"form-group\"> <label>Topic Description</label> <input type=\"text\" name=\"ArticleDescription\" class=\"form-control h-25\" placeholder=\"Add a short description of your article here.\" autocomplete =\"off\" value = \""+articleToDisplay.articleDescription+"\"></div>\r\n" + 
						"<select class=\"options\" name = \"types\" id=\"typeDropDown\">\r\n"+ 
						"<option>Select Articles Subject</option>\r\n";
				if(articleToDisplay.type.equals("Cars")) {
					tempString+="<option value=\"Cars\" selected>Cars</option>\r\n";
				}
				else {
					tempString+="<option value=\"Cars\">Cars</option>\r\n";
				}
				if(articleToDisplay.type.equals("Boats")) {
					tempString+="<option value=\"Boats\" selected>Boats</option>\r\n";
				}
				else {
					tempString+="<option value=\"Boats\">Boats</option>\r\n";
				}
				if(articleToDisplay.type.equals("Planes")) {
					tempString+="<option value=\"Planes\" selected>Planes</option>\r\n"; 
				}
				else {
					tempString+="<option value=\"Planes\">Planes</option>\r\n"; 
				}				
						tempString+="</select>       \r\n" +
						"<input type=\"hidden\" id=\"ArticleUniqueID\" name=\"ArticleUniqueID\" value=\""+articleToDisplay.uniqueString+"\">";
						if(articleToDisplay.filepathToImages.equals("NoImageUploaded")) {
							tempString+="<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
									"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
						}
						else {
							tempString+="<div style=\"display: none;\"><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
									"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
						}
				stringToSendToWebBrowser+=tempString;
			} catch (Exception e) {
				stringToSendToWebBrowser+="<div class=\"form-group\"> <label contenteditable=\"false\">Add Title Of New Topic</label> <input type=\"text\" name=\"Title\" class=\"form-control\" placeholder=\"Topic Title\" autocomplete = \"off\"> </div>\r\n" + 
						"            <div class=\"form-group\"> <label>Topic Description</label> <input type=\"text\" name=\"ArticleDescription\" class=\"form-control h-25\" placeholder=\"Add a short description of your article here.\" autocomplete =\"off\"></div>\r\n" + 
						"<select class=\"options\" name=\"types\" id=\"typeDropDown\">\r\n" + 
						"<option>Select Articles Subject</option>\r\n" + 
						"<option value=\"Cars\">Cars</option>\r\n" + 
						"<option value=\"Boats\">Boats</option>\r\n" + 
						"<option value=\"Planes\">Planes</option>\r\n" + 
						"</select>\r\n" ;
				if (articleToDisplay.filepathToImages.equals("NoImageUploaded")) {
					stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
							"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
							"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
				}
				else {
						stringToSendToWebBrowser+="<div style=\"display: none;\"><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
								"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
					}
			}
			//file reupload code but was buggy
			/*try {
				if(!articleToDisplay.fileName.equals("No File Uploaded")){
					String t1="<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
							"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\""+articleToDisplay.fileName+"\">"+
							"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\" value=\""+articleToDisplay.filepathToImages+"\">      \r\n" ;
					stringToSendToWebBrowser+=t1;
				} 
				else {
					stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
							"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
							"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
						"<input type=\"text\" readonly id= \"customText\" name = \"customText\"  value=\"No File Uploaded\">"+
						"<input type=\"file\" hidden=\"hidden\" accept = \"image/*\" name=\"fileToUpload\" id=\"fileToUpload\">       \r\n" ;
			}
			*/
			stringToSendToWebBrowser+="</div>\r\n";
			try {
				String temp = "			<div class=\"form-group\"> <label>Add a YouTube Link related to your article.</label> <input type=\"link\" autocomplete = \"off\" name = \"youtubeLink\"  value = \""+articleToDisplay.YoutubeLink+"\" class=\"form-control\" placeholder=\"Youtube Link\" ></div>\r\n";
				stringToSendToWebBrowser+=temp;
			} catch (Exception e) {
				// TODO: handle exception
				stringToSendToWebBrowser+="			<div class=\"form-group\"> <label>Add a YouTube Link related to your article.</label> <input type=\"link\" autocomplete = \"off\" name = \"youtubeLink\"  class=\"form-control\" placeholder=\"Youtube Link\" ></div>\r\n" ;
			}
			stringToSendToWebBrowser+=		"</div>\r\n" + 
					"</div>\r\n" + 
					"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\" onclick= \"form1ValidationNext()\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Next</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"second\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 1</div>\r\n" + 
					"        <div class=\"card-body\">\r\n";
					try {
						String tempString="			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading1\" autofocus class=\"form-control\" placeholder=\"Section Heading\" value = \""+SubSectionsForArticle.get(0).sectionHeading+"\"></div>\r\n" + 
								"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody1\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\">"+SubSectionsForArticle.get(0).sectionText+"</textarea></div>\r\n" + 
								" <input type=\"hidden\" id=\"Section1ID\" name=\"Section1ID\" value=\""+SubSectionsForArticle.get(0).UniqueString+"\">";
						tempString+="<div style=\"display: none;\"><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\"No File Uploaded\">"+
								"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\"  accept = \"image/*\">	</div>	\r\n" ;
						stringToSendToWebBrowser+=tempString;
					} catch (Exception e) {
						stringToSendToWebBrowser+="			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading1\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
								"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody1\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" ;					
						stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\"No File Uploaded\">"+
								"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\"  accept = \"image/*\">	</div>	\r\n" ;
					}
					//code for potential reupload but was buggy
					/*
					try {
						if(!SubSectionsForArticle.get(0).fileName.equals("No File Uploaded")) {
							String temp1="<div><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\""+SubSectionsForArticle.get(0).fileName+"\">"+
									"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\"  accept = \"image/*\" value=\""+SubSectionsForArticle.get(0).filepathToImage+"\"></div>		\r\n" ;
							stringToSendToWebBrowser+=temp1;
						}
						else {
							stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\"No File Uploaded\">"+
									"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\"  accept = \"image/*\">	</div>	\r\n" ;
						}
					}
					catch(Exception e){

						// TODO: handle exception
						stringToSendToWebBrowser+="<div><button type=\"button\" id=\"customFileButton1\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText1\" name = \"customText1\" value=\"No File Uploaded\">"+
								"<input type=\"file\" name=\"fileToUpload1\" hidden = \"hidden\" id=\"fileToUpload1\"  accept = \"image/*\">	</div>	\r\n" ;
					}
					*/
					stringToSendToWebBrowser+=								"		</div>\r\n" + 
							"    </div>\r\n" +
							"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection1('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Back</button><button class=\"btn btn-primary\" type=\"button\" contenteditable=\"false\" onclick= \"ValidateSubSection1('next');\" style=\"padding: 5px 10px; margin: 25px;\">Add Another Article Section</button><button class=\"btn btn-primary\" type = \"button\" contenteditable=\"false\" onclick = \"ValidateSubSection1('amend');\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"third\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 2</div>\r\n" + 
					"        <div class=\"card-body\">\r\n" ; 
					try {
						String tempString="			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading2\" autofocus class=\"form-control\" placeholder=\"Section Heading\" value = \""+SubSectionsForArticle.get(1).sectionHeading+"\"></div>\r\n" + 
								"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody2\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\">"+SubSectionsForArticle.get(1).sectionText+"</textarea></div>\r\n" + 
								" <input type=\"hidden\" id=\"Section1ID\" name=\"Section2ID\" value=\""+SubSectionsForArticle.get(1).UniqueString+"\">";
						tempString+="<div style=\"display: none;\"><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\" value=\"No File Uploaded\">"+
								"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" id=\"fileToUpload2\" accept = \"image/*\">	</div>	\r\n" ;
						
						stringToSendToWebBrowser+=tempString;
					} catch (Exception e) {
						stringToSendToWebBrowser+="			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading2\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
								"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody2\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" ;
						stringToSendToWebBrowser+=					"<div><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
								"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\" value=\"No File Uploaded\">"+
								"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" accept = \"image/*\" id=\"fileToUpload2\">	</div>	\r\n" ;
					}
					//code for potential file reupload but was buggy
					/*try {
						if(!SubSectionsForArticle.get(1).fileName.equals("No File Uploaded")){
							String temp2=					"<div><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\"  value=\""+SubSectionsForArticle.get(1).fileName+"\">"+
									"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" id=\"fileToUpload2\" value=\""+SubSectionsForArticle.get(1).filepathToImage+"\">	</div>	\r\n" ;
							stringToSendToWebBrowser+=temp2;
						}
						else {
							stringToSendToWebBrowser+=					"<div><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\" value=\"No File Uploaded\">"+
									"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" id=\"fileToUpload2\">	</div>	\r\n" ;
						}
					} 
					catch (Exception e) {
							// TODO: handle exception
							stringToSendToWebBrowser+=					"<div><button type=\"button\" id=\"customFileButton2\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly id= \"customText2\" name = \"customText2\" value=\"No File Uploaded\">"+
									"<input type=\"file\" hidden=\"hidden\" name=\"fileToUpload2\" id=\"fileToUpload2\">	</div>	\r\n" ;
						}
					*/
					stringToSendToWebBrowser+=								"		</div>\r\n" + 
							"    </div>\r\n" +
							"<button type=\"button\" class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection2('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Back</button><button class=\"btn btn-primary\" type=\"button\" contenteditable=\"false\" onclick= \"ValidateSubSection2('next');\" style=\"padding: 5px 10px; margin: 25px;\">Add Another Article Section</button><button class=\"btn btn-primary\" type=\"button\" contenteditable=\"false\" onclick=\"ValidateSubSection2('amend');\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"<fieldset id = \"fourth\">\r\n" + 
					"<div class=\"card\" >\r\n" + 
					"    <div class=\"card-header\"> Article Section Number 3 </div>\r\n" ;
					
					try {
						String tempString="        <div class=\"card-body\">\r\n"+ 
						"			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading3\" autofocus class=\"form-control\" placeholder=\"Section Heading\" value=\""+SubSectionsForArticle.get(2).sectionHeading+"\"></div>\r\n" + 
						"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody3\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\">"+SubSectionsForArticle.get(2).sectionText+"</textarea></div>\r\n" + 
						" <input type=\"hidden\" id=\"Section1ID\" name=\"Section3ID\" value=\""+SubSectionsForArticle.get(2).UniqueString+"\">";
						tempString+=
								"<div style=\"display: none;\"><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
										"<input type=\"text\" readonly name =\"customText3\" id= \"customText3\" value=\"No File Uploaded\">"+
										"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\">	</div>	\r\n" ;
						
						stringToSendToWebBrowser+=tempString;
					} catch (Exception e) {
						stringToSendToWebBrowser+="        <div class=\"card-body\">\r\n"+ 
						"			<div class=\"form-group\"> <label>Add Heading For This Section</label> <input type=\"text\" autocomplete = \"off\" name = \"SectionHeading3\" autofocus class=\"form-control\" placeholder=\"Section Heading\" ></div>\r\n" + 
						"            <div class=\"form-group\"> <label>Section Text</label> <textarea name=\"SectionBody3\" class=\"form-control h-25 flex-grow-1\" placeholder=\"Add the text for this section off your article here.\" rows = \"5\" cols = \"50\"></textarea></div>\r\n" ;
						stringToSendToWebBrowser+=
								"<div><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
										"<input type=\"text\" readonly name =\"customText3\" id= \"customText3\" value=\"No File Uploaded\">"+
										"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\">	</div>	\r\n" ;
					}
//code for potential file reupload but was a bit bugggy
					/*
					try {
						if(!SubSectionsForArticle.get(2).fileName.equals("No File Uploaded")) {
							String temp3 = "<div><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
									"<input type=\"text\" readonly name = \"customText3\" id= \"customText3\" value=\""+SubSectionsForArticle.get(2).fileName+"\">"+
									"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\" value = \""+SubSectionsForArticle.get(2).filepathToImage+"\">	</div>	\r\n" ;
							stringToSendToWebBrowser+=temp3;
						}
						else {
							stringToSendToWebBrowser+=
									"<div><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
											"<input type=\"text\" readonly name =\"customText3\" id= \"customText3\" value=\"No File Uploaded\">"+
											"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\">	</div>	\r\n" ;
						}
					}
					catch (Exception e) {
						stringToSendToWebBrowser+=
								"<div><button type=\"button\" id=\"customFileButton3\" class=\"btn btn-primary\" contenteditable=\"false\"  style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\">Choose File</button>\r\n" +
										"<input type=\"text\" readonly name =\"customText3\" id= \"customText3\" value=\"No File Uploaded\">"+
										"<input type=\"file\" name=\"fileToUpload3\" hidden=\"hidden\" accept = \"image/*\" id=\"fileToUpload3\">	</div>	\r\n" ;
					}*/
					stringToSendToWebBrowser+=						"		</div>\r\n" + 
							"    </div>\r\n"+
							"<button class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection3('prev');\" style=\"padding: 5px 10px; margin: 25px 25px 25px 0px;\" type=\"button\" >Back</button><button class=\"btn btn-primary\" contenteditable=\"false\" onclick = \"ValidateSubSection3('amend');\" type=\"button\" style=\"padding: 5px 10px; margin: 25px;\">Submit Article</button>\r\n" + 
					"</fieldset>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"    </div>\r\n" + 
					"	</div>\r\n" + 
					"  </form>\r\n" + 
					"<script>\r\n"+
					"const customButton = document.getElementById(\"customFileButton\");\r\n"+
					"const oldFileButton = document.getElementById(\"fileToUpload\");\r\n"+
					"const customText = document.getElementById(\"customText\");\r\n"+
					
					"const customButton1 = document.getElementById(\"customFileButton1\");\r\n"+
					"const oldFileButton1 = document.getElementById(\"fileToUpload1\");\r\n"+
					"const customText1 = document.getElementById(\"customText1\");\r\n"+
					
					"const customButton2 = document.getElementById(\"customFileButton2\");\r\n"+
					"const oldFileButton2 = document.getElementById(\"fileToUpload2\");\r\n"+
					"const customText2 = document.getElementById(\"customText2\");\r\n"+
					
					"const customButton3 = document.getElementById(\"customFileButton3\");\r\n"+
					"const oldFileButton3 = document.getElementById(\"fileToUpload3\");\r\n"+
					"const customText3 = document.getElementById(\"customText3\");\r\n"+
					
					"customButton.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton.click();\r\n"+
					"});\r\n"+
					"oldFileButton.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton.value){ \r\n"+
					"		customText.value = oldFileButton.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton1.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton1.click();\r\n"+
					"});\r\n"+
					"oldFileButton1.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton1.value){ \r\n"+
					"		customText1.value = oldFileButton1.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText1.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton2.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton2.click();\r\n"+
					"});\r\n"+
					"oldFileButton2.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton2.value){ \r\n"+
					"		customText2.value = oldFileButton2.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText2.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					
					"customButton3.addEventListener(\"click\", function(){\r\n"+
					"	oldFileButton3.click();\r\n"+
					"});\r\n"+
					"oldFileButton3.addEventListener(\"change\", function(){\r\n"+
					"	if(oldFileButton3.value){ \r\n"+
					"		customText3.value = oldFileButton3.value.match(\r\n" + 
					"      /[\\/\\\\]([\\w\\d\\s\\.\\-\\(\\)]+)$/\r\n" + 
					"    )[1];\r\n}\r\n"+
					"	else{\r\n"+
					"		customText3.value = \"No File Choosen\";\r\n}\r\n"+
					"});\r\n"+
					"</script>\r\n" +
					"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
					if(toProcess.cookies.get("username") == null || toProcess.cookies.get("username").equals(""))
					{
						stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" +
								"<html>\r\n" + 
								"<body onload=\"noCookies()\">"+
								"</body>\n"+					
								"  <script>\n" +
								"      function noCookies()\n" +					
								"      {\n" +
								"window.location.replace(\"login?NoAccount=true\");\r\n" + 
								"      }\n" +
								"  </script>\n" +	
								"</html>"; 
					}
			toProcess.r = new WebResponse( WebResponse.HTTP_OK,WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;

		}
		else if(toProcess.path.equalsIgnoreCase("addArticleProcess")) {
			//Add Article To The Database
			
			//file upload for article
			File uploaded;
			File oldFile;
			int ind;
			String extension;
			Article article1 = new Article();
			article1.uniqueString="Article_"+System.currentTimeMillis();
			article1.articleTitle=toProcess.params.get("Title");
			article1.articleDescription=toProcess.params.get("ArticleDescription");
			article1.filepathToImages=toProcess.params.get("fileToUpload");
			article1.type = toProcess.params.get("types");
			article1.fileName=toProcess.params.get("customText");
			try {
			article1.username = Account.username;
			}
			catch (Exception e) {
				article1.username= "Anon";
			}
			article1.YoutubeLink=toProcess.params.get("youtubeLink");
			try {
				uploaded = new File(article1.filepathToImages);
				oldFile = uploaded;
				if(!article1.filepathToImages.startsWith("tempMultipartFiles/_")) {
					ind = article1.filepathToImages.lastIndexOf('.');
					extension = article1.filepathToImages.substring(ind);
					uploaded.renameTo(new File("httpdocs/"+article1.uniqueString+extension));
					article1.filepathToImages = article1.uniqueString+extension;
					oldFile.delete();
				}
				else {
					article1.filepathToImages="NoImageUploaded";
					article1.fileName = "No File Uploaded";
					oldFile.delete();
				}
			}
			catch (Exception e) {
				// TODO: handle exception

			}
			MVMap<String, Article> articles = db.s.openMap("Articles");
			articles.put(article1.uniqueString, article1);
			//article subsection commit
    		try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				
			}

			ArticleSubSection articleSubSection;
			try {
				for(int i =1; i<4; i++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						
					}
					articleSubSection=new ArticleSubSection();
					if(!toProcess.params.get("SectionHeading"+i).equals("")){
						articleSubSection.ArticleIDString = article1.uniqueString;
						articleSubSection.UniqueString = "Sub_Section_"+System.currentTimeMillis();
						articleSubSection.sectionHeading=toProcess.params.get("SectionHeading"+i);
						articleSubSection.sectionText=toProcess.params.get("SectionBody"+i);
						articleSubSection.sectionNumber=i;
						articleSubSection.filepathToImage = toProcess.params.get("fileToUpload"+i);
						articleSubSection.fileName = toProcess.params.get("customText"+i);
						uploaded = new File(articleSubSection.filepathToImage);
						oldFile=uploaded;
						//int ind_temp_path = articleSubSection.filepathToImage.lastIndexOf("tempMultipartFiles/");
						try {
							if (!articleSubSection.filepathToImage.startsWith("tempMultipartFiles/_")) {
								ind = articleSubSection.filepathToImage.lastIndexOf('.');
								extension = articleSubSection.filepathToImage.substring(ind);
								uploaded.renameTo(new File("httpdocs/"+articleSubSection.UniqueString+extension));
								articleSubSection.filepathToImage = articleSubSection.UniqueString+extension;
								oldFile.delete();
							}
							else {
								articleSubSection.filepathToImage="NoImageUploaded";
								articleSubSection.fileName="No File Uploaded";
								oldFile.delete();
							}
						} 
						catch (Exception e) {
							// TODO: handle exception
						}
						
					}
					else {
						break;
					}
					//System.out.println("-------------");
					MVMap<String, ArticleSubSection> subsections0= db.s.openMap("SubSection");
					subsections0.put(articleSubSection.UniqueString, articleSubSection);
					db.commit();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			new File(toProcess.params.get("fileToUpload")).delete();
			new File(toProcess.params.get("fileToUpload1")).delete();
			new File(toProcess.params.get("fileToUpload2")).delete();
			new File(toProcess.params.get("fileToUpload3")).delete();
			//db.commit();
			String url = ("SearchResultsPage40226329view?entry="+article1.uniqueString);
			toProcess.r = new WebResponse( WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
									   "<html><body>Redirected: <a href=\"" + url + "\">" +
									   url + "</a></body></html>");
			toProcess.r.addHeader( "Location", url );
			return true;
			/*stringToSendToWebBrowser = "href='SearchResultsPage40226329view?entry="+article1.uniqueString+"'";
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );		
			return true;*/
		}
		else if (toProcess.path.equalsIgnoreCase("amendArticleProcess")) {
			MVMap<String, Article> articles = db.s.openMap("Articles");

			//file upload for article
			File uploaded;
			File oldFile;
			int ind;
			String extension;
			Article article1 = articles.get(toProcess.params.get("ArticleUniqueID"));
			Article ammendedArticle = new Article();
			//not amend article unique string
			ammendedArticle.uniqueString=article1.uniqueString;
			ammendedArticle.articleTitle=toProcess.params.get("Title");
			ammendedArticle.articleDescription=toProcess.params.get("ArticleDescription");
			if(article1.filepathToImages.equals("NoImageUploaded")) {
				ammendedArticle.filepathToImages = toProcess.params.get("fileToUpload");
			}
			else {
				ammendedArticle.filepathToImages=article1.filepathToImages;
			}

			ammendedArticle.type = toProcess.params.get("types");
			ammendedArticle.fileName = toProcess.params.get("customText");
			if(!ammendedArticle.filepathToImages.equals("NoImageUploadeed")) {
				try {
					ammendedArticle.username = Account.username;
				}
				catch (Exception e) {
					ammendedArticle.username="Anon";
				}
				ammendedArticle.YoutubeLink=toProcess.params.get("youtubeLink");

				try {
					uploaded = new File(ammendedArticle.filepathToImages);
					oldFile = uploaded;
					if(!ammendedArticle.filepathToImages.startsWith("tempMultipartFiles/_")) {
						new File(article1.filepathToImages).delete();
						ind = ammendedArticle.filepathToImages.lastIndexOf('.');
						extension = ammendedArticle.filepathToImages.substring(ind);
						uploaded.renameTo(new File("httpdocs/"+ammendedArticle.uniqueString+extension));
						ammendedArticle.filepathToImages = ammendedArticle.uniqueString+extension;
					}
					else {
						ammendedArticle.filepathToImages="NoImageUploaded";
						ammendedArticle.fileName="No File Uploaded";
					}
					oldFile.delete();
					if(!article1.filepathToImages.equals("NoImageUploaded")) {
						new File(article1.filepathToImages).delete();
					}

				}
				catch (Exception e) {
					// TODO: handle exception

				}
			}
			articles.replace(article1.uniqueString, ammendedArticle);
			db.commit();
			//article subsection commit
			MVMap<String, ArticleSubSection> subsections= db.s.openMap("SubSection");
			ArticleSubSection articleSubSection;
			ArticleSubSection ammendedArticleSubSection; 
			try {
				for(int i =1; i<=3; i++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						
					}
					articleSubSection=new ArticleSubSection();
					ammendedArticleSubSection = new ArticleSubSection();
					try {
						String UniqueSubSectionString = toProcess.params.get("Section"+i+"ID");
						articleSubSection = subsections.get(UniqueSubSectionString);
						if(articleSubSection==null) {
							if(!toProcess.params.get("SectionHeading"+i).equals("")){
								articleSubSection = new ArticleSubSection();
								articleSubSection.ArticleIDString = article1.uniqueString;
								articleSubSection.UniqueString = "Sub_Section_"+System.currentTimeMillis();
								articleSubSection.sectionHeading=toProcess.params.get("SectionHeading"+i);
								articleSubSection.sectionText=toProcess.params.get("SectionBody"+i);
								articleSubSection.sectionNumber=i;
								articleSubSection.filepathToImage = toProcess.params.get("fileToUpload"+i);
								
								//ensure that it is actually picture=>done on upload
								uploaded = new File(articleSubSection.filepathToImage);
								oldFile=uploaded;
								//int ind_temp_path = articleSubSection.filepathToImage.lastIndexOf("tempMultipartFiles/");

								if (!articleSubSection.filepathToImage.startsWith("tempMultipartFiles/_")) {
									ind = articleSubSection.filepathToImage.lastIndexOf('.');
									extension = articleSubSection.filepathToImage.substring(ind);
									uploaded.renameTo(new File("httpdocs/"+articleSubSection.UniqueString+extension));
									articleSubSection.filepathToImage = articleSubSection.UniqueString+extension;
									oldFile.delete();
								}

									else {
										articleSubSection.filepathToImage="NoImageUploaded";
										articleSubSection.fileName = "No File Uploaded";
										oldFile.delete();
									}
								MVMap<String, ArticleSubSection> subSections0 = db.s.openMap("SubSection");
									subSections0.put(articleSubSection.UniqueString, articleSubSection);	
									db.commit();
							}
							else {
								break;
							}
						}
						else { 
							if(!toProcess.params.get("SectionHeading"+i).equals("")){
								ammendedArticleSubSection.ArticleIDString = article1.uniqueString;
								ammendedArticleSubSection.sectionHeading=toProcess.params.get("SectionHeading"+i);
								ammendedArticleSubSection.sectionText=toProcess.params.get("SectionBody"+i);
								ammendedArticleSubSection.filepathToImage = articleSubSection.filepathToImage;
								ammendedArticleSubSection.UniqueString =UniqueSubSectionString;
								ammendedArticleSubSection.fileName = articleSubSection.fileName;
								//ensure that it is actually picture=>done on upload
								uploaded = new File(ammendedArticleSubSection.filepathToImage);
								oldFile=uploaded;
								//int ind_temp_path = articleSubSection.filepathToImage.lastIndexOf("tempMultipartFiles/");
								//didnt get file reupload working
								/*
								if (!ammendedArticleSubSection.filepathToImage.startsWith("tempMultipartFiles/_")) {
									ind = ammendedArticleSubSection.filepathToImage.lastIndexOf('.');
									extension = ammendedArticleSubSection.filepathToImage.substring(ind);
									uploaded.renameTo(new File("httpdocs/"+ammendedArticleSubSection.UniqueString+extension));
									ammendedArticleSubSection.filepathToImage = ammendedArticleSubSection.UniqueString+extension;
									oldFile.delete();

								}
								else {
									ammendedArticleSubSection.filepathToImage="NoImageUploaded";
									ammendedArticleSubSection.fileName="No File Uploaded";
									oldFile.delete();
								}
								if(!articleSubSection.filepathToImage.equals("NoImageUploaded")) {
									new File(articleSubSection.filepathToImage).delete();
								}*/
								MVMap<String, ArticleSubSection> subSections0 = db.s.openMap("SubSection");
								subSections0.replace(UniqueSubSectionString, ammendedArticleSubSection);
								db.commit();
							}
							if(toProcess.params.get("SectionHeading"+i).equals("")) {
								MVMap<String, ArticleSubSection> subSections0 = db.s.openMap("SubSection");
								subSections0.remove(UniqueSubSectionString);
								db.commit();
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			new File(toProcess.params.get("fileToUpload")).delete();
			new File(toProcess.params.get("fileToUpload1")).delete();
			new File(toProcess.params.get("fileToUpload2")).delete();
			new File(toProcess.params.get("fileToUpload3")).delete();

			String url = ("SearchResultsPage40226329view?entry="+article1.uniqueString);
			toProcess.r = new WebResponse( WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
									   "<html><body>Redirected: <a href=\"" + url + "\">" +
									   url + "</a></body></html>");
			toProcess.r.addHeader( "Location", url );
			return true;
			/*
			stringToSendToWebBrowser = "<html><body><p>Object added</p><a href=\"output40232871\">Display All Articles</a></body></html>";
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );		
			return true;*/
		}
		
		return false;
	}

	private ArrayList<ArticleSubSection> getSubSectionsForArticle(String articleID, MVMap<String, ArticleSubSection> subSections, List<String> subSectionKeys) {
		// TODO Auto-generated method stub
		ArrayList<ArticleSubSection> tempSubSectionList = new ArrayList<ArticleSubSection>();
		ArticleSubSection articleSubSection1=new ArticleSubSection();
		ArrayList<ArticleSubSection> allArticleSubsections=new ArrayList<ArticleSubSection>();
		for (int i = 0; i < subSectionKeys.size(); i++) {
			articleSubSection1.UniqueString = subSectionKeys.get(i);
			articleSubSection1 = subSections.get(articleSubSection1.UniqueString);
			if(articleSubSection1.ArticleIDString.equals(articleID)) {
				tempSubSectionList.add(articleSubSection1);
			}
			allArticleSubsections.add(articleSubSection1);
		}/*
		Collections.sort(tempSubSectionList, new Comparator<ArticleSubSection>() {
			@Override public int compare(ArticleSubSection a1, ArticleSubSection a2) {
				return a1.sectionNumber - a2.sectionNumber;//sorts list into accending order
			}
		} );*/
		return tempSubSectionList;
	}
}