import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);
    Book book1 = new Book(1, "Evgeny Onegin", 300, "Alex Pushkin");
    Book book2 = new Book(2, "Anna Karenina", 200, "Lev Tolstoy");
    Book book3 = new Book(3, "Ded Mazay", 100, "Nikolay Nekrasov");
    Smartphone smartphone1 = new Smartphone(4, "iPhone5", 1000, "Apple");
    Smartphone smartphone2 = new Smartphone(5, "iPhone8", 2000, "Apple");
    Smartphone smartphone3 = new Smartphone(6, "iPhone12", 5000, "Apple");

    @Test
    public void shouldAddProduct() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] actual = manager.findAll();
        Product[] expected = {book1, book2, book3, smartphone1, smartphone2, smartphone3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByText() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] actual = manager.searchBy("ho");
        Product[] expected = {smartphone1, smartphone2, smartphone3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByText2() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] actual = manager.searchBy("AAA");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByText3() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] actual = manager.searchBy("in");
        Product[] expected = {book1, book2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNullProduct() {

        Product[] actual = manager.findAll();
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRepositoryDeleteByIdZeroArray() {
        manager.removeById(1);

        Product[] actual = manager.findAll();
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    void shouldRepositoryDeleteByNotFoundExceptionId() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(7);
        });
    }

    @Test
    void shouldRepositoryDeleteByNotFoundExceptionId2() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(-1);
        });
    }

    @Test
    void shouldRepositoryDeleteById() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        manager.removeById(1);
        manager.removeById(3);
        manager.removeById(6);

        Product[] actual = manager.findAll();
        Product[] expected = {book2, smartphone1, smartphone2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddExistsProduct() {
        Book book1 = new Book(1, "Evgeny Onegin", 300, "Alex Pushkin");
        Book book2 = new Book(2, "Anna Karenina", 200, "Lev Tolstoy");
        Book book3 = new Book(3, "Ded Mazay", 100, "Nikolay Nekrasov");
        Smartphone smartphone1 = new Smartphone(4, "iPhone5", 1000, "Apple");
        Smartphone smartphone2 = new Smartphone(5, "iPhone8", 2000, "Apple");
        Smartphone smartphone3 = new Smartphone(6, "iPhone12", 5000, "Apple");


        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(book3);
        });
    }


}