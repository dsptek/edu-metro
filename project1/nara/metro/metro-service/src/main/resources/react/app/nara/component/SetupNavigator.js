
import React, { Component } from 'react';
import { Grid, Row, Col, Panel, Button, FormControl } from 'react-bootstrap';


class SetupNavigator extends Component {
  //

  render() {
    //
    const { step } = this.props;

    return (
      <div className="steps clearfix">
        <ul>
          <li className={`first ${step === '1' && 'current'}`}>
            <a>
              <span className="current-info audible"/>
              <span className="number">1.</span>Pavilion<br/>
              <small>Pavilion and Metro</small>
            </a>
          </li>
          <li className={step === '2' && 'current'}>
            <a>
              <span className="number">2.</span>Admin<br/>
              <small>Pavilion and Metro admin</small>
            </a>
          </li>
        </ul>
      </div>
    );
  }
}

export default SetupNavigator;
