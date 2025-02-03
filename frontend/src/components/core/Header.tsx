import React from "react";
import { Navbar, Nav, Button, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import "../css/Header.css";

const Header = () => {
  const navigate = useNavigate();

  return (
    <Navbar bg="white" expand="lg" className="header-navbar">
      <Container>
        <Navbar.Brand as={Link} to="/" className="navbar-brand">
          F1
        </Navbar.Brand>
      </Container>
    </Navbar>
  );
};

export default Header;
