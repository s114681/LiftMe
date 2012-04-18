package com.liftme;

// Easy Class to set and get the main information about the User
// It will use to get the most important information and use it to create the list of the users
// in our application



public class UserProfile {
	
	private String Name;
	private String Surname;
	private String Email;
	private String Location_ID;
	private String Location_name;
	private String usernam;
	private String passwor;
	
	public UserProfile(){
		
	}
	
	public UserProfile(String name, String surname ){
		
		Name = name;
		Surname = surname;		
		
	}
	
	
	public String getName(){
		return Name;
	}
	
	public void SetName( String name ){
		Name = name;
	}
	
	public String getSurname(){
		return Surname;
	}
	
	public void SetSurname( String surname ){
		Surname = surname;	
	}
	
	public String getEmail(){
		return Email;
	}
	
	public void SetEmail( String email ){
		Email = email;
	}
	
	public String getLocation(){
		return "id: " + Location_ID + "\n" + "name: " + Location_name;
	}
	
	public void SetLocation( String ID, String name ){
		Location_ID = ID;
		Location_name = name; 
	}
	
	public String getUsername(){
		return usernam;
	}
	
	public void SetUsername( String usern ){
		usernam = usern;
	}
	
	public String getPassword(){
		return passwor;
	}
	
	public void SetPassword( String pass ){
		passwor = pass;
	}

}
