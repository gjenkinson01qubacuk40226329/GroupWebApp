package model;
import java.io.Serializable;
import java.util.ArrayList;

public class ArticleSubSection implements Serializable {
	private static final long VerisonUniqueID =1L;//unique id change every time model is updated
	public String UniqueString;//subsection unique id
	public String ArticleIDString;//the unique id form the article
	public String sectionHeading; //title of the section
	public String sectionText;//details about the heading specified
	public int sectionNumber;//number of section
	public String filepathToImage; //path to image if required
	public String fileName;//originalfilename
}
