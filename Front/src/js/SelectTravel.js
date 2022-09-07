import { React, useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import API from "../API";
import axios from "axios";

const SelectTravel = () => {
  const user = useLocation().state.id;
  const su = useLocation().state.su;
  const [myTravel, setTravellist] = useState([]);
  const [userList, setUserlist] = useState([]);
  const [newTravel, setNewTravel] = useState("");

  const onNewTravel = (event) => {
    setNewTravel(event.currentTarget.value);
  };

  useEffect (() => {
    getInfor();
  },[]);
  
  const getInfor = async() => {
    await axios.all([ API.get("/userlist"), API.get(+"travelList")])
    .then(
      axios.spread((res_user, res_travel) => {
        console.log("userlist : ",res_user.data);
        console.log("travellist : " ,res_travel.data);
        setUserlist(res_user.data);
        setTravellist(res_travel.data);
    }))
    .catch((error) => {
      console.log(error)
    })
  }

  return (
    <div>
      <h1
        style={{ display: "block", margin: "10px auto", textAlign: "center" }}
      >
        My Travel List
      </h1>
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
      <input onChange={onNewTravel} />
      <Link
        to={`/${user}/${newTravel}`}
        state={{ user: user, travel: newTravel }}
      >
        <button style={{ display: "block", margin: "20px auto" }}>
          Create Travel
        </button>
      </Link>
      {
        su ? userList.map((use,id) => (
            <div style={{ display: "block", margin: "auto", textAlign: "center" }}>
              {id},{use.name}, {use.email}, {use.account}
            </div>
        ))
        : null
      }
    </div>
  );
};

export default SelectTravel;
