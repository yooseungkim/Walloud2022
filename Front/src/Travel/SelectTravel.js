import { React, useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";
import CreateTravel from "./CreateTravel";
import DeleteTravel from "./DeleteTravel";

const SelectTravel = () => {
  const user_id = useLocation().state.id;
  const [myTravel, setTravellist] = useState([]);
  const [try_del, setDelete] = useState(false);


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

  const Logout = () => {
    document.location.href = '/login'
  }

  const try_Delete = () => {
    if(!try_del) {
      setDelete(true);
    } else {
      setDelete(false);
    }
  }

  return (
    <div>
      <h2>My Travel List</h2>
      <button onClick={Logout}>Logout</button>
      <button onClick={try_Delete}>Delete</button>
      <h3>Existing Travels</h3>
      
      {
        try_del ? <div>{myTravel.map((travel, id) => (
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
        ))}</div> : 
        <DeleteTravel myTravel = {myTravel}/>
      }
        <CreateTravel user_id={user_id} myTravel = {myTravel}  />

        </div>
  );
};

export default SelectTravel;
