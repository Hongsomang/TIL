var net=require('net');
var client=net.connect({port:8107,host:'localhost'},function(){
    console.log('서버에 접속완료');
    client.write('녕');  
});
client.on('data',function(data){
    console.log('서버가 보낸 메세지 %s',data.toString());
    client.end();
});
client.on('end',function(){
    console.log('종료되었습니다.');
});