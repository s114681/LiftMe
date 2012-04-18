package com.liftme.control;

import java.util.Date;

import rb.dtu.R;
import com.liftme.model.User;
import com.liftme.model.Users;


public class Server {

	public static Users UpdateContacts(){
		/*FRIENDS*/
		Users friends = new Users();
		/*FBID, ICON, NAME, SURNAME, DATA, SCORE, HOME-WORK-CURRENT POS, */
		friends.Insert(new User(null,R.drawable.kermit, "Kermit","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},
				friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.piglet, "Piglet","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},
				friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.dog, "Dog","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.jakob, "Jakob","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.simone, "Simone","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.maciek, "Maciek","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		
		
		/*this is a mockup service, now is hardcoded, but the server should answer with the nearest 15 people*/
		Users users = new Users();
		users.Insert(new User(null,R.drawable.kermit, "Kermit","",new Date(),new int[]{2,-7},
				new float[]{55.584555f,12.524414f},
				new float[]{55.584555f,12.524414f},
				new float[]{55.472627f,12.282715f},
				friends,null,"054523572"));
		users.Insert(new User(null,R.drawable.piglet, "Piglet","",new Date(),new int[]{2,-7},
				new float[]{55.722083f,12.511368f},
				new float[]{55.783524f,12.518921f},
				new float[]{55.783524f,12.518921f},
				friends,null,"054523572"));
		users.Insert(new User(null,R.drawable.dog, "Dog","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},friends,null,"054523572"));
		users.Insert(new User(null,R.drawable.jakob, "Jakob","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		users.Insert(new User(null,R.drawable.simone, "Simone","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		users.Insert(new User(null,R.drawable.maciek, "Maciek","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		
		return users;
	}
	
	public static User getOwnContact(){
		/*FRIENDS*/
		Users friends = new Users();
		friends.Insert(new User(null,R.drawable.kermit, "Kermit","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},
				friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.piglet, "Piglet","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},
				friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.dog, "Dog","",new Date(),new int[]{2,-7},
				new float[]{50f,41f},
				new float[]{50f,41f},
				new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.jakob, "Jakob","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.simone, "Simone","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		friends.Insert(new User(null,R.drawable.maciek, "Maciek","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572"));
		return new User(null,R.drawable.simone, "Simone","",new Date(),new int[]{2,-7},new float[]{50f,41f},new float[]{50f,41f},new float[]{50f,41f},friends,null,"054523572");
	}
}
