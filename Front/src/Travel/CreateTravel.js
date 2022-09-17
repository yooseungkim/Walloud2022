import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const CreateTravel = (props) => {
    const navigate = useNavigate();
    const travel_List = props.myTravel;
    const user_id = props.user_id;
    const duplicate = false;
    const [Travel_name, setTravel_name] = useState("");

    const onChange = (event) => {
        setTravel_name(event.currentTarget.value);
    }

    const onSubmit = () => {
        if(Travel_name === "") {
            alert("Travel Name is blank!");
        }

        for(let i = 0; i < travel_List.length; i++) {
            if(travel_List[i].name === Travel_name) {
                alert("Travel Name exist");
                return;
            }
        }
        axios.post(`/api/${user_id}/createTravel`,
            {travel_name: Travel_name}
        ).then(() => {
            // navigate(`/${user_id}/${Travel_name}`, {state :{ user_id : user_id, travel:Travel_name }})
            window.location.reload();
        }).catch((error)=> {
            console.log(error);
        })
    }

    return (
        <div>
            <br />
            <input onChange={onChange} 
                value = {Travel_name}
                placeholder = "Travel Name" />
                <button style={{ display: "block", margin: "20px auto" }}
                    onClick = {onSubmit}
                >
                Create Travel
                </button>
        </div>
    )
}

export default CreateTravel