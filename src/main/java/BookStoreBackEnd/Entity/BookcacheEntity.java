package BookStoreBackEnd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bookcache", schema = "booktable", catalog = "")
public class BookcacheEntity {
    private int userid;
    private int cacheid;
    private String bookpath;
    private Integer status;
    private String bookname;
    private BigDecimal price;
    private String author;
    private Integer year;
    private Integer bookid;
    private Integer number;

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "cacheid")
    public int getCacheid() {
        return cacheid;
    }

    public void setCacheid(int cacheid) {
        this.cacheid = cacheid;
    }

    @Basic
    @Column(name = "bookpath")
    public String getBookpath() {
        return bookpath;
    }

    public void setBookpath(String bookpath) {
        this.bookpath = bookpath;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Basic
    @Column(name = "bookid")
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookcacheEntity that = (BookcacheEntity) o;

        if (userid != that.userid) return false;
        if (cacheid != that.cacheid) return false;
        if (bookpath != null ? !bookpath.equals(that.bookpath) : that.bookpath != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (bookid != null ? !bookid.equals(that.bookid) : that.bookid != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + cacheid;
        result = 31 * result + (bookpath != null ? bookpath.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (bookid != null ? bookid.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
