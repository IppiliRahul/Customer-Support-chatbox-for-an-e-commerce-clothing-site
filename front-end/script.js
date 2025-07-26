function getTopProducts() {
  fetch('http://localhost:8080/api/top-products')
    .then(response => response.json())
    .then(data => {
      const list = document.getElementById("top-products");
      list.innerHTML = "";
      data.forEach(product => {
        const item = document.createElement("li");
        item.textContent = `${product.productName} - Sold: ${product.sold}`;
        list.appendChild(item);
      });
    });
}

function getOrderStatus() {
  const orderId = document.getElementById("orderIdInput").value;
  fetch(`http://localhost:8080/api/order-status/${orderId}`)
    .then(response => response.text())
    .then(status => {
      document.getElementById("orderStatus").textContent = "Status: " + status;
    });
}

function getStock() {
  const productId = document.getElementById("productIdInput").value;
  fetch(`http://localhost:8080/api/stock/${productId}`)
    .then(response => response.text())
    .then(stock => {
      document.getElementById("stockInfo").textContent = stock;
    });
}