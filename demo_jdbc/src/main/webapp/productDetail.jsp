<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><title>Chi tiết sản phẩm</title><link rel="stylesheet" href="style.css"></head><body>
<header><a class="brand" href="product">Cửa hàng máy tính</a><nav><a href="cart">Giỏ hàng</a><a href="logout">Đăng xuất</a></nav></header>
<main><a href="product">← Quay lại danh sách</a><h1>${product.name}</h1>
<div class="detail-images"><c:forEach var="img" items="${product.images}"><img src="${img}" alt="${product.name}"></c:forEach></div>
<h2 class="price">${product.price} đ</h2><p>Số lượng còn: ${product.soluong}</p>
<c:if test="${product.soluong > 0}">
<form action="cart" method="post" class="inline-form"><input type="hidden" name="action" value="add"><input type="hidden" name="id" value="${product.id}">
<label>Số lượng</label><input type="number" name="quantity" value="1" min="1" max="${product.soluong}" required><button type="submit">Thêm vào giỏ hàng</button></form>
</c:if><c:if test="${product.soluong == 0}"><p class="error">Sản phẩm đã hết hàng.</p></c:if>
</main></body></html>