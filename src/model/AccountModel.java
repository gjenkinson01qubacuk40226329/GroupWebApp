package model;
import java.io.Serializable;

public class AccountModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public String email;
	public String addressLine1;
	public String addressLine2;
	public String postCode;
	public String pathToProfilePicture = "DefaultProfilePictures.png";
}