import React, {useState} from "react";
import { useLocation } from "react-router-dom";

const DeleteTravel = (props) => {
    const myTravel = props.myTravel;

    const [checkedItems, setCheckedItems] = useState([]);

    const checkHandler = (checked, elem) => {
        if(checked) {
            setCheckedItems(prev => [...prev, elem]);
            console.log(elem,"push", checkedItems);
        }
        else {
            setCheckedItems(checkedItems.filter((e) => e !== elem));
            console.log(elem,"pop", checkedItems);
        }
    }


    const handleAllCheck = (checked) => {
        if(checked) {
            const idArray = [];
            myTravel.forEach((e) => idArray.push(e));
            setCheckedItems(idArray);
            console.log("checked all",checkedItems);
        }
        else {
            setCheckedItems([]);
            console.log("unchecked all",checkedItems);
        }
    }

    return (
        <div className = "contStyle">
            <input type='checkbox'
              onChange={(e) => handleAllCheck(e.target.checked)}
              checked={checkedItems.length === myTravel.length ? true : false} 
            >
            </input>
            {myTravel.map((travel, travel_id) => (
                <label key= {travel_id} className = "innerBox">
                    <input type = "checkbox"
                    value = {travel_id}
                    onChange={(e) => checkHandler(e.target.checked, travel)}
                    checked = {checkedItems.includes(travel) ? true : false }
                    />
                    <h3>{travel}</h3>
                </label>
            ))}
        </div>
    )
}

export default DeleteTravel;