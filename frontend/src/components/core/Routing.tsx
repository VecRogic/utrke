import { Route, Routes } from "react-router-dom";

import HomePage from "../screens/Home";
import React from "react";
import ConstructorDetails from "../screens/ConstuctorDetails";
import DriverDetails from "../screens/DriverDetails";

const Routing = () => {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/constuctor/:id" element={<ConstructorDetails />} />
      <Route path="/driver/:id" element={<DriverDetails />} />
    </Routes>
  );
};

export default Routing;
