import { React, useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";
import CreateTravel from "../Create/CreateTravel";

const SelectTravel = () => {
  const user_id = useLocation().state.id;
  const [myTravel, setTravellist] = useState([]);


  useEffect(() => {
    getInfor();
  }, []);

  const getInfor = async() => {
    await axios.get (`/api/${user_id}`).then((response) => {
      setTravellist(response.data.travelList);
      console.log(response.data);
    }).catch((error) => {
      console.log(error);
    })
  }

  const Logout = (event) => {
    document.location.href = '/login'
  }

  const onDelete = () => {

  }

  return (
    <div>
      <h2>My Travel List</h2>
      <button onClick={Logout}>Logout</button>
      <h3>Existing Travels</h3>
      {myTravel.map((travel, id) => (
        <Link
          key={id}
          to={`/${user_id}/${travel}`}
          state={{ user_id: user_id, travel: travel }}
        >
          <h3 style={{ display: "block", margin: "auto", textAlign: "center" }}>
            {travel}
          </h3>
          <br />
        </Link>
        
      ))}
        <CreateTravel user_id={user_id} myTravel = {myTravel}  /> 
    </div>
  );
};

export default SelectTravel;
