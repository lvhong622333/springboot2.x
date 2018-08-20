function validate() {
	var submitFlag = true;
	// 验证用户名长度
	if (validateLength($("#userName").val(), 8, 12)) {
		return false;
	}
	// 验证密码和确认密码是否一致
	if (validatePassword($("#password").val(), $("#confirmPassword").val())) {
		return false;
	}
	// 验证输入手机号码格式
	if (validateTelePhone($("#telePhone").val())) {
		return false;
	}
	return submitFlag;
}
function validateLength(userName, min, max) {
	if (userName.length < min || userName.length > max) {
		$("#userNameError").text("输入字符长度不满足条件");
		$("#userNameError").css({
			"color" : "red"
		});
		return true;
	}
	$("#userNameError").text("");
	return false;
}
function validatePassword(password, confirmPassword) {
	if (password === confirmPassword) {
		$("#confirmPasswordError").text("");
		return false;
	} else {
		$("#confirmPasswordError").text("两次输入密码不一致");
		$("#confirmPasswordError").css({
			"color" : "red"
		});
		return true;
	}
}
function validateTelePhone(telephone) {
	if (telephone.length != 11) {
		$("#telePhoneError").text("号码长度不满足要求！");
		$("#telePhoneError").css({
			"color" : "red"
		});
		return true;
	}
	var reg = /(1[3-9]\d{9}$)/;
	if (!reg.test(telephone)) {
		$("#telePhoneError").text("号码格式不正确！");
		$("#telePhoneError").css({
			"color" : "red"
		});
		return true;
	}
	$("#telePhoneError").text("");
}
