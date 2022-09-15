import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";

const CreateTravel = (props) => {
    const user_id = props.user_id;
    const [Travel_name, setTravel_name] = useState("");

    const onChange = (event) => {
        setTravel_name(event.currentTarget.value);
    }

    const onSubmit = async() => {
        await axios.post(`/api/${user_id}/createTravel`,
            {travel_name: Travel_name}
        ).then((response) => {
            console.log(Travel_name);
            console.log(response.data)
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
            {/* <Link
             to={`/${user_id}/${Travel_name}`}
             state={{ user : user, travel:Travel_name}}> */}
                <button style={{ display: "block", margin: "20px auto" }}
                    onClick = {onSubmit}
                >
                Create Travel
                </button>
            {/* </Link> */}
        </div>
    )
}

export default CreateTravel