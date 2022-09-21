import React from "react";

const Calendar = () => {
  const today = new Date();

  const makeWeek = () => {};

  return (
    <div>
      <h1 className="calendarIcon">today</h1>
      <span>{today.getFullYear()} </span>
      <span>{today.getMonth()} </span>
      <span>{today.getDate()}</span>
    </div>
  );
};

export default Calendar;
