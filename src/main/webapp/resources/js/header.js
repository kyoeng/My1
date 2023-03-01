'use strict'

// header의 my_btn 클릭 이벤트
let check = false;

const navBox = document.getElementsByClassName('my_nav')[0];

document.getElementsByClassName('my_btn')[0].addEventListener('click', (e) => {
    check = !check;

    if (check) {
        e.target.innerHTML = "▲"
        navBox.style.visibility = "visible";
    } else {
        e.target.innerHTML = "▼"
        navBox.style.visibility = "hidden";
    }
});