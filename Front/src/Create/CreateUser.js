import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";

const CreateUser = () => {
  const { travel, travelName, user } = useParams();
  // const user = useLocation().state.user;
  // const travel = useLocation().state.travel;
  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2>Add User</h2>
      <label htmlFor="email">Email</label>
      <input id="email" type="email" />
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <button>Submit</button>
      </Link>
    </div>
  );
};

export default CreateUser;
