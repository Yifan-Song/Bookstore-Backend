package bookstorebackend.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bookcache", schema = "booktable", catalog = "")
public class BookcacheEntity {
    private int cacheid;
    private String author;
    private Integer bookid;
    private String bookname;
    private String bookpath;
    private Integer number;
    private BigDecimal price;
    private Integer status;
    private Integer userid;
    private Integer year;
    private String ordertime;
    private String paytime;

    @Id
    @Column(name = "cacheid")
    public int getCacheid() {
        return cacheid;
    }

    public void setCacheid(int cacheid) {
        this.cacheid = cacheid;
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
    @Column(name = "bookid")
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
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
    @Column(name = "bookpath")
    public String getBookpath() {
        return bookpath;
    }

    public void setBookpath(String bookpath) {
        this.bookpath = bookpath;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "userid")
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
    @Column(name = "ordertime")
    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    @Basic
    @Column(name = "paytime")
    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookcacheEntity that = (BookcacheEntity) o;

        if (cacheid != that.cacheid) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (bookid != null ? !bookid.equals(that.bookid) : that.bookid != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (bookpath != null ? !bookpath.equals(that.bookpath) : that.bookpath != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (ordertime != null ? !ordertime.equals(that.ordertime) : that.ordertime != null) return false;
        if (paytime != null ? !paytime.equals(that.paytime) : that.paytime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cacheid;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (bookid != null ? bookid.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (bookpath != null ? bookpath.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (ordertime != null ? ordertime.hashCode() : 0);
        result = 31 * result + (paytime != null ? paytime.hashCode() : 0);
        return result;
    }
}
