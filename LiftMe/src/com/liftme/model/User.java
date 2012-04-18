package com.liftme.model;

import java.util.Date;

public class User {
	
	private String _fbId;
	private int _icon;
	private String _firstName;
	private String _lastName;
	private Date _timeStamp;
	private int[] _scores;
	private float[] _homePosition; // do the retrive of that data!
	private float[] _workPosition; // do the retrive of that data!
	private float[] _currentPosition;
	private Users _fbFriends = new Users();
	private Users _starredFriends = new Users();
	
	private String _phoneNumber;
	
	public User(String _fbId, int _icon, String _firstName,
			String _lastName, Date _timeStamp, int[] _scores,
			float[] _homePosition, float[] _workPosition,
			float[] _currentPosition, Users _fbFriends,
			Users _starredFriends, String _phoneNumber) {
		super();
		this._fbId = _fbId;
		this._icon = _icon;
		this._firstName = _firstName;
		this._lastName = _lastName;
		this._timeStamp = _timeStamp;
		this._scores = _scores;
		this._homePosition = _homePosition;
		this._workPosition = _workPosition;
		this._currentPosition = _currentPosition;
		this._fbFriends = _fbFriends;
		this._starredFriends = _starredFriends;
		this._phoneNumber = _phoneNumber;
	}

	@Deprecated
	public User(int _icon, String _name, String _timeStamp,
			String _currentPosition, int[] _points, float _lat, float _lon,
			String _phoneNumber, Users _friends) {
		super();
		this._icon = _icon;
		this._firstName = _name;
		this._scores = _points;
		this._currentPosition[0] = _lat;
		this._currentPosition[1] = _lon;
		this._phoneNumber = _phoneNumber;
		this._fbFriends = _friends;
	}

	public String getFbId() {
		return _fbId;
	}

	public String getLastName() {
		return _lastName;
	}

	public float getHomePositionLatitude() {
		return _homePosition[0];
	}
	
	public float getHomePositionLongitude() {
		return _homePosition[1];
	}

	public float getWorkePositionLatitude() {
		return _workPosition[0];
	}
	
	public float getWorkPositionLongitude() {
		return _workPosition[1];
	}

	public Users getStarredFriends() {
		return _starredFriends;
	}

	public int getIcon() {
		return _icon;
	}

	public String getName() {
		return _firstName;
	}

	public Date getTimeStamp() {
		return _timeStamp;
	}

	public float getCurrentPositionLatitude() {
		return _currentPosition[0];
	}
	
	public float getCurrentPositionLongitude() {
		return _currentPosition[1];
	}

	public int[] getPoints() {
		return _scores;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public Users getFriends() {
		return _fbFriends;
	}
}
