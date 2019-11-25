package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Article implements Serializable {
	private static final long VerisonUniqueID =3L;//unique id change every time model is updated
	
	public String uniqueString;
	public String filepathToImages; //path to image used at top of article
	public String fileName;//originalnameoffile
	public String articleTitle; //title of the article
	public String articleDescription;//short description of what the article is about
	public String type;//type ie car, plane, boat, object
	public String username;//for the last user to make an edit or the user who added the article
	public String YoutubeLink;
	public String GoogleMaps;
}
