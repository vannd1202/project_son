<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><title>Đăng nhập</title><link rel="stylesheet" href="style.css"></head>
<body><div class="login-box"><h1>Đăng nhập</h1>
<p class="muted">Đăng nhập để tiếp tục mua sắm</p>
<% if (request.getAttribute("error") != null) { %><p class="error"><%= request.getAttribute("error") %></p><% } %>
<form action="login" method="post">
<label>Tên đăng nhập</label><input type="text" name="username" required>
<label>Mật khẩu</label><input type="password" name="password" required>
<button type="submit">Đăng nhập</button>
</form></div></body></html>