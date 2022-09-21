import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import personSrc from "../img/person.png";

function CreateEvent() {
  const users = useLocation().state.userList
  const payer = [];
  const participants = [...users];
  // const user = useLocation().state.user;
  // const travel = useLocation().state.travel;
  // const username = test["username"];
  // const travelName = test["travel"];

  const [inputs, setInputs] = useState({
    place: "",
    price: "",
    date: "",
  });

  const { place, price, date } = inputs;

  const onChange = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };

  function CreateUser({ user }) {
    const [participate, setParticipate] = useState("participate");
    const [nameColor, setNameColor] = useState("green");
    const onClickIcon = () => {
      if (participate === "participate") {
        setParticipate("no");
        setNameColor("black");
        participants.pop(each.name);
      } else if (participate === "no") {
        setParticipate("payer");
        setNameColor("blue");
        participants.push(each.name);
        payer.push(each.name);
      } else if (participate === "payer") {
        setParticipate("participate");
        setNameColor("green");
        payer.pop(each.name);
      }
      console.log(payer);
      console.log(participants);
    };

    return (
      <div className="user" onClick={onClickIcon}>
        <img className="user-icon" src={personSrc} alt="profile" />
        <br />
        <h4 style={{ color: nameColor }}>{each.name}</h4>
      </div>
    );
  }

  const onClickSubmit = (e) => {
    if (payer.length === 1) {
      console.log("okay");
      // const newEvent = {
      //   index: eventList.length + 1,
      //   place: document.querySelector("#place").value,
      //   name: payer[0],
      //   price: document.querySelector("#price").value,
      //   date: document.querySelector("#date").value,
      //   participants: participants,
      // };
      // console.log(participants);
      // eventList.push(newEvent);
    } else if (payer.length > 1) {
      alert("결제자는 한 명이어야 합니다\nError: Too Many Payers");
    } else if (payer.length === 0) {
      alert("결제자는 한 명이어야 합니다\nError: No Payer");
      e.preventDefault();
    }
  };

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        // state={{ user_id: userid, travel_id: travelid, travelName: travelname }}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2>Create Event</h2>
      <div>
        <label htmlFor="place">Place</label>
        <input
          type="text"
          id="place"
          name="place"
          onChange={onChange}
          value={place}
          size="5"
        />
        <label htmlFor="price">Price</label>
        <input
          type="text"
          id="price"
          name="price"
          onChange={onChange}
          value={price}
          size="5"
        />
        <label htmlFor="date">Date</label>
        <input
          type="date"
          id="date"
          name="date"
          onChange={onChange}
          value={date}
          size="5"
        />
      </div>
      <label htmlFor="create-event">Participants</label>
      <div className="box" id="create-event">
        {users.map((user) => (
          <CreateUser user={user} key={user.id} />
        ))}
      </div>
      <button onClick={onClickSubmit}>이벤트 추가</button>
    </div>
  );
}

export { CreateEvent};
