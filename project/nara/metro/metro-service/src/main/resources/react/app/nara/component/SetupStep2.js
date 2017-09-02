
import React, { Component } from 'react';
import { Grid, Row, Col, Panel, Button, FormControl, FormGroup } from 'react-bootstrap';


class SetupStep2 extends Component {
  //
  render() {
    //
    const { header } = this.props;

    return (
      <div className="content cleafix">
        <fieldset className="body current">

          <FormGroup >
            <label>Email *</label>
            <input type="text" className="form-control" />
          </FormGroup>

          <FormGroup >
            <label>First name *</label>
            <input type="text" className="form-control" />
          </FormGroup>

          <FormGroup >
            <label>Family name *</label>
            <input type="text" className="form-control" />
          </FormGroup>

          <FormGroup>
            <label>Password *</label>
            <input type="password" className="form-control" />
          </FormGroup>

          <FormGroup>
            <label>Check password *</label>
            <input type="password" className="form-control" />
          </FormGroup>

        </fieldset>
      </div>
    );
  }
}

export default SetupStep2;
