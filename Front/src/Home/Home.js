import React, { useEffect, useState } from "react";
import NavigationBar from "../js/NavigationBar";
import DisplayUsers from "./DisplayUsers";
import Events from "./Events";
import { Link, useLocation, useParams } from "react-router-dom";
import plusSrc from "../img/plus.jpg";
import axios from "axios";

const Home = () => {
  const user = useLocation().state.user_id;
  const travel = useLocation().state.travel_id;

  const [userList, setuserList] = useState([]);
  const [eventList, seteventList] = useState([]);
  //받아오는 거를 eventList에서 eventlist로 수정

  ////////////////////////////////////

  useEffect(() => {
    console.log(user);
    getEventandUser();
  }, []);

  // parameter = user info,
  const getEventandUser = async () => {
    await axios.get(`/api/${user}/${travel}`)
    .then((response) => {
      console.log("Resonsed Data : ",response.data);
      seteventList(response.data.eventList);
      setuserList(response.data.personList);
    }).catch((error) => {
      console.log(error);
    })
  }

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
      <div style={{ display: "flex" }}>
        <div className="main">
          <div className="big-box">
            <h2>Events</h2>
            {/* <Link to="createEvent" key={(user, travel)}>
              <img className="plus-icon" src={plusSrc} alt="add event" />
            </Link> */}
            <div id="event-box" className="box">
              <div style={{ display: "flex" }}>
                <h4 className="description">Place</h4>
                <h4 className="description">Payer</h4>
                <h4 className="description">Price</h4>
                <h4 className="description">Date</h4>
              </div>
              <hr />
              {eventList.map((event) => (
                <Events event={event} key={event.id} />
              ))}
            </div>
            <Link to="createEvent" state={{userList : userList}}>
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
        </div>
        <div>
          <div className="right-align">
            <NavigationBar
              preferences={preferences}
              setPreferences={setPreferences}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
