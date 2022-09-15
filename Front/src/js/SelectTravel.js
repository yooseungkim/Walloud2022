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

  useEffect(() => {
    getInfor();
  }, []);

  const getInfor = async() => {
    await axios.get (`/api/${user}`).then((response) => {
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
      <h2>My Travel List</h2>
      <button onClick={Logout}>Logout</button>
      <h3>Existing Travels</h3>
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
      <h4>Create New Travel</h4>
      <input onChange={onNewTravel} />
      <Link
        to={`/${user}/${newTravel}`}
        state={{ user: user, travel: newTravel }}
      >
        <button type="submit" style={{ display: "block", margin: "20px auto" }}>
          Create Travel
        </button>
      </Link>
      {/* {
        su ? userList.map((use,id) => (
            <div style={{ display: "block", margin: "auto", textAlign: "center" }}>
              {id},{use.name}, {use.email}, {use.account}
            </div>
        ))
        : null
      } */}
    </div>
  );
};

export default SelectTravel;
