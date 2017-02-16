package cn.shzj.dao;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

import cn.shzj.domain.User;
import cn.shzj.utils.User_Activities;
import cn.shzj.utils.User_Answer;
import cn.shzj.utils.User_Followers;
import cn.shzj.utils.User_Qestions;
import cn.shzj.utils.User_Topics;
import cn.shzj.utils.User_Username;

public class Dao {

	public User getUser(String userlink) throws ParserException {
		// 获取用户名
		String username = User_Username.getUserName(userlink);
		// 获取关注了多少人
		// 获取被关注人数
		int follower = Integer.parseInt(User_Followers.getPeopleNum(userlink)
				.get(0));
		int followeder = Integer.parseInt(User_Followers.getPeopleNum(userlink)
				.get(1));
		// 封装数据
		User user = new User();
		user.setUsername(username);
		user.setFollower(follower);
		user.setFolloweder(followeder);
		user.setUserlink(userlink);
		// 返回封装好的user对象
		return user;
	}

	// 获取关注的话题,回答,提问和动态,并返回
	public ArrayList<String> getDetailInfo(String str, String name)
			throws ParserException {
		ArrayList<String> li = null;
		if (str.equals("topics")) {
			li = User_Topics.getTopic(name);
		} else if (str.equals("answers")) {
			li = User_Answer.getAnswers(name);
		} else if (str.equals("questions")) {
			li = User_Qestions.getQestions(name);
		} else if (str.equals("activities")) {
			li = User_Activities.getActivities(name);
		}
		return li;
	}

}
