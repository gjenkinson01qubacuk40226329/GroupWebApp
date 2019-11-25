import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.h2.bnf.context.DbColumn;
import org.h2.mvstore.MVMap;

import model.Article;
import model.ArticleSubSection;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import views.AddEditView;
import views.AllResults40226329view;
import views.BugReportingFormView;
import views.CreateAccountView;
import views.SigninView;
import views.BugReporting;
import views.ContactFormView;
import views.ContactUs;
import views.HomeView;
import views.MyAccountPage;
import views.Output40232871;
import views.SearchResultsPage40226329view;
import views.TestForm;
import web.WebInterface;
import web.WebRequest;
import web.WebResponse;

//This program is a webserver designed for teaching purposes
	//the focus is to reduce complexity while demonstrating the key components of a web server suitable for web app development, 
	//hopefully this code will enable you to have a platform that you are comfortable reading and modifying
	//it should also be powerful enough that you can create useful web applications
	//Once you are comfortable with this approach you may want to learn more about how web app development is done at cutting edge companies
	//you will probably want to learn how to use some well supported web frameworks
	//for example, many startup web companies use Django (a Python based web framework) or Nodejs (A javascript based system that has powerful web development libraries)
	//Check out this discussion for some expert opinions: "https://www.quora.com/Which-framework-should-I-learn-Django-or-Node-js-Why"
	//Your final year project is a good opportunity to develop some of these skills
public class Main 
{
	//This is a main function, it is the first function that is called when a java program starts
		//The array of strings named commandLineParameters are extra information that can be
		//passed to a program when it is run using the command line
		//this is just one of many ways of varying a programs behaviour by passing information to it
			//for example some programs have config files, which are usually simple text files with name=value settings on each line
			//programs with databases (like this one) can store their settings in the database
			//some programs even read properties stored by the operating system in system variables
	public static void main(String[] commandLineParameters) throws Exception 
	{		
		//Creates a *DatabaseInterface* object
			//when this object is created it will attempt to access the database file
			//it will fix it if there were any problems (e.g. because the webserver crashed while it was writing data)
			//if the database file does not exist it will create it
			//the database is a store of structured information that the webserver can use to 
			//record knowledge used by an application
			//it is good for many small pieces of data that will change frequently
			//this information can be used to dynamically create HTML pages to display information
			//when users interact with web pages (for example clicking on a link) 
				//the page can send information to the server which can then be used to 
				//change the information stored in the database
		String databaseFilepath = "database.mv";
		DatabaseInterface databaseInterface = new DatabaseInterface(databaseFilepath);
		
		//Creates a *FileStoreInterface* object
			//this object manages access to large files that the webserver will provide to a webbrowser
			//such files might include, images, videos or HTML pages that rarely change
			//if a folder for storing these files does not exist it will be created when this object is constructed
		String fileStoreFilepath = "httpdocs";
		FileStoreInterface fileStoreInterface = new FileStoreInterface(fileStoreFilepath);
		
		
		//Creates a *WebInterface* object
			//when this object is created it will attempt to connect to a network port on the computer
			//and will wait for requests to be made to that port
			//if the connection is successful the URL of the webserver will be printed out to the console
			//when this program is running you can connect to this webserver by typing the URL into a webbrowser on your machine
			//depending on the settings of your firewall you may be able to connect to the webserver from 
			//any other machine connected to the same network 
			//(e.g. other computers in the Lab or on your home network if you share the same wifi connection)
		//Port 80 is the default port for serving webpages
			//when you type "http://127.0.0.1/" as a URL into a webbrowser it will try to access port 80 on your local machine
			//sometimes this port is used by other applications
			//to deal with this you can run your webserver on a different port e.g. 8080
			//then you can access the webserver using "http://127.0.0.1:8080/"
		int port = 8080;
		WebInterface webInterface = new WebInterface(port);
		
		//This method attaches a shutdown function to the running java system
			//on most platforms the attached function will be called as the last piece of code to run before the application terminates
			//however it is not safe to rely on this as it is not guaranteed to be called, for example if the computer power fails
			//it obviously won't run as, without power, it will stop immediately
		registerShutdownHook(databaseInterface);
		
		//An example dynamic page
//		ExampleView exampleDynamicPage = new ExampleView(databaseInterface,fileStoreInterface);
		//An example dynamic page that responds to a form in html
		
		TestForm testFormPage = new TestForm(databaseInterface,fileStoreInterface);
		//add sample data to the data base
		{
			
			MVMap<String, Article> article0 = databaseInterface.s.openMap("Articles");
        	//MVMap<String, ArticleSubSection> subSections0 = databaseInterface.s.openMap("SubSection");
        	if(article0.size()==0) {
        		Article Article = new Article();
        		Article.articleTitle = "VW Beetle Original Type 1";
        		Article.articleDescription = "The Volkswagen Beetle - officially the Volkswagen Type 1, is a two-door, rear-engine economy car, intended for five occupants (later, beetles were only allowed for four people in some countries), that was manufactured and marketed by German automaker Volkswagen (VW) from 1938 until 2003 before being redesigned and relaunched. ";
        		Article.uniqueString = "Article_"+System.currentTimeMillis();
        		Article.type="Cars";
        		Article.username="tester123";
        		Article.fileName="BeetleAarticlePicture.jpg";
        		Article.filepathToImages="BeetleArticlePic.jpg";
        		Article.YoutubeLink="https://www.youtube.com/watch?v=c7XBh6uuiD4";
        		MVMap<String, Article> articles = databaseInterface.s.openMap("Articles");
        		articles.put(Article.uniqueString, Article);
        		
        		ArticleSubSection sub = new ArticleSubSection();
        		sub.sectionHeading = "History of Production";
        		sub.sectionText = "The idea of the Beetle had been conceived in 1931, when Ferdinand Porsche and Zundapp developed the Porsche Type 12, or \"Auto fur Jedermann\" (translated as \"Car for Everyone\"). Porsche had already developed the flat 4 cylinder air cooled motor, and Zundapp was developing a Radial 5 cylinder water-cooled power plant. Porsche had chosen to use a \"swing-axle\" type rear suspension, previously invented by Edward Rumpler.\n" + 
        				"\n" + 
        				"By 1932, three prototypes were running, and a fourth, the Porsche Type 32, built by the NSU motorcycle factory, joined the line-up in 1933.\n" + 
        				"\n" + 
        				"In 1933 Adolf Hitler commissioned Porsche to develop a \"Peoples Car\" (literally a Volks Wagen), the car was to be able to seat 2 adults and 2 children, with room for their luggage, and be able to cruise at 100km/h (62mph). The term \"Volks\" had been applied to various Nazi party sponsored consumer products such as the Volksradio.\n" + 
        				"\n" + 
        				"The Volkswagen name however wasn\'t to become official until a few years down the line. The new car was initially tagged the Porsche Type 60, then christened the KdF-Wagen, KdF being the initials of the leisure arm of the Third Reich (Kraft durch Freude or Strength through Joy).\n" + 
        				"\n" + 
        				"October 1935 saw the first Type 60 prototype, known as the \"V1\" ready. By 1936, testing of the first three \"V3\" prototypes, built in Porsche's Stuttgart shop, began. By 1937 Thirty \"W30\" pre-production models, manufactured by Daimler-Benz, had undergone 1,800,000 mi (2,900,000 km) of further testing. All cars already had the distinctive round shape and the air-cooled, rear-mounted engine.\n" + 
        				"\n" + 
        				"Hitler's plan had been that the new car would be affordable to everyone, and introduced a form of part payment for them, not dissimilar to that of the \"Green-Shield\" system. Consumers would purchase a \"Sparkarte\" (a savings card) at a cost of 1 Reichsmark, the equivalent of about 30p, or around 40 US cents. Following the purchase of the Sparkarte, they were obliged to buy at least 5 Reichsmark of stamps per week, this worked out at roughly $2 USD. The average Germans salary at the time was around 32 Reichsmark a week. The sum total payable for the basic car was 990 Reichsmark.\n" + 
        				"\n" + 
        				"In 1938, the Stadt des KdF-Wagens had been built around the village of Hesslingen, as a town to house workers at the KdF-Wagen factory.\n" + 
        				"\n" + 
        				"By the outbreak of World War 2 in 1939, only a handful of consumer cars had been produced, and all customer orders had been cancelled as production was switched from civilian vehicles to that of military vehicles.\n" + 
        				"\n" + 
        				"The main two vehicles produced during the war period were both Beetle based variants, the flat 4 air cooled engine and rugged suspension being ideal for the harsh desert environments of Africa.";
        		sub.sectionNumber = 1;
        		sub.ArticleIDString = Article.uniqueString;
        		sub.UniqueString = "SubSection_"+System.currentTimeMillis();
        		sub.filepathToImage="BeetleProtype.jpg";
        		sub.fileName="PrototypeBeetle.jpg";
        		MVMap<String, ArticleSubSection> subSections = databaseInterface.s.openMap("SubSection");
        		subSections.put(sub.UniqueString, sub);
        		
        		Thread.sleep(1);
        		
        		sub = new ArticleSubSection();
        		sub.sectionHeading = "Production";
        		sub.sectionText = "After initially building mostly Beetles for the British military, in 1947 production transitioned to purely civilian Beetles, for the first time featuring chromed bumpers, hubcaps, and body and running board trim. Aside from some remaining military production, civilian output reached almost 9,000 units in 1947, and for 1948 total production increased to 19,244 cars. The late 1940s Beetles still had an understressed 1131 cc engine with just 25 horsepower, but it could effortlessly maintain cruising at the cars 60 mph top speed. Following the British Army-led restart of production and Hirst s establishment of sales network and exports to Netherlands, former Opel manager (and formerly a detractor of the Volkswagen) Heinz Nordhoff was appointed director of the Volkswagen factory in 1949. Under Nordhoff, production increased dramatically over the following decade, with the one-millionth car coming off the assembly line by 1955. During this post-war period, the Beetle had superior performance in its category with a top speed of 115 km/h (71 mph) and 0 to 100 km/h (0 to 62 mph) in 27.5 seconds with fuel consumption of 6.7 per 100 km (36 mpg) for the standard 25 kW (34 hp) engine. This was far superior to the Citroen 2CV, which was aimed at a low speed/poor road rural peasant market, and Morris Minor, designed for a market with no motorways or freeways; it was even competitive with more advanced small city cars like the Austin Mini. On 17 February 1972 Beetle No. 15,007,034 was produced, surpassing total production of the previous record holder, the Ford Model T. By 1973, total production was over 16 million, and by 23 June 1992, over 21 million had been produced. ";
        		sub.sectionNumber = 2;
        		sub.ArticleIDString = Article.uniqueString;
        		sub.UniqueString = "SubSection_"+System.currentTimeMillis();
        		sub.filepathToImage="BeetlePostWar.jpg";
        		sub.fileName="BeetlePostWar.jpg";
        		subSections.put(sub.UniqueString, sub);
        		
        		
        		Thread.sleep(1);
        		sub = new ArticleSubSection();
        		sub.sectionHeading = "Slowing of Type 1 production";
        		sub.sectionText = "Though extremely successful in the 1960s, experiencing its greatest sales growth in North America between 1960 and 1965, the Beetle was increasingly faced with stiff competition from more modern designs globally. The Japanese had refined rear-wheel-drive, water-cooled, front-engine, small cars including the Datsun 510 and Toyota Corona, whose sales in the North American market grew rapidly at the expense of Volkswagen in the late 1960s. Honda introduced the N600, based on the space-efficient transverse-engine, front-wheel-drive layout of the original Austin Mini, to the North American market in late 1969, and upgraded the model to the Honda Civic in 1972. The Japanese \"big three\" would soon dominate compact auto sales in North America. In 1971 Ford introduced its Pinto, which had some market impact as a low cost alternative in the wake of the drop of the US Dollar against the Deutschmark that same year. As the 1960s came to a close, Volkswagen faced increasingly stiff competition from European cars as well. The Beetle was faced with competition from new designs like the Fiat 127 and Renault 5, and more robust designs based on the Austin Mini layout such as the Superminis. German competitors, Ford and Opel also enjoyed strong sales of modern smaller cars like the Ford Escort and Opel Kadett. Volkswagen attempted to boost the power of their air-cooled motor to meet the demands of higher highway speeds in the late 1960s, then comply with new pollution control regulations, caused problems for reliability and fuel efficiency that impaired the reputation of the aging design. Safety issues with the Beetle came under increasing scrutiny, culminating in the 1972 release of a rather scathing report. During the early 1970s, sales of the Beetle in Europe and North America plummeted. \n" + 
        				"VW introduced other models to supplement the Beetle throughout the 1960s; the Type 3, Type 4, and the NSU-based and larger K70. None of these models, aimed at more upscale markets, achieved the level of success of the Beetle. The over-reliance on a single model, now in decline, meant that Volkswagen was in financial crisis by 1974. It needed German government funding to produce the Beetle's replacement. \n" + 
        				"Production lines at Wolfsburg switched to the new water-cooled, front-engined, front-wheel-drive Golf designed by Giorgetto Giugiaro in 1974, sold in North America at the time as the \"Rabbit\". The Golf eventually became Volkswagen's most successful model since the Beetle. It was periodically redesigned over its lifetime, with only a few components carried over between generations, entering its seventh generation in 2012; the Beetle had only minor refinements of its original design. \n" + 
        				"The Golf did not kill Beetle production, nor did the smaller Polo which was launched a year later. Production of the Beetle continued in smaller numbers at other German factories until 19 January 1978, when mainstream production shifted to Brazil and Mexico: markets where low operating cost was an important factor. After this shift in production, sales in Europe did not stop, but became very low. Beetle sedans were produced for U.S. markets until July 1977 and for European markets until 1985, with other companies continuing to import cars produced in Mexico after 1985. The Beetle convertible/Cabriolet ended production (as 1979 models) on January 31, 1980. \n" + 
        				"The last Beetle was produced in Puebla, Mexico, in July 2003. The final batch of 3,000 Beetles were sold as 2004 models and badged as the Ultima Edicion, with whitewall tires, a host of previously discontinued chrome trim, and the choice of two special paint colors taken from the New Beetle. Production in Brazil ended in 1986, then started again in 1993 and continued until 1996. \n" + 
        				"The Beetle outlasted most other cars which had adopted the rear-engine, air-cooled layout such as those by Subaru, Fiat, and General Motors. Porsche's 356 series which originally used some Volkswagen-sourced parts, continued to use the classic rear-engine layout (which later became water cooled) in the Porsche 911 996 series, which remains competitive in the second decade of the 21st century. \n" + 
        				"By 2002, over 21 million Type 1s had been produced, but by 2003, annual production had dropped to 30,000 from a peak of 1.3 million in 1971. VW announced the end of production in June 2003, citing decreasing demand, and the final original Type 1 VW Beetle (No. 21,529,464) rolled off the production line at Puebla, Mexico, on 30 July 2003, 65 years after its original launch. This last Beetle, nicknamed El Rey (Spanish for \"The King\" after a legendary Mexican song by Jose Alfredo Jimenez) was delivered to the company\'s museum in Wolfsburg, Germany.\n" + 
        				"";
        		sub.sectionNumber = 3;
        		sub.ArticleIDString = Article.uniqueString;
        		sub.UniqueString = "SubSection_"+System.currentTimeMillis();
        		sub.filepathToImage="Final%20Type%201%20beetle.jpg";
        		sub.fileName="FinalType!.jpg";
        		subSections.put(sub.UniqueString, sub);
        		databaseInterface.commit();
        		        		
        		Thread.sleep(1);

        		Article = new Article();
        		Article.uniqueString = "Article_"+System.currentTimeMillis();
        		Article.articleTitle = "Default Boat";
        		Article.articleDescription = "Boats are used to allower users to move across expanses of water, usually without getting wet! Find out how its made.";
        		Article.type="Boats";
        		Article.filepathToImages="Article_1553120491976.jpg";
        		Article.fileName="boat image";
        		MVMap<String, Article> articles1 = databaseInterface.s.openMap("Articles");
        		articles1.put(Article.uniqueString, Article);
        		databaseInterface.commit();
        		sub = new ArticleSubSection();
        		databaseInterface.commit();
        		sub.sectionHeading = "Default Boat";
        		sub.sectionText = "This an engine for a small boat it has only 5 horse power and could easily move a small craft at 3+knots.";
        		sub.sectionNumber = 1;
        		sub.ArticleIDString = Article.uniqueString;
        		sub.UniqueString = "SubSection_"+System.currentTimeMillis();
        		sub.filepathToImage="Sub_Section_1553120491976.jpg";
        		sub.fileName="small boat engine";
        		MVMap<String, ArticleSubSection> subSections2 = databaseInterface.s.openMap("SubSection");
        		subSections2.put(sub.UniqueString, sub);
        		databaseInterface.commit();

        		Thread.sleep(1);

        		Article = new Article();
        		Article.uniqueString = "Article_"+System.currentTimeMillis();
        		Article.articleTitle="Default Plane";
        		Article.articleDescription = "Planes are used by man to travel all over the world in style and comfort. Find out how it's made.";
        		Article.type = "Planes";
        		Article.filepathToImages="Article_1553120652420.jpg";
        		Article.fileName="plane in sky";
        		MVMap<String, Article> articles2 = databaseInterface.s.openMap("Articles");
        		articles2.put(Article.uniqueString, Article);
        		databaseInterface.commit();
        		sub = new ArticleSubSection();
        		sub.sectionHeading = "Planes";
        		sub.sectionText = "This is a plane it has been made using an aluminum as it is light and strong.";
        		sub.sectionNumber = 1;
        		sub.ArticleIDString = Article.uniqueString;
        		sub.UniqueString = "SubSection_"+System.currentTimeMillis();
        		sub.filepathToImage="Sub_Section_1553120652420.jpg";
        		sub.fileName="plane engine";
        		MVMap<String, ArticleSubSection> subSections3 = databaseInterface.s.openMap("SubSection");
        		subSections3.put(sub.UniqueString, sub);
        		databaseInterface.commit();
        	}
			

		}
		SearchResultsPage40226329view SearchResultsPage40226329 = new SearchResultsPage40226329view(databaseInterface, fileStoreInterface);
		AddEditView addEditView = new AddEditView(databaseInterface, fileStoreInterface);
		Output40232871 output40232871 = new Output40232871(databaseInterface, fileStoreInterface);
		SigninView signInViewPage = new SigninView(databaseInterface, fileStoreInterface);
		BugReportingFormView bugview = new BugReportingFormView(databaseInterface, fileStoreInterface);
		AllResults40226329view AllResults40226329 = new AllResults40226329view(databaseInterface, fileStoreInterface);

		CreateAccountView createAccountView = new CreateAccountView(databaseInterface, fileStoreInterface);

		HomeView homeview = new HomeView(databaseInterface,fileStoreInterface);
		ContactUs contactUs = new ContactUs(databaseInterface, fileStoreInterface);
		ContactFormView ContactFormView = new ContactFormView(databaseInterface, fileStoreInterface);
		BugReporting BugReporting = new BugReporting(databaseInterface, fileStoreInterface);
		MyAccountPage myAccountPage = new MyAccountPage(databaseInterface, fileStoreInterface);

		
		//this variable indicates that the program should keep running
			//by setting this variable to false the program will exit
		boolean shouldKeepRunning = true;
		
		ArrayList<WebRequest> requestToProcess = new ArrayList<WebRequest>();
		
		//the program goes into an "update" loop repeatedly checking requests from web browsers, 
			//this loop consumes a small amount of resources, 
			//it is based on a model where a program may need to perform regular real time updates
			//such as reading from sensors or updating a simulation
			//this is also a common design for applications with responsive user interfaces
		while(shouldKeepRunning)
		{
			//check the time since the last update of the ip
			//potentially update the otidae redirection
			
			//This application forces a strict flow of control over requests to the webserver from webbrowsers
				//One request is handled at a time to prevent there being issues with two commands modifying the 
				//database at the same time
				//this approach makes it much easier to understand how a web application will run
				//however if the code for responding to a webbrowser request takes a long time it will delay all other
				//web requests and it may seem as if the server has crashed
				//in fact if the code for a page does get into an infinite loop or has a serious error it will crash the server
				//to make this approach work requires that all pages be tested to a high standard 
			webInterface.getQueue(requestToProcess);
			
			for (int i = 0; i < requestToProcess.size(); i++) 
			{
		        WebRequest toProcess = requestToProcess.remove(i);

		        System.out.println("A web browser has requested the following address: http://"+toProcess.path);

		        //examines each request in turn
					//a set of if statements which identify how a web request should be handled 
					//the URL and parameters determine how to change the database or file store
					//they also determine what data should be sent back to the web browser
		        
		        //If no path was specified then set the path to index.html
		        //this will mean that a static index.html page will be loaded as default
		        //the example dynamic page will process the request if the index.html file is not present
	        	if(toProcess.path.length()==0)
	        		toProcess.path = "HomeView";	

	        	//Uncomment this code to see an example dynamic page or an example dynamic page that responds to a form
//		        if(exampleDynamicPage.process(toProcess))
//		        {
//		        	//example page is processed
//		        }
//		        else

		        if(testFormPage.process(toProcess))
		        {
		        	//testForm page is processed
		        }
		        else if(SearchResultsPage40226329.process(toProcess)) {
		        	
		        }
		        
                else if(homeview.process(toProcess)) {
		        	
		        }
                else if(ContactFormView.process(toProcess)) {
                	
                }
		        else if(BugReporting.process(toProcess)) {
                	
		       }
		       else if(bugview.process(toProcess)) {
                	
			    }

		        else if(addEditView.process(toProcess)) {
		        }
		        else if (createAccountView.process(toProcess))
		        {
		        	
		        }
		        else if(signInViewPage.process(toProcess)) 
		        {
		        	//Sign in view page is being processed
		        }
		        else if(myAccountPage.process(toProcess))
		        {
		        	
		        }
		        else if (output40232871.process(toProcess)) {
					
				}
		        else if (contactUs.process(toProcess))
		        {
		        	
		        }
		        
		        else if (AllResults40226329.process(toProcess)) {
		        	
		        }
		        
		        else
		        {
			        String asFilepath = fileStoreInterface.decodeFilePath(toProcess.path);
			        if((asFilepath!=null)&&fileStoreInterface.exists(asFilepath))
			        {
			        	toProcess.r = WebResponse.serveFile(toProcess.params, asFilepath);
			        }
		        }
		        
		        //If none of the previous rules identified a response then either the 
		        //website is broken pointing to a url that no longer exists
		        //or someone is deliberately trying to access the site in a way that wasn't designed for
		        //either way respond with a redirection message to the index page
		        if(toProcess.r == null)
		        {
		        	if(toProcess.path.equalsIgnoreCase("index.html"))
		        	{
			        	toProcess.r = new WebResponse( WebResponse.HTTP_NOTFOUND, WebResponse.MIME_PLAINTEXT,
								 "Error 404, file not found." );
		        	}
		        	
		        	
		        	else
		        	{
			        	String url = "/";
						toProcess.r = new WebResponse( WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
												   "<html><body>Redirected: <a href=\"" + url + "\">" +
												   url + "</a></body></html>");
						toProcess.r.addHeader( "Location", url );
		        	}
		        }
		        
		        //create a new thread that will send the response to the webbrowser
				Thread t = new Thread( toProcess );
				//daemon means that even if the program is closed this thread will continue until
				//it is finished
				t.setDaemon( true );
				//this starts the thread running
				t.start();		        
			}			
			
			//This command pauses the update loop for 10 milliseconds
				//the goal is to have the total time to calculate each loop be under 16.7 milliseconds
				//if this performance goal is reached then the server will be able to respond to
				//changes faster than the eye can detect changes 
				//if a graphical user interface were added to this application 
				//it would appear to be responding instantaneously
			Thread.sleep(10);
		}
		
		//Cleanly shuts down the application
			//it disconnects from the database and the file store, ensuring that information is saved
			//to disk as required
	}
	
	//NOTE
	//It is useful to have a simple main class for your application that can be easily modified
		//This ensures that you can quickly produce demos or add one-off functionality
		//some libraries take control of the main function and require that you
		//supply functions that modify parts of how they work, 
		//these are typically referred to as "opinionated" libraries
		//"opinionated" libraries try to make development simple and fast by hiding details from the user
		//they also restrict how you can modify them to reduce errors and improve performance
		//the disadvantage of using "opinionated" libraries is that you typically become 
		//locked-in to the libraries. By using them you develop skills with using a library 
		//but not necessarily with core programming skills. To truly master programming you want
		//to be able to get a computer to do anything you want and to understand any part of how it
		//works. To develop this skill it is a good idea to get practice working with "unopinionated" libraries
		//and to understand the software you use to such an extent you are happy to modify any part of it

    public static void registerShutdownHook( final DatabaseInterface s )
    {
        // Registers a shutdown hook for the database so that it
        // shuts down nicely when the Virtual Machine exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                s.close();
            }
        } );
    }

}