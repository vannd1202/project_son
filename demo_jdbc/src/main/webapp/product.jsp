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

        a {
            display: inline-block;
            padding: 5px 10px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <center>
        <h1>Danh Sách Sản Phẩm </h1>

        <form action="product" method="get">
            <input type="text" name="search"
                   placeholder="Nhập tên sản phẩm...">

            <input type="number" name="minPrice"
                   placeholder="Giá thấp nhất" min="0">

            <input type="number" name="maxPrice"
                   placeholder="Giá cao nhất" min="0">

            <select name="sort">
                   <option value="">-- Sắp xếp theo giá --</option>
                   <option value="asc">Giá tăng dần</option>
                   <option value="desc">Giá giảm dần</option>
            </select>

            <button type="submit">Tìm kiếm</button>
        </form>

         <div id="addForm" style="display: none;">
             <h3>Thêm sản phẩm mới</h3>

             <form action="product" method="post">
                 <input type="hidden" name="action" value="add">

                 <p>Tên: <input type="text" name="name" required></p>
                 <p>URL ảnh: <input type="text" name="image" required></p>
                 <p>Giá: <input type="number" name="price" required></p>
                 <p>Số lượng: <input type="number" name="soluong" required></p>

                 <button type="submit">Lưu sản phẩm</button>
                 <button type="button"
                         onclick="document.getElementById('addForm').style.display='none'">
                     Hủy
                 </button>
             </form>
         </div>

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
                     <button type="button"
                             onclick="document.getElementById('addForm').style.display='block'">
                         Thêm
                     </button>

                     <button type="button"
                             onclick="document.getElementById('edit-${item.id}').style.display='table-row'">
                         Sửa
                     </button>

                     <a href="product?action=delete&id=${item.id}"
                        onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?')">
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