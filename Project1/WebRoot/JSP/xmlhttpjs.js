function getXmlHttpRequest() {
	var xmlhttp = null;
	xmlhttp = null;
	// 针对所有浏览器的代码：
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}
	// 针对 IE 的代码：
	else if (window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}