// joinForm의 id check *****
function idCheck() {
    // id 중복체크 ajax 코드
    $.ajax({
        type: 'get',
        url: 'idcheck',
        data: {
            id: $('#id').val()
        },
        success: (res) => {
            if(res.code == 200) {
                if(confirm('사용 가능한 아이디입니다. 사용하시겠습니까?')) {
                    $('#id').prop('readonly', true).css({
                        opacity: 0.5
                    });

                    $('#check_btn').removeAttr('onclick').css({
                        opacity: 0.5
                    });

                    iCheck = true;
                }
            } else {
                alert('이미 사용중인 아이디입니다.');
                $('#id').focus();
            }
        },
        error: () => {
            alert('Error 발생');
        }
    });
}