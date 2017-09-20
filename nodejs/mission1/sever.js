var net=require('net');
var server=net.createServer(function(client){
    console.log('클라이언트 실행');
    
    client.on('data',function(data){
    console.log('클라이언트가 보낸 메세지:%s'+data.toString());
    client.write(data.toString());

    });

    client.on('end',function(){
    console.log('클라이언트가 종료 되었습니다.');
    });
});

server.listen(8107,function(){
    console.log('서버가 시작되었습니다.');
});