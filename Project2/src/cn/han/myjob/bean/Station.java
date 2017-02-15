package cn.han.myjob.bean;

import java.util.ArrayList;

public class Station {
		private String lineId;
		private ArrayList<String> stations;
		public String getLineId() {
			return lineId;
		}
		public void setLineId(String lineId) {
			this.lineId = lineId;
		}
		public ArrayList<String> getStations() {
			return stations;
		}
		public void setStations(ArrayList<String> stations) {
			this.stations = stations;
		}

}
