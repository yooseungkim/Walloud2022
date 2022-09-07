import axios from "axios";
import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Container = styled.div`
  margin-top: 50px;
  padding: 20px;
`;

const Input = styled.input`
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 40px;
  margin: 0 0 8px;
  padding: 5px 39px 5px 11px;
  border: solid 1px #dadada;
  background: #fff;
  box-sizing: border-box;
`;

const Button = styled.div`
  font-size: 18px;
  font-weight: 700;
  line-height: 49px;
  display: block;
  width: 100%;
  height: 49px;
  margin: 16px 0 7px;
  cursor: pointer;
  color: #fff;
  border: none;
  border-radius: 0;
  background-color: #03c75a;
  ${({ disabled }) =>
    disabled &&
    `
    background-color: #efefef;
  `}
`;

const LogIn = () => {
  const [su,setsu] = useState(true);
  const [isLoggin, setisLogin] = useState(false);
  const [input_id, setId] = useState("");
  const [input_password, setPassword] = useState("");
  const onIdHandler = (event) => {
    setId(event.currentTarget.value);
  };

  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value);
  };

  const try_LogIn = async(event) => {
    await axios.post("/api/login",{
      input_id : input_id,
      input_password : input_password
    }).then((response) => {
      if(response.data == null) {
        alert("Right");
      }
      else {
        alert("Login Success");
        console.log(response.data);
      }
    }).catch((error) => {
      event.preventDefault();
      console.log(error);
      alert("Loggin failed");
    })
  }

  const onSubmit = (event) => {
    if (localStorage.getItem(input_id) === null) {
      event.preventDefault();
      alert("Given ID does not exist");
    } else if (JSON.parse(localStorage.getItem(input_id)) !== input_password) {
      event.preventDefault();
      alert("Incorrect Password");
    } else {
      console.log(localStorage.getItem("id"));
      //localStorage.setItem("id", JSON.stringify(input_id));
      try_LogIn(event);
    }
  };

  return (
    /*
    <div>
      <h1
        style={{ margin: "10px auto", display: "block", textAlign: "center" }}
      >
        Log In
      </h1>
      <form>
        <div>
          <input name="id" placeholder="id" value={input_id} onChange={onIdHandler} />
        </div>
        <div>
          <input
            name="pw"
            placeholder="password"
            value={input_password}
            onChange={onPasswordHandler}
          />
        </div>
        <Link to={`/${input_id}/selectTravel`} state={{ id: input_id }}>
          <div>
            <button onClick={onSubmit}>Log In</button>
          </div>
        </Link>
        <Link to="/register">
          <div>
            <button>Register</button>
          </div>
        </Link>
      </form>
    </div>
    */
   <div>
    <h1
      style={{ margin: "10px auto", display: "block", textAlign: "center" }}
    >
      Log In
    </h1>
        <Container>
          <Input 
            name="id" 
            placeholder="id"
            value={input_id}
            onChange={onIdHandler}
          />
          <Input
            name="pw"
            type = "password"
            placeholder="password"
            value={input_password}
            onChange={onPasswordHandler}
          />
          <Link to= {`/${input_id}/${su}/selectTravel`} state={{ id: input_id, su : su }}>
            <Button onClick={onSubmit}>로그인</Button>
          </Link>
          <Link to="/register">
            <Button>Register</Button>
          </Link>
        </Container>
      </div>
  );
};

export default LogIn;
