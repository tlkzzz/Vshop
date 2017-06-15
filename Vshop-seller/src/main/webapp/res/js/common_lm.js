/**
 * 验证是否为数字
 */
var validateNum = function(value) {
	var reg = new RegExp("^[0-9]*$");
	if (!reg.test(value)) {
		return false;
	}
	return true;
}

/**
 * 验证是否为正整数
 * @returns {Boolean}
 */
var validatePositiveNum = function(value){
	var reg = new RegExp("^\+?[1-9][0-9]*$ ");
	if (!reg.test(value)) {
		return false;
	}
	return true;
}