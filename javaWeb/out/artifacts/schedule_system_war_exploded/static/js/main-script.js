function checkUsername(usernameInputId, usernameMsgId){
            
    // 1 定义用户名验证正则表达式
    var usernameReg = /^[a-zA-Z0-9]{5,10}$/
    // 2 获取用户名文本输入对象 ——> 用户名
    var usernameInput = document.getElementById(usernameInputId)
    var username = usernameInput.value
    // 3 调用正则表达式的测试换上.test(用户名)
    // 4 验证不通过进行提示
    var usernameMsg = document.getElementById(usernameMsgId)
    if(!usernameReg.test(username)){
        usernameMsg.innerText="用户名由5-10个字母或者数字组成"
        return false    
    } else {
        usernameMsg.innerText="用户名可用"
    }

    return true  // 验证通过
}

function checkUserPwd(userPwdInputId, userPwdMsgId){
    // 1 定义密码验证正则表达式
    var userPwdReg = /^[0-9]{6}$/
    // 2 获取密码文本输入对象 ——> 密码
    var userPwdInput = document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    // 3 调用正则表达式的测试换上.test(密码)
    // 4 验证不通过进行提示
    var userPwdMsg = document.getElementById("userPwdMsg")
    if(!userPwdReg.test(userPwd)){
        userPwdMsg.innerText="密码由6位数字组成"
        return false    
    } else {
        userPwdMsg.innerText="密码可用"
    }

    return true  // 验证通过
}
