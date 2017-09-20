var express=require('express');
var app=express();//앱플리케이션 객체 리턴
app.locals.pretty=true; //html줄바꿈
app.set('views','./views_file');
app.set('view engine','jade');
//라우팅
app.get('/topic/news',function(req,res){
   // res.send('hi');
    res.render('new');
    
})
app.listen(3000,function(){
    console.log('Connected,3000');
    

})