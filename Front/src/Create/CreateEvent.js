import axios from "axios";
import React, { useState } from "react";
import { useLocation, Link, useParams, useNavigate } from "react-router-dom";
import personSrc from "../img/person.png";

function CreateEvent() {
  const users = useLocation().state.userList;
  const { user, travel, travelName } = useParams();
  const payer = [];
  const participants = [...users];
  const navigate = useNavigate();
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

  // function CreateUser({ each }) {
  //   const [participate, setParticipate] = useState("participate");
  //   const [nameColor, setNameColor] = useState("green");
  //   const onClickIcon = () => {
  //     if (participate === "participate") {
  //       setParticipate("no");
  //       setNameColor("black");
  //       participants.pop(each);
  //     } else if (participate === "no") {
  //       setParticipate("payer");
  //       setNameColor("blue");
  //       participants.push(each);
  //       payer.push(each.id);
  //     } else if (participate === "payer") {
  //       setParticipate("participate");
  //       setNameColor("green");
  //       payer.pop(each);
  //     }
  //     console.log(payer);
  //     console.log(participants);
  //   };
  //   return (
  //     <div className="user" onClick={onClickIcon}>
  //       <img className="user-icon" src={personSrc} alt="profile" />
  //       <br />
  //       <h4 style={{ color: nameColor }}>{each.name}</h4>
  //     </div>
  //   );
  // }

  const onSubmit = (e) => {
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
      console.log("payer : ", payer);
      console.log("participant : ", participants);

      event_info();
    } else if (payer.length > 1) {
      alert("결제자는 한 명이어야 합니다\nError: Too Many Payers");
    } else if (payer.length === 0) {
      alert("결제자는 한 명이어야 합니다\nError: No Payer");
      e.preventDefault();
    }
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
      payer_person_id: payer[0],
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
        <select id="participants">
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
            <input className="checkbox" type="checkbox" id={user.id} />
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

// import { React, useState, useEffect } from "react";
// import { Link, useLocation } from "react-router-dom";
// import axios from "axios";
// import CreateTravel from "./CreateTravel";

// const SelectTravel = () => {
//   const user = useLocation().state.id;
//   const [myTravel, setTravellist] = useState([]);

//   const [try_del, setDelete] = useState(false);
//   const [checkAllButton, setCheckAllButton] = useState("전체 선택");
//   const [checkedItems, setCheckedItems] = useState([]);
//   const [checkedTravel, setCheckedTravel] = useState([]);

//   useEffect(() => {
//     getInfor();
//   }, []);

//   const getInfor = async () => {
//     await axios
//       .get(`/api/${user}`)
//       .then((response) => {
//         setTravellist(response.data.travelList);
//         console.log(response.data);
//       })
//       .catch((error) => {
//         console.log(error);
//       });
//   };

//   const Logout = () => {
//     document.location.href = "/login";
//   };

//   // Delete

//   const try_Delete = () => {
//     if (try_del) {
//       if (checkedItems.length !== 0) {
//         if (
//           window.confirm(
//             checkedTravel + " 이 선택되었습니다.\n 삭제하시겠습니까?"
//           )
//         ) {
//           checkedItems.map((travel_id, idx) => {
//             axios
//               .delete(`/api/${user}/${travel_id}/deleteTravel`)
//               .catch((error) => {
//                 console.log(error);
//               });
//           });
//           alert("삭제되었습니다.");
//           window.location.reload();
//         } else {
//           setCheckedItems([]);
//           setCheckedTravel([]);
//           console.log("unchecked all", checkedItems);
//           alert("취소되었습니다.");
//         }
//       }
//     }
//     setDelete(!try_del);
//   };

//   const checkHandler = (checked, elem) => {
//     if (checked) {
//       setCheckedItems((prev) => [...prev, elem.id]);
//       setCheckedTravel((prev) => [...prev, elem.name]);
//       console.log(elem, "push", checkedItems);
//     } else {
//       setCheckedItems(checkedItems.filter((e) => e !== elem.id));
//       setCheckedTravel(checkedTravel.filter((e) => e !== elem.name));
//       console.log(elem, "pop", checkedItems);
//     }
//   };

//   const handleAllCheck = (checked) => {
//     if (checked) {
//       setCheckAllButton("전체 선택");
//       const idArray = [];
//       const travelArray = [];
//       myTravel.forEach((e) => {
//         idArray.push(e.id);
//         travelArray.push(e.name);
//       });
//       setCheckedItems(idArray);
//       setCheckedTravel(travelArray);
//       console.log("checked all", checkedItems);
//     } else {
//       setCheckAllButton("전체 선택");
//       setCheckedItems([]);
//       setCheckedTravel([]);
//       console.log("unchecked all", checkedItems);
//     }
//   };

//   return (
//     <div>
//       <h1>Divide by N</h1>
//       <h2>My Travel List</h2>
//       <button onClick={Logout}>Log Out</button>
//       <button onClick={try_Delete}>Delete</button>
//       <h3>Existing Travels</h3>

//       {!try_del ? (
//         <div>
//           {myTravel.map((travel, idx) => (
//             <Link
//               key={idx}
//               to={`/${user}/${travel.id}/${travel.name}`}
//               state={{
//                 user: user,
//                 travel: travel.id,
//                 travelName: travel.name,
//               }}
//             >
//               <h4
//                 className="link-text"
//                 style={{
//                   display: "block",
//                   margin: "auto",
//                   textAlign: "center",
//                 }}
//               >
//                 {travel.name}
//               </h4>
//               <br />
//             </Link>
//           ))}
//         </div>
//       ) : (
//         <div className="contStyle">
//           <div style={{ alignItems: "center" }}>
//             {myTravel.map((travel, idx) => (
//               <div
//                 style={{
//                   display: "inline-block",
//                   minWidth: "33%",
//                   alignItems: "center",
//                   marginBottom: "3%",
//                 }}
//               >
//                 <input
//                   id={travel}
//                   style={{ display: "inline-block", margin: "0" }}
//                   className="checkbox"
//                   type="checkbox"
//                   value={travel.id}
//                   onChange={(e) => checkHandler(e.target.checked, travel)}
//                   checked={checkedItems.includes(travel.id) ? true : false}
//                 />
//                 <label htmlFor={travel} className="checkbox-text">
//                   {travel.name}
//                 </label>
//               </div>
//             ))}
//           </div>
//           <div
//             style={{
//               display: "inline-block",
//               marginBottom: "3%",
//             }}
//           >
//             <input
//               id="checkAll"
//               type="checkbox"
//               className="checkbox"
//               onChange={(e) => handleAllCheck(e.target.checked)}
//               checked={checkedItems.length === myTravel.length ? true : false}
//             ></input>
//             <label htmlFor="checkAll" className="checkbox-text">
//               {checkAllButton}
//             </label>
//           </div>
//         </div>
//       )}
//       <CreateTravel user={user} myTravel={myTravel} />
//     </div>
//   );
// };

// export default SelectTravel;
