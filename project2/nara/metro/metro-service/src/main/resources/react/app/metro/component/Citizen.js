
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import autoBind from 'react-autobind';
import { ButtonToolbar, Button, Table, Row, Col } from 'react-bootstrap';

import { dialogUtil } from 'nara-react';


class Citizen extends Component {
  //
  static propTypes = {
    citizen: PropTypes.object.isRequired,
    buttons: PropTypes.arrayOf(PropTypes.element).isRequired,
    onClose: PropTypes.func.isRequired,
    onChangeCitizenProp: PropTypes.func,
  };

  static defaultProps = {
    onChangeCitizenProp: () => {}
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }


  onSave() {
    const { citizen } = this.props;

    if (!citizen.name.firstName) {
      dialogUtil.notify('Please enter the first name.', 'warning');
      return;
    }
    if (!citizen.name.familyName) {
      dialogUtil.notify('Please enter the family name.', 'warning');
      return;
    }
    if (!citizen.email) {
      dialogUtil.notify('Please enter the email.', 'warning');
      return;
    }

    this.props.onSave(citizen)
      .then(() => dialogUtil.notify('Success save', 'success'));
  }

  onDisqualify() {
    dialogUtil.confirm('Really disqualify this citizen?', () => {
      this.props.onDisqualify(this.props.citizen);
    });
  }


  render() {
    //
    const { citizen, buttons, onClose, onChangeCitizenProp } = this.props;

    return (
      <div className="panel mb0 b">

        <div className="panel-heading bg-light-gray">
          <div className="clearfix">
            <div className="pull-right">
              <ButtonToolbar>
                { buttons }
                <Button bsStyle="default" className="b0" onClick={onClose}>
                  <em className="fa fa-times text-muted"/>
                </Button>
              </ButtonToolbar>
            </div>
          </div>
        </div>

        <div className="panel-body bt bb">
          <p>
            <strong>First name</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the first name"
                   value={citizen.name.firstName}
                   autoFocus={true}
                   onChange={(e) => onChangeCitizenProp(['name', 'firstName'], e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Middle name</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the middle name"
                   value={citizen.name.middleName}
                   onChange={(e) => onChangeCitizenProp(['name', 'middleName'], e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Family name</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the family name"
                   value={citizen.name.familyName}
                   onChange={(e) => onChangeCitizenProp(['name', 'familyName'], e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Display name</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the display name"
                   value={citizen.name.displayName}
                   onChange={(e) => onChangeCitizenProp(['name', 'displayName'], e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Username</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the username"
                   value={citizen.username}
                   onChange={(e) => onChangeCitizenProp('username', e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Email</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the email"
                   value={citizen.email}
                   onChange={(e) => onChangeCitizenProp('email', e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Phone</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   placeholder="Enter the phone"
                   value={citizen.phone}
                   onChange={(e) => onChangeCitizenProp('phone', e.currentTarget.value)}
            />
          </div>

          <p>
            <strong>Guest</strong>
          </p>
          <div className="form-group">
            <select className="form-control" value={citizen.guest} onChange={(e) => onChangeCitizenProp('guest', e.target.value === 'true')}>
              <option value="true">Y</option>
              <option value="false">N</option>
            </select>
          </div>

          <p>
            <strong>Time</strong>
          </p>
          <div className="form-group">
            <input type="text"
                   className="form-control"
                   value={citizen.time && new Date(citizen.time).toLocaleString()}
                   readOnly={true}
            />
          </div>

        </div>

      </div>
    );
  }
}


export default Citizen;
