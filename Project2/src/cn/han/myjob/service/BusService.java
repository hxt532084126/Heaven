package cn.han.myjob.service;

import cn.han.myjob.dao.BusDao;

public class BusService {
	public BusDao dao = new BusDao();
	//执行第1步,擦除city库并创建新citylinks表
	public String firstStep() {
		return dao.firstStep();
	}
	public String secondStep() {
		return dao.secondStep();
	}
	public String thirdStep() {
		return dao.thirdStep();
	}
}
