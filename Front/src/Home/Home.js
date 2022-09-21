import React, { useEffect, useState } from "react";
import NavigationBar from "../js/NavigationBar";
import DisplayUsers from "./DisplayUsers";
import Events from "./Events";
import { Link, useLocation, useParams } from "react-router-dom";
import { users, eventlist } from "../js/Var";
import API from "../API";
import plusSrc from "../img/plus.jpg";
import axios from "axios";

const Home = () => {
  const { user, travel, travelName } = useParams();
  console.log("Home");
  console.log(useLocation().state);
  console.log(user);
  console.log(travel);
  console.log(travelName);
  const [userList, setuserList] = useState(users);
  const [eventList, seteventList] = useState(eventlist);
  //받아오는 거를 eventList에서 eventlist로 수정

  ////////////////////////////////////

  useEffect(() => {
    console.log(user);
  }, []);

  // parameter = user info,
  const getEvent_User = async () => {
    await axios.all([axios.get(`/`), axios.get(`/`)]).then(
      axios.spread((res_users, res_travel) => {
        console.log(res_users.data);
      })
    );
  };

  /////////////////////////////////

  let initialPreferences = {
    displayIcon: true,
    displayMoney: true,
  };

  let [preferences, setPreferences] = useState(initialPreferences);

  localStorage.getItem("preferences") === null
    ? localStorage.setItem("preferences", JSON.stringify(initialPreferences))
    : (preferences = JSON.parse(localStorage.getItem("preferences")));

  const styleheader = {
    backgroundColor: "#DAF1D8",
    display: "inline-block",
    borderRadius: "15px",
    width: "95%",
    height: "10%",
    margin: "2.5%",
  };

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <h1>Divide by N</h1>
      </Link>
      <div className="big-box">
        <h2>Events</h2>
        {/* <Link to="createEvent" key={(user, travel)}>
              <img className="plus-icon" src={plusSrc} alt="add event" />
            </Link> */}
        <div id="event-box" className="box">
          <div style={{ display: "flex" }}>
            <h4 className="description">Event</h4>
            <h4 className="description">Name</h4>
            <h4 className="description">Price</h4>
            <h4 className="description">Date</h4>
          </div>
          <hr />
          {eventList.map((event) => (
            <div>
              <Events
                event={event}
                key={event.index}
                state={{
                  event: event,
                  user: user,
                  travel: travel,
                  travelName: travelName,
                }}
              />
              <hr />
            </div>
          ))}
        </div>
        <Link to="createEvent" key={(user, travel)}>
          <button>Add Event</button>
        </Link>
      </div>
      <div className="big-box">
        <h2>Participants</h2>
        {/* <Link to="createUser">
              <img className="plus-icon" src={plusSrc} alt="plus-icon" />
            </Link> */}
        <div id="user-box" className="box">
          <DisplayUsers users={userList} preferences={preferences} />
        </div>
        <Link to="createUser" key={(user, travel)}>
          <button>Add User</button>
        </Link>
      </div>

      <div className="right-align">
        <NavigationBar
          preferences={preferences}
          setPreferences={setPreferences}
        />
      </div>
    </div>
  );
};

export default Home;
