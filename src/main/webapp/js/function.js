/**
 * 去除字符串str头尾的空格
 * @param str 字符串
 * @return str去除头尾空格后的字符串。
 */
function trim(str){
    if(str == null) {
    	return "" ;
    }
    // 去除前面所有的空格
    while( str.charAt(0)  == ' ' ){
        str = str.substring(1,str.length);
    }
    // 去除后面的空格
    while( str.charAt(str.length-1)  == ' ' ){
        str = str.substring(0,str.length-1);
    }
    return str ;
}


/**
 * 检查(去头尾空格的)输入是否合法
 * @param str:字符串，regex：正则表达式
 */
function ckinput(str,regex){
	if(!regex.test(trim(str))){
		return false;
	}
		return true;
}