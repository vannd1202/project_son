
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <header>
        <a class="brand" href="product">Cửa hàng máy tính</a>

        <nav>
            <span>
                Xin chào, <b>${sessionScope.username}</b> (${sessionScope.role})
            </span>

            <a href="cart">Giỏ hàng</a>
            <a href="logout">Đăng xuất</a>
        </nav>
    </header>

    <main>
        <h1>Danh sách sản phẩm</h1>

        <form class="filter" action="product" method="get">
            <input
                name="search"
                value="${param.search}"
                placeholder="Tìm theo tên sản phẩm"
            >

            <input
                type="number"
                name="minPrice"
                min="0"
                step="1"
                value="${param.minPrice}"
                placeholder="Giá từ"
            >

            <input
                type="number"
                name="maxPrice"
                min="0"
                step="1"
                value="${param.maxPrice}"
                placeholder="Đến giá"
            >

            <select name="sort">
                <option value="">Sắp xếp theo giá</option>
                <option value="asc" ${param.sort == 'asc' ? 'selected' : ''}>
                    Giá tăng dần
                </option>
                <option value="desc" ${param.sort == 'desc' ? 'selected' : ''}>
                    Giá giảm dần
                </option>
            </select>

            <button type="submit">Lọc sản phẩm</button>
            <a class="button secondary" href="product">Đặt lại</a>
        </form>

        <c:if test="${sessionScope.role == 'admin'}">
            <p>
                <a class="button" href="product?action=add">+ Thêm sản phẩm</a>
            </p>
        </c:if>

        <div class="products">
            <c:forEach var="p" items="${products}">
                <div class="product-card">

                    <a href="product?action=detail&id=${p.id}">
                        <c:choose>
                            <c:when test="${not empty p.firstImage}">
                                <img src="${p.firstImage}" alt="${p.name}">
                            </c:when>

                            <c:otherwise>
                                <div class="no-image">Chưa có ảnh</div>
                            </c:otherwise>
                        </c:choose>

                        <h3>${p.name}</h3>
                    </a>

                    <p class="price">
                        <c:out value="${p.price}"/> đ
                    </p>

                    <p>Còn lại: ${p.soluong}</p>

                    <a class="button" href="product?action=detail&id=${p.id}">
                        Xem chi tiết
                    </a>

                    <c:if test="${sessionScope.role == 'admin'}">
                        <a class="button secondary" href="product?action=edit&id=${p.id}">
                            Sửa
                        </a>

                        <a
                            class="button danger"
                            href="product?action=delete&id=${p.id}"
                            onclick="return confirm('Bạn chắc chắn muốn xóa?')"
                        >
                            Xóa
                        </a>
                    </c:if>

                </div>
            </c:forEach>
        </div>

        <c:if test="${empty products}">
            <p>Không tìm thấy sản phẩm phù hợp.</p>
        </c:if>
    </main>
</body>
</html>
