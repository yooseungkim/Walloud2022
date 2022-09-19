import { React, useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";
import CreateTravel from "./CreateTravel";

const SelectTravel = () => {
  const user_id = useLocation().state.id;
  const [myTravel, setTravellist] = useState([]);
  const [try_del, setDelete] = useState(false);
  const [checkallbutton, setcheckallbutton] = useState("전체 선택");
  const [checkedItems, setCheckedItems] = useState([]);
  const [checekdTravels, setCheckedTravel] = useState([]);

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

  // Delete

  const try_Delete = () => {
    if(try_del) {
      if(checkedItems.length !== 0) {
        if(window.confirm(checekdTravels+" 이 선택되었습니다.\n 삭제하시겠습니까?")){
          checkedItems.map((travel_id, idx) => {
            axios.delete(`/api/${user_id}/${travel_id}/deleteTravel`).catch((error) => {
              console.log(error);
            })
          })
          alert("삭제되었습니다.");
          window.location.reload();
        }
        else {
          alert("취소되었습니다.");
        }
      }
    }
    setDelete(!try_del)
  }

  const checkHandler = (checked, elem) => {
    if(checked) {
        setCheckedItems(prev => [...prev, elem.id]);
        setCheckedTravel(prev => [...prev,elem.name])
        console.log(elem,"push", checkedItems);
    }
    else {
        setCheckedItems(checkedItems.filter((e) => e !== elem.id));
        setCheckedTravel(checekdTravels.filter((e) => e !== elem.name));
        console.log(elem,"pop", checkedItems);
    }
  }

  const handleAllCheck = (checked) => {
    if(checked) {
        setcheckallbutton("전체 선택")
        const idArray = [];
        const travelArray = [];
        myTravel.forEach((e) => {idArray.push(e.id);
          travelArray.push(e.name);
        });
        setCheckedItems(idArray);
        setCheckedTravel(travelArray);
        console.log("checked all",checkedItems);
    }
    else {
        setcheckallbutton("전체 선택")
        setCheckedItems([]);
        setCheckedTravel([]);
        console.log("unchecked all",checkedItems);
    }
  }

  return (
    <div>
      <h2>My Travel List</h2>
      <button onClick={Logout}>Logout</button>
      <button onClick={try_Delete}>Delete</button>
      <h3>Existing Travels</h3>
      
      {
        !try_del ? <div>{myTravel.map((travel, idx) => (
          <Link
            key={idx}
            to={`/${user_id}/${travel.id}/${travel.name}`}
            state={{ user_id: user_id, travel_id : travel.id }}
          >
            <h3 style={{ display: "block", margin: "auto", textAlign: "center" }}>
              {travel.name}
            </h3>
            <br />
          </Link>
        ))}</div> : 
        <div className = "contStyle">
            <input type='checkbox'
              onChange={(e) => handleAllCheck(e.target.checked)}
              checked={checkedItems.length === myTravel.length ? true : false} 
            ></input>{checkallbutton}
            {myTravel.map((travel, idx) => (
                <label key= {idx} className = "innerBox">
                    <input type = "checkbox"
                    value = {travel.id}
                    onChange={(e) => checkHandler(e.target.checked, travel)}
                    checked = {checkedItems.includes(travel.id) ? true : false }
                    />
                    <Link
                    key={idx}
                    to={`/${user_id}/${travel.id}/${travel.name}`}
                    state={{ user_id: user_id, travel_id : travel.id }}
                    >
                    <h3>{travel.name}</h3>
                    </Link>
                </label>
            ))}
        </div>
      }
        <CreateTravel user_id={user_id} myTravel = {myTravel}  />

        </div>
  );
};

export default SelectTravel
