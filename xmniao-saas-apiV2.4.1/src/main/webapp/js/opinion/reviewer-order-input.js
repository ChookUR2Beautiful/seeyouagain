/*提示组件*/
function tipsComponent(c) {
    this.i = 1;
}

tipsComponent.prototype.show = function(content) {
    $('body').append('<div class="tipsmodle"><p>' + content + '</p></div>');
    var twidth = $('.tipsmodle').css('width');
    $('.tipsmodle').css({ 'marginLeft': -(parseInt(twidth) + 20) / 2 + "px", left: "50%" });
    this.showAction();
}

tipsComponent.prototype.showAction = function() {
    var _this = this;
    $('.tipsmodle').animate({ opacity: "0.9", bottom: "25%" }, 100, "linear", function() {
        var tips_index = this;
        setTimeout(function() {
            _this.destoryTips(tips_index);
        }, 2000);
    });
}

tipsComponent.prototype.destoryTips = function(_this) {

    $(_this).animate({ opacity: "0", bottom: "30%" }, 100, "linear", function() {
        $(_this).remove();
    });

}

var Tips = new tipsComponent();
/*提示组件END*/

