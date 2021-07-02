import Header from './Header';
import Footer from './Footer';
import {BrowserRouter as Router} from 'react-router-dom';
import Body from './Body';
import React from 'react';



function App() {



  return (
    <div>
      <Router>
        <div>
          <Header/>
          <Body/>
        </div>
        <Footer/>
      </Router>
    </div>
  );
}

export default App;
