import axios from "axios";
import React, { useState } from "react";
import { useLocation, Link, useParams, useNavigate } from "react-router-dom";
import personSrc from "../img/person.png";

function CreateEvent() {
  const users = useLocation().state.userList;
  const { user, travel, travelName } = useParams();
  const [payer, setPayer] = useState(users[0].id);
  const [participants, setparticipants] = useState([]);
  const navigate = useNavigate();

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

  const checkHandler = (checked, elem) => {
    if (checked) {
      setparticipants((prev) => [...prev, elem]);
      console.log(elem, "push", participants);
    } else {
      setparticipants(participants.filter((e) => e !== elem));
    }
  };

  const onSubmit = (e) => {
    if (place === "") {
      alert("Set place\n");
    } else if (price === "") {
      alert("Set price\n");
    } else if (date === "") {
      alert("Set date\n");
    } else {
      console.log(participants);
      console.log("payer : ", payer);
      console.log("participant : ", participants);
      event_info();
    }
  };

  const setSelectedPayer = (e) => {
    setPayer(e.target.value);
  };

  const event_info = async () => {
    let temp_list = [...participants].map(function (row) {
      delete row.name;
      delete row.difference;

      return row;
    });

    console.log("event json", {
      parti_list: temp_list,
      event_name: place,
      event_date: date,
      price: price,
      payer_person_id: payer,
    });

    await axios
      .post(`/api/${user}/${travel}/CreateEvent`, {
        parti_list: temp_list,
        event_name: place,
        event_date: date,
        price: price,
        payer_person_id: payer[0],
      })
      .then((res) => {
        switch (res.data) {
          case -1:
            alert("fail to create event");
            break;
          case -2:
            alert("fail to create participate");
            break;
          case 200:
            alert("Success");
            navigate(`/${user}/${travel}/${travelName}`);
            break;
          default:
            throw "Network Error";
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        // state={{ user_id: userid, travel_id: travelid, travelName: travelname }}
      >
        <h1 className="home">{travelName}</h1>
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
          autoFocus
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
      {/* <label htmlFor="create-event">Participants</label> */}
      {/* <div className="box" id="create-event">
        {users.map((user) => (
          <CreateUser each={user} key={user.id} />
        ))}
      </div> */}
      <div>
        <label htmlFor="participants">Payer</label>
        <select id="participants" onChange={setSelectedPayer}>
          {users.map((user, id) => (
            <option value={user.id} key={id}>
              {user.name}
            </option>
          ))}
        </select>
      </div>
      <label>Participants</label>
      <br />
      <div
        style={{
          width: "400px",
          marginTop: "2%",
          verticalAlign: "center",
        }}
      >
        {users.map((user, id) => (
          <div
            style={{
              display: "inline-block",
              minWidth: "33%",
              alignItems: "center",
              marginBottom: "3%",
            }}
            key={id}
          >
            <input
              className="checkbox"
              defaultChecked
              type="checkbox"
              id={user.id}
              onChange={(e) => checkHandler(e.target.checked, user)}
            />
            <label className="checkbox-text" htmlFor={user.id}>
              {user.name}
            </label>
          </div>
        ))}
      </div>
      <button onClick={onSubmit}>이벤트 추가</button>
    </div>
  );
}

export { CreateEvent };
