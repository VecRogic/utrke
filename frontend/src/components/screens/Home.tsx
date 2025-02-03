import React, { useEffect } from "react";
import { Typography, Button, Container, Box } from "@mui/material";
import { Link } from "react-router-dom";
import { useAppSelector } from "../../store";
import { Col, Row } from "react-bootstrap";
import SeasonPicker from "../shared/SeasonPicker";
import RacePicker from "../shared/RacePicker";
import RaceResults from "../shared/RaceResults";

const HomePage = () => {
  return (
    <Container maxWidth="md" sx={{ textAlign: "center", mt: 4 ,mb: 20}}>
      <Row>
        <Col>
          <SeasonPicker />
        </Col>
        <Col>
          <RacePicker />
        </Col>
        <Row>
          <RaceResults />
        </Row>
      </Row>
    </Container>
  );
};

export default HomePage;
