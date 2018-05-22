package BookStoreBackEnd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book", schema = "booktable", catalog = "")
public class BookEntity {
    private String bookpath;
    private String bookname;
    private BigDecimal price;
    private String author;
    private Integer year;
    private int bookid;

    @Basic
    @Column(name = "bookpath")
    public String getBookpath() {
        return bookpath;
    }

    public void setBookpath(String bookpath) {
        this.bookpath = bookpath;
    }

    @Basic
    @Column(name = "bookname")
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Id
    @Column(name = "bookid")
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookid != that.bookid) return false;
        if (bookpath != null ? !bookpath.equals(that.bookpath) : that.bookpath != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookpath != null ? bookpath.hashCode() : 0;
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + bookid;
        return result;
    }
}
