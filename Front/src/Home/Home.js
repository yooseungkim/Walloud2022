import React, { useEffect, useState } from "react";
import NavigationBar from "../js/NavigationBar";
import DisplayUsers from "./DisplayUsers";
import Events from "./Events";
import { Link, useParams } from "react-router-dom";
import { users, eventlist } from "../js/Var";
import axios from "axios";

const Home = () => {
  const { user, travel, travelName } = useParams();
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
              <Events event={event} key={event.index} />
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
          <DisplayUsers
            users={userList}
            preferences={preferences}
            travelName={travel}
          />
        </div>
        <Link to="createUser" key={(user, travel)}>
          <button>Add User</button>
        </Link>
      </div>
      <div className="right-align">
        <h4>{user}</h4>
        <NavigationBar
          preferences={preferences}
          setPreferences={setPreferences}
        />
      </div>
    </div>
  );
};

export default Home;
