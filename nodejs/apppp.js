var express=require('express');
var bodyparser = require('body-parser');
var mysql=require('mysql');
var dbconfig=require('./database');
var connection=require=mysql.createConnection(dbconfig);
var appp=express();

appp.set('port',process.env.PORT||3000);
appp.get('./forget_id',function(req,res){
    var user_name=req.body.name;
    var user_email=req.body.email;
    console.log(user_id+','+user_email);
    var ForgetId_Query="SELECT user_id FROM user WHERE user_email='"+user_email+"'";
connection.query(ForgetId_Query,function(err,findid){
        if(err){
            console.log(err);
            res.status(400).send({
                err:err
            });
            res.end();
        }
        if(findid){
            res.status(200).send(JSON.stringify(findid));
            console.log(findid);
            res.end();
        }
    });
    
});
appp.get('./forget_pw_search',function(req,res){
    var id=req.body.id;
    var conde=req.body.code;
    console.log(id+','+code);
    var f_pw_sQuery="SELECT id FROM user WHERE user_id='"+user_id+"'";
 connection.query(f_pw_sQuery,function(err,search){
        if(err){
             console.log(err);
            res.status(400).send({
                err:err
            });
            res.end();
        }
        if(search){
            res.status(200);
            res.end();
        }
        
    });
    
    
});
appp.post('./forget_pw',function(req,res){
    var user_pw=req.body.user_pw;
    var user_id=req.body.user_id;
    var f_pwQuery="UPDATE user SET '"+user_pw+"' WHERE id='"+user_id+"'";
     console.log(user_pw);
 connection.query(f_pwQuery,function(err,setpw){
       if(err){
            console.log(err);
            res.stutus(400).send({err:err});
            res.end;
        }
        if(setpw){
            res.stutus(200);
            res.end;
        }
 });
});
appp.listen(appp.get('port'), function () {
  console.log('Express server listening on port ' + appp.get('port'));
    
});
//app.get('./modify',function(req,res){
//    var user_id=
//});
