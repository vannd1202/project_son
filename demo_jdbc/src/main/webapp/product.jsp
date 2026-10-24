<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Sản phẩm</title>
    <style>
        table {
            font-family: Arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
    <center>
        <h1>Danh Sách Sản Phẩm </h1>

        <form action="product" method="get">
            <input type="text" name="search"
                   placeholder="Nhập tên sản phẩm...">
            <button type="submit">Tìm kiếm</button>
        </form>

         <table>
           <tr>
             <th>Tên sản phẩm</th>
             <th>Ảnh</th>
             <th>Giá</th>
             <th>Số lượng</th>
             <th>Thao tác</th>
           </tr>

           <c:forEach var="item" items="${list_product}">
               <tr>
                 <td>${item.name}</td>
                 <td>
                    <img
                        alt="Ảnh sản phẩm"
                        src="${item.image}"
                        style="width: 200px; background-color: transparent;">
                 </td>
                 <td>${item.price}</td>
                 <td>${item.soluong}</td>
                 <td>
                     <a href="javascript:void(0)"
                        onclick="document.getElementById('edit-${item.id}').style.display='table-row'">
                         Sửa
                     </a>
                     |
                     <a href="product?action=delete&id=${item.id}"
                        onclick="return confirm('Chắc chưa?')">
                         Xóa
                     </a>
                 </td>
               </tr>

               <tr id="edit-${item.id}" style="display: none;">
                   <td colspan="5">
                       <form action="product" method="post">
                           <input type="hidden" name="action" value="update">
                           <input type="hidden" name="id" value="${item.id}">

                           <p>
                               Tên:
                               <input type="text" name="name"
                                      value="${item.name}" required>
                           </p>

                           <p>
                               URL ảnh:
                               <input type="text" name="image"
                                      value="${item.image}" required>
                           </p>

                           <p>
                               Giá:
                               <input type="number" name="price"
                                      value="${item.price}" required>
                           </p>

                           <p>
                               Số lượng:
                               <input type="number" name="soluong"
                                      value="${item.soluong}" required>
                           </p>

                           <button type="submit">Lưu thay đổi</button>
                           <button type="button"
                                   onclick="document.getElementById('edit-${item.id}').style.display='none'">
                               Hủy
                           </button>
                       </form>
                   </td>
               </tr>
           </c:forEach>
         </table>
    </center>

</body>
</html>