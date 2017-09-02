
import React, { Component } from 'react';
import { Grid, Row, Col, Panel, Button, FormControl } from 'react-bootstrap';


class PavilionSetup extends Component {
  //
  render() {
    return (
      <NaraWrapper className="content-inner">

        <h3>Setup Pavilion</h3>

        <Panel>
          <form>
            <div className="wizard clearfix">


              <SetupStep1
                header={<SetupNavigator />}
              />

            </div>
          </form>
        </Panel>
      </NaraWrapper>
    );
  }
}
