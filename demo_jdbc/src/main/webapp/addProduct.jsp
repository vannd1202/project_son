<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><title>Thêm sản phẩm</title><link rel="stylesheet" href="style.css"></head><body>
<header><a class="brand" href="product">Cửa hàng máy tính</a><nav><a href="product">Danh sách sản phẩm</a><a href="logout">Đăng xuất</a></nav></header>
<main class="form-box"><h1>THÊM SẢN PHẨM</h1>
<form action="product" method="post">
<input type="hidden" name="action" value="add">
<c:if test="${not empty product}"><input type="hidden" name="id" value="${product.id}"></c:if>
<label>Tên sản phẩm</label><input type="text" name="name" value="${product.name}" required>
<label>Giá (VNĐ)</label><input type="number" name="price" min="0" step="1" value="${product.price}" required>
<label>Số lượng</label><input type="number" name="soluong" min="0" value="${product.soluong}" required>
<label>URL hình ảnh 1</label><input type="url" name="images" value="${product.images[0]}">
<label>URL hình ảnh 2</label><input type="url" name="images" value="${product.images[1]}">
<label>URL hình ảnh 3</label><input type="url" name="images" value="${product.images[2]}">
<p class="muted">Có thể để trống những ô URL ảnh không sử dụng. Dùng đường dẫn ảnh trực tiếp.</p>
<button type="submit">Lưu sản phẩm</button> <a class="button secondary" href="product">Hủy</a>
</form></main></body></html>