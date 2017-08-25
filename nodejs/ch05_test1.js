var http=require('http');
var server=http.createServer();
var host='192.168.1.101';
var port=3000;
server.listen(port,host,'50000',function(){
    console.log('웹서버가 실행됩니다.:%s,%d',host,port);
    
});