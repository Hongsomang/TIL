var fs=require('fs');
fs.writeFile('./output.txt','name:hongosmang \n age:18 \n phone:010-1010',function(err){
    if(err){
       console.log('Error:'+err);
       }
    console.log('output.txt파일에 데이터 쓰기 완료');

})