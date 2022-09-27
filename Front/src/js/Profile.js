import axios from "axios";
import React, {  useState, useEffect } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import { eventlist, users } from "./Var";

const Profile = () => {
  const { user, travel, travelName, username } = useParams();
  const personid = useLocation().state.personid;
  
  const [profile, setProfile] = useState({});
  useEffect(() => {
    getProfile();
  },[]);

  const getProfile = async() => {
    await axios.get(`/api/${user}/${travel}/${personid}`
    ).then((res) => {
      console.log(res.data);
      setProfile(res.data);
    }).catch((error) => {
      console.log(error);
    })
  }

  const displayEvents = () => {
    const events = profile.eventList;
    console.log("eventList : ",events);

    return (
      <div style={{ display: "flex" }}>
        {events.map((event, index) => (
          <div>
            <Link
              to={`/${user}/${travel}/${travelName}/${event}`}
              state={{ event: event }}
            >
              <h3 className="link-text" key={index}>
                {event}
              </h3>
            </Link>
          </div>
        ))}
      </div>
    );
  };

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2>{profile.userName}</h2>
      <h3>Account : {profile.userAccount}</h3>
      <h3 style={{ color: profile.type === "Depositors" ? "red" : "blue" }}>
        Spent: {profile.sumGet}₩
      </h3>
      <h3 id="headers">Participated Events: </h3>
      {/* <div style={{ display: "flex" }}>
        {profile.eventList.map((event, index) => (
          <div>
            <Link
              to={`/${user}/${travel}/${travelName}/${event}`}
              state={{ event: event }}
            >
              <h3 className="link-text" key={index}>
                {event}
              </h3>
            </Link>
          </div>
        ))}
      </div> */}
      <br />
    </div>
  );
};

export default Profile;
