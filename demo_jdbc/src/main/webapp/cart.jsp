
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <header>
        <a class="brand" href="product">Cửa hàng máy tính</a>

        <nav>
            <a href="product">Tiếp tục mua hàng</a>
            <a href="logout">Đăng xuất</a>
        </nav>
    </header>

    <main>
        <h1>Giỏ hàng</h1>

        <c:if test="${not empty sessionScope.checkoutMessage}">
            <p class="success">${sessionScope.checkoutMessage}</p>

            <c:remove var="checkoutMessage" scope="session"/>
        </c:if>

        <c:choose>
            <c:when test="${empty cartLines}">
                <p>Giỏ hàng đang trống.</p>
            </c:when>

            <c:otherwise>
                <table>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Tạm tính</th>
                        <th></th>
                    </tr>

                    <c:forEach var="line" items="${cartLines}">
                        <tr>
                            <td>${line.product.name}</td>
                            <td>${line.quantity}</td>
                            <td>${line.product.price} đ</td>
                            <td>${line.subtotal} đ</td>

                            <td>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="id" value="${line.product.id}">

                                    <button class="danger" type="submit">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <form action="checkout" method="post" class="checkout">
                    <button type="submit" onclick="return confirm('Xác nhận đặt hàng?')">
                        Thanh toán / Đặt hàng
                    </button>
                </form>
            </c:otherwise>
        </c:choose>
    </main>
</body>
</html>
