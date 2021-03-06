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
import AccountNavButtonPart from "./UserSigning/AccountNavButtonPart.js";

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
          <NavbarBrand href="/">PollSt✰r</NavbarBrand>
          <NavbarToggler onClick={toggle} />
          <Collapse isOpen={isOpen} navbar>
            <Nav className="mr-auto" navbar>
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Options
                </DropdownToggle>
                <DropdownMenu right>
                  <Link to="/CreatePoll">
                    <DropdownItem>Poll Creation</DropdownItem>
                  </Link>
                  <DropdownItem divider />
                  <Link to="/ViewPoll">
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
            <AccountNavButtonPart/>
          </Collapse>
        </Navbar>

      </div>
    //</Router>
  );
};

export default NavBar;
