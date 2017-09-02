
import React, { Component } from "react";
import PropTypes from 'prop-types';
import autoBind from "react-autobind";
import { ButtonToolbar, Button, Table } from 'react-bootstrap';

import { dialogUtil, NaraWrapper } from 'nara-react';
import { constant } from '../../metro/module/metroList';


class MetroList extends Component {
  //
  static propTypes = {
    metros: PropTypes.array,
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }


  onSaveEditedMetro() {
    //
    const { editingMetro, editingAdmin, saveEditedMetro } = this.props;

    if (!editingMetro.name) {
      dialogUtil.notify('Please enter the metro name.', 'warning');
      return;
    }
    if (!editingAdmin.firstName) {
      dialogUtil.notify('Please enter the admin first name.', 'warning');
      return;
    }
    if (!editingAdmin.familyName) {
      dialogUtil.notify('Please enter the admin family name.', 'warning');
      return;
    }
    if (!editingAdmin.email) {
      dialogUtil.notify('Please enter the admin email.', 'warning');
      return;
    }

    saveEditedMetro(editingMetro, editingAdmin).then((result) => {
      if (constant.SAVE_RESULT.METRO_NAME_EXISTENT === result) {
        dialogUtil.notify(`Already exists metro name ${editingMetro.name}`, 'error');
      }
      else if (constant.SAVE_RESULT.ADMIN_NAME_EXISTENT === result) {
        dialogUtil.notify(`Already exists admin name ${editingAdmin.name}`, 'error');
      }
      else if (constant.SAVE_RESULT.ADMIN_EMAIL_EXISTENT === result) {
        dialogUtil.notify(`Already exists admin email ${editingAdmin.email}`, 'error');
      }
      else {
        dialogUtil.notify('Success to save.', 'success');
      }
    });
  }


  render() {
    //
    const {
      metros, editingMetro,
      onAdd,
    } = this.props;

    return (
      <NaraWrapper className="content-inner">
        <h3>Metros</h3>

        <div className="panel b radius-clear">

          <div className="panel-heading bg-light-gray">
            <div className="clearfix">
              <div className="pull-right">
                <ButtonToolbar>
                  <Button bsSize="small" bsStyle="primary" disabled={editingMetro} onClick={onAdd}>
                    <span>
                      <em className="fa fa-plus mr-sm"/>
                      Add
                    </span>
                  </Button>
                </ButtonToolbar>
              </div>
            </div>
          </div>

          <div className="panel-body p0 bt">
            <Table striped>
              <colgroup>
                <col width="5%"/>
                <col width="15%"/>
                <col width="20%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="5%"/>
              </colgroup>
              <thead>
                <tr>
                  <th className="text-center">No</th>
                  <th className="text-center">Name</th>
                  <th className="text-center">Memo</th>
                  <th className="text-center" colSpan={3}>Primary Admin</th>
                  <th className="text-center">Admins Count</th>
                  <th className="text-center">Time</th>
                  <th className="text-center"/>
                </tr>
              </thead>
              <tbody>
                { editingMetro &&
                  this.renderEditingMetro()
                }
                { metros.map((metro, index) => {
                  return this.renderViewMetro(index, metro);
                })}
              </tbody>
            </Table>
          </div>

        </div>
      </NaraWrapper>
    );
  }

  renderViewMetro(index, metro) {
    //
    const { editingExistent, onEditCitizen, onViewCitizen } = this.props;
    const adminExistent = metro.admins && metro.admins.primaryAdmin;

    return (
      <tr className="text-center" key={index}>
        <td>{index + 1}</td>
        <td>{metro.name}</td>
        <td>{metro.memo}</td>
        <td colSpan={2}>{adminExistent && metro.admins.primaryAdmin.id }</td>
        <td>{adminExistent && metro.admins.primaryAdmin.name }</td>
        <td>{adminExistent && metro.admins.list.length }</td>
        <td>{metro.time && new Date(metro.time).toLocaleDateString()}</td>
      </tr>
    );
  }

  renderEditingMetro(index) {
    //
    const {
      editingMetro, editingAdmin,
      onChangeEditingMetroProp, onChangeEditingAdminProp, cancelEditing,
    } = this.props;

    return (
      <tr className="text-center" key={index ? index : 'add-citizen'}>
        <td>{index && index + 1}</td>
        <td>
          <input type="text" className="form-control" placeholder="Name" autoFocus={true}
                 value={editingMetro.name}
                 onChange={(e) => onChangeEditingMetroProp('name', e.target.value)}
          />
        </td>
        <td>
          <input type="text" className="form-control" placeholder="Memo"
                 value={editingMetro.memo}
                 onChange={(e) => onChangeEditingMetroProp('memo', e.target.value)}
          />
        </td>
        <td>
          <input type="text" className="form-control" placeholder="First name"
                 value={editingAdmin.firstName}
                 onChange={(e) => onChangeEditingAdminProp('firstName', e.target.value)}
          />
        </td>
        <td>
          <input type="text" className="form-control" placeholder="Family name"
                 value={editingAdmin.familyName}
                 onChange={(e) => onChangeEditingAdminProp('familyName', e.target.value)}
          />
        </td>
        <td>
          <input type="text" className="form-control" placeholder="Email"
                 value={editingAdmin.email}
                 onChange={(e) => onChangeEditingAdminProp('email', e.target.value)}
          />
        </td>
        <td>
          <input type="text" className="form-control" disabled={true} />
        </td>
        <td>
          <input type="text" className="form-control" disabled={true} />
        </td>
        <td>
          <a className="clickable mr-lg" onClick={cancelEditing}>
            <em className="fa fa-close text-muted"/>
          </a>
          <a className="clickable" onClick={this.onSaveEditedMetro}>
            <em className="fa fa-check text-muted"/>
          </a>
        </td>
      </tr>
    );
  }
}

export default MetroList;
