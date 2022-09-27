import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const CreateUser = () => {
  const { travel, travelName, user } = useParams();
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const onChange = (event) => {
    setEmail(event.currentTarget.value);
  }

  const onClick = async() => {
    await axios.post(`/api/${user}/${travel}/createUser`,{
      user_email : email
    }).then((res) => {
      switch(res.data) {
        case -1:
          alert("check the email is correct");
          break;
        case -2:
          alert("Network Error");
          break;
        case 200:
          alert("Successfully Created");
          navigate(`/${user}/${travel}/${travelName}`);
          break;
        default:
          throw res;
      }
    }).catch((error) => {
      console.log(error);
    })
  }

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2>Add User</h2>
      <label htmlFor="email">Email</label>
      <input id="email" type="email" onChange={onChange} />
      <Link to={`/${user}/${travel}/${travelName}`}>
        <button onClick={onClick}>Submit</button>
      </Link>
    </div>
  );
};

export default CreateUser;
