var express=require('express'),
    http=require('http');
var app=express();
app.use(function(req,res,next){
    console.log('첫 번째 미들웨어에서 요청을 처리함');
    res.writable('200',{'Content-Type':'text/html;charset=utf8'});
    res.end('<h1>hello<h1>');
    
});
http.createServer(app).listen(3000,function(){
    console.log('express실행');
});