import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";

const CreateTravel = (props) => {
    const user = props.user;
    const [Travel_name, setTravel_name] = useState("");

    const onChange = (event) => {
        setTravel_name(event.currentTarget.value);
    }

    const onSubmit = async() => {
        await axios.post("/api/createTravel",{
            Travel_name : Travel_name
        }).then((response) => {
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
            <Link
             to={`/${user}/${Travel_name}`}
             state={{ user : user, travel:Travel_name}}>
                <button style={{ display: "block", margin: "20px auto" }}
                    onChange = {onSubmit}
                >
                Create Travel
                </button>
            </Link>
        </div>
    )
}

export default CreateTravel