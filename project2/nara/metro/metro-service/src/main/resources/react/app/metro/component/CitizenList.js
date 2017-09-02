
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { ButtonToolbar, Button, Table } from 'react-bootstrap';


class CitizenList extends Component {
  //
  static propTypes = {
    citizens: PropTypes.arrayOf(PropTypes.object).isRequired,
    buttons: PropTypes.arrayOf(PropTypes.element),
  };


  render() {
    //
    const {
      citizens,
      buttons,
    } = this.props;

    return (
      <div className="panel mb0 b">

        <div className="panel-heading bg-light-gray">
          <div className="clearfix">
            <div className="pull-right">
              <ButtonToolbar>
                { buttons }
              </ButtonToolbar>
            </div>
          </div>
        </div>

        <div className="panel-body p0 bt">
          <Table striped>
            <colgroup>
              <col width="5%"/>
              <col width="20%"/>
              <col width="25%"/>
              <col width="25%"/>
              <col width="10%"/>
              <col width="15%"/>
            </colgroup>
            <thead>
              <tr>
                <th className="text-center">No</th>
                <th className="text-center">Username</th>
                <th className="text-center">email</th>
                <th className="text-center">phone</th>
                <th className="text-center">guest</th>
                <th className="text-center">Time</th>
              </tr>
            </thead>
            <tbody>
              { citizens.map((citizen, index) => {
                return this.renderViewCitizen(index, citizen);
              })}
            </tbody>
          </Table>
        </div>

      </div>
    );
  }

  renderViewCitizen(index, citizen) {
    //
    const { onSelectCitizen } = this.props;

    return (
      <tr className="text-center clickable" key={index} onClick={() => onSelectCitizen(citizen)}>
        <td>{index + 1}</td>
        <td>{citizen.username}</td>
        <td>{citizen.email}</td>
        <td>{citizen.phone}</td>
        <td>{citizen.guest ? 'Y' : 'N'}</td>
        <td>{citizen.time && new Date(citizen.time).toLocaleDateString()}</td>
      </tr>
    );
  }

}

export default CitizenList;
