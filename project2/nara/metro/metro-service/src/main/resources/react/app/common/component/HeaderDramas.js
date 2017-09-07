
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router';

import { naraContext } from 'nara-core';


class HeaderDramas extends Component {
  //
  static propTypes = {
    loggedIn: PropTypes.bool,
    dramas: PropTypes.array,
    pathname: PropTypes.string,
  };

  render() {
    //
    const props = this.props;

    /*
    if (!props.loggedIn) {
      return null;
    }
    */

    return (
      <div className="nav-wrapper b">
        <ul className="nav-app">

          { props.dramas.map((drama, index) =>
            <li className={`app-item app-default ${props.pathname.startsWith(drama.url) && 'app-active'}`} key={index}>
              <Link to={drama.url} className={drama.className}>
                <img src={drama.img} alt="App" />
              </Link>
              <span className="app-tooltip">{drama.name}</span>
            </li>
          )}

          { props.dramas.length > 0 &&
            <li className="app-item app-all">
              <a className="app-img thumb24" onClick={props.onToggleLaunchpad}>
                <img src={`${naraContext.path()}/res/img/img-app-all.png`} alt="All drama"/>
              </a>
              <span className="app-tooltip">전체 드라마</span>
            </li>
          }

        </ul>
      </div>
    );
  }
}

export default HeaderDramas;
