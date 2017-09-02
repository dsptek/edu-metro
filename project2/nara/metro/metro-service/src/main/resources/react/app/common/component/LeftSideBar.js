
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router';


class LeftSideBar extends Component {
  //
  static propTypes = {
    //
    location: PropTypes.object.isRequired,
    title: PropTypes.string,
    items: PropTypes.array.isRequired,
  };

  render() {
    //
    const props = this.props;

    return (
      <aside className="aside">
        <nav className="n-sidebar">

          <div className="nav bar-title-area bb bt pl-lg">
            <h4>{props.title}</h4>
          </div>

          <ul className="nav">
            { props.items.map((item, index) =>
              <li key={index} className={`${props.location.pathname.startsWith(item.url) && 'active'}`} >
                <Link to={item.url}>
                  <span>{item.name}</span>
                </Link>
              </li>
            )}
          </ul>
        </nav>
      </aside>
    );
  }
}

export default LeftSideBar;
