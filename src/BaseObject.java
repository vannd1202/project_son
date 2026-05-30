/*
Bài tập về nhà
Day 8 (1)
- Dùng tính kế thừa, áp dụng class Products2 với BaseObject
- Áp dụng try-catch để code không còn Exception
*/

import java.time.LocalDateTime;

public class BaseObject {
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
