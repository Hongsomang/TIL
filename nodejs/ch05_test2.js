var http=require('http');
var server=http.createServer();
var port=3000;
server.listen(port,function(){
    console.log('웹서버가 실행됩니다.',port);
});
server.on('connection',function(socket){
    var addr=socket.address();
    console.log('클라이언트가 접속했습니다.',addr.address,addr.port);
});
server.on('request',function(req,res){
    console.log('클라이언트 요청이 들어왔습니다.');
    console.dir(req);
});
server.on('close',function(){
    console.log('서비스가 종료됩니다.');
});
