
import React, { Component } from 'react';
import { Link } from 'react-router';


class NotFound extends Component {
  //
  render() {
    console.log('Not found');
    return (
      <div className="abs-center wd-xl" style={{ height: 500 }}>
        <div className="text-center mb-xl">
          <div className="text-lg mb-lg">404</div>
          <p className="lead m0">We couldn't find this page.</p>
          <p>The page you are looking for does not exists.</p>
        </div>

        <ul className="list-inline text-center text-sm mb-xl">
          <li><Link to="/" className="text-muted">Go to App</Link></li>
          <li className="text-muted">|</li>
          <li><Link to="login" className="text-muted">Login</Link></li>
          <li className="text-muted">|</li>
          <li><Link to="sign-up" className="text-muted">Sign up</Link></li>
        </ul>
      </div>
    );
  }

}

export default NotFound;
