function myPost(url,args){
	    var body = $(document.body),
	        form = $("<form method='post'></form>"),
	        input;
	    form.attr({"action":url});
	    $.each(args,function(key,value){
	        input = $("<input type='hidden'>");
	        input.attr({"name":key});
	        input.val(value);
	        form.append(input);
	    });

	    form.appendTo(document.body);
	    form.submit();
	    document.body.removeChild(form[0]);
	}

function formatDate(date, fmt)
{
    date = date == undefined ? new Date() : date;
    date = typeof date == 'number' ? new Date(date) : date;
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
    var obj =
    {
        'y': date.getFullYear(), // 年份，注意必须用getFullYear
        'M': date.getMonth() + 1, // 月份，注意是从0-11
        'd': date.getDate(), // 日期
        'q': Math.floor((date.getMonth() + 3) / 3), // 季度
        'w': date.getDay(), // 星期，注意是0-6
        'H': date.getHours(), // 24小时制
        'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
        'm': date.getMinutes(), // 分钟
        's': date.getSeconds(), // 秒
        'S': date.getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for(var i in obj)
    {
        fmt = fmt.replace(new RegExp(i+'+', 'g'), function(m)
        {
            var val = obj[i] + '';
            if(i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
            for(var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
            return m.length == 1 ? val : val.substring(val.length - m.length);
        });
    }
    return fmt;
}


$(".amount").keyup(function () {
    var reg = $(this).val().match(/\d+\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
        if(parseFloat(txt)>parseFloat('5000')){
        	txt=5000;
        }
    }
    $(this).val(txt);
}).change(function () {
    $(this).keypress();
    var v = $(this).val();
    if (/\.$/.test(v))
    {
        $(this).val(v.substr(0, v.length - 1));
    }
});