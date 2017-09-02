
import React, { Component } from 'react';
import { Grid, Row, Col, Panel, Button, FormControl } from 'react-bootstrap';


class SetupButtons extends Component {
  //
  render() {
    //
    const { header } = this.props;

    return (
      <div className="actions clearfix">
        <ul>
          <li className="disabled">
            <a>Prev</a>
          </li>
          <li>
            <a>Next</a>
          </li>
        </ul>
      </div>
    );
  }
}

export default SetupButtons;
