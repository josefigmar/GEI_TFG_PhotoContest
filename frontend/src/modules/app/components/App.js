import Header from './Header';
import Footer from './Footer';
import {BrowserRouter as Router} from 'react-router-dom';


function App() {
  return (
    <div>
      <Router>
        <div>
          <Header/>
        </div>
        <Footer/>
      </Router>
    </div>
  );
}

export default App;
