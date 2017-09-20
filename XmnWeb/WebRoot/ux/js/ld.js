//	全国三级城市联动 js版
function Dsy(areaList){
	this.Items = areaList;
}
Dsy.prototype.Exists = function(id){
	if(typeof(this.Items[id]) == "undefined") return false;
	return true;
}

var dsy;

function findSelect(name){
	var elems;
	$("div.input-group").each(function(){
		$(this).find("select[name="+name+"]").each(function(){
			var selectName = $(this).attr("name");
			if($(this).attr(selectName+"isClick")){
				elems=this;
				return false;
			}
		});
		if(elems){
			return false;
		}
	});
	return elems;
}

function change(v){
	var ss =findSelect(s[v]) ;
	var id =0;
	var pSelect;
	var pVal;
	if(v>0){
		var val ;
		pSelect =  findSelect(s[v-1]);
		var val = $(pSelect).val();
		id = parseInt(val);
	}
		pVal =  $(ss).attr("initValue");
		if(pVal){
		    pVal = parseInt(pVal);
		}
	$(ss).empty();
	var options = new Array();
	$(ss).append(["<option  value=",0,">",opt0[v],"</option>"].join(""));
	if(!(v<0)){
		if(v==0 || (pSelect && $(pSelect).get(0).selectedIndex>0)){
			if(dsy.Exists(id)){
				ar = dsy.Items[id];
				for(i=0;i<ar.length;i++){
					var areaid= parseInt(ar[i]["areaId"]);
					if(areaid == pVal){
						options.push(["<option  selected='selected' value=",ar[i]["areaId"],">",ar[i]["title"],"</option>"].join(""));
						continue;
					}
					options.push(["<option  value=",ar[i]["areaId"],">",ar[i]["title"],"</option>"].join(""));
				}
				$(ss).append(options.join(""));
			}
		}
		
	}//end if v
	if(++v<s.length){change(v);}
	
}

var s=["province","city","area"];//三个select的id
var optIndex = {"province":0,"city":1,"area":2}
var opt0 = ["请选择省份","请选择市","请选择区"];//初始值
function _init_area(form){  //初始化函数
	var jsonObj = eval("("+alist+")");
	dsy = new Dsy(jsonObj);
	var index;
	for(i=0;i<s.length-1;i++){
		$(form).on("change","select[name="+s[i]+"]",function(event){
			var name = $(event.target).attr("name");
			var index =  optIndex[name];
			change(index+1);
		});
	}
	change(0);
}
