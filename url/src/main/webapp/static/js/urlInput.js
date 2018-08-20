function validateUrlInfo() {
		if (valicateUrlName() && valicateUrlDesc() && validateUrl()) {
			return true;
		}
		return false;
	}

	function validateUrl() {
		var url = $("#urlX").val();
		if (url.length > 200) {
			$("#urlError").text("输入字符长度超过规定长度！");
			return false;
		}
		var reg = /^((https?|http|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
		if (!reg.test(url)) {
			$("#urlError").text("输入网址格式不正确！");
			return false;
		}
		$("#urlError").text("");
		return true;
	}

	function valicateUrlName() {
		var urlName = $("#urlName").val();
		if (urlName.length > 20) {
			$("#urlNameError").text("输入字符长度超过规定长度！");
			return false;
		}
		$("#urlNameError").text("");
		return true;
	}

	function valicateUrlDesc() {
		var urlDesc = $("#urlDesc").val();
		if (urlDesc.length > 200) {
			$("#urlDescError").text("输入字符长度超过规定长度！");
			return false;
		}
		$("#urlDescError").text("");
		return true;
	}