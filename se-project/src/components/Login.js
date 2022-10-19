import React from 'react';
import './../App.css';
import logo from './../images/logo.png';

//this is not working yet
const LoginUser =(e)=>{
    e.preventDefault();
    fetch("http://localhost:8080/login", {
      method:"POST",
      headers:{
        "Content-Type" : "application/json"
      },
      //body: JSON.stringify(book)
    })
    .then(res=>{
        console.log(1,res);
        if(res.status === 201){
          return res.json();
        }else{
          return null;
        }
      });

  }

const Login = () => {
  return (
    //better way of writing flex items, need to check
    /*<div className="Container">
      <div class="fixed"><input type="text" 
      value={"Username/Email"}  />
      <br/>
      <input type="text" 
      value={"Password"}  />
      <br/>
      <input type="submit" value="Submit" /></div>
  <div class="flex-item"><inputtext>
      Test text...........
      </inputtext></div>
    </div>*/
<div>
    <div style={{width: '100%', overflow: 'hidden'}}>
    <div style={{width: '600px', float: 'left'}}> 
    <br/>
    <br/>
    <input type="text" 
      value={"Username/Email"}  />
      <br/>
      <br/>
      <input type="text" 
      value={"Password"}  />
      <br/>
      <br/>
      <input type="submit" value="Login" onClick={LoginUser}/>
      <br/>
      <br/>
      <inputtext>
      Not an existing user?
      <input type="submit" value="Create Account" />
      </inputtext>
      </div>
    <div style={{marginLeft: '620px'}}> 
    <div>
    <img src={logo} style={{width:'40' ,height:'40'}} alt="logo" />
    </div>
    <inputtext>
      Test text...........
      </inputtext>
       </div>
</div>
</div>

  )
}

export default Login
