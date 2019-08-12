package com.example.mall.dao.impl;

import com.example.mall.dao.BookRepository;
import com.example.mall.enty.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author lala
 */
@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "some book");
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch(InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
