var http=require('http');
var server=http.createSever();
var pot=3000;
server.listen(port,function){
    console.log('웹서버가 시작되었습니다.:%d',port);
});