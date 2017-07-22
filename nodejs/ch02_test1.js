var result=0;
console.time('test');
for(var i=1;i<=100;i++){
    result+=i;
}
console.timeEnd('test');
console.log('1부터 100까지 더한 결과물:%d',result);

var Person={name:'소녀시대',age:18};
console.dir(Person);
console.log('현재 실행한 파일 이름:%s',__filename);
console.log('현재 실행한 파일의 패스:%s',__dirname);