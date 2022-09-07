import axios from "axios";
import { React, useState } from "react";
import { Link } from "react-router-dom";

const Register = () => {  
  const [user_name, setname] = useState("");
  const [user_account,setaccout] = useState("");
  const [user_email, setEmail] = useState("");
  const [user_password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const onUserHandler = (event) => {
    setname(event.currentTarget.value);
  };

  const onAccountHandler = (event) => {
    setaccout(event.currentTarget.value);
  }

  const onEmailHandler = (event) => {
    setEmail(event.currentTarget.value);
  };

  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value);
  };

  const onConfirmPasswordHandler = (event) => {
    setConfirmPassword(event.currentTarget.value);
  };

  const CreateUser = async () => {
    await axios.post('/api/Register',{
      user_name : user_name,
      user_email : user_email,
      user_password : user_password,
      user_account : user_account
    })
    .then((response) =>{
      console.log(response);
      setname("");
      setEmail("");
      setaccout("");
      setPassword("");
      setConfirmPassword("");
    })
    .catch((error) => {
      console.log(error);
    })
  };

  const onSubmit = (event) => {
    if (user_email == null) {
      event.preventDefault();
      alert("Given ID already exists");
    } else if (user_password !== confirmPassword) {
      event.preventDefault();
      alert("Passwords do not match");
    } else if (user_password.length < 5) {
      event.preventDefault();
      alert("Password is too short");
    } else {
      event.preventDefault();
      console.log(user_name,user_account,user_email,user_password);
      CreateUser();
    }
  };

  // const onSubmit = (event) => {
  //   if (localStorage.getItem(user_email) !== null) {
  //     event.preventDefault();
  //     alert("Given ID already exists");
  //   } else if (user_password !== confirmPassword) {
  //     event.preventDefault();
  //     alert("Passwords do not match");
  //   } else if (user_password.length < 5) {
  //     event.preventDefault();
  //     alert("Password is too short");
  //   } else {
  //     alert("Succesfully Registered");
  //     localStorage.setItem(user_email, JSON.stringify(user_password));
  //   }
  // };

  return (
    <div>
      <h1
        style={{ margin: "10px auto", display: "block", textAlign: "center" }}
      >
        Register
      </h1>
      <form style={{ margin: "10px auto", textAlign: "center" }}>
        <div>
          <input
            placeholder="name"
            value={user_name}
            onChange={onUserHandler}
          />
        </div>
        <div>
          <input
            placeholder="account"
            value={user_account}
            onChange={onAccountHandler}
          />
        </div>
        <div>
          <input placeholder="email" value={user_email} onChange={onEmailHandler} />
        </div>
        <div>
          <input
            type = "password"
            placeholder="password"
            value={user_password}
            onChange={onPasswordHandler}
          />
        </div>
        <div>
          <input
            type = "password"
            placeholder="confirm password"
            value={confirmPassword}
            onChange={onConfirmPasswordHandler}
          />
        </div>
        <Link to = "/login">
          <div>
            <button onClick={onSubmit}>Submit</button>
          </div>
         </Link>
      </form>
    </div>
  );
};

export default Register;
