function checkform(){
    return !!document.getElementById("content").value;
}

function valid(){
    if ((document.form2.password.value == "")||(document.form2.confirm_password.value == "")){
        document.getElementById('info').innerHTML = '请输入值';
        return false;
    } else if (document.form2.password.value != document.form2.confirm_password.value) {
        document.getElementById('info').innerHTML = '密码不相同,请重新输入';
        return false;
    }
    return true;
}
