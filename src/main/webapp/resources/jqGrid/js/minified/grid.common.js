/**
*
* @license Guriddo jqGrid JS - v4.7.1 - 2015-02-12
* Copyright(c) 2008, Tony Tomov, tony@trirand.com
* 
* License: http://guriddo.net/?page_id=103334
*/
!function(a){"use strict";"function"==typeof define&&define.amd?define(["jquery","./grid.base","./jqModal","./jqDnR"],a):a(jQuery)}(function(a){"use strict";a.extend(a.jgrid,{showModal:function(a){a.w.show()},closeModal:function(a){a.w.hide().attr("aria-hidden","true"),a.o&&a.o.remove()},hideModal:function(b,c){c=a.extend({jqm:!0,gb:"",removemodal:!1,formprop:!1,form:""},c||{});var d=c.gb&&"string"==typeof c.gb&&"#gbox_"===c.gb.substr(0,6)?a("#"+c.gb.substr(6))[0]:!1;if(c.onClose){var e=d?c.onClose.call(d,b):c.onClose(b);if("boolean"==typeof e&&!e)return}if(c.formprop&&d&&c.form){var f=a(b)[0].style.height;f.indexOf("px")>-1&&(f=parseFloat(f));var g,h;"edit"===c.form?(g="#"+a.jgrid.jqID("FrmGrid_"+c.gb.substr(6)),h="formProp"):"view"===c.form&&(g="#"+a.jgrid.jqID("ViewGrid_"+c.gb.substr(6)),h="viewProp"),a(d).data(h,{top:parseFloat(a(b).css("top")),left:parseFloat(a(b).css("left")),width:a(b).width(),height:f,dataheight:a(g).height(),datawidth:a(g).width()})}if(a.fn.jqm&&c.jqm===!0)a(b).attr("aria-hidden","true").jqmHide();else{if(""!==c.gb)try{a(".jqgrid-overlay:first",c.gb).hide()}catch(i){}a(b).hide().attr("aria-hidden","true")}c.removemodal&&a(b).remove()},findPos:function(a){var b=0,c=0;if(a.offsetParent)do b+=a.offsetLeft,c+=a.offsetTop;while(a=a.offsetParent);return[b,c]},createModal:function(b,c,d,e,f,g,h){d=a.extend(!0,{},a.jgrid.jqModal||{},d);var i,j=document.createElement("div"),k=this;h=a.extend({},h||{}),i="rtl"===a(d.gbox).attr("dir")?!0:!1,j.className="ui-widget ui-widget-content ui-corner-all ui-jqdialog",j.id=b.themodal;var l=document.createElement("div");l.className="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix",l.id=b.modalhead,a(l).append("<span class='ui-jqdialog-title'>"+d.caption+"</span>");var m=a("<a class='ui-jqdialog-titlebar-close ui-corner-all'></a>").hover(function(){m.addClass("ui-state-hover")},function(){m.removeClass("ui-state-hover")}).append("<span class='ui-icon ui-icon-closethick'></span>");a(l).append(m),i?(j.dir="rtl",a(".ui-jqdialog-title",l).css("float","right"),a(".ui-jqdialog-titlebar-close",l).css("left","0.3em")):(j.dir="ltr",a(".ui-jqdialog-title",l).css("float","left"),a(".ui-jqdialog-titlebar-close",l).css("right","0.3em"));var n=document.createElement("div");a(n).addClass("ui-jqdialog-content ui-widget-content").attr("id",b.modalcontent),a(n).append(c),j.appendChild(n),a(j).prepend(l),g===!0?a("body").append(j):"string"==typeof g?a(g).append(j):a(j).insertBefore(e),a(j).css(h),void 0===d.jqModal&&(d.jqModal=!0);var o={};if(a.fn.jqm&&d.jqModal===!0){if(0===d.left&&0===d.top&&d.overlay){var p=[];p=a.jgrid.findPos(f),d.left=p[0]+4,d.top=p[1]+4}o.top=d.top+"px",o.left=d.left}else(0!==d.left||0!==d.top)&&(o.left=d.left,o.top=d.top+"px");if(a("a.ui-jqdialog-titlebar-close",l).click(function(){var c=a("#"+a.jgrid.jqID(b.themodal)).data("onClose")||d.onClose,e=a("#"+a.jgrid.jqID(b.themodal)).data("gbox")||d.gbox;return k.hideModal("#"+a.jgrid.jqID(b.themodal),{gb:e,jqm:d.jqModal,onClose:c,removemodal:d.removemodal||!1,formprop:!d.recreateForm||!1,form:d.form||""}),!1}),0!==d.width&&d.width||(d.width=300),0!==d.height&&d.height||(d.height=200),!d.zIndex){var q=a(e).parents("*[role=dialog]").filter(":first").css("z-index");d.zIndex=q?parseInt(q,10)+2:950}var r=0;if(i&&o.left&&!g&&(r=a(d.gbox).width()-(isNaN(d.width)?0:parseInt(d.width,10))-8,o.left=parseInt(o.left,10)+parseInt(r,10)),o.left&&(o.left+="px"),a(j).css(a.extend({width:isNaN(d.width)?"auto":d.width+"px",height:isNaN(d.height)?"auto":d.height+"px",zIndex:d.zIndex,overflow:"hidden"},o)).attr({tabIndex:"-1",role:"dialog","aria-labelledby":b.modalhead,"aria-hidden":"true"}),void 0===d.drag&&(d.drag=!0),void 0===d.resize&&(d.resize=!0),d.drag)if(a(l).css("cursor","move"),a.fn.jqDrag)a(j).jqDrag(l);else try{a(j).draggable({handle:a("#"+a.jgrid.jqID(l.id))})}catch(s){}if(d.resize)if(a.fn.jqResize)a(j).append("<div class='jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se'></div>"),a("#"+a.jgrid.jqID(b.themodal)).jqResize(".jqResize",b.scrollelm?"#"+a.jgrid.jqID(b.scrollelm):!1);else try{a(j).resizable({handles:"se, sw",alsoResize:b.scrollelm?"#"+a.jgrid.jqID(b.scrollelm):!1})}catch(t){}d.closeOnEscape===!0&&a(j).keydown(function(c){if(27===c.which){var e=a("#"+a.jgrid.jqID(b.themodal)).data("onClose")||d.onClose;k.hideModal("#"+a.jgrid.jqID(b.themodal),{gb:d.gbox,jqm:d.jqModal,onClose:e,removemodal:d.removemodal||!1,formprop:!d.recreateForm||!1,form:d.form||""})}})},viewModal:function(b,c){if(c=a.extend({toTop:!0,overlay:10,modal:!1,overlayClass:"ui-widget-overlay",onShow:a.jgrid.showModal,onHide:a.jgrid.closeModal,gbox:"",jqm:!0,jqM:!0},c||{}),a.fn.jqm&&c.jqm===!0)c.jqM?a(b).attr("aria-hidden","false").jqm(c).jqmShow():a(b).attr("aria-hidden","false").jqmShow();else{""!==c.gbox&&(a(".jqgrid-overlay:first",c.gbox).show(),a(b).data("gbox",c.gbox)),a(b).show().attr("aria-hidden","false");try{a(":input:visible",b)[0].focus()}catch(d){}}},info_dialog:function(b,c,d,e){var f={width:290,height:"auto",dataheight:"auto",drag:!0,resize:!1,left:250,top:170,zIndex:1e3,jqModal:!0,modal:!1,closeOnEscape:!0,align:"center",buttonalign:"center",buttons:[]};a.extend(!0,f,a.jgrid.jqModal||{},{caption:"<b>"+b+"</b>"},e||{});var g=f.jqModal,h=this;a.fn.jqm&&!g&&(g=!1);var i,j="";if(f.buttons.length>0)for(i=0;i<f.buttons.length;i++)void 0===f.buttons[i].id&&(f.buttons[i].id="info_button_"+i),j+="<a id='"+f.buttons[i].id+"' class='fm-button ui-state-default ui-corner-all'>"+f.buttons[i].text+"</a>";var k=isNaN(f.dataheight)?f.dataheight:f.dataheight+"px",l="text-align:"+f.align+";",m="<div id='info_id'>";m+="<div id='infocnt' style='margin:0px;padding-bottom:1em;width:100%;overflow:auto;position:relative;height:"+k+";"+l+"'>"+c+"</div>",m+=d?"<div class='ui-widget-content ui-helper-clearfix' style='text-align:"+f.buttonalign+";padding-bottom:0.8em;padding-top:0.5em;background-image: none;border-width: 1px 0 0 0;'><a id='closedialog' class='fm-button ui-state-default ui-corner-all'>"+d+"</a>"+j+"</div>":""!==j?"<div class='ui-widget-content ui-helper-clearfix' style='text-align:"+f.buttonalign+";padding-bottom:0.8em;padding-top:0.5em;background-image: none;border-width: 1px 0 0 0;'>"+j+"</div>":"",m+="</div>";try{"false"===a("#info_dialog").attr("aria-hidden")&&a.jgrid.hideModal("#info_dialog",{jqm:g}),a("#info_dialog").remove()}catch(n){}a.jgrid.createModal({themodal:"info_dialog",modalhead:"info_head",modalcontent:"info_content",scrollelm:"infocnt"},m,f,"","",!0),j&&a.each(f.buttons,function(b){a("#"+a.jgrid.jqID(this.id),"#info_id").bind("click",function(){return f.buttons[b].onClick.call(a("#info_dialog")),!1})}),a("#closedialog","#info_id").click(function(){return h.hideModal("#info_dialog",{jqm:g,onClose:a("#info_dialog").data("onClose")||f.onClose,gb:a("#info_dialog").data("gbox")||f.gbox}),!1}),a(".fm-button","#info_dialog").hover(function(){a(this).addClass("ui-state-hover")},function(){a(this).removeClass("ui-state-hover")}),a.isFunction(f.beforeOpen)&&f.beforeOpen(),a.jgrid.viewModal("#info_dialog",{onHide:function(a){a.w.hide().remove(),a.o&&a.o.remove()},modal:f.modal,jqm:g}),a.isFunction(f.afterOpen)&&f.afterOpen();try{a("#info_dialog").focus()}catch(o){}},bindEv:function(b,c){var d=this;a.isFunction(c.dataInit)&&c.dataInit.call(d,b,c),c.dataEvents&&a.each(c.dataEvents,function(){void 0!==this.data?a(b).bind(this.type,this.data,this.fn):a(b).bind(this.type,this.fn)})},createEl:function(b,c,d,e,f){function g(b,c,d){var e=["dataInit","dataEvents","dataUrl","buildSelect","sopt","searchhidden","defaultValue","attr","custom_element","custom_value","oper"];void 0!==d&&a.isArray(d)&&a.merge(e,d),a.each(c,function(c,d){-1===a.inArray(c,e)&&a(b).attr(c,d)}),c.hasOwnProperty("id")||a(b).attr("id",a.jgrid.randId())}var h="",i=this;switch(b){case"textarea":h=document.createElement("textarea"),e?c.cols||a(h).css({width:"98%"}):c.cols||(c.cols=20),c.rows||(c.rows=2),("&nbsp;"===d||"&#160;"===d||1===d.length&&160===d.charCodeAt(0))&&(d=""),h.value=d,g(h,c),a(h).attr({role:"textbox",multiline:"true"});break;case"checkbox":if(h=document.createElement("input"),h.type="checkbox",c.value){var j=c.value.split(":");d===j[0]&&(h.checked=!0,h.defaultChecked=!0),h.value=j[0],a(h).attr("offval",j[1])}else{var k=(d+"").toLowerCase();k.search(/(false|f|0|no|n|off|undefined)/i)<0&&""!==k?(h.checked=!0,h.defaultChecked=!0,h.value=d):h.value="on",a(h).attr("offval","off")}g(h,c,["value"]),a(h).attr("role","checkbox");break;case"select":h=document.createElement("select"),h.setAttribute("role","select");var l,m=[];if(c.multiple===!0?(l=!0,h.multiple="multiple",a(h).attr("aria-multiselectable","true")):l=!1,null!=c.dataUrl){var n=null,o=c.postData||f.postData;try{n=c.rowId}catch(p){}i.p&&i.p.idPrefix&&(n=a.jgrid.stripPref(i.p.idPrefix,n)),a.ajax(a.extend({url:a.isFunction(c.dataUrl)?c.dataUrl.call(i,n,d,String(c.name)):c.dataUrl,type:"GET",dataType:"html",data:a.isFunction(o)?o.call(i,n,d,String(c.name)):o,context:{elem:h,options:c,vl:d},success:function(b){var c,d,e=[],f=this.elem,d=this.vl,h=a.extend({},this.options),j=h.multiple===!0,k=h.cacheUrlData===!0,l="",m=a.isFunction(h.buildSelect)?h.buildSelect.call(i,b):b;"string"==typeof m&&(m=a(a.trim(m)).html()),m&&(a(f).append(m),g(f,h,o?["postData"]:void 0),void 0===h.size&&(h.size=j?3:1),j?(e=d.split(","),e=a.map(e,function(b){return a.trim(b)})):e[0]=a.trim(d),setTimeout(function(){if(a("option",f).each(function(b){c=a(this).text(),d=a(this).val()||c,k&&(l+=(0!==b?";":"")+d+":"+c),0===b&&f.multiple&&(this.selected=!1),a(this).attr("role","option"),(a.inArray(a.trim(c),e)>-1||a.inArray(a.trim(d),e)>-1)&&(this.selected="selected")}),k)if("edit"===h.oper)a(i).jqGrid("setColProp",h.name,{editoptions:{buildSelect:null,dataUrl:null,value:l}});else if("search"===h.oper)a(i).jqGrid("setColProp",h.name,{searchoptions:{dataUrl:null,value:l}});else if("filter"===h.oper&&a("#fbox_"+i.p.id)[0].p){var b,g=a("#fbox_"+i.p.id)[0].p.columns;a.each(g,function(){return b=this.index||this.name,h.name===b?(this.searchoptions.dataUrl=null,this.searchoptions.value=l,!1):void 0})}},0))}},f||{}))}else if(c.value){var q;void 0===c.size&&(c.size=l?3:1),l&&(m=d.split(","),m=a.map(m,function(b){return a.trim(b)})),"function"==typeof c.value&&(c.value=c.value());var r,s,t,u=void 0===c.separator?":":c.separator,v=void 0===c.delimiter?";":c.delimiter;if("string"==typeof c.value)for(r=c.value.split(v),q=0;q<r.length;q++)s=r[q].split(u),s.length>2&&(s[1]=a.map(s,function(a,b){return b>0?a:void 0}).join(u)),t=document.createElement("option"),t.setAttribute("role","option"),t.value=s[0],t.innerHTML=s[1],h.appendChild(t),l||a.trim(s[0])!==a.trim(d)&&a.trim(s[1])!==a.trim(d)||(t.selected="selected"),l&&(a.inArray(a.trim(s[1]),m)>-1||a.inArray(a.trim(s[0]),m)>-1)&&(t.selected="selected");else if("[object Array]"===Object.prototype.toString.call(c.value)){for(var w=c.value,q=0;q<w.length;q++)if(2===w[q].length){var x=w[q][0],y=w[q][1];t=document.createElement("option"),t.setAttribute("role","option"),t.value=x,t.innerHTML=y,h.appendChild(t),l||a.trim(x)!==a.trim(d)&&a.trim(y)!==a.trim(d)||(t.selected="selected"),l&&(a.inArray(a.trim(y),m)>-1||a.inArray(a.trim(x),m)>-1)&&(t.selected="selected")}}else if("object"==typeof c.value){var x,w=c.value;for(x in w)w.hasOwnProperty(x)&&(t=document.createElement("option"),t.setAttribute("role","option"),t.value=x,t.innerHTML=w[x],h.appendChild(t),l||a.trim(x)!==a.trim(d)&&a.trim(w[x])!==a.trim(d)||(t.selected="selected"),l&&(a.inArray(a.trim(w[x]),m)>-1||a.inArray(a.trim(x),m)>-1)&&(t.selected="selected"))}g(h,c,["value"])}break;case"text":case"password":case"button":var z;z="button"===b?"button":"textbox",h=document.createElement("input"),h.type=b,h.value=d,g(h,c),"button"!==b&&(e?c.size||a(h).css({width:"98%"}):c.size||(c.size=20)),a(h).attr("role",z);break;case"image":case"file":h=document.createElement("input"),h.type=b,g(h,c);break;case"custom":h=document.createElement("span");try{if(!a.isFunction(c.custom_element))throw"e1";var A=c.custom_element.call(i,d,c);if(!A)throw"e2";A=a(A).addClass("customelement").attr({id:c.id,name:c.name}),a(h).empty().append(A)}catch(p){var B=a.jgrid.getRegional(i,"errors"),C=a.jgrid.getRegional(i,"edit");"e1"===p&&a.jgrid.info_dialog(B.errcap,"function 'custom_element' "+C.msg.nodefined,C.bClose),"e2"===p?a.jgrid.info_dialog(B.errcap,"function 'custom_element' "+C.msg.novalue,C.bClose):a.jgrid.info_dialog(B.errcap,"string"==typeof p?p:p.message,C.bClose)}}return h},checkDate:function(a,b){var c,d=function(a){return a%4!==0||a%100===0&&a%400!==0?28:29},e={};if(a=a.toLowerCase(),c=-1!==a.indexOf("/")?"/":-1!==a.indexOf("-")?"-":-1!==a.indexOf(".")?".":"/",a=a.split(c),b=b.split(c),3!==b.length)return!1;var f,g,h=-1,i=-1,j=-1;for(g=0;g<a.length;g++){var k=isNaN(b[g])?0:parseInt(b[g],10);e[a[g]]=k,f=a[g],-1!==f.indexOf("y")&&(h=g),-1!==f.indexOf("m")&&(j=g),-1!==f.indexOf("d")&&(i=g)}f="y"===a[h]||"yyyy"===a[h]?4:"yy"===a[h]?2:-1;var l,m=[0,31,29,31,30,31,30,31,31,30,31,30,31];return-1===h?!1:(l=e[a[h]].toString(),2===f&&1===l.length&&(f=1),l.length!==f||0===e[a[h]]&&"00"!==b[h]?!1:-1===j?!1:(l=e[a[j]].toString(),l.length<1||e[a[j]]<1||e[a[j]]>12?!1:-1===i?!1:(l=e[a[i]].toString(),l.length<1||e[a[i]]<1||e[a[i]]>31||2===e[a[j]]&&e[a[i]]>d(e[a[h]])||e[a[i]]>m[e[a[j]]]?!1:!0)))},isEmpty:function(a){return a.match(/^\s+$/)||""===a?!0:!1},checkTime:function(b){var c,d=/^(\d{1,2}):(\d{2})([apAP][Mm])?$/;if(!a.jgrid.isEmpty(b)){if(c=b.match(d),!c)return!1;if(c[3]){if(c[1]<1||c[1]>12)return!1}else if(c[1]>23)return!1;if(c[2]>59)return!1}return!0},checkValues:function(b,c,d,e){var f,g,h,i,j,k,l=this,m=l.p.colModel,n=a.jgrid.getRegional(this,"edit.msg");if(void 0===d)if("string"==typeof c){for(g=0,j=m.length;j>g;g++)if(m[g].name===c){f=m[g].editrules,c=g,null!=m[g].formoptions&&(h=m[g].formoptions.label);break}}else c>=0&&(f=m[c].editrules);else f=d,h=void 0===e?"_":e;if(f){if(h||(h=null!=l.p.colNames?l.p.colNames[c]:m[c].label),f.required===!0&&a.jgrid.isEmpty(b))return[!1,h+": "+n.required,""];var o=f.required===!1?!1:!0;if(f.number===!0&&(o!==!1||!a.jgrid.isEmpty(b))&&isNaN(b))return[!1,h+": "+n.number,""];if(void 0!==f.minValue&&!isNaN(f.minValue)&&parseFloat(b)<parseFloat(f.minValue))return[!1,h+": "+n.minValue+" "+f.minValue,""];if(void 0!==f.maxValue&&!isNaN(f.maxValue)&&parseFloat(b)>parseFloat(f.maxValue))return[!1,h+": "+n.maxValue+" "+f.maxValue,""];var p;if(f.email===!0&&!(o===!1&&a.jgrid.isEmpty(b)||(p=/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,p.test(b))))return[!1,h+": "+n.email,""];if(f.integer===!0&&(o!==!1||!a.jgrid.isEmpty(b))){if(isNaN(b))return[!1,h+": "+n.integer,""];if(b%1!==0||-1!==b.indexOf("."))return[!1,h+": "+n.integer,""]}if(f.date===!0&&!(o===!1&&a.jgrid.isEmpty(b)||(m[c].formatoptions&&m[c].formatoptions.newformat?(i=m[c].formatoptions.newformat,k=a.jgrid.getRegional(l,"formatter.date.masks"),k&&k.hasOwnProperty(i)&&(i=k[i])):i=m[c].datefmt||"Y-m-d",a.jgrid.checkDate(i,b))))return[!1,h+": "+n.date+" - "+i,""];if(f.time===!0&&!(o===!1&&a.jgrid.isEmpty(b)||a.jgrid.checkTime(b)))return[!1,h+": "+n.date+" - hh:mm (am/pm)",""];if(f.url===!0&&!(o===!1&&a.jgrid.isEmpty(b)||(p=/^(((https?)|(ftp)):\/\/([\-\w]+\.)+\w{2,3}(\/[%\-\w]+(\.\w{2,})?)*(([\w\-\.\?\\\/+@&#;`~=%!]*)(\.\w{2,})?)*\/?)/i,p.test(b))))return[!1,h+": "+n.url,""];if(f.custom===!0&&(o!==!1||!a.jgrid.isEmpty(b))){if(a.isFunction(f.custom_func)){var q=f.custom_func.call(l,b,h,c);return a.isArray(q)?q:[!1,n.customarray,""]}return[!1,n.customfcheck,""]}}return[!0,"",""]}})});