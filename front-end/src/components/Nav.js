import React, { useState } from "react";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from "reactstrap";
import CreatePoll from "./Polls/CreatePoll.js";
import ViewPoll from "./Polls/ViewPoll.js";

//import { Navbar, Nav, NavLink } from "react-bootstrap";

import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

const NavBar = (props) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const newTo = { 
    pathname: "/ViewPoll/3"
  };

  return (
    //<Router>
      <div>
        <Navbar color="light" light expand="md">
          <NavbarBrand href="/">reactstrap</NavbarBrand>
          <NavbarToggler onClick={toggle} />
          <Collapse isOpen={isOpen} navbar>
            <Nav className="mr-auto" navbar>
              <NavItem>
                <NavLink href="/components/">Components</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">
                  GitHub
                </NavLink>
              </NavItem>
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Options
                </DropdownToggle>
                <DropdownMenu right>
                  <Link to="/CreatePoll">
                    <DropdownItem>Poll Creation</DropdownItem>
                  </Link>
                  <DropdownItem divider />
                  <Link to={newTo}>
                    <DropdownItem>View Poll</DropdownItem>
                  </Link>
                  <DropdownItem divider />
                  <Link to="/VotePoll">
                    <DropdownItem>Vote Poll</DropdownItem>
                  </Link>
                  <DropdownItem divider />
                  <DropdownItem>Reset</DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
            <NavbarText>Simple Text</NavbarText>
            <Link to="/User/SignIn">
               Login
            </Link>
            <Link to="/User/SignUp">
               Register
            </Link>
          </Collapse>
        </Navbar>

      </div>
    //</Router>
  );
};

export default NavBar;
