
import React, { Component } from 'react';
import { Grid, Row, Col, Panel, Button, FormControl, FormGroup } from 'react-bootstrap';


class SetupStep1 extends Component {
  //
  render() {
    //
    const { header } = this.props;

    return (
      <div className="content cleafix">
        <fieldset className="body current">
          <FormGroup className="mb">
            <label>Metro name *</label>
            <input type="text" className="form-control" disabled={true} value="넥스트리" />
          </FormGroup>

          <FormGroup className="mb">
            <label>Metro memo</label>
            <textarea className="form-control" disabled={true}>넥스트리 메트로입니다</textarea>
          </FormGroup>

          {/*
          <FormGroup className="mb">
              <input type="checkbox" value="check" />
              <span>Same with metro name</span>
          </FormGroup>
           */}

          <FormGroup className="mb">
            <label>Pavilion name *</label>
            <input type="text" className="form-control" />
          </FormGroup>

        </fieldset>
      </div>
    );
  }
}

export default SetupStep1;
