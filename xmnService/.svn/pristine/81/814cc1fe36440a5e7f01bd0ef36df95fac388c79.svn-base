<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="gray">
<head>
    <meta charset="UTF-8">
    <title>积分</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <link rel="stylesheet" href="main.css"/><link>
    
	<style type="text/css">
	    	
		/*公共样式*/
		address,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block}
		html{font-size:62.5%;}
		*{-webkit-touch-callout:none;user-select:none;-webkit-tap-highlight-color:rgba(255,255,255,0);overflow-scrolling:touch;margin:0;padding:0;outline:0;font-style:normal;border:none;background:0 0}
		input,label,select{vertical-align:middle;}
		h1,h2,h3,h4,h5,h6{font-weight:normal;}
		input[type=checkbox],input[type=radio]{box-sizing:border-box;}
		input[type=search]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box}
		body{font-family: "微软雅黑";min-width:320px;word-wrap:break-word;line-height:1;cursor:default;-ms-content-zooming:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none;text-size-adjust:none}
		table{border-collapse:collapse;border-spacing:0;}
		caption,th{text-align:left;font-weight:400;}
		dl,li,ol,ul{list-style:none;}
		textarea{resize:none;overflow:auto;vertical-align:top;}
		del{text-decoration:line-through;}
		a,button{cursor:pointer;}
		a,a:hover{text-decoration:none;-webkit-tap-highlight-color:transparent;}
		a:active{blr:expression(this.onFocus=this.blur());outline:0;}
		a:focus{outline:0;-moz-outline:none;}
		img{border:0;vertical-align:middle;-ms-interpolation-mode:bicubic;-webkit-tap-highlight-color:transparent;}
		.flex{display:-webkit-box;display:-moz-box;display:-ms-flexbox;display:flex;}
		.flex-fixed{width:auto;display:block;}
		.flex-auto{-webkit-box-flex:1;-moz-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1;}
		.gray{
		    background: #f4f4f4;
		}
		.introduce{
		    background: #ffffff;
		    padding-top: 25px;
		}
		.introduce h3{
		    font-size: 15px;
		    color: #ffffff;
		    width: 315px;
		    background: url(images/title_bg.png) no-repeat center;
		    background-size: 100%;
		    height: 36px;
		    margin: auto;
		    text-align: center;
		    line-height: 28px;
		}
		@media screen and (max-width: 350px) {
		    .introduce h3{
		        width: 290px;
		    }
		}
		.use-box{
		    margin-top: 20px;
		}
		.introduce ul{
		    padding: 0 22px;
		    font-size: 14px;
		    color: #4a4a4a;
		    line-height: 24px;
		    margin-top: 20px;
		}
		.introduce ul li{
		    position: relative;
		    padding-left: 20px;
		    padding-bottom: 12px;
		    border-left: 2px solid #bd9e70;
		}
		.introduce ul li span.sequence{
		    position: absolute;
		    left: -13px;
		    top: 0;
		    z-index: 1;
		    width: 24px;
		    height: 24px;
		    line-height: 24px;
		    text-align: center;
		    color: #fff;
		    background: #bd9e70;
		    border-radius: 50%;
		}
		.introduce ul li:last-child{
		    border-left: 2px solid #fff;
		}
		.gain-box p.title{
		    width: 128px;
		    text-align: center;
		    height: 30px;
		    line-height: 30px;
		    font-size: 15px;
		    color: #ffffff;
		    background: #bd9e70;
		    margin: auto;
		}
		.gain-box  .tree{
		    display: block;
		    width: 86%;
		    margin: auto;
		}
		.tree-map{
		    padding-bottom: 10px;
		}
		.tree-map p{
		    font-size: 14px;
		    text-align: center;
		    color: #4a4a4a;
		    line-height: 25px;
		}
		    	
	    	
	</style>
    
    
</head>
<body>
    <div class="gain-box introduce">
        <h3>“寻蜜鸟”会员积分获取规则</h3>
        <ul>
            <li><span class="sequence">1</span>会员在寻蜜鸟上消费（小鸟超市除外），在订单支付成功后，积分将实时发放至相应账户</li>
            <li><span class="sequence">2</span>支付金额获得同等积分，积分将发放至消费会员的寻蜜鸟账户内；积分计算：实际支付金额=获得积
                分。如：在商户实际支付98元，此获得积分为98积分。（备注：使用账号余额支付不赠送积分）</li>
            <li><span class="sequence">3</span>积分超市商品，现金支付部分不赠送积分；线下商户提供的兑换商品，现金支付部分不赠送积分</li>
        </ul>
        <div class="tree-map">
            <p class="title">积分类型</p>
            <img src="images/font_bg.png" class="tree" />
            <p>消费积分与奖励积分合并后<br />统称为会员积分<br />会员个人中心可进行查看</p>
        </div>
    </div>
    <div class="use-box introduce">
        <h3>“寻蜜鸟”会员积分使用规则</h3>
        <ul>
            <li><span class="sequence">1</span>会员获取的积分可用于换购寻蜜鸟积分商城内的商品</li>
            <li><span class="sequence">2</span>每笔商城商品订单须使用【第三方支付+积分】组合支付或【第三方支付】形式进行换购，可用积
                分支付额度视每款商品情况而定</li>
            <li><span class="sequence">3</span>会员通过积分换购积分商城内商品时，每1积分等于1元价值使用，换购的商品使用的积分为整数兑换</li>
            <li><span class="sequence">4</span>当会员的积分不足兑换相应商品时，会员可以选择通过第三方支付不足部分价款</li>
            <li><span class="sequence">5</span>所获取的积分，自获取之日起365天内有效，会员使用积分时，优先消耗旧积分。寻蜜鸟将会自动扣除过期部分的积分额</li>
            <li><span class="sequence">6</span>同一用户多次通过寻蜜鸟消费时所获赠的积分，可累积、可叠加使用，但积分不能兑现，不可转让</li>
            <li><span class="sequence">7</span>积分仅限于同一账号下累积，不同的账号代表不同“身份”，多个账号下的积分不能合并</li>
            <li><span class="sequence">8</span>在消费、获赠积分，并使用积分后，如在消费环节产生退款的，因积分已被使用，所以退款前，会相应扣除积分同等价值的金额，再进行退款</li>
            <li><span class="sequence">9</span>商品退货时，使用积分兑换的部分，积分将返还至用户积分处。通过第三方支付的部分，将通过第三方支付软件退款</li>
        </ul>
    </div>
</body>
</html>