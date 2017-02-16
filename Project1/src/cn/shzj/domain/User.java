package cn.shzj.domain;

public class User {
	private String userlink;
	private int followeder;
	private int follower;
	private String username;
	public int getFolloweder() {
		return followeder;
	}
	public void setFolloweder(int followeder) {
		this.followeder = followeder;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserlink() {
		return userlink;
	}
	public void setUserlink(String userlink) {
		this.userlink = userlink;
	}
}
