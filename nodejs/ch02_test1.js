var result=0;
console.time('test');
for(var i=1;i<=100;i++){
    result+=i;
}
console.timeEnd('test');
console.log('1부터 100까지 더한 결과물:%d',result);
