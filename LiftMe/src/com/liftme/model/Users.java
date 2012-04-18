package com.liftme.model;

public class Users {

	private User[] _array;
	private int _size;
	private static final int DEFAULTCAPACITY = 15;
	private static User[] _emptyArray = new User[0];
	
	public Users() {
		super();
		this._array = _emptyArray;
		_size = 0;
	}
	
	public User[] getArray() {
		User[] array = new User[_size];
		for (int i=0;i<_size;i++) {
			array[i] = _array[i];
		}
		return array;
	}

	public int getSize() {
		return _size;
	}

	public void Insert(User item){
		if (_size == _array.length){
			User[] destinationArray = new User[(_array.length == 0 ? DEFAULTCAPACITY : (2*_array.length))];
			CopyArray(_array,destinationArray);
			_array = destinationArray;
		}
		_array[_size++] = item;
	}
	
	public void Clear(){
		_array = _emptyArray;
		_size = 0;
	} 
	
	public User getUserByName(String name){
		for (User contact : _array) {
			if(contact.getName().equalsIgnoreCase(name))
				return contact;
		}
		return null;
	}
	
	public User getUserByPosition(int pos){
		return _array[pos];
	}
	
	//UTIL
	public void CopyArray(User[] arraySource, User[] arrayDest){
		int count = 0;
		for (User contact : arraySource) {
			arrayDest[count] = contact;
			count++;
		}
	}
}
