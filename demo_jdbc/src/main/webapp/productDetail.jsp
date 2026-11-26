
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

<header>
    <a class="brand" href="product">Cửa hàng máy tính</a>

    <nav>
        <a href="cart">Giỏ hàng</a>
        <a href="logout">Đăng xuất</a>
    </nav>
</header>

<main class="product-detail">

    <!-- Đường dẫn điều hướng -->
    <div class="breadcrumb">
        <a href="product">Trang chủ</a>
        <span>/</span>
        <span>PC GVN</span>
        <span>/</span>
        <span class="current">${product.name}</span>
    </div>

    <!-- Bố cục hai cột -->
    <div class="product-layout">

        <!-- CỘT BÊN TRÁI: HÌNH ẢNH -->
        <section class="product-gallery">

            <div class="product-main-image">

                <c:choose>
                    <c:when test="${not empty product.images}">
                        <img id="mainImage"
                             src="${product.firstImage}"
                             alt="${product.name}">
                    </c:when>

                    <c:otherwise>
                        <div class="no-image">
                            Chưa có hình ảnh
                        </div>
                    </c:otherwise>
                </c:choose>

                <!-- Nút chuyển ảnh -->
                <c:if test="${product.images.size() > 1}">
                    <button type="button"
                            class="gallery-btn prev"
                            onclick="changeImage(-1)">
                        ❮
                    </button>

                    <button type="button"
                            class="gallery-btn next"
                            onclick="changeImage(1)">
                        ❯
                    </button>
                </c:if>

                <!-- Số thứ tự ảnh -->
                <c:if test="${not empty product.images}">
                    <span id="imageCounter">
                        1/${product.images.size()}
                    </span>
                </c:if>

            </div>

            <!-- Danh sách ảnh nhỏ -->
            <div class="product-thumbnails">

                <c:forEach var="img"
                           items="${product.images}"
                           varStatus="status">

                    <img src="${img}"
                         alt="Ảnh sản phẩm ${status.index + 1}"
                         class="${status.first ? 'active' : ''}"
                         onclick="selectImage(${status.index})">

                </c:forEach>

            </div>

        </section>


        <!-- CỘT BÊN PHẢI: THÔNG TIN SẢN PHẨM -->
        <section class="product-info">

            <h1>${product.name}</h1>

            <div class="product-spec-link">
                Thông tin sản phẩm
            </div>

            <div class="product-price">
                ${product.price} đ
            </div>

            <div class="product-stock">
                Số lượng còn:
                <strong>${product.soluong}</strong>
            </div>

            <div class="product-buy">

                <c:if test="${product.soluong > 0}">

                    <form action="cart"
                          method="post"
                          class="product-buy-form">

                        <input type="hidden"
                               name="action"
                               value="add">

                        <input type="hidden"
                               name="id"
                               value="${product.id}">

                        <label for="quantity">Số lượng</label>

                        <input type="number"
                               id="quantity"
                               name="quantity"
                               value="1"
                               min="1"
                               max="${product.soluong}"
                               required>

                        <button type="submit">
                            Thêm vào giỏ hàng
                        </button>

                    </form>

                </c:if>

                <c:if test="${product.soluong == 0}">
                    <p class="error">
                        Sản phẩm đã hết hàng.
                    </p>
                </c:if>

            </div>

        </section>

    </div>

</main>


<!-- JAVASCRIPT CHUYỂN ẢNH -->
<script>

    var currentIndex = 0;

    function selectImage(index) {

        var images = document.querySelectorAll(
            ".product-thumbnails img"
        );

        if (images.length === 0) {
            return;
        }

        currentIndex = index;

        // Thay đổi ảnh chính
        document.getElementById("mainImage").src =
            images[currentIndex].src;

        // Cập nhật số thứ tự ảnh
        document.getElementById("imageCounter").innerHTML =
            (currentIndex + 1) + "/" + images.length;

        // Cập nhật ảnh nhỏ đang được chọn
        images.forEach(function(image) {
            image.classList.remove("active");
        });

        images[currentIndex].classList.add("active");
    }


    function changeImage(direction) {

        var images = document.querySelectorAll(
            ".product-thumbnails img"
        );

        if (images.length <= 1) {
            return;
        }

        currentIndex += direction;

        // Nếu đang ở ảnh cuối thì quay về ảnh đầu
        if (currentIndex >= images.length) {
            currentIndex = 0;
        }

        // Nếu đang ở ảnh đầu thì quay về ảnh cuối
        if (currentIndex < 0) {
            currentIndex = images.length - 1;
        }

        selectImage(currentIndex);
    }

</script>

</body>
</html>
