package cn.han.myjob.utils;

public class AlertInfo {
	public static String getAlert(String requestStatus, String sessionStatus) {
		if (sessionStatus.equals("1e") && requestStatus.equals("1s")) {
			return "第一步操作已经成功";
		} else if (sessionStatus.equals("2e") && requestStatus.equals("2s")) {
			return "第二步操作已经成功";
		} else if (sessionStatus.equals("3e") && requestStatus.equals("3s")) {
			return "第三步操作已经成功";
		} else if (sessionStatus.equals("1e")) {
			return "只完成第一步操作,下一步应该是第二步操作";
		} else if (requestStatus.equals("2e")) {
			return "只完成第二步操作,下一步应该是第三步操作";
		} else if (requestStatus.equals("3e")) {
			return "全部三项操作都已经完成,无需继续导入";
		}
		return "未完成任何操作,请从第一步开始";   //为啥这里还需要一个return
	}
}
