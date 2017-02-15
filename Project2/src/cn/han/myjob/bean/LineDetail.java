package cn.han.myjob.bean;

public class LineDetail {
	private String lineId;
	private String lineName;
	private String operatingTime;
	private String intervalTime;
	private String ticketPrice;
	private String company;
	private String updateTime;
	private String lineLink;

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLineLink() {
		return lineLink;
	}

	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}

	// 重写hashCode()方法
	public int hashCode() {
		return this.getLineName().hashCode();
	}

	// 重写equals()方法
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LineDetail)) {
			return false;
		}
		if (((LineDetail) obj).getLineLink() == this.getLineLink()) {
			return true;
		}
		return false;
	}

}
