
$(function() {
	document.title = index_title;
	$('#site_title').html("&nbsp;" + index_title);
	InitLeftMenu();
	$('body').layout();
});

function InitLeftMenu() {
	$('.easyui-accordion li a').click(function() {
		var tabTitle = $(this).text();
		var url = $(this).attr("way");
		addTab(tabTitle, url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			width : $('#mainPanle').width() - 10,
			height : $('#mainPanle').height() - 26
		});
	} else {
		$('#tabs').tabs('select', subtitle);
	}
}

function createFrame(url) {
	var s = '<iframe name="mainFrame" scrolling="no" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

$.goLoginpage = function() {
	location.href = "login.html";
};
$.checkLoginResult = function(result) {
	// alert( JSON.stringify(result));
	if (result == null || result["code"] != 1) {
		$.goLoginpage();
	} else {
		$("#loginname").text(result["name"]);
		// authority.name = result["name"];
		$.showMenu(result["menuContent"]);

	}
};
$.checkLogin = function() {
	var checkLogin = {};
	checkLogin["checkLogin"] = {};
	$.submitControl(checkLogin, $.checkLoginResult);
};

$(document).ready(function() {

	$.checkLogin();
	if (isShowPlatformAndGameSelect) {
		$.initPlatformAndGameSelect();
	} else {
		$("#main_panel").layout("remove","north");
	}

});
$.initPlatformAndGameSelect = function() {
	$('#platformSelect').change(function() {

		global_devicetype = $("#platformSelect").val();
		if (global_devicetype == "null") {
			global_devicetype = undefined;
		}
	});

	var mgame = {
		"moregame" : {}
	};
	$.submitControl(mgame, $.submitMoreGameResult);
	$('#platformSelect').combobox({
		width : 100,
		editable : false,
		valueField : "id",
		textField : "value",
		value : "null",
		data : [ {
			'id' : 'null',
			'value' : '全部'
		}, {
			'id' : '0',
			'value' : 'Android'
		}, {
			'id' : '1',
			'value' : 'IOS'
		} ],
		onSelect : function() {
			global_devicetype = $("#platformSelect").combobox('getValue');
			if (global_devicetype == "null") {
				global_devicetype = undefined;
			}
		}
	});
};
var global_devicetype;//全局变量--设备类型：空 ：所有；0：android；1：IOS：2：apache
var global_gameid = 0;//游戏:0:跑到大
var moregamecontent = null;
var jsonmoregamecontent = null;
$.submitMoreGameResult = function(jsonResult) {
	moregamecontent = jsonResult['moregameResponse'];
	$('#moregame_id').combobox({
		width:100,
		editable:false,
		valueField: "id",
		textField: "value",
		data: moregamecontent,
		onSelect: function(){   
			var selectgame_id=$("#moregame_id").combobox('getValue');
			var game_name = {
				"cutoverGeme" : selectgame_id
			};
			$.submitControl(game_name);
		}
	});
	$("#moregame_id").combobox('setValue', '跑到大');
	var game_name2 = {
		"cutoverGeme" : 1
	};
	$.submitControl(game_name2);
};
// $.logoutCallback =
function logoutFun() {
	var mt = {
		"exitLogin" : {
			"id" : 1
		}
	};
	$.submitControl(mt, $.goLoginpage);

}
$.openDialog = function(title, url, onLoadFunction) {
	$('#dd').dialog({
		title : title,
		width : 800,
		height : 600,
		closed : false,
		cache : false,
		href : url,
		modal : true,
		onClose : function() {
			// window.location.reload();
		},
		onLoad : onLoadFunction
	});
};
function modifyPassword() {
	$.openDialog('修改密码', 'module/manager/user/modifyPassword.html');
}

$.showMenu = function(jsonResult) {
	// alert( JSON.stringify(jsonResult));
	var allMenuUrl = jsonResult;
	var menu_url = '';
	var menuname_parent = '';
	for ( var i = 0; i < allMenuUrl.length; i++) {
		// alert(allMenuUrl[i].parent);
		if (allMenuUrl[i].parent == -1) {
			var menuUrl_id = allMenuUrl[i].id;
			menuname_parent = allMenuUrl[i].name;
			menu_url += '<div title="' + allMenuUrl[i].name
					+ '" style="padding: 20px 0px 0px 0px;" ><ul style="padding: 0px;" >';
			for ( var j = 0; j < allMenuUrl.length; j++) {
				if (allMenuUrl[j].parent == menuUrl_id) {
					menu_url += '<li  id="'
							+ allMenuUrl[j].key
							+ '"><div style="overflow-x:hidden"><a   target="mainFrame" way="'
							+ allMenuUrl[j].url + '">' + allMenuUrl[j].name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ '</a></div></li>';
				}
			}
			menu_url += "</ul></div>";
			// alert(menu_url);
			var iconWhere='icon-home';
			if(allMenuUrl[i].key=="top_manager") {
				iconWhere='icon-config';
			}
			$('#menu').accordion('add', {
				title : menuname_parent,
				// click:GetSmallMenu(n.MenuID,n.MenuName),
				iconCls : iconWhere,
				selected : i == 0,
				content : menu_url
			});
			menu_url = '';
		}
	}
	$('#menu').accordion('add', {
		title : 'Help',
		// click:GetSmallMenu(n.MenuID,n.MenuName),
		iconCls : 'icon-help',
		selected : false,
		content : '<div title="关于" ><h4>帮助主题</h4></div>'
	});
	InitLeftMenu();
};