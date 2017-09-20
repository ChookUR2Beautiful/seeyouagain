$(function () {
  var myPopupPanel = new Redstorm.Widget.PopupPanel();
  $('a.16sucai').bind('click', function () {
    myPopupPanel.createPanelShare({
      width: 340,
      oEvent: EventUtil.getEvent()
    });
    return false;
  });
  $('a.error-report').bind('click', function () {
    myPopupPanel.createPanelErrorReport({
      width: 300,
      oEvent: EventUtil.getEvent()
    });
    return false;
  });
  $('a.panel-comment').bind('click', function () {
    myPopupPanel.createPanelComment({
      width: 300,
      oEvent: EventUtil.getEvent()
    });
    return false;
  });
  /*
  $('div.docoda').codaSlider({
    dynamicArrowLeftText: "&nbsp;",
    dynamicArrowRightText: "&nbsp;",
    slideEaseDuration: 500
  })
  */
  var obj = $('div.tag-topic');
  obj.find('li').hover(function () {
    $(this).find('.title').animate({
      height: 'toggle',
      opacity: 0.7
    }, 200);
  }, function () {
    obj.find('.title').fadeOut();
  });
  $('#gotop a').click(function () {
    $("#wrap-header").scrollTo(300);
    return false;
  });
  $('div.pagerebuild ul').children('b').remove().end().contents().filter('[nodeValue="总数："]').remove();
});
var isPanel = false;
var Redstorm;
if (!Redstorm) Redstorm = {};
if (!Redstorm.Widget) Redstorm.Widget = {};
Redstorm.Widget.PopupPanel = function (opts) {
  this.init(opts);
};
Redstorm.Widget.PopupPanel.prototype.init = function (opts) {
  this.settings = {
    top: 0,
    left: 0,
    offsetY: 10,
    offsetX: -25,
    width: 300,
    zIndex: 888,
    oEvent: null
  };
  Redstorm.Widget.setOptions(this.settings, opts);
};
Redstorm.Widget.PopupPanel.prototype.createPanel = function (opts) {
  Redstorm.Widget.setOptions(this.settings, opts);
  var html = '';
  html += '<div id="panel-popup" class="dn">';
  html += '<div class="overlay"></div><div class="close"></div>';
  html += '<div class="outerbox"><div class="innerbox clearfix"></div></div>';
  html += '<div class="footbox"><div></div></div>';
  html += '</div>';
  $('body').find('#panel-popup').remove().end().append(html);
  $('#panel-popup').fadeIn().css({
    'top': this.settings.oEvent.pageY ? this.settings.oEvent.pageY + this.settings.offsetY : this.settings.top,
    'left': this.settings.oEvent.pageX ? this.settings.oEvent.pageX + this.settings.offsetX : this.settings.left,
    'width': this.settings.width + 'px',
    'z-index': this.settings.zIndex
  }).bind('mouseover', function () {
    isPanel = true;
  }).bind('mouseout', function () {
    isPanel = false;
  });
  $('#panel-popup .close').bind('click', function () {
    Redstorm.Widget.PopupPanel.remove();
  });
  $('body').bind('click', function () {
    if (!isPanel) Redstorm.Widget.PopupPanel.remove();
  });
};
Redstorm.Widget.PopupPanel.remove = function () {
  $('#panel-popup').fadeOut();
};
Redstorm.Widget.PopupPanel.prototype.createPanelShare = function (opts) {
  this.createPanel(opts);
  var oTarget = this.settings.oEvent.target;
  var shareVal = {
    t: oTarget.title ? encodeURIComponent(oTarget.title) : encodeURIComponent(document.title),
    c: oTarget.content ? encodeURIComponent(oTarget.content) : '',
    u: oTarget.href ? encodeURIComponent(oTarget.href) : encodeURIComponent(document.location.href),
    p: oTarget.pic ? encodeURIComponent(oTarget.pic) : '',
    s: encodeURIComponent(document.location.host)
  }
  var shareObj = {
    tsina: {
      name: '新浪微博',
      url: 'http://v.t.sina.com.cn/share/share.php',
      param: 'title=[t]&url=[u]&pic=[p]&source=[s]'
    },
    kaixin: {
      name: '开心网',
      url: 'http://www.kaixin001.com/repaste/share.php',
      param: 'rtitle=[t]&rurl=[u]'
    },
    renren: {
      name: '人人网',
      url: 'http://share.renren.com/share/buttonshare.do',
      param: 'title=[t]&link=[u]'
    },
    qzone: {
      name: 'QQ空间',
      url: 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey',
      param: 'title=[t]&url=[u]'
    },
    googlebkmk: {
      name: '谷歌书签',
      url: 'http://www.google.com/bookmarks/mark',
      param: 'op=add&bkmk=[u]&title=[t]'
    },
    cangbaidu: {
      name: '百度收藏',
      url: 'http://cang.baidu.com/do/add',
      param: 'it=[t]&iu=[u]&fr=ien#nw=1'
    },
    jianghu: {
      name: '淘江湖',
      url: 'http://share.jianghu.taobao.com/share/addShare.htm',
      param: 'url=[u]'
    },
    douban: {
      name: '豆瓣',
      url: 'http://www.douban.com/recommend/',
      param: 'url=[u]&title=[t]&v=1'
    }
  }
  var html = '';
  var url = '';
  for (var key in shareObj) {
    if (shareObj[key] == undefined) continue;
    url = shareObj[key].url + '?' + shareObj[key].param;
    url = url.replace('[t]', shareVal.t);
    url = url.replace('[c]', shareVal.c);
    url = url.replace('[u]', shareVal.u);
    url = url.replace('[p]', shareVal.p);
    url = url.replace('[s]', shareVal.s);
    html += '<li>';
    html += '<a href="' + url + '" target="_blank" class="' + key + '">' + shareObj[key].name + '</a>';
    html += '</li">';
  }
  html = html != '' ? '<ul class="sharebox">' + html + '</ul>' : '';
  $('#panel-popup .innerbox').html(html);
};
Redstorm.Widget.PopupPanel.prototype.createPanelErrorReport = function (opts) {
  this.createPanel(opts);
  var oTarget = this.settings.oEvent.target;
  var reportVal = {
    title: oTarget.title ? oTarget.title : '',
    url: oTarget.href ? oTarget.href : '',
    cid: oTarget.name ? oTarget.name : 0
  }
  $('#panel-popup .innerbox').html(sHtmlReport);
  $('#reportform').bind('submit', this.checkFormErrorReport);
  $('#error_title').val(reportVal.title);
  $('#error_link').val(reportVal.url);
  $('#error_contentid').val(reportVal.cid);
};
Redstorm.Widget.PopupPanel.prototype.createPanelComment = function (opts) {
  this.createPanel(opts);
  var oTarget = this.settings.oEvent.target;
  var commentVal = {
    keyid: oTarget.name ? oTarget.name.replace(/(.*)\|(.*)/, '$1') : '',
    verify: oTarget.name ? oTarget.name.replace(/(.*)\|(.*)/, '$2') : ''
  }
  $('#panel-popup .innerbox').html(sHtmlComment);
  $('#commentform').bind('submit', this.checkFormComment);
  $('#commentform input[name="keyid"]').val(commentVal.keyid);
  $('#commentform input[name="verify"]').val(commentVal.verify);
};
Redstorm.Widget.PopupPanel.prototype.checkFormErrorReport = function () {
  if ($("#reportform input[@type=radio][@checked]").val() === undefined) {
    alert('类别不能为空！');
    return false;
  }
  if ($('#reportform #error_title').val() == '') {
    $('#reportform #error_title').focus();
    alert('标题不能为空！');
    return false;
  }
  if ($('#reportform #error_link').val() == '') {
    $('#reportform #error_link').focus();
    alert('地址不能为空！');
    return false;
  }
  if ($('#reportform #checkcode').val() == '') {
    $('#reportform #checkcode').focus();
    alert('验证码不能为空！');
    return false;
  }
};
Redstorm.Widget.PopupPanel.prototype.checkFormComment = function () {
  if ($('#commentform #comment').val() == '') {
    $('#commentform #comment').focus();
    alert('评论内容不能为空！');
    return false;
  }
  if ($('#commentform #checkcode').val() == '') {
    $('#commentform #checkcode').focus();
    alert('验证码不能为空！');
    return false;
  }
};
Redstorm.Widget.setOptions = function (obj, optionsObj, ignoreUndefinedProps) {
  if (!optionsObj) return;
  for (var optionName in optionsObj) {
    if (ignoreUndefinedProps && optionsObj[optionName] == undefined) continue;
    obj[optionName] = optionsObj[optionName];
  }
};
var sUserAgent = navigator.userAgent;
var fAppVersion = parseFloat(navigator.appVersion);
function compareVersions(sVersion1, sVersion2) {
  var aVersion1 = sVersion1.split(".");
  var aVersion2 = sVersion2.split(".");
  if (aVersion1.length > aVersion2.length) {
    for (var i = 0; i < aVersion1.length - aVersion2.length; i++) {
      aVersion2.push("0");
    }
  } else if (aVersion1.length < aVersion2.length) {
    for (var i = 0; i < aVersion2.length - aVersion1.length; i++) {
      aVersion1.push("0");
    }
  }
  for (var i = 0; i < aVersion1.length; i++) {
    if (aVersion1[i] < aVersion2[i]) {
      return -1;
    } else if (aVersion1[i] > aVersion2[i]) {
      return 1;
    }
  }
  return 0;
}
var isOpera = sUserAgent.indexOf("Opera") > -1;
var isMinOpera4 = isMinOpera5 = isMinOpera6 = isMinOpera7 = isMinOpera7_5 = false;
if (isOpera) {
  var fOperaVersion;
  if (navigator.appName == "Opera") {
    fOperaVersion = fAppVersion;
  } else {
    var reOperaVersion = new RegExp("Opera (//d+//.//d+)");
    reOperaVersion.test(sUserAgent);
    fOperaVersion = parseFloat(RegExp["$1"]);
  }
  isMinOpera4 = fOperaVersion >= 4;
  isMinOpera5 = fOperaVersion >= 5;
  isMinOpera6 = fOperaVersion >= 6;
  isMinOpera7 = fOperaVersion >= 7;
  isMinOpera7_5 = fOperaVersion >= 7.5;
}
var isKHTML = sUserAgent.indexOf("KHTML") > -1 || sUserAgent.indexOf("Konqueror") > -1 || sUserAgent.indexOf("AppleWebKit") > -1;
var isMinSafari1 = isMinSafari1_2 = false;
var isMinKonq2_2 = isMinKonq3 = isMinKonq3_1 = isMinKonq3_2 = false;
if (isKHTML) {
  isSafari = sUserAgent.indexOf("AppleWebKit") > -1;
  isKonq = sUserAgent.indexOf("Konqueror") > -1;
  if (isSafari) {
    var reAppleWebKit = new RegExp("AppleWebKit///(//d+(?://.//d*)?)");
    reAppleWebKit.test(sUserAgent);
    var fAppleWebKitVersion = parseFloat(RegExp["$1"]);
    isMinSafari1 = fAppleWebKitVersion >= 85;
    isMinSafari1_2 = fAppleWebKitVersion >= 124;
  } else if (isKonq) {
    var reKonq = new RegExp("Konqueror///(//d+(?://.//d+(?://.//d)?)?)");
    reKonq.test(sUserAgent);
    isMinKonq2_2 = compareVersions(RegExp["$1"], "2.2") >= 0;
    isMinKonq3 = compareVersions(RegExp["$1"], "3.0") >= 0;
    isMinKonq3_1 = compareVersions(RegExp["$1"], "3.1") >= 0;
    isMinKonq3_2 = compareVersions(RegExp["$1"], "3.2") >= 0;
  }
}
var isIE = sUserAgent.indexOf("compatible") > -1 && sUserAgent.indexOf("MSIE") > -1 && !isOpera;
var isMinIE4 = isMinIE5 = isMinIE5_5 = isMinIE6 = false;
if (isIE) {
  var reIE = new RegExp("MSIE (//d+//.//d+);");
  reIE.test(sUserAgent);
  var fIEVersion = parseFloat(RegExp["$1"]);
  isMinIE4 = fIEVersion >= 4;
  isMinIE5 = fIEVersion >= 5;
  isMinIE5_5 = fIEVersion >= 5.5;
  isMinIE6 = fIEVersion >= 6.0;
}
var isMoz = sUserAgent.indexOf("Gecko") > -1 && !isKHTML;
var isMinMoz1 = sMinMoz1_4 = isMinMoz1_5 = false;
if (isMoz) {
  var reMoz = new RegExp("rv:(//d+//.//d+(?://.//d+)?)");
  reMoz.test(sUserAgent);
  isMinMoz1 = compareVersions(RegExp["$1"], "1.0") >= 0;
  isMinMoz1_4 = compareVersions(RegExp["$1"], "1.4") >= 0;
  isMinMoz1_5 = compareVersions(RegExp["$1"], "1.5") >= 0;
}
var isNS4 = !isIE && !isOpera && !isMoz && !isKHTML && (sUserAgent.indexOf("Mozilla") == 0) && (navigator.appName == "Netscape") && (fAppVersion >= 4.0 && fAppVersion < 5.0);
var isMinNS4 = isMinNS4_5 = isMinNS4_7 = isMinNS4_8 = false;
if (isNS4) {
  isMinNS4 = true;
  isMinNS4_5 = fAppVersion >= 4.5;
  isMinNS4_7 = fAppVersion >= 4.7;
  isMinNS4_8 = fAppVersion >= 4.8;
}
var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh");
var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
var isWin95 = isWin98 = isWinNT4 = isWin2K = isWinME = isWinXP = false;
var isMac68K = isMacPPC = false;
var isSunOS = isMinSunOS4 = isMinSunOS5 = isMinSunOS5_5 = false;
if (isWin) {
  isWin95 = sUserAgent.indexOf("Win95") > -1 || sUserAgent.indexOf("Windows 95") > -1;
  isWin98 = sUserAgent.indexOf("Win98") > -1 || sUserAgent.indexOf("Windows 98") > -1;
  isWinME = sUserAgent.indexOf("Win 9x 4.90") > -1 || sUserAgent.indexOf("Windows ME") > -1;
  isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
  isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
  isWinNT4 = sUserAgent.indexOf("WinNT") > -1 || sUserAgent.indexOf("Windows NT") > -1 || sUserAgent.indexOf("WinNT4.0") > -1 || sUserAgent.indexOf("Windows NT 4.0") > -1 && (!isWinME && !isWin2K && !isWinXP);
}
if (isMac) {
  isMac68K = sUserAgent.indexOf("Mac_68000") > -1 || sUserAgent.indexOf("68K") > -1;
  isMacPPC = sUserAgent.indexOf("Mac_PowerPC") > -1 || sUserAgent.indexOf("PPC") > -1;
}
if (isUnix) {
  isSunOS = sUserAgent.indexOf("SunOS") > -1;
  if (isSunOS) {
    var reSunOS = new RegExp("SunOS (//d+//.//d+(?://.//d+)?)");
    reSunOS.test(sUserAgent);
    isMinSunOS4 = compareVersions(RegExp["$1"], "4.0") >= 0;
    isMinSunOS5 = compareVersions(RegExp["$1"], "5.0") >= 0;
    isMinSunOS5_5 = compareVersions(RegExp["$1"], "5.5") >= 0;
  }
}
var EventUtil = new Object;
EventUtil.addEventHandler = function (oTarget, sEventType, fnHandler) {
  if (oTarget.addEventListener) {
    oTarget.addEventListener(sEventType, fnHandler, false);
  } else if (oTarget.attachEvent) {
    oTarget.attachEvent("on" + sEventType, fnHandler);
  } else {
    oTarget["on" + sEventType] = fnHandler;
  }
};
EventUtil.removeEventHandler = function (oTarget, sEventType, fnHandler) {
  if (oTarget.removeEventListener) {
    oTarget.removeEventListener(sEventType, fnHandler, false);
  } else if (oTarget.detachEvent) {
    oTarget.detachEvent("on" + sEventType, fnHandler);
  } else {
    oTarget["on" + sEventType] = null;
  }
};
EventUtil.formatEvent = function (oEvent) {
  if (isIE && isWin) {
    oEvent.charCode = (oEvent.type == "keypress") ? oEvent.keyCode : 0;
    oEvent.eventPhase = 2;
    oEvent.isChar = (oEvent.charCode > 0);
    oEvent.pageX = oEvent.clientX + document.documentElement.scrollLeft;
    oEvent.pageY = oEvent.clientY + document.documentElement.scrollTop;
    oEvent.preventDefault = function () {
      this.returnValue = false;
    };
    if (oEvent.type == "mouseout") {
      oEvent.relatedTarget = oEvent.toElement;
    } else if (oEvent.type == "mouseover") {
      oEvent.relatedTarget = oEvent.fromElement;
    }
    oEvent.stopPropagation = function () {
      this.cancelBubble = true;
    };
    oEvent.target = oEvent.srcElement;
    oEvent.time = (new Date).getTime();
  }
  return oEvent;
};
EventUtil.getEvent = function () {
  if (window.event) {
    return this.formatEvent(window.event);
  } else {
    return EventUtil.getEvent.caller.arguments[0];
  }
};