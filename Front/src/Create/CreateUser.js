import React from "react";
import { Link, useLocation } from "react-router-dom";

const CreateUser = () => {
  const user = useLocation().state.user;
  const travel = useLocation().state.travel;
  return (
    <div>
      <h1 id="headers">Create User</h1>
      <input placeholder="name" />
      <input placeholder="account" />
      <Link to={`/${user}/${travel}`} state={{ user: user, travel: travel }}>
        <button>Submit</button>
      </Link>
    </div>
  );
};

export default CreateUser;
