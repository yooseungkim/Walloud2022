import { React, useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import API from "../API";
import axios from "axios";
import CreateTravel from "./CreateTravel"


const SelectTravel = () => {
  const user = useLocation().state.id;
  const isLoggin = useLocation().state.isLoggin;
  const [myTravel, setTravellist] = useState([]);
  const [newTravel, setNewTravel] = useState("");

  useEffect (() => {
    getInfor();
  },[]);
  
  const getInfor = async() => {
    await axios.get ("/api/Travel",{
      user : user
    }).then((response) => {
      setTravellist(response.data);
      console.log(response.data);
    }).catch((error) => {
      console.log(error);
    })
  }

  const Logout = (event) => {
    document.location.href = '/login'
  }

  return (
    <div>
      <h1
        style={{ display: "block", margin: "10px auto", textAlign: "center" }}
      >
        My Travel List
      </h1>
      <button onClick={Logout}>Logout</button>
      {myTravel.map((travel, id) => (
        <Link
          key={id}
          to={`/${user}/${travel}`}
          state={{ user: user, travel: travel }}
        >
          <h3 style={{ display: "block", margin: "auto", textAlign: "center" }}>
            {travel}
          </h3>
          <br />
        </Link>
      ))}
      <div>
        <CreateTravel user={user}/>
      </div>
    </div>
  );
};

export default SelectTravel;
