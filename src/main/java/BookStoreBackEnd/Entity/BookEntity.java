package BookStoreBackEnd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book", schema = "booktable", catalog = "")
public class BookEntity {
    private int bookid;
    private String author;
    private String bookname;
    private String bookpath;
    private BigDecimal price;
    private Integer salesVolume;
    private Integer stock;
    private Integer year;
    private String bookAbstract;
    private String authorAbstract;
    private String isbn;
    private String press;

    @Id
    @Column(name = "bookid")
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "sales_volume")
    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Basic
    @Column(name = "stock")
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
    @Column(name = "book_abstract")
    public String getBookAbstract() {
        return bookAbstract;
    }

    public void setBookAbstract(String bookAbstract) {
        this.bookAbstract = bookAbstract;
    }

    @Basic
    @Column(name = "author_abstract")
    public String getAuthorAbstract() {
        return authorAbstract;
    }

    public void setAuthorAbstract(String authorAbstract) {
        this.authorAbstract = authorAbstract;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "press")
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookid != that.bookid) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (bookpath != null ? !bookpath.equals(that.bookpath) : that.bookpath != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (salesVolume != null ? !salesVolume.equals(that.salesVolume) : that.salesVolume != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (bookAbstract != null ? !bookAbstract.equals(that.bookAbstract) : that.bookAbstract != null) return false;
        if (authorAbstract != null ? !authorAbstract.equals(that.authorAbstract) : that.authorAbstract != null)
            return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (press != null ? !press.equals(that.press) : that.press != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookid;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (bookpath != null ? bookpath.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (salesVolume != null ? salesVolume.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (bookAbstract != null ? bookAbstract.hashCode() : 0);
        result = 31 * result + (authorAbstract != null ? authorAbstract.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (press != null ? press.hashCode() : 0);
        return result;
    }
}
