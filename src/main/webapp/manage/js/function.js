function sysTime() {
    var d = new Date(),
    yyyy = d.getFullYear(),
    MM = d.getMonth() + 1,
    dd = d.getDate(),
    hh = d.getHours(),
    mm = d.getMinutes(),
    ss = d.getSeconds();

    MM = MM < 10 ? "0" + MM : MM;
    dd = dd < 10 ? "0" + dd : dd;
    hh = hh < 10 ? "0" + hh : hh;
    mm = mm < 10 ? "0" + mm : mm;
    ss = ss < 10 ? "0" + ss : ss;

    $(".systime i").html([yyyy, "-", MM, "-", dd, " ", hh, ":", mm, ":", ss].join(""));
}

setInterval(function () {
    sysTime();
}, 1000);

$(function () {
    sysTime();

    $(".main_data_table tr:odd").addClass("odd")
})
