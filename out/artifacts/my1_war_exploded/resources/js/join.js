'use strict'

// password 규칙 확인 *****
function pwRule() {
    let passwordv = $('#password').val();

    if(passwordv.length < 8) {
        $('#message').html('비밀번호는 최소 8자 이상이어야 합니다.');
        return false;
    } else if(passwordv.replace(/[!-*|@]/gi, '') >= passwordv.length) {
        $('#message').html('비밀번호는 반드시 특수문자가 1개 이상 포함되어야 합니다.');
        return false;
    } else if(passwordv.replace(/[a-z|0-9|!-*|@]/gi, '').length > 0) {
        $('#message').html('비밀번호는 영문, 숫자, 특수문자로만 입력 가능합니다.');
        return false;
    } else {
        $('#message').html('');
        return true;
    }
}


// password와 password2와 비교 ( 같은지 )
function checkPw() {
    if($('#password').val() === $('#password2').val()) {
        $('#message').html('');
        return true;
    } else {
        $('#message').html('입력하신 비밀번호와 다릅니다.');
        return false;
    }
}


// name
function checkName() {
    let name = $('#name').val();

    if (name.length < 2) {
        $('#message').html('이름은 2글자 이상 입력입니다.');
        return false;
    } else if (name.replace(/[a-z|가-힣]/gi, '').length > 0) {
        $('#message').html('이름은 영문 또는 한글로만 입력 가능합니다.');
        return false;
    } else {
        $('#message').html('');
        return true;
    }
}


//phone
function checkPhone() {
    let phone = $('#phone').val();

    console.log(parseInt(phone));
    console.log(Number.isInteger(parseInt(phone)));

    if(phone.replace(/[^0-9]/gi, '') < phone.length || Number.isInteger(parseInt(phone)) === false || phone.length !== 11) {
        $('#message').html('전화번호를 정확히 작성해주세요.');
        return false;
    } else {
        $('#message').html('');
        return true;
    }
}