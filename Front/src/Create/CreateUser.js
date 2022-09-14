import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";

const CreateUser = () => {
  const user = useParams()["user"];
  const travel = useParams()["travel"];
  // const user = useLocation().state.user;
  // const travel = useLocation().state.travel;
  return (
    <div>
      <h2>Add User</h2>
      <label>Email</label>
      <input type="email" />
      <Link to={`/${user}/${travel}`} state={{ user: user, travel: travel }}>
        <button>Submit</button>
      </Link>
    </div>
  );
};

export default CreateUser;
