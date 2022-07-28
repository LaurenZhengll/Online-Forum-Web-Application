var Af = {}

Af.rest = function(url, data, success, error){
	if(data == null) data = {}; // 若data为null,则发送一个默认JSON{}
	
	$.ajax({
		method: 'POST' ,  
		url: url ,
		contentType: 'application/json' ,  // HTTP请求中的 Content-Type
		data : JSON.stringify(data) ,      // HTTP请求正文 
		processData : false,               // 
		dataType: 'json',                  // HTTP应答按JSON处理
		success : success ,
		error : error ,
	})	
}

Af.rest2 = function(arg){
	if(arg.data == null) arg.data = {}; // 若data为null,则发送一个默认JSON{}
	
	$.ajax({
		method: 'POST' ,  
		url: arg.url ,
		contentType: 'application/json' ,  // HTTP请求中的 Content-Type
		data : JSON.stringify(arg.data) ,      // HTTP请求正文 
		processData : false,               // 
		dataType: 'json',                  // HTTP应答按JSON处理
		success : arg.success ,
		error : arg.error ,
	})	
}



/* 获取查询参数 */
Af.getUrlParam = function (name, defValue) 
{
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) 
    	return unescape(r[2]);
    else
    	return defValue;
}

// file , 必需
// url, 必需
// 事件回调 (可选) : complete , error, abort, progress
Af.upload = function( args ){
	if(args.file == null) throw 'Af.upload: no file parameter!';
	if(args.url == null)  throw 'Af.upload: no url parameter!';
	
	var form = new FormData();
	form.append('file1', args.file); 
	// if(args.extra != null){ // 附加表单参数
	// 	for( var key in args.extra)	form.append(key, args.extra[key]);
	// }	
	
	var httpRequest = new XMLHttpRequest();
	
	// 指定一些回调方法
	var ctx = args;
	ctx.httpRequest = httpRequest;
	httpRequest.ctx = httpRequest.upload.ctx = ctx;
	httpRequest.addEventListener("load", Af.evt_upload_complete, false);
	httpRequest.addEventListener("error", Af.evt_upload_error, false);
	httpRequest.addEventListener("abort", Af.evt_upload_abort, false);				
	httpRequest.upload.addEventListener("progress", Af.evt_upload_progress, false);
				
	// 上传到后台			
	httpRequest.open("POST", args.url );
	httpRequest.send(form);			
	
}

Af.evt_upload_complete = function(evt)
{
	var ctx = this.ctx;  // this指向formRequest
	if(ctx.complete){
		ctx.complete( {
			responseText : evt.target.responseText
		} );
	}
};	
	 
Af.evt_upload_error = function (evt) 
{		
	var ctx = this.ctx;  // this指向formRequest
	if(ctx.error){
		ctx.error();
	}	
};

Af.evt_upload_abort  = function(evt) 
{
	var ctx = this.ctx;  // this指向formRequest
	if(ctx.abort){
		ctx.abort(  );
	}	
};

Af.evt_upload_progress = function(evt)
{
	var ctx = this.ctx;  // this指向formRequest.upload
	if(ctx.progress){
		ctx.progress( {
		  	loaded: evt.loaded ,
			total: evt.total ,
			percent : Math.round(evt.loaded * 100 / evt.total) ,
		} );
	}
};	




