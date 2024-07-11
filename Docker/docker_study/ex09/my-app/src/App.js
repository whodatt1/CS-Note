import { useEffect, useState } from 'react';
import './App.css';
import Card from './components/Card';

function App() {

  const [products, setProducts] = useState([]);

  async function onLoad() {
    let response = await fetch("/api/products");
    let responseBody = await response.json();
    console.log("onLoad", responseBody);

    setProducts(responseBody);
  }

  // 해당 페이지가 열릴 때 한번 실행. (Init 메서드)
  // 두번째 인수는 어떤 값이 변경될때 다시 그림을 그리는지.. 빈배열(어떤 값이든)
  useEffect(() => {
    onLoad();
  }, []);

  return (
    <div>
      <h1>상품목록페이지</h1>
      <hr/>
      <div>
        {products.map((product) => <Card product={product}/>)}
      </div>
    </div>
  );
}

export default App;
