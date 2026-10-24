<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>
    <h1>Chào mừng bạn đến với dự án Servlet!</h1>
    <p>Trạng thái DB: ${status}</p>

    <div class="container" id="main">
        <form action="/home" method="post">
            <label for="">họ và tên</label>
            <input type="text" name="name" value="${name}">
            <br>
            <label for="">tuổi</label>
            <input type="text" name="age" value="${age}">
            <button type="submit">Cập nhật</button>

        </form>
    </div>
</body>
</html>