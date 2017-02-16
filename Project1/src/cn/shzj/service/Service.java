package cn.shzj.service;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

import cn.shzj.dao.Dao;
import cn.shzj.domain.User;

public class Service{
	private Dao dao = new Dao();
	public User getUserInfo(String userlink) throws ParserException {
		User user = dao.getUser(userlink);
		return user;
	}
	public ArrayList<String> getDetailInfo(String str, String name) throws ParserException {
		ArrayList<String> li = dao.getDetailInfo(str,name);		
		return li;		
	}
}
