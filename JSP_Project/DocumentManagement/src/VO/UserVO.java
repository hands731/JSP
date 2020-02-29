package VO;

import java.util.List;

public class UserVO {
private String userid;
private String name;
private String grade;
private String _class;
private String position;
private List<String> groupList;

public UserVO() {
	
}

public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}

public List<String> getGroupList() {
	return groupList;
}

public void setGroupList(List<String> groupList) {
	this.groupList = groupList;
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String get_class() {
	return _class;
}
public void set_class(String _class) {
	this._class = _class;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
}